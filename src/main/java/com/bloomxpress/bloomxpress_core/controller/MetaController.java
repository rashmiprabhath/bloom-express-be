package com.bloomxpress.bloomxpress_core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meta")
public class MetaController {

    @GetMapping("/name")
    public String getName() {
        return "BloomXpress";
    }
}
