package wang.ismy.soc.recruit.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wang.ismy.soc.recruit.pojo.Config;
import wang.ismy.soc.recruit.service.ConfigService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * config控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/config")
public class ConfigController {

	@Autowired
	private ConfigService configService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",configService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param variable ID
	 * @return
	 */
	@RequestMapping(value="/{variable}",method= RequestMethod.GET)
	public Result findById(@PathVariable String variable){
		return new Result(true,StatusCode.OK,"查询成功",configService.findById(variable));
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
		Page<Config> pageList = configService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Config>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",configService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param config
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Config config  ){
		configService.add(config);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param config
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Config config, @PathVariable String variable ){
		config.setVariable(variable);
		configService.update(config);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param variable
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String variable){
		configService.deleteById(variable);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
