package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserRepository extends JpaRepository<user, Integer>  {

    user findByUserid(String userid);

}
