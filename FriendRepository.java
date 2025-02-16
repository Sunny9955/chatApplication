package com.socalMedia.socalMedia.Repository;

import com.socalMedia.socalMedia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<User, String> {

    @Query("SELECT f FROM User u JOIN u.following f WHERE u.email = :userEmail")
    List<User> findFollowersByUserEmail(@Param("userEmail") String userEmail);
}
