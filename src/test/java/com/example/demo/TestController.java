package com.example.demo;

import io.micrometer.tracing.Tracer;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private Tracer tracer;

    @GetMapping("trace-id")
    public String traceId() throws InterruptedException {
        String tracerTraceID = tracer.currentSpan().context().traceId();
        String mdcTraceID = MDC.get("traceId");
        Thread.sleep(2000L);
        return tracerTraceID + "-" + mdcTraceID;
    }

}
