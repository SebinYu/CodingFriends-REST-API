package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.VO.ReviewInputVO;
import net.skhu.codingFriends.dto.RequestDTO.ParticipationRequsetDTO;
import net.skhu.codingFriends.dto.ResponseDTO.ParticipationResponseDTO;
import net.skhu.codingFriends.entity.participationrate;
import net.skhu.codingFriends.entity.review;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
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

    @Transactional
    public List<ParticipationResponseDTO> getEXColleague(Long studygroup_id, user myUserInfo) {
        studygroup studygroupTemp = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });


        List<participationrate> participationrateList = participationRepository.findByStudygroupAndStatus(studygroupTemp);
        for(int i = 0; i<participationrateList.size(); i++){
            if(participationrateList.get(i).getUser() == myUserInfo){
                participationrateList.remove(myUserInfo);
            }
        }
        List<ParticipationResponseDTO> participationRequsetDTOS = new ArrayList<>();
        participationrateList.forEach(s -> participationRequsetDTOS.add(ParticipationResponseDTO.toDto(s)));
        return participationRequsetDTOS;
    }
    @Transactional
    public ReviewInputVO getReviewInputInfo(Long studygroup_id, Long User_id) {
        studygroup studygroupTemp = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });
        Optional<user> userTemp = userRepository.findById(Math.toIntExact(User_id));
        ReviewInputVO reviewInputVO = new ReviewInputVO();
        reviewInputVO.setStudygroup(studygroupTemp);
        reviewInputVO.setUser(userTemp.get());
        return reviewInputVO;
    }
    @Transactional
    public review postReview(Long studygroup_id, user user, Long User_id, review review) {
        studygroup studygroupTemp = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });

        Optional<user> userTemp = userRepository.findById(Math.toIntExact(User_id));

        review reviewTemp = new review();
        reviewTemp.setStudyGroupPartner(user.getName());
        reviewTemp.setReviewScore(review.getReviewScore());
        reviewTemp.setReviewContents(review.getReviewContents());
        reviewTemp.setObjection(0);
        //상대방
        reviewTemp.setUser(userTemp.get());
        reviewTemp.setStudygroup(studygroupTemp);

        reviewRepository.save(reviewTemp);

        return reviewTemp;
    }
    @Transactional
    public List<review> getMyReviews(user user) {
        return reviewRepository.findByUser(user);
    }
    @Transactional
    public String postObjection(Long review_id, user user1) {
        List<review> reviews = reviewRepository.findByUser(user1);
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
