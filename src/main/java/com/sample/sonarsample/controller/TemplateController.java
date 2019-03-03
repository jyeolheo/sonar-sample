package com.sample.sonarsample.controller;

import com.sample.sonarsample.model.Sonar;
import com.sample.sonarsample.model.SonarRequest;
import com.sample.sonarsample.model.message.ResultBody;
import com.sample.sonarsample.service.SonarService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TemplateController {

    final Integer SUCCESS_CODE = 0;
    final String SUCCESS_MESSAGE = "OK";

    @Autowired
    SonarService sonarService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody SonarRequest request) {
        val sonar = Sonar.builder().name(request.getName()).value(request.getValue()).build();
        val created = sonarService.set(sonar);
        return null;
    }
    //TEST for CRUD
    @GetMapping(value = "/getAll")
    public ResultBody getAll(){
        val result = sonarService.getAll();
        return new ResultBody(SUCCESS_CODE, SUCCESS_MESSAGE, result);
    }

    @GetMapping(value = "/get/{name}")
    public ResultBody get(@PathVariable String name){
        val result = sonarService.getByName(name);
        return new ResultBody(SUCCESS_CODE, SUCCESS_MESSAGE, result);
    }

    @PostMapping(value = "/set", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultBody set(@RequestBody SonarRequest request){
        val sonar = Sonar.builder().name(request.getName()).value(request.getValue()).build();
        val result = sonarService.set(sonar);
        return new ResultBody(SUCCESS_CODE, SUCCESS_MESSAGE, result);
    }

}
