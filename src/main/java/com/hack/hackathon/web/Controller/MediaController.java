package com.hack.hackathon.web.Controller;

import com.hack.hackathon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/image-api")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping
    public ResponseEntity<Object> saveFile(@RequestParam("extension") String extension) {
        return new ResponseEntity<>(mediaService.save(extension), HttpStatus.OK);
    }
}
