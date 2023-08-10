package net.skhu.codingFriends.entity.event;

import lombok.Data;
import net.skhu.codingFriends.entity.User;

import javax.persistence.*;

@Data
@Entity
@Table(name="eventTicket")
public class EventTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
