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
            ,index //인덱스.
            ;
    static long sum //최종 결과값
            ,K_sum //기간의 총 합. k(k+1)/2 여기에서 미리 100000123 이걸로 나누기 해야함.
            ,fixedValue = 100000123;
    static Integer num[] = new Integer[100000];
    static Integer daily[] = new Integer[100000]; // 일자별 에너지가 증가하는 발전기의 번호 를 넣어둘 변수
    static HashMap<Integer, Integer> energyByGenerator = new HashMap<>();
    static ArrayList<HashMap<Integer, Integer>> dailyEnergyByGenerator= new ArrayList<>();
    static HashMap<Integer, Integer> midIndexMap; //compare 내부에서 사용할 임시 map 변수

    public static void main(String[] args) throws Exception {

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer mid, Integer keyValue) {

                long subSum=0;

                subSum = calculateDailyEnergyByGenerator(mid);

                if(subSum > keyValue) return 1;
                else if(subSum < keyValue) return -1;
                else return 0;
            }
        };

        System.setIn(new FileInputStream("./input_txt/etc/bread_input.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int i=0; i<T; i++){

            sum = 0; //최종값 초기화.
            energyByGenerator.clear();
            dailyEnergyByGenerator.clear();

            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            //K_sum 미리 계산.
            K_sum = (((K*K)%fixedValue)+K)/2;

            for(int j=0; j<K; j++){
                int temp = sc.nextInt();
                num[j] = j;
                if(energyByGenerator.containsKey(temp)){
                    energyByGenerator.put(temp, energyByGenerator.get(temp)+1);
                }else{
                    energyByGenerator.put(temp, 1);
                }

                dailyEnergyByGenerator.add((HashMap<Integer, Integer>)energyByGenerator.clone());
            }

            for(int p=0; p<N; p++){

                m1 = sc.nextInt();
                m2 = sc.nextInt();
                m3 = sc.nextInt();
                startPoint = sc.nextInt();

                index =  Arrays.binarySearch(num,0, K, startPoint, c);

                if(index > -1){

                    while (index > 0) {
                        if (calculateDailyEnergyByGenerator(index - 1) == calculateDailyEnergyByGenerator(index)) {
                            index--;
                        } else {
                            break;
                        }
                    }

                    sum = sum + K_sum - ((index*index)%fixedValue+index)/2;
                }
            }

            System.out.println("#"+(i+1)+" "+sum);
        }
    }

    private static long calculateDailyEnergyByGenerator(Integer midIndex){

        long midSum = 0;
        midIndexMap = dailyEnergyByGenerator.get(midIndex);

        if(midIndexMap.containsKey(m1)) midSum = midSum + midIndexMap.get(m1);
        if(midIndexMap.containsKey(m2)) midSum = midSum +  midIndexMap.get(m2);
        if(midIndexMap.containsKey(m3)) midSum = midSum +  midIndexMap.get(m3);

        return midSum;
    }

}
