package algorithm.etc.pro;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
(입력)
4
3 3
2
2 1
1 2
0
4 3
2
2 1
3 1
1
3 0 2 2 0
4 2
0
1
2 0 1 0 -3
10 2
2
1 1
5 1
2
0 1 9 0 25
3 0 8 1 -6
(출력)
#1 noway
#2 4
#3 mininf
#4 -2
 */

public class Inter {

    static int  T, xSize, ySize, Mc, Wc, tempX, tempY; //전체 케이스, x,y사이즈, 운석갯수, 워프갯수
    static int Xxx, Yyy; //현재 좌표에서 좌/우/위/아래 방향으로 1씩 이동한 좌표 변수
    static int dCoust = 1; //한칸 이동 시 비용(여기에서는 1)
    static int[] xx= {0, 1, 0, -1}; //x축 위, 오른쪽, 아래, 왼쪽
    static int[] yy= {1, 0, -1, 0};
    static int[][] dCost = new int[30][30]; //이동한 좌표까지의 비용을 저장할 배열
    static int[][] Mxy = new int[30][30]; //운석 좌표, 워프 좌표// 없으면 -1 / 운석은 -2 / 이후에 0 이상부터는 워프의 wp 인덱스
    static int INF = 100000000;
    static List<Point> wp = new ArrayList<>();
    static String result = "";
    static Point point;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("C:/Users/SDS/git/algorithmStudy/input_txt/sds/inter.txt"));

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            //초기화
            tempX = -1;
            tempY = -1;
            result = "";
            wp.clear();
            for(int i=0; i<30; i++){
                for(int j=0; j<30; j++){
                    dCost[i][j] = INF;
                    Mxy[i][j]  = -1;
                }
            }
            dCost[0][0] = 0; //출발점이니까 0

            xSize = sc.nextInt();
            ySize = sc.nextInt();
            Mc = sc.nextInt();
            if(Mc !=0 ){
                for(int k=0; k<Mc; k++){
                    Mxy[sc.nextInt()][sc.nextInt()] = -2; //해당좌표에 운석이 있으면 -2
                }
            }
            Wc = sc.nextInt();
            if(Wc !=0 ){
                for(int m=0; m<Wc; m++){
                    Mxy[sc.nextInt()][sc.nextInt()] = m;
                    wp.add(new Point(sc.nextInt(),sc.nextInt(),sc.nextInt()));
                }
            }
            for(int ab = 0; ab<xSize*ySize; ab++) {
                for (int a = 0; a < xSize; a++) {
                    for (int b = 0; b < ySize-1; b++) {
                        //운석인 경우
                        if (Mxy[a][b] == -2) continue;
                        //워프인 경우
                        if (Mxy[a][b] >= 0) {
                            point = wp.get(Mxy[a][b]);

                            if (Mxy[point.x][point.y] >= 0 && point.t < 0) {
                                result = "mininf";
                                break;
                            }

                            if (dCost[point.x][point.y] > dCost[a][b] + point.t) {
                                dCost[point.x][point.y] = dCost[a][b] + point.t;
                            }
//                            tempX = a;
//                            tempY = b;
//                            a = point.x;
//                            b = point.y;
                        }

                        if(Mxy[a][b] == -1) {
                            for (int c = 0; c < 4; c++) {
                                Xxx = a + xx[c];
                                Yyy = b + yy[c];
                                if ((Xxx >= 0 && Xxx < xSize && Yyy >= 0 && Yyy < ySize) && Mxy[Xxx][Yyy] != -2 && dCost[Xxx][Yyy] > dCost[a][b] + dCoust) {
                                    dCost[Xxx][Yyy] = dCost[a][b] + dCoust;
                                    if (ab == xSize * ySize - 1) result = "mininf";
                                    break;
                                }
                            }
                        }

//                        if (tempX != -1) {
//                            a = tempX;
//                            b = tempY;
//                            tempX = -1;
//                            tempY = -1;
//                        }
                    }
                }
            }

            //mininf 가 아닌 경우이면서, 탈출할 방법이 없는 경우.
            if(result!="mininf"){
                if(dCost[xSize-1][ySize-1]==INF){
                    result = "noway";
                }else {
                    result = dCost[xSize-1][ySize-1]+"";
                }
            }
            System.out.println("#"+test_case+" "+result);
        }
    }

    static class Point{
        int x,y,t = 0;
        Point(int ix, int iy,int it){
            x = ix;
            y = iy;
            t = it;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public int getT() {
            return t;
        }
    }
}
