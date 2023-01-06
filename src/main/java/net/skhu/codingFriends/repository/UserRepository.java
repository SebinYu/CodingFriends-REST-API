package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Integer>  {
    user findByUsername(String userid);

}
