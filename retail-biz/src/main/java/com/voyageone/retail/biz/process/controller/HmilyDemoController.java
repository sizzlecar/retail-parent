package com.voyageone.retail.biz.process.controller;


import com.voyageone.retail.biz.process.service.HmilyDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retail-biz/hmily/")
@Slf4j
public class HmilyDemoController {

    private final HmilyDemoService hmilyDemoService;

    public HmilyDemoController(HmilyDemoService hmilyDemoService) {
        this.hmilyDemoService = hmilyDemoService;
    }

    /**
     * 查询任务历史
     */
    @GetMapping("insert")
    public String createRole(@RequestParam("name") String name){
        return hmilyDemoService.createRole(name);
    }
}
