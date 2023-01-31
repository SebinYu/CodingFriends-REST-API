package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * Qreview is a Querydsl query type for review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qreview extends EntityPathBase<review> {

    private static final long serialVersionUID = 64526088L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final Qreview review = new Qreview("review");

    public final NumberPath<Integer> objection = createNumber("objection", Integer.class);

    public final NumberPath<java.math.BigInteger> review_id = createNumber("review_id", java.math.BigInteger.class);

    public final StringPath reviewContents = createString("reviewContents");

    public final NumberPath<Double> reviewScore = createNumber("reviewScore", Double.class);

    public final Qstudygroup studygroup;

    public final StringPath studyGroupPartner = createString("studyGroupPartner");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final Quser user;

    public Qreview(String variable) {
        this(review.class, forVariable(variable), INITS);
    }

    public Qreview(Path<? extends review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public Qreview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public Qreview(PathMetadata metadata, PathInits inits) {
        this(review.class, metadata, inits);
    }

    public Qreview(Class<? extends review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studygroup = inits.isInitialized("studygroup") ? new Qstudygroup(forProperty("studygroup"), inits.get("studygroup")) : null;
        this.user = inits.isInitialized("user") ? new Quser(forProperty("user")) : null;
    }

}

