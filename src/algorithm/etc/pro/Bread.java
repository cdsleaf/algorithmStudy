package algorithm.etc.pro;

import java.io.FileInputStream;
import java.util.*;

/**
 #1 20
 #2 1690
 #3 1065
 #4 226
 */
//최종점수는 100,000,123 이걸로 나눠야 함.
public class Bread {

    static int T  //전체 경우의수
                ,N //붕어빵 기계의 수
                ,M //발전기의 수. 1번부터 M번까지 있음.
                ,K //기간
                ,m1 ,m2 ,m3 ,startPoint //각 기계에 있는 발전기 번호들과 마지막엔 기계가 움직이는 에너지량

    ;
    static long sum //최종 결과값
                ,midSum //중간계산과정에 사용할 중간 합
                ,K_sum //기간의 총 합. k(k+1)/2 여기에서 미리 100000123 이걸로 나누기 해야함.
                ,fixedValue = 100000123;
    static Integer daily[] = new Integer[100000]; // 일자별 에너지가 증가하는 발전기의 번호 를 넣어둘 변수
    static Integer num[] = new Integer[100000];

    public static void main(String[] args) throws Exception {

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer mid, Integer keyValue) {

                long subSum=0;

                for(int i=0; i<= mid; i++){
                    if(daily[i] == m1 || daily[i] == m2 || daily[i] == m3) subSum = subSum+1;

                }
                //System.out.println("aaa1:"+mid+":"+subSum+":"+keyValue);
                if(subSum > keyValue) return 1;
                else if(subSum < keyValue) return -1;
                else return 0;

            }
        };

        System.setIn(new FileInputStream("./input_txt/etc/bread.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int i=0; i<T; i++){

            sum = 0; //최종값 초기화.

            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            //K_sum 미리 계산.
            K_sum = (((K*K)%fixedValue)+K)/2;

            for(int j=0; j<K; j++){
                daily[j] = sc.nextInt();
                num[j] = j;
            }

            for(int p=0; p<N; p++){

                m1 = sc.nextInt();
                m2 = sc.nextInt();
                m3 = sc.nextInt();
                startPoint = sc.nextInt();

                midSum = Arrays.binarySearch(num, 0,  K, startPoint, c);System.out.println("ㄴㄴ "+midSum);
                if(midSum >= 0){
                    sum = sum + K_sum - ((midSum*midSum)%fixedValue+midSum)/2;
                }
            }

            System.out.println("#"+(i+1)+" "+sum);
        }
    }
}
