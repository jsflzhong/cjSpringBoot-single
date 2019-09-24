package com.michael.springBoot.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/endPoint")
    public Object testEndpoint(String id) throws UnknownHostException {
        logger.info("@@@endPoint-01 getting the request, id:{}", id);
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        String hostName = address.getHostName();
        return "@@@This is endPoint-1, hostAddress:" + hostAddress + ", hostName:" + hostAddress;
    }
}
