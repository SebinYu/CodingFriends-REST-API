package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<user, Integer>  {
    user findByUsername(String userid);
    List<user> findByAddressLike(String address);
}
