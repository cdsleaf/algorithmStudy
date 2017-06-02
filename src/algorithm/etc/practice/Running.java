package algorithm.etc.practice;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
2
4
2 2 3 3
4
3 1 2 4

#1 4
#2 4
 */
public class Running {

    static int T // 전체 케이스
            ,N; //달리기 선수의 수
    static int result; //결과값

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./input_txt/practice/running.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int i=0; i<T; i++){

            result = 0;
            N = sc.nextInt();

            for(int j=0; j<N; j++){

            }



            System.out.println("#"+(i+1)+" "+result);
        }
    }
}
