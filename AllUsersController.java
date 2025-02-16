package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Service.AllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AllUsersController {
    @Autowired
    private AllUsersService allUsersService;
    @CrossOrigin(origins = "http://localhost:5500")
    @PostMapping("/getUser")
    public List<User> getUser(@RequestParam String userEmail)
    {
        return allUsersService.get(userEmail);
    }

}
