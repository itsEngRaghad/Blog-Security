package com.example.blogsecurity.Repository;

import com.example.blogsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);
    User findUserByUsername(String username);

}
