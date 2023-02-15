package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * Quser is a Querydsl query type for user
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Quser extends EntityPathBase<user> {

    private static final long serialVersionUID = -133908613L;

    public static final Quser user = new Quser("user");

    public final StringPath address = createString("address");

    public final StringPath address_detail = createString("address_detail");

    public final BooleanPath admin = createBoolean("admin");

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> user_id = createNumber("user_id", Integer.class);

    public final StringPath username = createString("username");

    public final StringPath userType = createString("userType");

    public Quser(String variable) {
        super(user.class, forVariable(variable));
    }

    public Quser(Path<? extends user> path) {
        super(path.getType(), path.getMetadata());
    }

    public Quser(PathMetadata metadata) {
        super(user.class, metadata);
    }

}

