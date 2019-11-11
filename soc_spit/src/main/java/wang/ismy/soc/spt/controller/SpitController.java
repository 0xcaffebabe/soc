package wang.ismy.soc.spt.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.AllArgsConstructor;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.web.bind.annotation.*;
import wang.ismy.soc.spt.pojo.Spit;
import wang.ismy.soc.spt.service.SpitService;

/**
 * @author MY
 * @date 2019/11/11 21:19
 */
@RestController
@CrossOrigin
@RequestMapping("spit")
@AllArgsConstructor
public class SpitController {

    private SpitService spitService;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @GetMapping("{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"添加成功",null);
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Spit spit,@PathVariable String id){
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"更新成功",null);
    }

    @DeleteMapping("/{id}")
    public Result update(@PathVariable String id){
        spitService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功",null);
    }

    @GetMapping("comment/{pid}/{page}/{size}")
    public Result findByParentId(@PathVariable String pid, @PathVariable Integer page, @PathVariable Integer size){

        return new Result(true,StatusCode.OK,"查询成功",spitService.findByParentid(pid, page, size));
    }

    @PutMapping("thumbup/{sid}")
    public Result thumbup(@PathVariable String sid){
        return spitService.thumbup(sid);

    }

}
