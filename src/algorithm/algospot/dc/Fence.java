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
		
		int mid, left, right, minH; 
		boolean checkStart, checkEnd;
		
		minH = left = right = mid = (end+start)/2;	
		checkStart = checkEnd = true;
		
		int ret = Math.max(searching(start, mid), searching(mid+1, end));
		
		while(checkStart || checkEnd){
			
			if(!checkStart || !checkEnd){
				
				if(checkStart){
					left--;
					if(left == start) checkStart  = false;
				}else{
					right++;
					if(right == end) checkEnd  = false;
				}
			}else{
				//양쪽 모두 확장 가능한 경우.
				if(fenceArray[left-1] > fenceArray[right+1]){
					left--;
					if(left == start) checkStart  = false;
				}else{
					right++;
					if(right == end) checkEnd  = false;
				}
			}
			
			minH = getMinIndex(left, minH, right);
			ret = Math.max(ret, ((right-left+1) * fenceArray[minH]));
		}
		
		return ret;
	}
	
	public static int getMinIndex(int l, int m, int r){
		
		if(fenceArray[l] > fenceArray[r]) return fenceArray[r] < fenceArray[m] ? r:m;
		else return fenceArray[l] < fenceArray[m] ? l:m;
	}
}
