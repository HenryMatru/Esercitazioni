package it.academy.corso.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyHelloController {

    @GetMapping("/myhi")
    public ResponseEntity<String> myHi() {
        return new ResponseEntity<>("Enrico", HttpStatus.OK);
    }

    @GetMapping("/myhimap")
    public ResponseEntity<Map<String, String>> myHiMap(){
        Map<String, String> provv = new HashMap<String, String>();
        provv.put("name", "Enrico");
        return new ResponseEntity<>(provv, HttpStatus.OK);
    }

    @GetMapping("/myhi/{name}")
    public ResponseEntity<String> myHi(@PathVariable String name) {
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

}
