package me.oudedong.springbootDeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Builder
    public User(String email, String password, String auth, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }
    @Override
    public String getUsername(){ return email;}
    @Override
    public String getPassword(){ return password;}
    @Override
    public boolean isAccountNonExpired(){ return true;}
    @Override
    public boolean isAccountNonLocked(){ return true;}
    @Override
    public boolean isCredentialsNonExpired(){ return true;}
    @Override
    public boolean isEnabled(){
        return isAccountNonExpired()&&isAccountNonLocked()&&isCredentialsNonExpired();
    }
    @Override
    public String toString(){return email;}
    public User update(String name){
        this.nickname = name;
        return this;
    }
}
