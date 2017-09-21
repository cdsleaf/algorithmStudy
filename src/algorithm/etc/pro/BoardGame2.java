package algorithm.etc.pro;

import java.io.FileInputStream;
import java.util.Scanner;

/*
#1 1400
#2 19
#3 181
#4 20
 */

public class BoardGame2 {

    static int T, N, K, M, radix, num; //T 전체 테스트 케이스, N 보드의 크기, K 이동 카드의 종류, M 이동 카드의 각 숫자를 사용할 수 있는 횟수
    static int[] cost = new int[100]; //각 칸의 비용
    static int[][] save = new int[100][20000]; //DP 용
    static int[][] solution = new int[100][20000]; //솔루션 저장 용
    static int result;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("C:/Users/SDS/git/algorithmStudy/input_txt/sds/boardGame2.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int i=0; i<T; i++){

            N = sc.nextInt(); //보드 크기
            K = sc.nextInt(); // 이동 카드 종류
            M = sc.nextInt(); // 사용가능 횟수

            for(int j=0; j<N; j++){
                cost[j] = sc.nextInt();
            }

            radix = M + 1;
            num = (int)Math.pow(radix, 6) -1;

            //초기화
            for(int k = 0;k< N+K; k++){
                for(int m=0; m<=num; m++){
                    save[k][m] = -1;
                    solution[k][m] = 0;
                }
            }
            result = Integer.MAX_VALUE;





            System.out.println("#"+(i+1)+" "+result);
        }
    }
}
