package algorithm.sds;
import java.io.FileInputStream;
import java.util.Scanner;

// 제품의 일련번호
public class productSerialNumber {
	
	static int T, N; //T는 전체 테스트 케이스의 수, N은 일련번호에 사용되는 알파벳의 갯수
	static String A, B; //A는 첫번재 일련번호, B는 두번째 일련번호
	static long result; //결과값. 문제에서 64비트 정수형을 언급했으므로 long 타입으로 선언

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/input_txt/sds/productSerialNumber_input.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			A = sc.next();
			B = sc.next();
			
			//주어진 일련번호 A B 중에 어느 값이 더 나중에 생산되었는지 알 수 없기 때문에 Math.abs 함수를 활용해서 절대값을 구한다.
			result = Math.abs(returnSerialNumber(A) - returnSerialNumber(B))-1; //두 제품사이의 생산된 제품 수 이므로 자기자신을 추가로 빼야함.

			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static long returnSerialNumber(String serial){
		
		long serialNumber = 0; //입력받은 문자열의 제품 생산번호.
		int[] abc = new int[N]; //사용한 알파벳을 체크 할 배열.

		for(int i = 0; i < N; i++){
			abc[i] = 0; // i는  문자-'a' 의 정수형 형변환 값을 의미. 즉, a=0, b=1 ... 사용하지 않은 알파벳의 값을 0으로 입력 
		}
		
		for(int i = 0; i < N; i++){
			
			int charNum = (int)(serial.charAt(i)- 'a');
			
			int count = 0; //이미 사용한 알파벳의 갯수를 계산. 
			for(int j=0; j<charNum; j++){ //동일 알파벳이 두번 나올 수 없으므로, for 문에서 charNum 전까지만 검사 
				if(abc[j] == 0){
					count++;
				}
			}
			
			//count는 charNum 보다 앞자리의 알파벳중 이미 사용된 알파벳을 제외한 나머지의 갯수.
			serialNumber = serialNumber + count * factorial(N-i-1);
			
			abc[charNum] = 1; //사용되었으므로 값으로 1을 입력;
		}
		
		return serialNumber;
	}
	
	public static long factorial(long val){
		
		if(val == 0) return 1;
		return val * factorial(val-1);
	}
}
