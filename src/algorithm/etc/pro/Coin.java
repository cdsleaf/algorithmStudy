package algorithm.etc.pro;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 2
 4
 -1 0 0 0
 11
 4 9 0 0 -1 9 4 0 4 8 4

 #1 5
 #2 15

 10개 테케 답
 #1 107
 #2 10059
 #3 7450
 #4 5661
 #5 730
 #6 7242
 #7 2696
 #8 6317
 #9 4112
 #10 11562

 https://www.acmicpc.net/problem/1693
 https://m.blog.naver.com/PostView.nhn?blogId=programmer18&logNo=220757715920&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F

 */
public class Coin {

    static final int coinMaxNum = 15;
    static final int indexMax = 10001;
    static final int initMaxValue = 99999999;
    static int  T, N, root, result,nodeIndex; //T는 전체 테스트 케이스의 수, N은 노드의 갯수.
    static ArrayList<Integer> nodeRelation[] = new ArrayList[indexMax];
    static boolean visited[] = new boolean[indexMax];
    static int savedCoinValue[][] = new int[indexMax][coinMaxNum+1]; //coinNum 은 1~15 까지 (0 은 사용하지 않음)

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("C:/Users/SDS/git/algorithmStudy/input_txt/sds/coin.txt"));

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            N = sc.nextInt();

            //초기화
            result = 0;
            for(int i=0; i<indexMax; i++){
                for(int j=1; j<=coinMaxNum; j++){
                    savedCoinValue[i][j] = -1;
                }
                visited[i] = false;
                nodeRelation[i] = new ArrayList<>();
            }

            for(int i=0; i<N; i++){
                nodeIndex = sc.nextInt();

                if(nodeIndex == -1){
                    root = i;
                }else{
                    nodeRelation[nodeIndex].add(i);
                    //nodeRelation[i].add(nodeIndex);
                }
            }

            //코인의 액면가를 DFS+DP 를 활용하여 최소값 구하기.
            result = setCoinValue(root, 1); //root 에서 시작하고 코인 액면가는 1부터 시작.

            System.out.println("#"+test_case+" "+result);
        }
    }

    public static int setCoinValue(int index, int coinNum) {

        int returnValue;
        int minValue;

        //먼저 이미 저장된 값이 있는지 확인한다.
        returnValue = savedCoinValue[index][coinNum];
        if(returnValue != -1) return returnValue;

        //자식이 없는 끝 노드 라면 입력받은 액면가를 그대로 받고 그 값을 리턴.
        if(nodeRelation[index].size() == 0){
            savedCoinValue[index][coinNum] = coinNum;
            return coinNum;
        }

        //현재 index 를 방문중임으로 체크 true
        visited[index] = true;

        for(int k=0; k<nodeRelation[index].size(); k++){
            if(visited[nodeRelation[index].get(k)]) continue;
            minValue = initMaxValue;

            //index 노드와 연결된 노드들에(nodeRelation[index].get(x)) 액면가(coinNum)가 다르면 해당 액면가로 setCoinValue호출하여 해당 노드의 최소값을 구하고 minValue를 갱신한다.
            for(int m=1; m<=coinMaxNum; m++){

                if(m != coinNum) minValue = Math.min(minValue, setCoinValue(nodeRelation[index].get(k), m));
            }
            returnValue += minValue;
        }

        //마지막에 방문상태를 false
        visited[index] = false;

        savedCoinValue[index][coinNum] = returnValue;

        return returnValue;
    }
}
