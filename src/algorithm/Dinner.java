package algorithm;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//Template 
//#1 1 2
//#2 3 6 5 2
//#3 3 5
//링크드리스트를 활용한 분할정복 방식의 합병정렬 merge sort를 활용 한다.(링크드 리스트는 링크 인덱스만 변경하면 되기 때문)
//먼저 배열로 구현해보고 그담에 링크드 리스트로 구현해보자....
public class Dinner {
	
	static int T, N; //T는 전체 테스트 케이스의 수, N은 모든 식당의 총 갯수.
	static int X[] = new int[200005];
	static int Y[] = new int[200005]; //x,y 좌표값. 최대 200000 개 까지 들어올수 있으니까.
	static int sortX[] = new int[200005]; //x 기준으로 정렬한 결과값 여기의 값은 X의 배열 인덱스.
	static int result[] = new int[200005]; //정답 좌표의 번호 저장할 배열.	

	public static void main(String[] args) throws Exception {

		// Start time
		long startTime = System.nanoTime();
		
		System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/examples/2_1_저녁식사/input.txt"));
		//System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/examples/2_1_저녁식사/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			
			int tempSortIndex = 0;
			for(int i=1; i<=N; i++){
				X[i] = sc.nextInt();
				Y[i] = sc.nextInt();
				sortX[tempSortIndex] = i;
				tempSortIndex++;
			}
			
			//우선X 좌표로 정렬을 한다.
			runMergeSort(sortX, N);
			
			System.out.println("#"+test_case+" ");
			
//			for(int k=0; k<N; k++){
//				System.out.println(X[sortX[k]]);
//			}
			
		}
		
		// End time
		long endTime = System.nanoTime();
		

		// Total times
		long lTime = endTime - startTime;
		System.out.println("TIME : " + lTime/1000000.0 + "(ms)");
	}
	
	public static void runMergeSort(int list[], int n){

		int mid;
		
		if(n == 1){
			return;
		}
		
		mid = n/2;
		
		int[] left = new int[mid];
		int[] right = new int[n-mid];
		
		for(int i=0; i<mid; i++){
			left[i] = list[i];
		}
		
		int k = 0;
		for(int j=mid; j<n; j++){
			right[k] = list[j];
			k++;
		}
		
		runMergeSort(left, mid);
		runMergeSort(right, n-mid);
		
		mergeX(left, right, list);
	}
	
	public static void mergeX(int[] left, int[] right, int[] list){
		
		int a, b, c, d;
		a = 0; //left index
		b = 0; //right index
		c = 0; //list index
		d = 1; //result index -> 마지막 합병정렬 처리 일 때 동작. list.length == N
		
		while(true){
			
			if(a < left.length){
				if(b < right.length){
					if(X[left[a]] < X[right[b]]){
						list[c] = left[a];
						a++;
					}else if(X[left[a]] == X[right[b]]){
						if(Y[left[a]] < Y[right[b]]){
							list[c] = left[a];
							c++;
							list[c] = right[b];
						}else{
							list[c] = right[b];
							c++;
							list[c] = left[a];
						}
						a++;
						b++;
					}else{
						list[c] = right[b];
						b++;
					}
				}else{
					if(a < left.length){
						list[c] = left[a];
						a++;
					}
				}
			}else{
				if(b < right.length){
					list[c] = right[b];
					b++;
				}
			}
			
			//마지막 합병정렬일 때만 동작하는 코드 여기에서 후보군을 추려낸다.
			//runMergeSort 하면서 저녁식사 후보가 될 수 있는 식당을 골라낸다.
//			1. x,y 를 둘다 보다 큰 점은 제외  
//			2. x가 동일할때 y가 큰 점 제외
//			3. y가 동일할때 x가 큰 점 제외
			
			if(list.length == N+11111){
				
				if(c == 0){
					result[0] = list[0]; //X좌표가 가장 작은 값을 입력한다.
				}
				
				for(int k=0; k<d; k++){
//					if((X[result[k]] == X[list[c]] && Y[result[k]] >= Y[list[c]]) || (Y[result[k]] == Y[list[c]] && X[result[k]] >= X[list[c]])){
//						
//						result[d] = list[c];
//						d++;
//						break;
//					}
					
					if(X[result[k]] == X[list[c]] || Y[result[k]] == Y[list[c]]){
						
						
					}
				}
				
				
			}
			
			c++;
			
			if(list.length == c){
				break;
			}
		}
	}
}
