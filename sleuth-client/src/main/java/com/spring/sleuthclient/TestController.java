package com.spring.sleuthclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final ITestSleuthClient iTestSleuthClient;

    @RequestMapping(value = "/v1/client", method = RequestMethod.POST)
    public ResponseEntity<TestObject> process(@RequestBody TestObject testObject) {

        log.info("In client processing testObject {}", testObject);

        var response = iTestSleuthClient.process(testObject);

        return ResponseEntity.ok(response);
    }
}
