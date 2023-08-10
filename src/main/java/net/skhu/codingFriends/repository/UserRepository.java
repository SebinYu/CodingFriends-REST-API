package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>  {
    User findByUsername(String userid);
    List<User> findByAddressLike(String address);
}
