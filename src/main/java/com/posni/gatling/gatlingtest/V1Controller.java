package com.posni.gatling.gatlingtest;

import com.posni.gatling.gatlingtest.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class V1Controller {
    @GetMapping("/greeting")
    public String sayHello() {
        System.out.println("Saying hello at " + Calendar.getInstance().getTime());
        return "hello....";
    }


    @PostMapping("/student")
    public Student takeData(@RequestBody Student student) {
        System.out.print(student.toString());
        System.out.println("Posting student at " + Calendar.getInstance().getTime());
        return student;
    }
}
