package algorithm;
import java.io.FileInputStream;
import java.util.Scanner;

//Template 
//#1 1 2
//#2 3 6 5 2
//#3 3 5
public class Dinner {
	
	static int T, N; //T는 전체 테스트 케이스의 수, N은 일련번호에 사용되는 알파벳의 갯수
	static int X[] = new int[200005];
	static int Y[] = new int[200005]; //x,y 좌표값. 최대 200000 개 까지 들어올수 있으니까.
	static int sortX[] = new int[200005]; //x 기준으로 정렬한 결과값 여기의 값은 X의 배열 인덱스.
	static int result[] = new int[200005]; //정답 좌표의 번호 저장할 배열.
	
	static int sortLastNum; //버블정렬 시 반복처리의 마지막 배열인덱스
	

	public static void main(String[] args) throws Exception {

		// Start time
		long startTime = System.nanoTime();
		
		System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/examples/2_1_저녁식사/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			
			for(int i=1; i<=N; i++){
				X[i] = sc.nextInt();
				Y[i] = sc.nextInt();
				sortX[i] = i;
			}
			
			//우선X 좌표로 정렬을 한다.
			sortLastNum = N; //최초엔 N으로 설정한다.
			int j=1
			while(true){ //compare_X 에서 j+1로 비교하므로 for문은 sortLastNum-1 까지만 반복.
				
				compare_X(j,j+1);
				
				j++;
			}
			
			

			System.out.println("#"+test_case+" "+result);
		}
		
		// End time
		long endTime = System.nanoTime();
		

		// Total time
		long lTime = endTime - startTime;
		System.out.println("TIME : " + lTime/1000000.0 + "(ms)");
	}
	
	public static void compare_X(int indexA, int indexB){

		if(X[indexA] > X[indexB]){
			sortX[indexB] = indexA;
			sortX[indexA] = indexB;
		}
		
		//마지막까지 오면 sortLastNum를 -1
		if(indexB == sortLastNum){
			sortLastNum = sortLastNum-1;
		}
	}
}
