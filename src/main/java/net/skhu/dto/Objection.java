package net.skhu.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;

@Data
public class Objection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger objection_id;

    BigInteger review_id;
    Integer objection;
}
