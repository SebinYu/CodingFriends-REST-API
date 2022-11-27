package net.skhu.repository;

import net.skhu.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<user, Integer>  {

    user findByUserid(String userid);

}
