package com.itproject.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentRestController
{
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest()
    {
        //call from the web browser http://localhost:8080/students/test
        return "Hello test";
    }
}