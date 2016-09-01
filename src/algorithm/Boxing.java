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
public class Boxing {
	
	static int T, TC, N; //T는 전체 테스트 케이스의 수, TC는 각 테스트케이스의 총 장난감 갯수, N은 각 테스트케이스의 장난감 종류 갯수
	// N 은 1부터 N 까지의 자연수.
	static int[] toyArray = new int[1000];
	static int[][] boxingCost = new int[1000][1000]; //장난감들의 순서에 따라 각각 포장한 비용을 저장하는 배열.
	static HashSet<Integer> initSet = new HashSet<Integer>();
	
	public static void main(String[] args) throws Exception {
		// Start time
		//long startTime = System.nanoTime();
				
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
			for(int i = 0; i<TC; i++){
				toyNum = sc.nextInt();
				toyArray[i] = toyNum;
				
				if(i!=0){
					if(toyArray[i-1] != toyArray[i]){
						boxingCost[i-1][1] = 2;
					}else{
						boxingCost[i-1][1] = 1;
					}
				}
				
				if(i > 1){
					if((toyArray[i-2] != toyArray[i-1]) && (toyArray[i-1] != toyArray[i])){
						boxingCost[i-2][2] = 3;
					}
				}
				
			}
			
			boxingCost[0][TC-1] = TC;
			
			//boxingCost를 반복문으로 모든 경우의 수를 계산해서 최소비용을 찾는다.
			if(TC != N){ 
				repeatBoxing(0, 0, TC-1, TC-1, 0);
			}

			System.out.println("#"+test_case+" "+boxingCost[0][TC-1]);
			
		}
		
		// End time
		//long endTime = System.nanoTime();
				
		// Total times
		//long lTime = endTime - startTime;
		//System.out.println("TIME : " + lTime/1000000.0 + "(ms)");
	}
	
	public static void repeatBoxing(int originStart, int start, int originEndIndex, int endIndex, int sectionCost) {
		
		if(sectionCost >= boxingCost[originStart][originEndIndex]){
			return;
		}
		
		if(sectionCost != 0 && boxingCost[start][endIndex] != -1){ //sectionCost==0 이면 최초. 최초에는 비교 하지 않는다.
			//이미 값이 있다면.
			
			if(sectionCost+boxingCost[start][endIndex] < boxingCost[originStart][originEndIndex]){
				boxingCost[originStart][originEndIndex] = sectionCost+boxingCost[start][endIndex];
			}
			
			return;	
		}	

		for (int k = 0; k <= endIndex; k++) {
			
			if (boxingCost[start][k] == -1) {
				// 해당 구간에 대해 최소값 구하는 계산 시작.
				
				boolean pass = false;
				if(k< 20){
					for(int p=0; p<=k; p++){
						initSet.add(toyArray[start+p]);
					}
					
					if(initSet.size() == 1){
						boxingCost[start][k] = 1; //boxingCost[start][k] 구간의 최소값을 구함.
						pass = true;
					}else if((initSet.size() * initSet.size()) < start+k ){
						boxingCost[start][k] = initSet.size() * initSet.size(); //해당 구간의 비용을 디폴트로 셋팅.(디폴트는 해당 구간 장난감 전체의 수)
					}else{
						boxingCost[start][k] = k+1; //해당 구간의 비용을 디폴트로 셋팅.(디폴트는 해당 구간 장난감 전체의 수)
					}
					initSet.clear();
				}else{
					boxingCost[start][k] = k+1;
				}
					
				if(!pass){
					repeatBoxing(start, start, k, k, 0);
				}
			}
			
			repeatBoxing(originStart, k+start+1, originEndIndex, endIndex, sectionCost+boxingCost[start][k]);
		}
	}
}
