package net.skhu.codingFriends.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class LearningMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int learningMaterial_id;

    String materialType;

}