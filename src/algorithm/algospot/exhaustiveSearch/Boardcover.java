package algorithm.algospot.exhaustiveSearch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/*

3 
3 7 
#.....# 
#.....# 
##...## 
3 7 
#.....# 
#.....# 
##..### 
8 10 
########## 
#........# 
#........# 
#........# 
#........# 
#........# 
#........# 
########## 
------------------------
0
2
1514

 */

public class Boardcover {

	static int T, //전체 테스트 케이스
			H, W, wc=0, sum, //게임판의 크기. 높이 H 넓이 W 각 경우의 수 sum, 흰칸의 수 wc
			check[][][] = {
					{{0,0},{1,0}, {0,1}},
					{{0,0},{0,1}, {1,1}},
					{{0,0},{1,0}, {1,1}},
					{{0,0},{1,0}, {1,-1}}
					}; //각 좌표에서 xy으로 한칸씩 확장 시킬 때 사용할 경우의 수 배열.
	static char board[][], inputChar; //게임판
	static String inputLine;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./input_txt/algospot/boardcover.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc=0;tc<T;tc++){
			
			sum = 0; wc=0;
			H = sc.nextInt(); W = sc.nextInt();
			board = new char[H][W];
			
			for(int yp=0; yp<H; yp++){
				inputLine = sc.next();
				for(int xp=0; xp<W; xp++){
					inputChar = inputLine.charAt(xp);
					if(inputChar == '.'){
						board[yp][xp] = 0;
						wc++;
					}else{
						board[yp][xp] = 1;
					}
				}
			}
			if(wc !=0) sum = cover();
			else sum = 1;
			
			System.out.println(sum);
		}
		
	}
	
	public static boolean set(int y, int x, int type, int delta){
		boolean ok = true;
		for(int i=0; i<3; i++){
			int ny = y+check[type][i][0];
			int nx = x+check[type][i][1];
			if(ny<0 || ny >=H || nx<0 ||nx>=W){
				ok = false;
			}else if((board[ny][nx] += delta)>1) ok = false;
			
		}
		
		return ok;
	}
	
	public static int cover(){
		
		int y =-1, x=-1;
		for(int i=0; i<H; i++){
			for(int j=0; j<W; j++){
				if(board[i][j] == 0){
					y = i;
					x = j;
					break;
				}
				if(y != -1) break;
			}
			
		}
		
		if(y==-1) return 1;
		int ret=0;
		for(int type=0; type<4; type++){
			
			if(set(y, x,type, 1)) ret +=cover();
			set(y,x,type, -1);
		}
		
		return ret;
	}
}
