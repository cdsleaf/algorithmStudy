package algorithm;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//장난감 박스 포장하기.
/*
 * #1 4
 * #2 5
 * #3 9
 * #4 21
 * #5 31
 * #6 29
 */
public class Boxing {
	
	static int T, TC, N; //T는 전체 테스트 케이스의 수, TC는 각 테스트케이스의 총 장난감 갯수, N은 각 테스트케이스의 장난감 종류 갯수
	// N 은 1부터 N 까지의 자연수.
	static int[] toyArray = new int[1000];
	static int[][] boxingCost = new int[1000][20]; //장난감들의 순서에 따라 각각 포장한 비용을 저장하는 배열. 두번째 배열크기는 박스당 최대 20 이므로 첫번째 배열에서 최대 20개까지만 경우의 수 비용을 저장.
	static int sectionResult = 0; //각 구간별 최소값을 구할 때 최소 비용 
	static Set<Integer> toyTypeSet = new  HashSet<Integer>(); //각 구간별 장난감 종류.
	static int lastSectionToyType = 0;
	static int sectionEnd = 0;
	static int result = 0; //각각의 테스트케이스 별 장난감 포장 최소 비용

	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:/input/boxing.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		//boxingCost 초기화. 초기화 값은 -1
		for(int boxingCostA = 0; boxingCostA<1000; boxingCostA++){
			for(int boxingCostB = 0; boxingCostB<19; boxingCostB++){
				
				if(boxingCostB == 0) {
					boxingCost[boxingCostA][boxingCostB] = 1;
				}else{
					boxingCost[boxingCostA][boxingCostB] = -1;
				}
			}
		}

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			TC = sc.nextInt();
			N = sc.nextInt();
			
			int toyNum =0;
			int firstToy = 0;
			int firstIndex = 0;
			for(int i = 0; i<TC; i++){
				toyNum = sc.nextInt();
				toyArray[i] = toyNum;
				
				if(i==0){
					firstToy = toyNum;
					firstIndex = 0;
					result = 1;
				}

				if(i!=0){
					if(firstToy != toyNum){
						boxingCost[firstIndex][i-1-firstIndex] = 1;
						firstIndex = i;
						firstToy = toyNum;
						result++;
					}
				}	
			}
			//여기까지 toyArray 셋팅하고 전체 최소비용 최초값 계산완료
			
			//boxingCost를 반복문으로 모든 경우의 수를 계산해서 최소비용을 찾는다.
			repeatBoxing(0, 1);	

			System.out.println("#"+test_case+" "+result);
			break; //임시 테스트용 break.
		}
	}
	
	public static void repeatBoxing(int n, int t){
		//박싱 비용 계산 중, 현재까지의 최소비용 - result 보다 같거나 커질 경우 중단. 이전 으로 되돌아 간다.
		if(t >= result){
			return;
		}else if(n > TC -1){
			result = t; //최소값 갱신.
			return;
		}
		
		for(int k=0; k<20 ;k++){
			
			if(n+k > TC-1){
				break;
			}
			
			if(boxingCost[n][k] == -1){
				//해당 구간에 대해 최소값 구하는 계산 시작.
				sectionResult = -1;
				toyTypeSet.clear();
				lastSectionToyType = toyArray[n];
				sectionEnd = n+k;
				calculateSection(n, n+k,1);
				
				boxingCost[n][k] = sectionResult;
			}
			
			//이미 계산된 최소비용이 있으면 그대로 사용.

			repeatBoxing(k+n+1, t+boxingCost[n][k]);
		}
	}
	
	public static void calculateSection(int start, int end, int sectionCost){
		
		if(start > sectionEnd){
			if(sectionResult == -1){
				for(int m=start; m<=end; m++){
					toyTypeSet.add(toyArray[m]);
				}
				
				int totalSet = toyTypeSet.size();
				
				if( (totalSet*totalSet) <sectionResult){
					sectionResult = sectionCost;
				}
			}
			
			if(sectionCost < sectionResult){
				sectionResult = sectionCost;
			}
			//가장 최소값을 비용 배열에 저장한다.
			boxingCost[start][end-start] = sectionResult;
			return;
		}
		
		if(boxingCost[start][end-start] != -1){
			//값을 알면 !!
			sectionResult = boxingCost[start][end-start];
			
		}else{
			//값을 모르면? 찾자.
			for(int m=0;m<=end-start; m++){
				calculateSection(start+m+1, end ,sectionCost+boxingCost[start][m]);
			}
		}
		
		
	}
}
