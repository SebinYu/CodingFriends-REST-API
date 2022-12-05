package net.skhu.controller;

import net.skhu.dto.response.ResponseStudygroup;
import net.skhu.mapper.LearningMaterialMapper;
import net.skhu.mapper.StudygroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/studygroup/sequence")
public class OrderController {

    @Autowired
    StudygroupMapper studygroupMapper;

    @Autowired
    LearningMaterialMapper learningMaterialMapper;

    @GetMapping("updateDate")
    public String updateDate(Model model) throws Exception {
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());

        LocalDateTime[] UpdateDate = studygroupMapper.findUpdateDate();
        System.out.println("정렬 전");

        for (int i = 0; i < UpdateDate.length ; i++){
            System.out.println(UpdateDate[i]);
        }

        QSort(UpdateDate, 0, UpdateDate.length - 1);

        System.out.println("정렬 후");
        for (int i = 0; i < UpdateDate.length ; i++){
            System.out.println(UpdateDate[i]);
        }

            List<ResponseStudygroup> studygroups = new ArrayList<>();

        for (int i = 0; i < UpdateDate.length ; i++){
            System.out.println("업로드" + UpdateDate[i]);
            ResponseStudygroup UpdateDateInfo = studygroupMapper.findUpdateDateInfo(UpdateDate[i]);
            studygroups.add(UpdateDateInfo);
        }

        model.addAttribute("studygroups", studygroups);

        return "studygroup/sequence/updateDate";
    }


    public void QSort(LocalDateTime[] UpdateDate, int start, int end) {
        if(start>=end) {return;} //원소가 1개일 경우 종료
        int pivot = start; //피벗은 리스트의 첫번째 요소
        int left = start + 1;
        int right = end;

        while(left<=right) {
            while(left<=end && UpdateDate[left].isBefore(UpdateDate[pivot]) ) {
                left++; //피벗보다 큰 데이터를 찾을 때까지 반복
            }

            while(left<=end && UpdateDate[left].isEqual(UpdateDate[pivot]) ) {
                left++; //피벗과 같은 데이터를 찾을 때까지 반복
            }

            while(right>start && UpdateDate[right].isAfter(UpdateDate[pivot])) {
                right--; //피벗보다 작은 데이터를 찾을 때 까지 반복
            }

            if(left>right) {//데이터가 엇갈린다면
                LocalDateTime temp = UpdateDate[pivot];
                UpdateDate[pivot] = UpdateDate[right];
                UpdateDate[right] = temp;
            }else {
                LocalDateTime temp = UpdateDate[left];
                UpdateDate[left] = UpdateDate[right];
                UpdateDate[right] = temp;
            }
        }
        QSort(UpdateDate, start, right-1); //분할이후 왼쪽에서 정렬수행
        QSort(UpdateDate, right+1,end);
    }

    @GetMapping("startDate")
    public String startDate(Model model) throws Exception {
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());

        List<ResponseStudygroup> studygroups = studygroupMapper.findStartDate();

        model.addAttribute("studygroups", studygroups);

        return "studygroup/sequence/startDate";
    }

}
