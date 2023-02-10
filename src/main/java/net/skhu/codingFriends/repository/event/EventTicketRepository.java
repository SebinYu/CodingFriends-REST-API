package net.skhu.codingFriends.repository.event;

import net.skhu.codingFriends.entity.event.eventTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTicketRepository extends JpaRepository<eventTicket, Long> {
}
