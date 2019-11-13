package wang.ismy.soc.search.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wang.ismy.soc.search.pojo.ArticleIndex;
import wang.ismy.soc.search.service.ArticleService;

/**
 * @author MY
 * @date 2019/11/12 22:42
 */
@RestController
@RequestMapping("article")
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @PostMapping
    public Result save(@RequestBody ArticleIndex article){
        articleService.save(article);
        return  Result.success("添加成功");
    }

    @GetMapping("/{key}/{page}/{size}")
    public Result find(@PathVariable String key,@PathVariable Integer page,@PathVariable Integer size){
        PageResult<ArticleIndex> articles = articleService.find(key,page,size);
        return new Result(true, StatusCode.OK,"查询成功",articles);
    }
}
