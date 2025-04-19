package com.scaler.productserviceaprilmwf.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("hello")
public class HelloController {

        @GetMapping("/say/{name}/{times}")
        public String sayHello(@PathVariable("name") String requesterName, @PathVariable("times") int numberOfTimes) {

            String answer = "";
            for (int i = 0; i < numberOfTimes; i++) {

                answer += "Hello " + requesterName;
                answer += "<br>";
            }

            return answer;
        }
}

