package algorithm.sds;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

// 0 1 2 3 4 0 6
public class Tower {
	static int T; //T는 전체 테스트 케이스의 수
	static long result; //결과값. 문제에서 64비트 정수형을 언급했으므로 long 타입으로 선언

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/input_txt/sds/tower.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int[] towerArray = new int[T+1];
		int[] resultArray = new int[T+1];
		Stack<Integer> towerStack = new Stack<Integer>();

		for(int i = 1; i < T+1; i++)
		{
			towerArray[i] = sc.nextInt();			
		}
		
		for(int j = T; j > 0; j--){
			if(j==1){
				resultArray[1] = 0;
				
			}else if(towerArray[j] <= towerArray[j-1]){
				resultArray[j] = j-1;
				if(!towerStack.isEmpty()){
					while(true){
						if(towerArray[towerStack.peek()] <= towerArray[j-1]){
							resultArray[towerStack.pop()] = j-1;
						}else{
							break;
						}
						
						if(towerStack.isEmpty()){
							break;
						}
					}
				}
			}else{
				towerStack.push(j);
				resultArray[j] = 0;
			}
		}
		
		System.out.print(resultArray[1]);
		for(int k=2; k<T+1; k++){
			System.out.print(" "+resultArray[k]);
		}
	}
}
