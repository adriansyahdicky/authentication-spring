package com.project.authentication.controller;

import com.project.authentication.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping(value = "/api/job/list")
    public ResponseEntity<Object>
    getJobList(){
        return ResponseEntity.ok(jobService.getJobList());
    }

    @GetMapping(value = "/api/job/{id}")
    public ResponseEntity<Object>
    getJobById(@PathVariable("id") String id){
        return ResponseEntity.ok(jobService.getJobById(id));
    }

}
