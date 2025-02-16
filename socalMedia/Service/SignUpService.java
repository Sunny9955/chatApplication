package com.socalMedia.socalMedia.Service;

import com.cloudinary.Cloudinary;
import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class SignUpService {
    @Autowired
    private SignUpRepository signUpRepository;
    @Autowired
    private Cloudinary cloudinary;

    public String upload(MultipartFile file) {
        try {
            Map data = cloudinary.uploader().upload(file.getBytes(), Map.of());
            String imageurl = (String) data.get("url");

            return imageurl;
        }catch (IOException e)
        {
            throw new RuntimeException("Image Not Upload");
        }
    }


    public boolean checkUserName(String userName)
    {
        if(signUpRepository.existsById(userName))
        {
            return true;
        }
        return false;
    }

    public boolean signUp(String email,String password,String userName,String bio,String imageUrl )
    {
        try {
//            String encodePass= passwordEncoder.encode(password);
            User user = User.builder()
                    .email(email)
                    .username(userName)
                    .password(password)
                    .bio(bio)
                    .profilePicture(imageUrl)
                    .build();
            signUpRepository.save(user);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }

    }

}
