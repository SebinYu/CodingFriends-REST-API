package net.skhu.codingFriends.entity.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QeventTicket is a Querydsl query type for eventTicket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QeventTicket extends EntityPathBase<eventTicket> {

    private static final long serialVersionUID = -829574334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QeventTicket eventTicket = new QeventTicket("eventTicket");

    public final Qevent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.skhu.codingFriends.entity.Quser user;

    public QeventTicket(String variable) {
        this(eventTicket.class, forVariable(variable), INITS);
    }

    public QeventTicket(Path<? extends eventTicket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QeventTicket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QeventTicket(PathMetadata metadata, PathInits inits) {
        this(eventTicket.class, metadata, inits);
    }

    public QeventTicket(Class<? extends eventTicket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new Qevent(forProperty("event")) : null;
        this.user = inits.isInitialized("user") ? new net.skhu.codingFriends.entity.Quser(forProperty("user")) : null;
    }

}

