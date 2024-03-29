package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.VO.ReviewInputVO;
import net.skhu.codingFriends.dto.RequestDTO.ReviewRequsetDTO;
import net.skhu.codingFriends.dto.ResponseDTO.ParticipationResponseDTO;
import net.skhu.codingFriends.entity.Participationrate;
import net.skhu.codingFriends.entity.Review;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.User;
import net.skhu.codingFriends.exception.studygroup.StudygroupIdNotFound;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.repository.participation.ParticipationRepository;
import net.skhu.codingFriends.repository.review.ReviewRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ParticipationRepository participationRepository;
    private final StudygroupRepository studygroupRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ParticipationResponseDTO> getPreviousParticipantList(Long studygroup_id, User myUserInfo) {
        Studygroup studygroupTemp = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });


        List<Participationrate> participationrateList = participationRepository.findByStudygroupAndStatus(studygroupTemp);
        for(int i = 0; i<participationrateList.size(); i++){
            if(participationrateList.get(i).getUser() == myUserInfo){
                participationrateList.remove(myUserInfo);
            }
        }
        List<ParticipationResponseDTO> participationRequsetDTOS = new ArrayList<>();
        participationrateList.forEach(s -> participationRequsetDTOS.add(ParticipationResponseDTO.toDto(s)));
        return participationRequsetDTOS;
    }


    @Transactional(readOnly = true)
    public ReviewInputVO getPreviousParticipant(Long studygroup_id, Long User_id) {
        Studygroup studygroupTemp = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });
        Optional<User> userTemp = userRepository.findById(Math.toIntExact(User_id));
        ReviewInputVO reviewInputVO = new ReviewInputVO();
        reviewInputVO.setStudygroup(studygroupTemp);
        reviewInputVO.setUser(userTemp.get());
        return reviewInputVO;
    }


    @Transactional
    public Review postReview(ReviewRequsetDTO reviewRequsetDTO, User user ) {
        Studygroup studygroupTemp = studygroupRepository.findById(reviewRequsetDTO.getStudygroupId()).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });

        Optional<User> userTemp = userRepository.findById(reviewRequsetDTO.getStudentId().intValue());

        Review reviewTemp = new Review();
        reviewTemp.setStudyGroupPartner(user.getName());
        reviewTemp.setReviewScore(reviewRequsetDTO.getReviewScore());
        reviewTemp.setReviewContents(reviewRequsetDTO.getReviewContents());
        reviewTemp.setObjection(0);
        //상대방
        reviewTemp.setUser(userTemp.get());
        reviewTemp.setStudygroup(studygroupTemp);

        reviewRepository.save(reviewTemp);

        return reviewTemp;
    }


    @Transactional(readOnly = true)
    public List<Review> getReview(User user) {
        return reviewRepository.findByUser(user);
    }


    @Transactional
    public String postReviewObjection(Long review_id, User user1) {
        List<Review> reviews = reviewRepository.findByUser(user1);
        Integer totalNum = 0;
        String response = "";
        for(int i = 0; i<reviews.size(); i++){
            totalNum += reviews.get(i).getObjection();
        }

        if(totalNum >= 3){
            response = "3번 이의신청 기회를 소진하셨습니다.(내년부터 이의신청가능)";
        }else {
            response = "이의신청 완료";
        }
        reviewRepository.updateObjection(BigInteger.valueOf(review_id));
        return response;
    }
}
