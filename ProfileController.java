package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @CrossOrigin(origins = ("http://localhost:55000"))
@PostMapping("/users/profile")
    public User profile(@RequestParam String userEmail)
    {
        return profileService.getProfile(userEmail);
    }
}
