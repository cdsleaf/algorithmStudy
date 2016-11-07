package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SortAndSerch {

	//버블정렬보다는 좋으나, 새로운 리스트를 반환해야하므로 리스트의 두배 정도 공간이 더 필요.
	//LinkedList가 중간 삽입에서 arrayList 보다 훨씬 효율적.
	public static List<Integer> insertSort(final List<Integer> numbers){
		
		final List<Integer> sortedList = new LinkedList<>();

		originalList: for(Integer number : numbers){
			for(int i=0; i<sortedList.size();i++){
				if(number < sortedList.get(i)){
					sortedList.add(i, number);
					continue originalList;
				}
			}
			
			sortedList.add(sortedList.size(), number);
		}
		
		return sortedList;
	}
	
	//버블과 삽입보다 훨씬 효율적.두개의 리스트로 분리시 O(n)이고, 각각의 재귀호출은 각 리스트 숫자의 절반만큰의 횟수만 발생. 즉, O(n log n)
	//하지만 최악의 경우 위와 동일하게 o(n^2)
	public static List<Integer> quickSort(List<Integer> numbers){
		
		//재귀수행 시 아래 조건에 따라 재귀실행 종료.
		if(numbers.size() <2){
			return numbers;
		}
		
		final Integer pivot = numbers.get(0);
		final List<Integer> lower = new ArrayList<>();
		final List<Integer> higher = new ArrayList<>();
		
		for(int i=1; i<numbers.size(); i++){
			if(numbers.get(i) <pivot){
				lower.add(numbers.get(i));
			}else{
				higher.add(numbers.get(i));
			}
		}
		
		final List<Integer> sorted = quickSort(lower);
		sorted.add(pivot);
		sorted.addAll(quickSort(higher));
		
		return sorted;
	}
	
	//병합정렬의 성능은 O(n log n) 이고 각 병합 시간은  O(n), 각 재귀 호출은 각 리스트의 절반 만큼 발생.
	public static List<Integer> mergesort(final List<Integer> values){
		if(values.size() < 2){
			return values;
		}
		
		final List<Integer> leftHalf = values.subList(0, values.size()/2);
		final List<Integer> rightHalf = values.subList(values.size()/2, values.size());
		
		return merge(mergesort(leftHalf), mergesort(rightHalf));
	}
	
	//두개의 리스트를 정렬해서 병합하는 메소드!!
	private static List<Integer> merge(final List<Integer> left, final List<Integer> right){
		int leftPtr= 0;
		int rightPtr = 0;
		
		final List<Integer> merged = new ArrayList<>(left.size()+right.size());
		
		while(leftPtr < left.size() && rightPtr < right.size()){
			if(left.get(leftPtr) < right.get(rightPtr)){
				merged.add(left.get(leftPtr));
				leftPtr++;
			}else{
				merged.add(right.get(rightPtr));
				rightPtr++;
			}
		}
		
		while(leftPtr < left.size()){
			merged.add(left.get(leftPtr));
			leftPtr++;
		}
		
		while(rightPtr < right.size()){
			merged.add(right.get(rightPtr));
			rightPtr++;
		}
		
		return merged;
	}
	
	//binary search
	public static boolean binarySearch(final List<Integer> numbers, final Integer value){
		if(numbers == null || numbers.isEmpty()){
			return false;
		}
		
		final Integer comparison = numbers.get(numbers.size() / 2);
		if(value.equals(comparison)){
			return true;
		}
		
		if(value < comparison){
			return binarySearch(numbers.subList(0,numbers.size() / 2), value);
		}else{
			return binarySearch(numbers.subList(numbers.size()/2 +1 , numbers.size()), value);
		}
	}
}
