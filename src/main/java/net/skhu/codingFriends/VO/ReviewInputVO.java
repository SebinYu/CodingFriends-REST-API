package net.skhu.codingFriends.VO;

import lombok.Data;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.User;

@Data
public class ReviewInputVO {
    Studygroup studygroup;
    User user;

}
