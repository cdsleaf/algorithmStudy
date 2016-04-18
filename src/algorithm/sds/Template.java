package algorithm.sds;
import java.io.FileInputStream;
import java.util.Scanner;

//Template 
public class Template {
	
	static int T, N; //T는 전체 테스트 케이스의 수, N은 일련번호에 사용되는 알파벳의 갯수
	static String A, B; //A는 첫번재 일련번호, B는 두번째 일련번호
	static long result; //결과값. 문제에서 64비트 정수형을 언급했으므로 long 타입으로 선언

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:/input/input.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			A = sc.next();
			B = sc.next();
			
			/*
			 * 이 부분에 알고리즘을 구현한다.
			 * 
			 */

			System.out.println("#"+test_case+" "+result);
		}
	}
}
