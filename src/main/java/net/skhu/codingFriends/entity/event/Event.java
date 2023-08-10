package net.skhu.codingFriends.entity.event;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketLimit;


    @OneToMany(mappedBy = "event")
    private List<EventTicket> eventTickets;

    public boolean isClosed() {
        return eventTickets.size() >= ticketLimit;
    }
}
