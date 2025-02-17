package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;
    @CrossOrigin(origins = "http://localhost:5500")
    @PostMapping("/search/user")
    public List<User> search(@RequestParam String userEmail)
    {
        return searchService.searchUser(userEmail);
    }
}
