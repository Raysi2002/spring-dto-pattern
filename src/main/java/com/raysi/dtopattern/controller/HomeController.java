package com.raysi.dtopattern.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController {
    @GetMapping("/")
    public String home(){
        return """
                <title>DTO Pattern</title>
                <body>
                    <h1 style="text-align: center" >This is for DTO pattern understanding</h1>
                </body>
                """;
    }
}
