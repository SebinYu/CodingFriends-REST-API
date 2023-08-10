package net.skhu.codingFriends.repository.event;

import net.skhu.codingFriends.entity.event.Event;
import net.skhu.codingFriends.entity.event.EventTicket;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventTicketRepository extends JpaRepository<EventTicket, Long> {
    @Query("select et from EventTicket et where et.event = :event")
    List<EventTicket> findByEvent_id(@Param("event") Optional<Event> event);
}
