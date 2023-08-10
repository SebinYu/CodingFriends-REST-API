package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParticipationrate is a Querydsl query type for Participationrate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParticipationrate extends EntityPathBase<Participationrate> {

    private static final long serialVersionUID = -2085665839L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParticipationrate participationrate = new QParticipationrate("participationrate");

    public final NumberPath<Double> lectureScore = createNumber("lectureScore", Double.class);

    public final NumberPath<java.math.BigInteger> participationRate_id = createNumber("participationRate_id", java.math.BigInteger.class);

    public final QStudygroup studygroup;

    public final StringPath studyGroup_Leader = createString("studyGroup_Leader");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final QUser user;

    public final NumberPath<Integer> week = createNumber("week", Integer.class);

    public final StringPath weeklyAttendance = createString("weeklyAttendance");

    public final StringPath weeklyHomework = createString("weeklyHomework");

    public QParticipationrate(String variable) {
        this(Participationrate.class, forVariable(variable), INITS);
    }

    public QParticipationrate(Path<? extends Participationrate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParticipationrate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParticipationrate(PathMetadata metadata, PathInits inits) {
        this(Participationrate.class, metadata, inits);
    }

    public QParticipationrate(Class<? extends Participationrate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studygroup = inits.isInitialized("studygroup") ? new QStudygroup(forProperty("studygroup"), inits.get("studygroup")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

