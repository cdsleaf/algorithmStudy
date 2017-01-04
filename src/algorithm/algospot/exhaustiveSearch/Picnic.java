package algorithm.algospot.exhaustiveSearch;

import java.io.FileInputStream;
import java.util.Scanner;

/*
3 
2 1 
0 1 
4 6 
0 1 1 2 2 3 3 0 0 2 1 3 
6 10 
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5

출력
1
3
4
 */
public class Picnic {
	
	static int T, //전체 테스트 케이스 수 
			N; //각 케이스 별 학생 수
			

	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("./input_txt/algospot/PICNIC.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int testCase = 0; testCase<T; testCase++){
			
		}
		
		
		System.out.println();
	}

}
