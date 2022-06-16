package com.spring.sleuthclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "sleuthclient",
        url = "http://localhost:8081",
        configuration = FeignConfiguration.class)
public interface ITestSleuthClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/server")
    TestObject process(TestObject testObject);

}
