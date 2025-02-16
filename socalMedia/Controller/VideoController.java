package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Model.Video;
import com.socalMedia.socalMedia.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/video/upload")
    public ResponseEntity<?> videoUpload(@RequestParam("video") MultipartFile video,@RequestParam String senderEmail,@RequestParam String senderUsername, String bio) {
        try {
            Map<String, Object> result = videoService.upload(video,senderEmail,senderUsername,bio);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading video: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @GetMapping("/get/reels")
    public List<Video> reels()
    {
        return videoService.getReels();
    }
}