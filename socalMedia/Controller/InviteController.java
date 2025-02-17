package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class InviteController {
    @Autowired
    private InviteService inviteService;
    @CrossOrigin(origins = "http://localhost:5500")
    @PostMapping("/invite")
    public boolean invite(@RequestParam String userFromFollow,@RequestParam String userToFollow)
    {
        if(userFromFollow.length()==0||userToFollow.length()==0)
        {
            return false;
        }
        return inviteService.inviteFriend(userFromFollow,userToFollow);
    }
}
