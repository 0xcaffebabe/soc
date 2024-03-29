package wang.ismy.soc.qa.controller;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import wang.ismy.soc.qa.client.BaseClient;
import wang.ismy.soc.qa.pojo.Problem;
import wang.ismy.soc.qa.service.ProblemService;

/**
 * 问题控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
@AllArgsConstructor
public class ProblemController {

	private ProblemService problemService;
	private BaseClient baseClient;

	@GetMapping("error")
	public Result error(){
		return baseClient.findById("1");
	}

	@GetMapping("newlist/{label}/{page}/{size}")
	public Result newList(@PathVariable String label,@PathVariable Integer page,@PathVariable Integer size){
		return new Result(true,StatusCode.OK,"查询成功",problemService.newList(label,page,size));
	}

	@GetMapping("hotlist/{label}/{page}/{size}")
	public Result hotList(@PathVariable String label,@PathVariable Integer page,@PathVariable Integer size){
		return new Result(true,StatusCode.OK,"查询成功",problemService.hotList(label,page,size));
	}

	@GetMapping("waitlist/{label}/{page}/{size}")
	public Result waitList(@PathVariable String label,@PathVariable Integer page,@PathVariable Integer size){
		return new Result(true,StatusCode.OK,"查询成功",problemService.waitList(label,page,size));
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
