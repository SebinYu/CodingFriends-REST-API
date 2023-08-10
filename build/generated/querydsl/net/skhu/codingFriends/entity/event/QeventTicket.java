package net.skhu.codingFriends.entity.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventTicket is a Querydsl query type for EventTicket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventTicket extends EntityPathBase<EventTicket> {

    private static final long serialVersionUID = 838294306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventTicket eventTicket = new QEventTicket("eventTicket");

    public final QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.skhu.codingFriends.entity.QUser user;

    public QEventTicket(String variable) {
        this(EventTicket.class, forVariable(variable), INITS);
    }

    public QEventTicket(Path<? extends EventTicket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventTicket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventTicket(PathMetadata metadata, PathInits inits) {
        this(EventTicket.class, metadata, inits);
    }

    public QEventTicket(Class<? extends EventTicket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event")) : null;
        this.user = inits.isInitialized("user") ? new net.skhu.codingFriends.entity.QUser(forProperty("user")) : null;
    }

}

