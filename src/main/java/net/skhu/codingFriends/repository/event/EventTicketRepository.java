package net.skhu.codingFriends.repository.event;

import net.skhu.codingFriends.entity.event.event;
import net.skhu.codingFriends.entity.event.eventTicket;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventTicketRepository extends JpaRepository<eventTicket, Long> {
    @Query("select et from eventTicket et where et.event = :event")
    List<eventTicket> findByEvent_id(@Param("event") Optional<event> event);
}
