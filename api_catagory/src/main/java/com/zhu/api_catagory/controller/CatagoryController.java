package com.zhu.api_catagory.controller;

import com.zhu.api_catagory.entity.Catagory;
import com.zhu.api_catagory.service.CatagoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * 服务控制器
 *
 * @author zhu
 * @since 2022-04-27 16:53:21
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/catagory")
public class CatagoryController implements IController {
    private final CatagoryService catagoryService;

    @GetMapping("/listAll")
    public R listAll(){
        try {
            return success(catagoryService.list());
        } catch (Exception e){
            e.printStackTrace();
            return fail(e.getMessage());
        }

    }

    @DeleteMapping("/deleteById/{catagoryId}")
    public R deleteById(@PathVariable("catagoryId") Integer catagoryId){
        return catagoryService.removeById(catagoryId) ? success() : fail("删除失败");
    }

    @PutMapping("/insertOrUpdate")
    public R insertOrUpdate(Catagory catagory){
        return catagoryService.saveOrUpdate(catagory) ? success() : fail("操作失败");
    }
}
