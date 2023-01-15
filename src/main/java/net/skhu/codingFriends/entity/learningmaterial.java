package net.skhu.codingFriends.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class learningmaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int learningMaterial_id;

    String materialType;

}