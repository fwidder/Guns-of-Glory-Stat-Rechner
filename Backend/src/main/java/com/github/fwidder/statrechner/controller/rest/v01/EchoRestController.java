package com.github.fwidder.statrechner.controller.rest.v01;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("rest/v01/echo")
public class EchoRestController {
    @GetMapping(value = "/{text}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getEcho(@PathVariable String text) {
        return ResponseEntity.ok(Collections.singletonList(text));
    }
}
