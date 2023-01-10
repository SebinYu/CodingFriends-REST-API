package net.skhu.codingFriends.repository.apply;

import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.user;

import java.util.List;

public interface ApplyCustomRepository {

    List<apply> findByUser(user user1);

}
