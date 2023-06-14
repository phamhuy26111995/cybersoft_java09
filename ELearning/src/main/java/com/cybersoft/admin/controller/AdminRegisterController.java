package com.cybersoft.admin.controller;

import com.cybersoft.consts.Consts;
import com.cybersoft.dto.UserDto;
import com.cybersoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/register")
public class AdminRegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public Object post(@RequestBody UserDto dto) {
        try {
            userService.registerAdminUser(dto);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}
