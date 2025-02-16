package com.socalMedia.socalMedia.Service;

import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private SearchRepository searchRepository;
    public List<User>searchUser(String userEmail)
    {
       return searchRepository.findUser(userEmail);
    }
}
