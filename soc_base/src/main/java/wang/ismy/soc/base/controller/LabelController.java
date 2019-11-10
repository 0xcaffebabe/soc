package wang.ismy.soc.base.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wang.ismy.soc.base.pojo.Label;
import wang.ismy.soc.base.service.LabelService;

/**
 * @author MY
 * @date 2019/11/9 16:59
 */
@RestController
@CrossOrigin
@RequestMapping("label")
@AllArgsConstructor
public class LabelController {

    private LabelService labelService;

    @GetMapping
    public Result findList(){
        int a=1/0;
        return new Result(true, StatusCode.OK.getCode(),"查询成功",labelService.findList());
    }

    @GetMapping("{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK.getCode(),"查询成功",labelService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK.getCode(),"保存成功",null);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable String id,@RequestBody Label label){
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK.getCode(),"更新成功",null);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        labelService.delete(id);
        return new Result(true, StatusCode.OK.getCode(),"删除成功",null);
    }

    @PostMapping("search")
    public Result search(@RequestBody Label condition){
        return new Result(true,StatusCode.OK.getCode(),"查询成功",labelService.search(condition));
    }

    @PostMapping("search/{page}/{size}")
    public Result searchByPage(@RequestBody Label condition, @PathVariable Integer page, @PathVariable Integer size){
        return new Result(true,StatusCode.OK.getCode(),"查询成功",labelService.search(condition,page,size));
    }


}
