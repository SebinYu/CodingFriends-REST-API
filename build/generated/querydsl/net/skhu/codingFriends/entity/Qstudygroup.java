package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * Qstudygroup is a Querydsl query type for studygroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qstudygroup extends EntityPathBase<studygroup> {

    private static final long serialVersionUID = -41523706L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final Qstudygroup studygroup = new Qstudygroup("studygroup");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> currentNum = createNumber("currentNum", Integer.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> learningMaterial_id = createNumber("learningMaterial_id", Integer.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final NumberPath<java.math.BigInteger> studyGroup_id = createNumber("studyGroup_id", java.math.BigInteger.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> totalNum = createNumber("totalNum", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final Quser user;

    public final StringPath writer = createString("writer");

    public Qstudygroup(String variable) {
        this(studygroup.class, forVariable(variable), INITS);
    }

    public Qstudygroup(Path<? extends studygroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public Qstudygroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public Qstudygroup(PathMetadata metadata, PathInits inits) {
        this(studygroup.class, metadata, inits);
    }

    public Qstudygroup(Class<? extends studygroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new Quser(forProperty("user")) : null;
    }

}

