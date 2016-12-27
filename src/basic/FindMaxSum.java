package basic;

public class FindMaxSum {
	
	static final int input[] = {-7, 4, -3, 6, 3, -8, 3, 4};
	static final int MIN = Integer.MIN_VALUE;
	static int r = 0;
	
	public static void main(String[] args) {

		r = inefficientMaxSum(input); // O(n^3)
		r = betterMaxSum(input); // O(n^2)
		r = fastMaxSum(input, 0, input.length-1); //O(nlgn)
		r = fastestMaxSum(input); // O(n)
		
		System.out.println(r);	
	}
	
	public static int inefficientMaxSum(int[] list){
		
		int N = list.length, result = MIN;
		
		for(int i=0; i<N; i++){
			for(int j=i; j<N; j++){
				int sum=0;
				for(int k=i; k<=j; k++) sum+=list[k];
				result = Math.max(sum, result);
			}
		}
		
		return result;
	}
	
	public static int betterMaxSum(int[] list){
		
		int N = list.length, result = MIN;
		
		for(int i=0; i<N; i++){
			int sum=0;
			for(int j=i; j<N; j++){
				sum+=list[j];
				result = Math.max(sum, result);
			}
		}
		
		return result;
	}
	
	public static int fastMaxSum(int[] list, int start, int end){
		
		if(start == end) return list[start];
	
		int mid = (start + end) / 2;
		int left = MIN, right = MIN, sum = 0;
		
		for(int i=mid; i>=start; i--){
			sum += list[i];
			left = Math.max(left, sum);
		}
		
		sum = 0;
		
		for(int j=mid+1; j<=end; j++){
			sum += list[j];
			right = Math.max(right, sum);
		}
		
		int single = Math.max(fastMaxSum(list, start, mid), fastMaxSum(list, mid+1, end));
		
		return Math.max(left+right, single);
	}
	
	public static int fastestMaxSum(int[] list){
		
		int N = list.length, result=MIN, savedMax = 0;
		
		for(int i=0; i<N; i++){
			savedMax = Math.max(savedMax, 0) + list[i];
			result = Math.max(savedMax, result);
		}
		return result;
	}
}
