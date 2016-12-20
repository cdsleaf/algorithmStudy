package algorithm.etc;

import java.io.FileInputStream;
import java.math.BigInteger;
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
	static int originMax; //2의 (N+1) 승 -1 값.
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("C:/input/maxHeap.txt"));
		
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		
		for(int i=0; i<T; i++){
			
			N = sc.nextInt();
			
			//가장 큰 값은..
			originMax = (int)Math.pow(2, N+1)-1;
			
			System.out.println("#"+(i+1)+" "+recurMaxHeap(originMax).remainder(BigInteger.valueOf(100000123)));
		}
		
	}
	
	public static BigInteger recurMaxHeap(int max){
		 
		if(max<=2) return BigInteger.valueOf(1);

		int sideCount = (max-1)/2;
		
		BigInteger upper = factorial(BigInteger.valueOf(max-1), BigInteger.valueOf(sideCount+1));
		BigInteger lower = factorial(BigInteger.valueOf(sideCount), BigInteger.valueOf(1));
		//System.out.println(upper+" "+lower);
		return (upper.divide(lower)).multiply(recurMaxHeap((max-1)/2)).multiply(recurMaxHeap((max-1)/2));
		
	}
	
	public static BigInteger factorial( BigInteger n, BigInteger end){
		
		if(n.equals(end)){
			return n;
		}else{			

			return n.multiply(factorial(n.subtract(BigInteger.valueOf(1)) , end));
		}
	}
}
