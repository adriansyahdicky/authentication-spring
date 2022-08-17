package com.project.authentication.controller;

import com.project.authentication.dto.RequestCreateUser;
import com.project.authentication.dto.RequestLoginDto;
import com.project.authentication.dto.ResponseLoginDto;
import com.project.authentication.entity.DataUser;
import com.project.authentication.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private UserCrudService userCrudService;

    @PostMapping(value = "/api/auth/create")
    public ResponseEntity<DataUser> create(@RequestBody RequestCreateUser requestCreateUser){
        return ResponseEntity.ok(userCrudService.save(requestCreateUser));
    }

    @PostMapping(value = "/api/auth/login")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody RequestLoginDto requestLoginDto) throws Exception {
        return ResponseEntity.ok(userCrudService.login(requestLoginDto));
    }

}
