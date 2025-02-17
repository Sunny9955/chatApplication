package com.socalMedia.socalMedia.Service;

import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;
    public List<User> getfriend(String userEmail)
    {
        return friendRepository.findFollowersByUserEmail(userEmail);
    }
}
