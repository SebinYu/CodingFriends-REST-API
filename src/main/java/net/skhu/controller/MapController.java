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

        double[][] coordinates = {{32.1651461, 52.64648841}, {12, 30}, {4, 4}};
        System.out.println("서울 강남구 가로수길 5과 가장 가까운 위도/경도");
        System.out.println("위도: " + closest_pair(coordinates)[1][0]);
        System.out.println("경도: " + closest_pair(coordinates)[1][1]);

        return "studygroup/map/index";
    }


    double distance(double[] store1, double[] store2){
        return sqrt(Math.pow((store1[0] - store2[0]), 2) + Math.pow((store1[1] - store2[1]), 2));
    }

    double[][] closest_pair(double[][] coordinates){
        double x = 37.5182112402056;
        double y = 127.023150432187;
        double[] startPoint = {x, y};
        double[][] pair = { startPoint, coordinates[0]};

            for(int j = 0; j < coordinates.length; j++)
            {
                double[] spot1 = coordinates[j];
                if (distance(startPoint, pair[1]) > distance(startPoint, spot1)){
                    pair = new double[][]{startPoint, spot1};
                }
            }
        return pair;
    }


}
