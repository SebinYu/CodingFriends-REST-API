package net.skhu.codingFriends.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLearningmaterial is a Querydsl query type for Learningmaterial
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLearningmaterial extends EntityPathBase<Learningmaterial> {

    private static final long serialVersionUID = -740826603L;

    public static final QLearningmaterial learningmaterial = new QLearningmaterial("learningmaterial");

    public final NumberPath<Integer> learningMaterial_id = createNumber("learningMaterial_id", Integer.class);

    public final StringPath materialType = createString("materialType");

    public QLearningmaterial(String variable) {
        super(Learningmaterial.class, forVariable(variable));
    }

    public QLearningmaterial(Path<? extends Learningmaterial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLearningmaterial(PathMetadata metadata) {
        super(Learningmaterial.class, metadata);
    }

}

