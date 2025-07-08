package com.example.complaintplatform_backend.controller;

import com.example.complaintplatform_backend.dto.Result;
import com.example.complaintplatform_backend.entity.Example;
import com.example.complaintplatform_backend.service.ExampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 样例控制器
 */
@RestController
@RequestMapping("/api/examples")
@Api(tags = "样例管理")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/search")
    @ApiOperation("根据名称搜索样例")
    public Result<List<Example>> searchExamples(@RequestParam String name) {
        List<Example> examples = exampleService.findByNameContaining(name);
        return Result.success(examples);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID获取样例")
    public Result<Example> getExampleById(@PathVariable Long id) {
        Optional<Example> example = exampleService.findById(id);
        if (example.isPresent()) {
            return Result.success(example.get());
        }
        return Result.error("未找到对应样例");
    }

    @PostMapping
    @ApiOperation("创建新样例")
    public Result<Integer> createExample(@RequestBody Example example) {
        int result = exampleService.insert(example);
        return result > 0 ? Result.success(result) : Result.error("创建失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("更新样例")
    public Result<Integer> updateExample(@PathVariable Long id, @RequestBody Example example) {
        example.setId(id);
        int result = exampleService.update(example);
        return result > 0 ? Result.success(result) : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除样例")
    public Result<Integer> deleteExample(@PathVariable Long id) {
        int result = exampleService.deleteById(id);
        return result > 0 ? Result.success(result) : Result.error("删除失败");
    }

    @GetMapping("/count")
    @ApiOperation("统计名称数量")
    public Result<Long> countByName(@RequestParam String name) {
        long count = exampleService.countByName(name);
        return Result.success(count);
    }
}
