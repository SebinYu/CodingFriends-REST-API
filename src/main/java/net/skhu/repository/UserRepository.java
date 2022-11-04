package net.skhu.repository;

import net.skhu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>  {

    User findByUserid(String userid);

}
