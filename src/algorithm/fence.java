package algorithm;
import java.io.FileInputStream;
import java.util.Scanner;

//울타리 잘라내기
//3
//7
//7 1 5 9 6 7 3
//7
//1 4 4 4 4 1 1
//4
//1 8 2 2

//20 16 8
public class fence {
	
	static int T, N; //T는 전체 테스트 케이스의 수, N은 판자의 갯수
	static int[] A;
	static long result; //결과값.

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/examples/1_3_울타리잘라내기/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			A = new int[N];
			
			for(int i=0; i<N; i++){
				A[i] = sc.nextInt();
			}
			
			int rrr = maxSize(0, N-1);

			System.out.println(rrr);
		}
	}
	
	public static int maxSize(int L, int R ){
		
		int maxValue = 0;
		
		//하나밖에 없는 경우.
		if(L==R){
			return A[L];
		}
		
		//mid를 구하고 좌측, 우측을 비교 한다.
		int mid = (L+R)/2;
		
		maxValue = Math.max(maxSize(L, mid), maxSize(mid+1, R));
		
		//그 다음에 mid를 기준으로 하나씩 좌우로 증가시키면서 최대값을 비교한다. 이때 좌우를 선택하는 기준은 더 큰 값을 기준으로..
		
		int tmpL = mid;
		int tmpR = mid+1;
		
		int hight = Math.min(A[mid], A[mid+1]);
		
		maxValue = Math.max(maxValue, hight*2);
		
		while(L < tmpL || tmpR < R){
			
			
			if(tmpL > L && (tmpR == N-1 || (A[tmpL-1] > A[tmpR+1]))){
				--tmpL;
				hight = Math.min(hight, A[tmpL]);
				
				
			}else{
				++tmpR;
				hight = Math.min(hight, A[tmpR]);

				
			}
			
			maxValue = Math.max(maxValue, (tmpR-tmpL+1)*hight);
		}
		
		return maxValue;
	}
}
