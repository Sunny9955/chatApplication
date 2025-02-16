package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SignUpController {
    @Autowired
    private SignUpService signUpService;
    @CrossOrigin(origins="http://127.0.0.1:5500")
    @PostMapping("/signup")
    public boolean signup(@RequestParam String email, @RequestParam String userName, @RequestParam String bio, @RequestParam("picture") MultipartFile file, @RequestParam String password)
    {
        if(signUpService.checkUserName(email))
        {
            return false;
        }
        String imageUrl=signUpService.upload( file);

       if(signUpService.signUp(email,password,userName,bio,imageUrl))
       {

           signUpService.signUp(email,password,userName,bio,imageUrl);
           return true;
       }
        return false;
    }

}
