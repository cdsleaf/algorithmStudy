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

    static int T,  //전체 경우의수
                N, //붕어빵 기계의 수
                M, //발전기의 수. 1번부터 M번까지 있음.
                K, //기간
                sum, //최종 결과값
                m1, m2, m3, startPoint
    ;
    static Integer daily[] = new Integer[100000]; // 일자별 에너지가 증가하는 발전기의 번호 를 넣어둘 변수
    static Integer num[] = new Integer[100000];
    //static List<HashMap<String, Integer>> machines = new ArrayList<>();; //각 붕어빵 기계에있는 발전기들을 저장할 변수

    public static void main(String[] args) throws Exception {

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int subSum1=0;
                int subSum2=0;
                for(int i=0; i< o1; i++){
                    if(daily[i] == m1 || daily[i] == m2 || daily[i] == m3) subSum1++;
                }
                for(int i=0; i< o2; i++){
                    if(daily[i] == m1 || daily[i] == m2 || daily[i] == m3) subSum2++;
                }
                System.out.println("aaa1:"+o1+":"+o2);
                System.out.println("aaa:"+subSum1+":"+subSum2);
                if(subSum1 < startPoint && subSum2 > startPoint) return 1;
                else if(startPoint == subSum1) return 0;
                else return -1;

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

            for(int j=0; j<K; j++){
                daily[j] = sc.nextInt();
                num[j] = j;
            }

            for(int p=0; p<N; p++){

                m1 =  sc.nextInt();
                m2 = sc.nextInt();
                m3 = sc.nextInt();
                startPoint = sc.nextInt();

                int a = Arrays.binarySearch(num, 0,  K, K/2, c);
                System.out.println("ttt:"+a);
            }

            System.out.println("#"+i+" "+sum);
        }
    }
}
