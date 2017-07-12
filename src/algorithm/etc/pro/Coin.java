package algorithm.etc.pro;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 2
 4
 -1 0 0 0
 11
 4 9 0 0 -1 9 4 0 4 8 4

 #1 5
 #2 15
 */
public class Coin {

    static int T, N, num, root; //T는 전체 테스트 케이스의 수, N은 노드의 갯수.
    static int cost[] = new int[10000]; //각 노드의 액면가.
    static int c_p[] = new int[10000]; //자식-부모 관계
    static HashMap<Integer, ArrayList<Integer>> p_c = new HashMap(); //부모-자식 관계
    static int result; //결과값.

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("C:/Users/SDS/git/algorithmStudy/input_txt/sds/coin.txt"));

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            N = sc.nextInt();

            //초기화
            p_c.clear();
            result = 0;

            for(int i=0; i<N; i++){
                num = sc.nextInt();

                if(num == -1){
                    root = i;
                    cost[i] = 2; //액면가
                }

                c_p[i] = num; //자식-부모관계

                //부모-자식관계
                if(p_c.containsKey(num)){
                    p_c.get(num).add(i);
                }else{
                    ArrayList<Integer> nodeArr = new ArrayList();
                    nodeArr.add(i);
                    p_c.put(num, nodeArr);
                }
            }

            //각 노드의 최적 액면가 셋팅..
            searchChildren(root);

            for(int j=0; j<N;j++){
                result += cost[j];
            }

            System.out.println("#"+test_case+" "+result);
        }
    }

    public static void searchChildren(int index){

        if(!p_c.containsKey(index)) return;

        int childrenCost = 1;
        int size = p_c.get(index).size();

        if(index != root) {
            if(c_p[index] == root) {
                if(size > 1) cost[index] = 3;
                else childrenCost = 2;
            }else{
                if(size > 1) cost[index] = cost[c_p[index]] == 2 ? 3 : 2;
                else childrenCost = cost[c_p[index]] == 1 ? 2 : 1;
            }
        }

        for(int i=0; i<size; i++){
            cost[p_c.get(index).get(i)] = childrenCost;
            searchChildren(p_c.get(index).get(i));
        }
    }
}
