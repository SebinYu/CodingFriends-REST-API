package net.skhu.controller;

import net.skhu.dto.response.ResponseStudygroup;
import net.skhu.mapper.LearningMaterialMapper;
import net.skhu.mapper.StudygroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.lang.Math.sqrt;

@Controller
@RequestMapping("/studygroup/map")
public class MapController {

    @Autowired
    StudygroupMapper studygroupMapper;

    @Autowired
    LearningMaterialMapper learningMaterialMapper;

    @GetMapping("index")
    public String mapIndex(Model model) throws Exception {
        List<ResponseStudygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        model.addAttribute("studygroups", studygroups);
        double[][] coordinates = {{3, 3}, {32, 52}, {12, 30}, {3, 4}};
        System.out.println(closest_pair(coordinates)[0][0]);
        System.out.println(closest_pair(coordinates)[1][0]);

        return "studygroup/map/index";
    }


    double distance(double[] store1, double[] store2){
        return sqrt(Math.pow((store1[0] - store2[0]), 2) + Math.pow((store1[1] - store2[1]), 2));
    }

    double[][] closest_pair(double[][] coordinates){
        double[][] pair = {coordinates[0], coordinates[1]};

        for(int i=0; i< coordinates.length-1; i++)
        {
            for(int j=i+1; j < coordinates.length; j++)
            {
                double[] store1 = coordinates[i];
                double[] store2 = coordinates[j];
//# 더 가까운 두 매장을 찾으면 pair 업데이트
                if (distance(pair[0], pair[1]) > distance(store1, store2)){
                    pair = new double[][]{store1, store2};

                }

            }
        }
        return pair;
    }

}
