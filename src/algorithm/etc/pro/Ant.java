package algorithm.etc.pro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
3                                                      ← 3 test cases in total
3 2 10                                              ← 1st case
2 R
4 L
8 R
4 2 100                                            ← 2nd case
15 R
55 L
45 R
99 L
4 3 100                                            ← 3rd case
15 R
55 L
45 R
99 L

출력
#1 1 4
#2 4 55
#3 2 85
 */
public class Ant {

	static class Ants{
		
		int x, num;
		char dir;
		
		public Ants(int x, char dir, int num){ //x 는 초기 위치, dir 은 방향, num 은 개미의 번호
			this.x = x;
			this.dir = dir;
			this.num = num;
		}
	}
	
	
	
	
	
	static int T, //전체 테스트 케이스
			N, K, L, time, ant, antCount, RL; // 개미의 수N, K 번째로 떨어지는 개미 찾기, 선분의 전체 길이 L, 개미가 떨어지는 시각 time, K번째로 떨어지는 개미의 번호., RL 은 간격이 가장 짧은..
	static int antLoc[] = new int[100001]; //각 개미들의 현재 위치를 저장. 배열의 인덱스가 개미의 번호와 일치. 1부터 시작.
	static String antLR[] = new String[100001]; //각 개미들의 방향 LR 저장. 배열의 인텍스가 개미의 번호와 일치.
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Ants abc[] = new Ants[10]; 
		
		abc[0] = new Ants(1,'A', 1);
		
		Arrays.sort(abc, 1, 5, new Comparator<Ants>(){
			public int compare(Ants a, Ants b){
				return a.x-b.x;
			}
		});
		
		System.setIn(new FileInputStream("./input_txt/etc/pro/ant.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc=0;tc<T;tc++){
			
			time = 0;
			RL = 0;
			
			N = sc.nextInt();
			K = sc.nextInt();
			L = sc.nextInt();
			
			for(int i=1; i<=N; i++){
				antLoc[i]=sc.nextInt();
				antLR[i]=sc.next();
				
				if(i > 1 && "R".equals(antLR[i-1]) && "L".equals(antLR[i])){
					if(RL==0){ 
						RL = antLoc[i]-antLoc[i-1]; //최초 입력.
					}else{
						RL = Math.min(RL, antLoc[i]-antLoc[i-1]); //간격이 가장 작은 값을 저장.
					}
				}
				
			}
			
			if(RL==0){

				int first = 0; //현재 좌우측의 가장 끝 점.
				int end = N;
				
				if(antLR[0].equals(antLR[N])){ // RRRR 또는 LLLL 이런 경우임.
					
					antCount = K;
					
					if("L".equals(antLR[0])){
						time = antLoc[K];
					}else{
						time = L-antLoc[N-K];
					}
				}else{ // LL RR 인 경우
					while(true){
						if(antLoc[first] <= L-antLoc[end]){
							ant  = first;
							time = antLoc[first];
							first++;
						}else{
							ant  = end;
							time = L-antLoc[end];
							end--;
						}
						
						antCount++;
						
						if(ant == K) break;
						
						if(antLoc[first] == L-antLoc[end]){
							antCount++;
							if(antCount == K) break;
							end--;
						}
					}
				}

			}else{ //RL 이 0 보다 더 많은 경우.
				while(true){
					
					for(int k=1; k<=N;k++){
						if(k > 1 && "R".equals(antLR[k-1]) && "L".equals(antLR[k])){
							if(RL%2==0){
								antLoc[k] = antLoc[k]-RL;
							}else{
								antLoc[k-1] = antLoc[k-1]+RL-1;
								antLoc[k] = antLoc[k]-RL+1;
							}
							antLR[k-1] = "L";
							antLR[k] = "R";
						}else{
							if("R".equals(antLR[k])){
								antLoc[k] = antLoc[k]+RL;
							}else{
								antLoc[k] = antLoc[k]-RL;
							}
						}
					}
					
				}
			}
			
			
			
			System.out.println("#"+tc+1+" "+ant+" "+time);
		}
	}
}
