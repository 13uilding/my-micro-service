package com.revature.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloTest {

    @GetMapping
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("hello world");
    }

}
