package net.skhu.codingFriends.repository.event;

import net.skhu.codingFriends.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
