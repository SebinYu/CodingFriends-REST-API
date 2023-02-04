package net.skhu.codingFriends.batch.mapper;

import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.UserRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderMapper implements RowMapper<apply> {
    UserRepository userRepository;

    @Override
    public apply mapRow(ResultSet rs, int rowNum) throws SQLException {
//        user user = userRepository.findByUser_id(rs.getLong("userId"));

        return apply
                .builder()
                .applyStatus(rs.getString("applyStatus"))
                .mail_sent(rs.getBoolean("mail_sent"))
                .user(userRepository.findByUser_id(rs.getLong("userId")))
                .build();
    }
}
