package net.skhu.codingFriends.entity.event;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class eventTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private event event;
}
