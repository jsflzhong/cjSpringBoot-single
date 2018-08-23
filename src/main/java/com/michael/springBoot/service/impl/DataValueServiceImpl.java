package com.michael.springBoot.service.impl;

import com.michael.springBoot.cache.DataValue;
import com.michael.springBoot.service.DataValueService;
import org.springframework.stereotype.Service;

/**
 * Created by jsflz on 2018/8/22.
 */
@Service
public class DataValueServiceImpl implements DataValueService {

    @Override
    public DataValue getDataValueById_test(Integer id) {
        return new DataValue("testFunction");
    }

    @Override
    public String getStringByKey_test(String key) {
        return "test1";
    }
}
