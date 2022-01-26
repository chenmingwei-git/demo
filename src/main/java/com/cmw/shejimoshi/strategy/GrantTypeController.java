package com.cmw.shejimoshi.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 策略模式入口
 * @author: cmw
 * @date: 2022/1/26
 */
@RestController
public class GrantTypeController {
    @Autowired
    private QueryGrantTypeService queryGrantTypeService;

    @PostMapping("/grantType")
    public String test(String resourceName){
        return queryGrantTypeService.getResult(resourceName);
    }

}
