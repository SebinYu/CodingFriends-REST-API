package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<user, Integer>  {
    user findByUsername(String userid);

    public boolean existsByUsername(String username);
    @Query("SELECT u FROM user u WHERE u.user_id=?1")
    user findByUser_id(Long id);
}
