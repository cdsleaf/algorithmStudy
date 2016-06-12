package algorithm;

import java.util.Scanner;

//쿼드트리
/* 아래는 결과 값.
w
xwbbw
xxbwwbbbw
xxwbxwwxbbwwbwbxwbwwxwwwxbbwb
*/
public class QuadTree {

	static int T; //T는 전체 테스트 케이스의 수
	static String A; //A는 테스트 케이스의 문자열

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/input_txt/sds/QuadTree.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			A = sc.next();

			System.out.println(reverse(A));
		}
	}
	
	public static String reverse(String quad){

		if(quad.charAt(0) != 'x'){
			return quad.charAt(0) + "";
		}

		int beginPoint = 1;
		String upperLeft = reverse(quad.substring(beginPoint));
		
		beginPoint = beginPoint + upperLeft.length();
		String upperRight = reverse(quad.substring(beginPoint));
		
		beginPoint = beginPoint + upperRight.length();
		String lowerLeft = reverse(quad.substring(beginPoint));
		
		beginPoint = beginPoint + lowerLeft.length();
		String lowerRight = reverse(quad.substring(beginPoint));
		
		return 'x'+lowerLeft+lowerRight+upperLeft+upperRight;
	}
}