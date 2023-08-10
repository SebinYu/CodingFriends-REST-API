package net.skhu.codingFriends.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="learningmaterial")
public class Learningmaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int learningMaterial_id;

    String materialType;

}