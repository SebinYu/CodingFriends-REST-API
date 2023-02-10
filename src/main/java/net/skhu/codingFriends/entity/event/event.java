package net.skhu.codingFriends.entity.event;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketLimit;


    @OneToMany(mappedBy = "event")
    private List<eventTicket> eventTickets;

    public boolean isClosed() {
        return eventTickets.size() >= ticketLimit;
    }
}
