package algorithm.algospot.dc;

import java.io.FileInputStream;
import java.util.Scanner;

/*
3
7
7 1 5 9 6 7 3
7
1 4 4 4 4 1 1
4
1 8 2 2
------------
20
16
8
 */

public class Fence {
	
	static int T, fenceCount, result;
	static int[] fenceArray;

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("./input_txt/algospot/fence.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int i=0; i<T; i++){
			
			fenceCount = sc.nextInt();
			fenceArray = new int[fenceCount+1];
			
			for(int j=1; j<=fenceCount; j++){
				fenceArray[j] = sc.nextInt();
			}
			
			result = searching(1, fenceCount);
			
			System.out.println(result);
		}
	}
	
	public static int searching(int start, int end){
		
		if(start == end) return fenceArray[start];
		
		if(end-start == 1){
			if(fenceArray[start] > fenceArray[end]){
				if(fenceArray[start] > fenceArray[end]*2) return fenceArray[start];
				else return fenceArray[end]*2;
			}else{
				if(fenceArray[end] > fenceArray[start]*2) return fenceArray[end];
				else return fenceArray[start]*2;
			}
		}
		
		int mid = (end+start)/2;
		
		int ret = Math.max(searching(start, mid), searching(mid+1, end));
		
		int left = mid-1<start ? start : mid-1;
		int right = mid+1>end ? end : mid+1 ;
		
		boolean checkStart = true,  checkEnd = true;
		int minH = mid;
		
		while(left >= start || right <= end){
			
			if(fenceArray[left] > fenceArray[right]){
				
				if(fenceArray[minH] > fenceArray[left]) minH = left;
				
				ret = Math.max(ret, ((right-left+1) * fenceArray[minH]));
				
				if(left != start) left--;
			}else{
				
				if(fenceArray[minH] > fenceArray[right]) minH = right;
				
				ret = Math.max(ret, ((right-left+1) * fenceArray[minH]));
				if(right != end) right++;
			}
			
			if(left == start && right == end) break;
		}
		
		return ret;
	}
}
