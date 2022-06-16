package com.spring.sleuthserver;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    private final QueueSenderService queueSenderService;

    @RequestMapping(value = "/v1/server", method = RequestMethod.POST)
    public ResponseEntity<TestObject> process(@RequestBody TestObject testObject) throws JsonProcessingException {

        log.info("Currente traceId = {}", tracer.currentTraceContext().context().traceId());

        log.info("Processing testObject from client {}", testObject);

        testObject.setName(testObject.getName() + " from server ");

        log.info("Sending testObject {}", testObject);

        queueSenderService.send(testObject);

        return ResponseEntity.ok(testObject);
    }
}
