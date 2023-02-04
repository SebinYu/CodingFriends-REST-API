package net.skhu.codingFriends.batch.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;


public class OrderMapper implements RowMapper<apply> {


    @Override
    public apply mapRow(ResultSet rs, int rowNum) throws SQLException {


        return apply
                .builder()
                .apply_id(BigInteger.valueOf(rs.getLong("apply_id")))
                .userIdtemp(BigInteger.valueOf(rs.getLong("userId")))
                .studyIdTemp(BigInteger.valueOf(rs.getLong("studygroupId")))
                .applyStatus(rs.getString("applyStatus"))
                .mail_sent(rs.getBoolean("mail_sent"))
                .title(rs.getString("title"))
                .application(rs.getString("application"))
                .name(rs.getString("name"))
                .updateDate(rs.getObject("updateDate", LocalDateTime.class))
                .build();
    }
}
