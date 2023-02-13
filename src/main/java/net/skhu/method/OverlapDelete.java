package net.skhu.method;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OverlapDelete {
    public static BigInteger[] solution(Integer[] arr) {

        ArrayList<Integer> temp_arr = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                temp_arr.add(arr[i]);
            } else {
                if (arr[i] != temp_arr.get(temp_arr.size() - 1)) {
                    temp_arr.add(arr[i]);
                }
            }

        }

        BigInteger[] answer = new BigInteger[temp_arr.size()];
        for (int i = 0; i < temp_arr.size(); i++) {
            answer[i] = BigInteger.valueOf(temp_arr.get(i));
        }

        return answer;
    }
}
