package com.socalMedia.socalMedia.Service;

import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Repository.AllUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllUsersService {
    @Autowired
    private AllUsersRepository allUsersRepository;
    public List<User> get(String userEmail)
    {

        return allUsersRepository.findUsersNotFollowedBy(userEmail);
    }


}
