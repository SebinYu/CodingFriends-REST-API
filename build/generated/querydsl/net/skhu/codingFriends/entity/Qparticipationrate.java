package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * Qparticipationrate is a Querydsl query type for participationrate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qparticipationrate extends EntityPathBase<participationrate> {

    private static final long serialVersionUID = -1729428495L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final Qparticipationrate participationrate = new Qparticipationrate("participationrate");

    public final NumberPath<Double> lectureScore = createNumber("lectureScore", Double.class);

    public final NumberPath<java.math.BigInteger> participationRate_id = createNumber("participationRate_id", java.math.BigInteger.class);

    public final Qstudygroup studygroup;

    public final StringPath studyGroup_Leader = createString("studyGroup_Leader");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final Quser user;

    public final NumberPath<Integer> week = createNumber("week", Integer.class);

    public final StringPath weeklyAttendance = createString("weeklyAttendance");

    public final StringPath weeklyHomework = createString("weeklyHomework");

    public Qparticipationrate(String variable) {
        this(participationrate.class, forVariable(variable), INITS);
    }

    public Qparticipationrate(Path<? extends participationrate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public Qparticipationrate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public Qparticipationrate(PathMetadata metadata, PathInits inits) {
        this(participationrate.class, metadata, inits);
    }

    public Qparticipationrate(Class<? extends participationrate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studygroup = inits.isInitialized("studygroup") ? new Qstudygroup(forProperty("studygroup"), inits.get("studygroup")) : null;
        this.user = inits.isInitialized("user") ? new Quser(forProperty("user")) : null;
    }

}

