package algorithm.etc;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Scanner;

/*
(입력)
2
1
2
(출력)
#1 2
#2 80

1부터 (2의 (n+1) 승) -1
높이가 (N+1)인 최대 힙의 개수를 100,000,123으로 나눈 나머지 
 */
public class maxHeap {

	static int T, N; //T 는 전체 케이스 수, N은 11이하의 음이 아닌 정수
	static double originMax; //2의 (N+1) 승 -1 값.
	static double result = 0; //최대 힙의 총 경우의 수.
	static int layerCount = 1;
	static LinkedList<Integer> arr  = new LinkedList<Integer>() ;
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("C:/input/maxHeap.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int i=0; i<T; i++){
			
			//초기화 시작
			arr.clear();
			result = 0;
			layerCount = 1;
			//초기화 끝
			
			N = sc.nextInt();
			
			//가장 큰 값은..
			originMax = Math.pow(2, N+1)-1;
				
			for(int j=1; j<originMax; j++){
				arr.add(j);
			}
			
			recurSearchMaxHeap(arr);
			
			System.out.println("#"+i+1+" "+result);
		}
		
	}
	
	public static void recurSearchMaxHeap(LinkedList<Integer> list){
		
		int tempValue;
		
		if(list.size() == 2){
			result = result + 2;
			return;
		}
		
		if(layerCount > N+1){
			return;
		}
		
		int minIndex = (list.size()-1)/2-1; //다음 기준점이 될 수 있는 후보값중 가장 작은 index
		
		for(int k=minIndex; k<list.size()-1; k++){
			tempValue = list.get(k);
			list.remove(k);
			
			layerCount++;
			recurSearchMaxHeap(list);
			layerCount--;
			
			list.add(k, tempValue);
		}
	}
}
