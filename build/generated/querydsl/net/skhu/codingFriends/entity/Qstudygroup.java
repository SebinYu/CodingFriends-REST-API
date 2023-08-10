package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudygroup is a Querydsl query type for Studygroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudygroup extends EntityPathBase<Studygroup> {

    private static final long serialVersionUID = 1951941158L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudygroup studygroup = new QStudygroup("studygroup");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> currentNum = createNumber("currentNum", Integer.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> learningMaterial_id = createNumber("learningMaterial_id", Integer.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final NumberPath<java.math.BigInteger> studyGroup_id = createNumber("studyGroup_id", java.math.BigInteger.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> totalNum = createNumber("totalNum", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final QUser user;

    public final StringPath writer = createString("writer");

    public QStudygroup(String variable) {
        this(Studygroup.class, forVariable(variable), INITS);
    }

    public QStudygroup(Path<? extends Studygroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudygroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudygroup(PathMetadata metadata, PathInits inits) {
        this(Studygroup.class, metadata, inits);
    }

    public QStudygroup(Class<? extends Studygroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

