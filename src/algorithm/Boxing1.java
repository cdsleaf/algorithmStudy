package algorithm;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;

//장난감 박스 포장하기.
/*
 * #1 4
 * #2 5
 * #3 9
 * #4 21
 * #5 31
 * #6 29
 * #7 41
 * #8 50
 * #9 43
 * #10 50
 */
public class Boxing1 {
	
	static int T, TC, N; //T는 전체 테스트 케이스의 수, TC는 각 테스트케이스의 총 장난감 갯수, N은 각 테스트케이스의 장난감 종류 갯수
	// N 은 1부터 N 까지의 자연수.
	static int[] toyArray = new int[1000];
	static int[][] boxingCost = new int[1000][1000]; //장난감들의 순서에 따라 각각 포장한 비용을 저장하는 배열.
	static int result = 0; //각각의 테스트케이스 별 장난감 포장 최소 비용
	
	public static void main(String[] args) throws Exception {

		//boxingCost 초기화. 초기화 값은 -1
//		for(int boxingCostA = 0; boxingCostA<1000; boxingCostA++){
//			for(int boxingCostB = 0; boxingCostB<1000; boxingCostB++){
//				
//				if(boxingCostB == 0) {
//					boxingCost[boxingCostA][boxingCostB] = 1;
//				}else{
//					boxingCost[boxingCostA][boxingCostB] = -1;
//				}
//			}
//		}
		
		System.setIn(new FileInputStream("C:/input/boxing.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			TC = sc.nextInt();
			N = sc.nextInt();
			
			//boxingCost 초기화. 초기화 값은 -1
			for(int boxingCostA = 0; boxingCostA<TC; boxingCostA++){
				for(int boxingCostB = 0; boxingCostB<TC; boxingCostB++){
					
					if(boxingCostB == 0) {
						boxingCost[boxingCostA][boxingCostB] = 1;
					}else{
						boxingCost[boxingCostA][boxingCostB] = -1;
					}
				}
			}
			
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
						boxingCost[firstIndex][i-1-firstIndex] = 1; //i-1-firstIndex 여기에서 -1 하는 건 직전까지로 계산해야하기 때문.
						firstIndex = i;
						firstToy = toyNum;
						result++;
					}
					
					if(toyArray[i-1] != toyArray[i]){
						boxingCost[i-1][1] = 2;
					}else{
						boxingCost[i-1][1] = 1;
					}
				}	
			}
			//여기까지 toyArray 셋팅하고 전체 최소비용 최초값 계산완료
			
			//아래 코드는 임시 테스트용 
//			for(int boxingCostA = 0; boxingCostA<1000; boxingCostA++){
//				for(int boxingCostB = 0; boxingCostB<19; boxingCostB++){
//					
//					if(boxingCost[boxingCostA][boxingCostB] != -1) {
//						System.out.println("boxingCost["+boxingCostA+"]["+boxingCostB+"] = "+boxingCost[boxingCostA][boxingCostB]);
//					}
//				}
//			}
			
			//boxingCost를 반복문으로 모든 경우의 수를 계산해서 최소비용을 찾는다.
			repeatBoxing(0, 0);	

			System.out.println("#"+test_case+" "+result);
			//break; //임시 테스트용 break.
//			if(test_case == 2){
//				break;
//			}
		}
	}
	
	public static void repeatBoxing(int n, int t) {
		// 박싱 비용 계산 중, 현재까지의 최소비용 - result 보다 같거나 커질 경우 중단. 이전 으로 되돌아 간다.
		if (t >= result) {
			return;
		} else if (n == TC - 1) {
			if(t+1 < result){
				result = t+1; // 최소값 갱신.
			}
			return;
		}else if(n> TC-1){
			if(t < result){
				result = t; // 최소값 갱신.
			}
			return;
		}

		for (int k = 0; k < 20; k++) {

			if (n + k > TC - 1) {
				break;
			}
			//동일한 장난감이 붙어 있다면 하나의 묶음으로 처리해서 건너뛴다.
			if(k != 19 && toyArray[n+k] == toyArray[n+k+1]){
				continue;
			}
			
			if (boxingCost[n][k] == -1) {
				// 해당 구간에 대해 최소값 구하는 계산 시작.
				HashSet initSet = new HashSet();
				boxingCost[n][k] = k+1; //해당 구간의 비용을 디폴트로 셋팅.(디폴트는 해당 구간 장난감 전체의 수)
				calculateSection(n, k, n, k, 0, initSet);
			}

			repeatBoxing(k+n+1, t+boxingCost[n][k]);
		}
	}
	
	//인자값은 원 시작점, 원 마지막 인덱스, 시작점, 마지막 인덱스, 현재까지의 누적 비용, originStart과 endIndex 구간에 대한 현재까지의 최소비용 
	public static void calculateSection(int originStart, int originEndIndex, int start, int endIndex, int sectionCost, HashSet toySet){
		//해당 구간의 최소비용을 산출한다. start => 1 이고 endindex가 18 이라면, 1~19 사이 구간의 최소비용 계산
		//여기서 구하는건 최대 20개가 있는 구간임.
		// x, 1 은 이미 값이 구해져 있으므로, 가장 처음은 x, 2 (즉, 3개 집합)
		
		if(endIndex == 0){
			//마지막 까지 왔다면. 지금까지의 누적된 비용+1 이게 마지막까지 도달한 이번 케이스의 비용.
			//이제 최소비용값과 비교해서 최소비용값을 갱신처리.  boxingCost[originStart][start+endIndex-1] 이게 원래 구하려는 구간의 최소비용
			toySet.add(toyArray[start]);
			
			if((toySet.size() * toySet.size()) < boxingCost[originStart][originEndIndex]){
				boxingCost[originStart][originEndIndex] = (toySet.size() * toySet.size());
			}
			
			if(sectionCost+1 < boxingCost[originStart][originEndIndex]){
				boxingCost[originStart][originEndIndex] = sectionCost+1;
			}
			
			return;
			
		}else if(sectionCost != 0 && boxingCost[start][endIndex] != -1){ //sectionCost==0 이면 최초. 최초에는 비교 하지 않는다.
			//이미 값이 있다면.
			
			if(sectionCost+boxingCost[start][endIndex] < boxingCost[originStart][originEndIndex]){
				boxingCost[originStart][originEndIndex] = sectionCost+boxingCost[start][endIndex];
			}
			
			return;	
		}	

		for(int l=0; l<endIndex; l++){
			if(boxingCost[start][l] == -1) {
				// 해당 구간에 대해 최소값 구하는 계산 시작.
				HashSet initSet = new HashSet();
				boxingCost[start][l] = start+l; //해당 구간의 비용을 디폴트로 셋팅.(디폴트는 해당 구간 장난감 전체의 수)
				calculateSection(start, l, start, l, 0, initSet);
			}
			toySet.add(toyArray[start+l]);
			calculateSection(originStart, originEndIndex, start+l+1, endIndex-l-1, sectionCost+boxingCost[start][l], toySet);
		}
		
	}
}
