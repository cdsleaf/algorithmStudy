package algorithm.etc.pro;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//Template 
/*
 * #1 8
 * #2 9
 * #3 29
 * #4 19
 */
public class Perfume implements Comparator<Integer>{
	
	static int T, N; //T는 전체 테스트 케이스의 수, N은 오일의 갯수.
	static List<Integer> serialNumList = new ArrayList<Integer>(1000);
	static int bestBefore[] = new int[1000], duration[] = new int[1000], maxDuration[] = new int[1000]; //일련번호, 유통기한, 지속시간, 각 구간별 최대지속시간
	static int result; //결과값. 

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:/Users/cho/git/algorithmStudy/input_txt/sds/perfume.txt"));

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			serialNumList.clear(); //일련번호를 저장할 List 객체를 초기화 한다.
			N = sc.nextInt();
			
			for(int i=0; i<N; i++){
				serialNumList.add(i);
				bestBefore[i] = sc.nextInt();
				duration[i] = sc.nextInt();
				maxDuration[i] = -1;
			}
			result = maxDuration[N-1] = duration[N-1];
			
			//serialNum을 bestBefore 값을 기준으로 정렬한다. 정렬은 오름차순으로 한다.
			Collections.sort(serialNumList, new Perfume());
			
			//정렬된 serialNumList 에서 뒤에서부터 순서대로 각 시작점에서의 최대값을 구한 후 maxDuration 에 담아 둔다. maxDuration가 DP의 핵심.
			//각 계산된 최대값중에 가장 큰 값을 result에 담아두고 계산 비교해서 result를 갱신한다.
			//findMax(N-2); //N-1은 이미 알고 있으므로 N-2 부터 시작..

			System.out.println("#"+test_case+" "+result);
		}
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		return bestBefore[o1]-bestBefore[o2];
	}
	
	public static void findMax(int n, int d){
		
		if(n < 0) return;
		if(maxDuration[n] != -1){
			
		}

		
		//findMax(n--);
	}
}
