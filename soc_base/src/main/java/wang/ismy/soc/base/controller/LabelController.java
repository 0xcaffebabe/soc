package wang.ismy.soc.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;
import wang.ismy.soc.base.pojo.Label;

/**
 * @author MY
 * @date 2019/11/9 16:59
 */
@RestController
@CrossOrigin
@RequestMapping("label")
public class LabelController {

    @GetMapping
    public Result findList(){
        return new Result(true, StatusCode.OK.getCode(),"查询成功",new Object());
    }

    @GetMapping("{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK.getCode(),"查询成功",new Object());
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        return new Result(true, StatusCode.OK.getCode(),"保存成功",new Object());
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable String id,@RequestBody Label label){
        return new Result(true, StatusCode.OK.getCode(),"更新成功",new Object());
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        return new Result(true, StatusCode.OK.getCode(),"删除成功",new Object());
    }


}
