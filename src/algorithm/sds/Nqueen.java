package algorithm.sds;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

/*
1
0
92
 */
public class Nqueen {
	static int T, N; //T는 전체 테스트 케이스의 수
	static int[] col = new int[12]; //이미 사용한 col .문제에서 최대 12 칸이라고 제시 됨.
	static int[] sol = new int[12]; //결과
	
	static int queenCnt = 0;
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/input_txt/sds/nqueen.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++){
			N = sc.nextInt();
			
			for(int i = 0; i< N; i++){
				col[i] = 0;
				sol[i] = 0;
			}
			queenCnt = 0;
			backtrack(0);
			System.out.println(queenCnt);
		}
	}
	
	public static void backtrack(int row) {
		
		if(isSolution(row)){
			processSolution();
			
			return;
		}
		
		loop:for(int i=0; i<N; i++){
			if(col[i] == 1){ //이미 해당 row 에 퀸이 있다면 열 가지치기
				continue loop;
			}
			
			//대각선 방향의 퀸이 있는 지 확인 있다면 가지치기
			for(int j=0; j<N; j++){
				if(row-j == i-col[j]){
					
				}
			}
			
			backtrack(i+1);
		}
	}
	
	public static boolean isSolution(int row){
		
		return (row==N);
	}
	
	public static void processSolution(){
		queenCnt++;
	}
}
