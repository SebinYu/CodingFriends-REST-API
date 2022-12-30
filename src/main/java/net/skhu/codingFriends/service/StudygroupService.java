package net.skhu.codingFriends.service;

import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.StudygroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudygroupService {

    @Autowired
    public StudygroupRepository studygroupRepository;
    public List<studygroup> findAll(){
        return studygroupRepository.findAll();
    }

//    public User createEntity(UserRegistration userRegistration) {
//        User user = new User();
//        user.setUserid(userRegistration.getUserid());
//        user.setPassword(userRegistration.getPasswd1());
//        user.setName(userRegistration.getName());
//        user.setEmail(userRegistration.getEmail());
//        user.setDepartment(new Department());
//        user.getDepartment().setId(userRegistration.getDepartmentId());
//        user.setEnabled(true);
//        user.setUserType("학생");
//        return user;
//    }
//
//    public void save(UserRegistration userRegistration) {
//        User user = createEntity(userRegistration);
//        userRepository.save(user);
//    }


}