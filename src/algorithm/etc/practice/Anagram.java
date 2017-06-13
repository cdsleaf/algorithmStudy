package algorithm.etc.practice;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
1
6
anagram
managra
flow
wolf
nameless
salesmen

#1 3
 */
public class Anagram {

    static int T // 전체 케이스
            ,N; //문자열의 수
    static long count ,result; //결과값
    static char[] anangramChar;
    static String[] anagramList;
    static String anagram;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./input_txt/etc/practice/anagram.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int i=0; i<T; i++){

            result = 0;
            N = sc.nextInt();

            anagramList = new String[N];

            for(int j=0; j<N; j++){
                anangramChar = sc.next().toCharArray();
                Arrays.sort(anangramChar);
                anagramList[j] = new String(anangramChar);
            }

            Arrays.sort(anagramList);

            for(int k=0; k<N; k++){

                if(k==0) {
                    count = 1;
                    anagram = anagramList[0];
                }else{
                    if(anagram.equals(anagramList[k])){
                        count++;
                    }else{
                        result += (count*(count-1))/2;
                        anagram = anagramList[k];
                        count = 1;
                    }
                }
            }

            result += (count*(count-1))/2;

            System.out.println("#"+(i+1)+" "+result);
        }
    }
}
