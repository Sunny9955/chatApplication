package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController {
    @Autowired
    private FriendService friendService;
    @CrossOrigin(origins="http://localhost:5500")
    @PostMapping("/getfriend")
    public List<User> friend(@RequestParam String userEmail)
    {
        return friendService.getfriend(userEmail);
    }
}
