package algorithm.sds;

public class perm {

	static int N = 4;
	static int[] sol = new int[N];
	
	// 현재 사용중인 숫자를 표시하는 벡터
	static int[] perm = new int[N];
	
	public static void main(String args[]) {
		backtrack(0);
	}
	
	public static void backtrack(int n) {
		
		if(isSolution(n)){
			processSolution();
			
			return;
		}
		
		for(int i=0; i<N; i++){
			if(perm[i] == 0){
				
				sol[n] = i+1; //실제 숫자가 저장되어야 하므로.+1
				
				perm[i] = 1; //해당 숫자가 사용되었음으로 표시.
				
				backtrack(n+1);
				
				perm[i] = 0; //벡터의 사용중 표시 해제
				
			}
			
		}
		
	}
	
	public static boolean isSolution(int n){
		
		return (n==N);
	}
	
	// 솔루션 처리 메소드
	public static void processSolution() {
		for(int i = 0; i < N; i++) {
			System.out.printf("%d ", sol[i]);
		}
		System.out.println();
	}
}
