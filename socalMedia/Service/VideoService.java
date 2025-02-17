package com.socalMedia.socalMedia.Service;


import com.cloudinary.Cloudinary;
import com.socalMedia.socalMedia.Model.Video;
import com.socalMedia.socalMedia.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Map<String, Object> upload(MultipartFile video,String senderEmail,String senderUsername,String bio) {
        try {
//            String originalFilename = video.getOriginalFilename();
            byte[] bytes = video.getBytes();
            Map<String, Object> uploadResult = cloudinary.uploader().upload(bytes, Map.of("resource_type", "video"));

            String videoUrl = (String) uploadResult.get("url");

            Video v = Video.builder()
                    .videoUrl(videoUrl)
                    .senderEmail(senderEmail)
                    .senderUsername(senderUsername)
                    .bio(bio)
                    .build();
            videoRepository.save(v);

            return uploadResult;

        } catch (IOException e) {
            throw new RuntimeException("Video could not be uploaded", e);
        }
    }

    public List<Video> getReels()
    {
        return videoRepository.findAll();
    }
}