package com.example.blueeagle.Controller;

// GroupController

import com.example.blueeagle.Service.TripDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groupLink")
public class GroupController {

    @Autowired
    private TripDetailsService tripDetailsService;

    @PostMapping("/join")
    public ResponseEntity<String> joinGroup(@RequestParam("token") String token) {

        return ResponseEntity.ok("Joined the group successfully!");
    }
}
