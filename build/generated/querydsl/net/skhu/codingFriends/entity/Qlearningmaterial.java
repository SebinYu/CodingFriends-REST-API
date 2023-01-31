package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * Qlearningmaterial is a Querydsl query type for learningmaterial
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qlearningmaterial extends EntityPathBase<learningmaterial> {

    private static final long serialVersionUID = 101948917L;

    public static final Qlearningmaterial learningmaterial = new Qlearningmaterial("learningmaterial");

    public final NumberPath<Integer> learningMaterial_id = createNumber("learningMaterial_id", Integer.class);

    public final StringPath materialType = createString("materialType");

    public Qlearningmaterial(String variable) {
        super(learningmaterial.class, forVariable(variable));
    }

    public Qlearningmaterial(Path<? extends learningmaterial> path) {
        super(path.getType(), path.getMetadata());
    }

    public Qlearningmaterial(PathMetadata metadata) {
        super(learningmaterial.class, metadata);
    }

}

