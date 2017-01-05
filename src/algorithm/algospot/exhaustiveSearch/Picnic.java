package algorithm.algospot.exhaustiveSearch;

import java.io.FileInputStream;
import java.util.Scanner;

/*
3 
2 1 
0 1 
4 6 
0 1 1 2 2 3 3 0 0 2 1 3 
6 10 
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5

출력
1
3
4
 */
public class Picnic {
	
	static int T, //전체 테스트 케이스 수 
			N, //각 케이스 별 학생 수
			fc, //친구 쌍의 수
			fList[], //친구 쌍 배열
			used[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //모든 테스트케이스에서 최대 학생는 10명
			usedCount,
			result; //가능한 조합의 수.

	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("./input_txt/algospot/PICNIC.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int testCase = 0; testCase<T; testCase++){
			
			result = 0; //각 테스트 케이스 별 초기화.
			usedCount = 0;
			for(int k=0; k<10; k++){
				used[k] = 0;
			}
			
			N = sc.nextInt();
			fc = sc.nextInt();
			
			fList = new int[fc*2];
			for(int i=0; i<fc*2; i++){
				fList[i] = sc.nextInt();
			}
			
			searchF(0);
			
			System.out.println(result);
		}		
	}
	
	public static void searchF(int startIndex){
		
		int a,b;
		
		if(usedCount==N){
			result++;
			return;
		}
		
		//먼저 startIndex와 startIndex+1 의 학생이 이미 선택되었는지 체크한다.
		//이미 선택되었다면 startIndex+2 하여 다음 친구쌍을 찾는다.
		for(int j=startIndex; j<fc*2; j+=2){
			a=fList[j];
			b=fList[j+1];		
			if(used[a]==0 && used[b]==0){
				
				used[a] = 1;
				used[b] = 1;
				usedCount+=2;
				
				searchF(j+2);
				
				used[a] = 0;
				used[b] = 0;
				usedCount-=2;
				
			}
		}
	}
}
