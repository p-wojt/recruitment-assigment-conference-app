package com.example.conferenceapp.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}/email}")
    public ResponseEntity<?> changeEmail(@PathVariable final long id, @RequestBody final String email){
        this.userService.changeEmail(id, email);
        return ResponseEntity.noContent().build();
    }
}
