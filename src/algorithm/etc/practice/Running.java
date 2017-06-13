package algorithm.etc.practice;

import java.io.FileInputStream;
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
    static int[] runners  = new int[100000];
    static int[] temp = new int[100000];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./input_txt/etc/practice/running.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int i=0; i<T; i++){

            N = sc.nextInt();

            for(int j=0; j<N; j++){
                runners[j] = sc.nextInt();
            }

            System.out.println( "#"+(i+1)+" "+mergeSort(0, N-1) );
        }
    }

    public static long mergeSort(int s, int e){

        if(s == e) return 0;
        int m = s+e >> 1;
        long ret = mergeSort(s, m) + mergeSort(m+1, e);

        for(int i=s, l=s, r=m+1; i<=e; i++){
            if(l > m || r <= e && runners[r] <= runners[l]){
                ret += l-s;
                temp[i] = runners[r++];
            }else{
                temp[i] = runners[l++];
            }
        }
        for(int i=s; i<=e; i++) runners[i] = temp[i];

        return ret;
    }
}
