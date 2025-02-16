package com.socalMedia.socalMedia.Service;

import com.socalMedia.socalMedia.Model.Connection;
import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Repository.ConnectionRepository;
import com.socalMedia.socalMedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class InviteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    public boolean inviteFriend(String userFromFollow, String userToFollow) {
        try {
            Optional<User> userFromOpt = userRepository.findById(userFromFollow);
            Optional<User> userToOpt = userRepository.findById(userToFollow);

            if (userFromOpt.isPresent() && userToOpt.isPresent()) {
                User userFrom = userFromOpt.get();
                User userTo = userToOpt.get();

                // Add the userTo to the following list of userFrom
                userFrom.getFollowing().add(userTo);

                // Add the userFrom to the followers list of userTo
                userTo.getFollowers().add(userFrom);

                // Save the updated users
                userRepository.save(userFrom);
                userRepository.save(userTo);

                // Create and save the connection
                Connection connection = Connection.builder()
                        .follower(userFrom)
                        .following(userTo)
                        .timestamp(new Date())
                        .build();
                connectionRepository.save(connection);

                return true;
            } else {
                return false; // One or both users not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }
}


