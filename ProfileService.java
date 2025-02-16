package com.socalMedia.socalMedia.Service;

import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;
    public User getProfile(String userEmail)
    {
        return userRepository.findByEmail(userEmail);
    }
}
