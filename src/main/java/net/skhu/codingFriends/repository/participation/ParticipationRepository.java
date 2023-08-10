package net.skhu.codingFriends.repository.participation;

import net.skhu.codingFriends.entity.Participationrate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ParticipationRepository extends JpaRepository<Participationrate, BigInteger>, ParticipationCustomRepository {


}
