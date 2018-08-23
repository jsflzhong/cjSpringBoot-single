package com.michael.springBoot.controller.cache;

import com.michael.springBoot.cache.CjCaffeineCache;
import com.michael.springBoot.cache.DataValue;
import com.michael.springBoot.service.DataValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jsflz on 2018/8/22.
 * @author cj
 */
@Controller
@RequestMapping("/caffeineCache")
public class CaffeineCacheController {

    @Autowired
    CjCaffeineCache cjCaffeineCache;
    @Autowired
    private DataValueService dataValueService;

    @RequestMapping("/getByKey")
    @ResponseBody
    public String getByKey(String key) {
        return cjCaffeineCache.getByKey(key).getData();
    }

    @RequestMapping("/put")
    @ResponseBody
    public Object put(String key) {
        cjCaffeineCache.put(key,new DataValue("test1"));
        return "success";
    }

    @RequestMapping("/getByFunction")
    @ResponseBody
    public Object getByFunction(String key) {
        DataValue dataValue = cjCaffeineCache.getByFunction(key,
                k -> dataValueService.getDataValueById_test(1));
        return dataValue.getData();
    }

    @RequestMapping("/removeByKey")
    @ResponseBody
    public Object removeByKey(String key) {
        cjCaffeineCache.removeByKey(key);
        return "success";
    }

    @RequestMapping("/getCacheSize")
    @ResponseBody
    public Object getCacheSize() {
        return cjCaffeineCache.getCacheSize();
    }


}
