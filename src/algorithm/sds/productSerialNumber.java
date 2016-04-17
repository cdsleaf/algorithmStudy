package algorithm.sds;

import java.io.FileInputStream;
import java.util.Scanner;

public class productSerialNumber {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/input_txt/sds/productSerialNumber_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		//표준입력 System.in 으로 부터 스캐너를 만들어 데이터를 읽어옵니다.
		System.out.println("#"+sc.nextInt());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			/*
						각 테스트 케이스를 표준 입력에서 읽어옵니다.
			 */

			int N = sc.nextInt();

			char[][] map = new char[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					String tmp = sc.next();
					map[i][j] = tmp.charAt(0);
				}
			}




		//	System.out.println("#"+test_case+" "+AnswerN);
		}
	}
}
