package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;

import java.util.List;


/**
 * Qapply is a Querydsl query type for apply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qapply extends EntityPathBase<apply> {

    private static final long serialVersionUID = 125251006L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final Qapply apply = new Qapply("apply");

    public final StringPath application = createString("application");

    public final NumberPath<java.math.BigInteger> apply_id = createNumber("apply_id", java.math.BigInteger.class);

    public final StringPath applyStatus = createString("applyStatus");

    public final StringPath name = createString("name");

    public final Qstudygroup studygroup;

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final Quser user;
    public List<? extends Path<?>> mail_sent;

    public Qapply(String variable) {
        this(apply.class, forVariable(variable), INITS);
    }

    public Qapply(Path<? extends apply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public Qapply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public Qapply(PathMetadata metadata, PathInits inits) {
        this(apply.class, metadata, inits);
    }

    public Qapply(Class<? extends apply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studygroup = inits.isInitialized("studygroup") ? new Qstudygroup(forProperty("studygroup"), inits.get("studygroup")) : null;
        this.user = inits.isInitialized("user") ? new Quser(forProperty("user")) : null;
    }

}

