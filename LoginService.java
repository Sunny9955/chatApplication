package com.socalMedia.socalMedia.Service;


import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Repository.LoginRepository;
import com.socalMedia.socalMedia.Repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements userDetailsServiceDatabase {
    @Autowired
    private LoginRepository login_Repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = login_Repository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));
        return user;
    }
}

