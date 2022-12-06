package net.skhu.method;


public class OverlapCheck {
    public static String solution(Integer[] arr) {
        System.out.println("정렬 전 배열");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        String answer = "신규";
        for(int i=1; i<arr.length; i++){
            int tmp=arr[i], j;
            for(j=i-1; j>=0; j--){
                if(arr[j]>tmp) arr[j+1]=arr[j];
                else break;
            }
            arr[j+1]=tmp;
        }

        System.out.println("정렬된 배열");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                answer = "중복";
                break;
            }
        }
        return answer;
    }
}
