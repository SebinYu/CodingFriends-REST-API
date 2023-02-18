package net.skhu.codingFriends.entity.event;

import lombok.Data;
import net.skhu.codingFriends.entity.user;

import javax.persistence.*;

@Data
@Entity
public class eventTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    user user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private event event;
}
