package com.example.blogsecurity.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    //    User Class:
//id - username - password (Add All validation required)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    username :String
    @NotEmpty(message = "username shouldn't be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String username;

    //    password :String
    @NotEmpty(message = "password shouldn't be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    //role : String check (USER or ADMIN)
    @NotEmpty(message = "role can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( role='USER' or role='ADMIN')")
    private String role;


    //------------------Relation-------------------------//

    //define one-many relation with blogs
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Blog> blogSet;



    //------------------Implementing Methods-----------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
        //every time generate authority depending on role they give me by this.role

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
