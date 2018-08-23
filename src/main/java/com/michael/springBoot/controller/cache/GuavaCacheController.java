package com.michael.springBoot.controller.cache;

import com.google.common.base.Strings;
import com.michael.springBoot.cache.CjCaffeineCache;
import com.michael.springBoot.cache.CjGuavaCache;
import com.michael.springBoot.cache.DataValue;
import com.michael.springBoot.service.DataValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jsflz on 2018/8/22.
 * Tested for guava cache.
 *
 * @author cj
 */
@Controller
@RequestMapping("/guavaCache")
public class GuavaCacheController {

    @Autowired
    CjGuavaCache cjGuavaCache;
    @Autowired
    private DataValueService dataValueService;

    @RequestMapping("/getByKey")
    @ResponseBody
    public String getByKey(String key) {
        String value = cjGuavaCache.getByKey(key);
        //if(Strings.isNullOrEmpty(value)) //Get value from db...
        return value;
    }

    @RequestMapping("/put")
    @ResponseBody
    public Object put(String key,String value) {
        cjGuavaCache.put(key,value);
        return "success";
    }

    @RequestMapping("/getByFunction")
    @ResponseBody
    public Object getByFunction(String key) {
        String value = cjGuavaCache.getByCallable(key,
                () -> dataValueService.getStringByKey_test(key));
        return value;
    }

    @RequestMapping("/removeByKey")
    @ResponseBody
    public Object removeByKey(String key) {
        cjGuavaCache.removeByKey(key);
        return "success";
    }

    @RequestMapping("/getCacheSize")
    @ResponseBody
    public Object getCacheSize() {
        return cjGuavaCache.getCacheSize();
    }


}
