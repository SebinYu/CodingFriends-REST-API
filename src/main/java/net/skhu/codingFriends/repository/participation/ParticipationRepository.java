package net.skhu.codingFriends.repository.participation;

import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.participationrate;
import net.skhu.codingFriends.repository.apply.ApplyCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ParticipationRepository extends JpaRepository<participationrate, BigInteger>, ParticipationCustomRepository {


}
