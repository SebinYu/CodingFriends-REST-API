package net.skhu.codingFriends.entity.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * Qevent is a Querydsl query type for event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qevent extends EntityPathBase<event> {

    private static final long serialVersionUID = -681270122L;

    public static final Qevent event = new Qevent("event");

    public final ListPath<eventTicket, QeventTicket> eventTickets = this.<eventTicket, QeventTicket>createList("eventTickets", eventTicket.class, QeventTicket.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> ticketLimit = createNumber("ticketLimit", Long.class);

    public Qevent(String variable) {
        super(event.class, forVariable(variable));
    }

    public Qevent(Path<? extends event> path) {
        super(path.getType(), path.getMetadata());
    }

    public Qevent(PathMetadata metadata) {
        super(event.class, metadata);
    }

}

