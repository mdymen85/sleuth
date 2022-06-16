package com.spring.sleuthserver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final Tracer tracer;

    @RequestMapping(value = "/v1/server", method = RequestMethod.POST)
    public ResponseEntity<TestObject> process(@RequestBody TestObject testObject) {

        log.info("Currente traceId = {}", tracer.currentTraceContext().context().traceId());

        log.info("Processing testObject from client {}", testObject);

        testObject.setName(testObject.getName() + " from server ");

        log.info("Sending testObject {}", testObject);

        return ResponseEntity.ok(testObject);
    }
}
