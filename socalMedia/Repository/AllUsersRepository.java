package com.socalMedia.socalMedia.Repository;

import com.socalMedia.socalMedia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllUsersRepository extends JpaRepository<User,String> {
    @Query("SELECT u FROM User u WHERE u.email != :userEmail AND u NOT IN (SELECT f FROM User u JOIN u.following f WHERE u.email = :userEmail)")
    List<User> findUsersNotFollowedBy(@Param("userEmail") String userEmail);
}
