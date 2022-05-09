package com.example.conferenceapp.conference;

import com.example.conferenceapp.ConferenceAppApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.conferenceapp.Constants.CONFERENCE_ID;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    @GetMapping
    public ResponseEntity<Conference> getConferencePlan(){
        final Conference conference = ConferenceAppApplication.conferences.get(CONFERENCE_ID);
        return ResponseEntity.ok(conference);
    }
}
