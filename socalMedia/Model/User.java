package com.socalMedia.socalMedia.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
@Entity
@Setter // in case of  @ManyToMany relationship we have to use getter and setter
// annotation it we use @data annotation then it give stackoverflow runtime error
// When @Data generates the toString(), equals(), or hashCode() methods, it includes all
// fields in the entity. This leads to infinite recursion because:
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    private String email;
    private String username;
    private String password;
    private String profilePicture;
    private String bio;

    @ManyToMany
    @JoinTable(
            name = "connection",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    @JsonIgnore
    private Set<User> following;

    @ManyToMany(mappedBy = "following")
    @JsonIgnore
    private Set<User> followers;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getUserEmail() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
