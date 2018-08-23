package com.michael.springBoot.service;

import com.michael.springBoot.cache.DataValue;

/**
 * Created by jsflz on 2018/8/22.
 */
public interface DataValueService {

    DataValue getDataValueById_test(Integer id);

    String getStringByKey_test(String key);
}
