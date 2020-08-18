package com.bitree.server.controller;

import com.bitree.server.model.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("login")
    public ResponseBean login() {
        return ResponseBean.error("Please login!");
    }
}
