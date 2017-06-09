package algorithm.etc.practice;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
2
4
2 2 3 3
4
3 1 2 4

#1 4
#2 4
 */
public class Running {

    static int T // 전체 케이스
            ,N; //달리기 선수의 수
    static long result; //결과값
    static List<Integer> inputRunners = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./input_txt/practice/running.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int i=0; i<T; i++){

            result = 0;
            inputRunners.clear();
            N = sc.nextInt();

            for(int j=0; j<N; j++){
                inputRunners.add(sc.nextInt());
            }

            mergeSort(inputRunners);

            System.out.println("#"+(i+1)+" "+result);
        }
    }

    public static List<Integer> mergeSort(List<Integer> runners){

        if(runners.size() < 2) return runners;

        final List<Integer> leftList = runners.subList(0, runners.size()/2);
        final List<Integer> rightList = runners.subList( runners.size()/2, runners.size());

        return merge(mergeSort(leftList), mergeSort(rightList));
    }

    public static List<Integer> merge(List<Integer> left, List<Integer> right){

        int leftIndex = 0;
        int rightIndex = 0;
        int duplicatedCount = 0;
        List<Integer> calculList = new ArrayList<>(left.size());
        List<Integer> returnList = new ArrayList<>(left.size()+right.size());

        while(left.size() > leftIndex && right.size() > rightIndex){

            if(left.get(leftIndex) < right.get(rightIndex)) {
                calculList.add(left.get(leftIndex));
                returnList.add(left.get(leftIndex));
                leftIndex++;
            }else if(left.get(leftIndex) > right.get(rightIndex)){

                duplicatedCount = calculatedResult(calculList, right, rightIndex, duplicatedCount);

                returnList.add(right.get(rightIndex));
                rightIndex++;
            }else{

                duplicatedCount = calculatedResult(calculList, right, rightIndex, duplicatedCount);

                returnList.add(left.get(leftIndex));
                calculList.add(left.get(leftIndex));
                returnList.add(right.get(rightIndex));
                leftIndex++;
                rightIndex++;
            }
        }

        while(left.size() > leftIndex){
            returnList.add(left.get(leftIndex));
            leftIndex++;
        }

        while(right.size() > rightIndex) {

            duplicatedCount = calculatedResult(calculList, right, rightIndex, duplicatedCount);

            returnList.add(right.get(rightIndex));
            rightIndex++;
        }

        return returnList;
    }

    public static int calculatedResult(List<Integer> mergedList, List<Integer> inputList, int index, int dulpl){

        if(mergedList.isEmpty()) return dulpl;

        if(mergedList.get(mergedList.size()-1) != inputList.get(index)){
            //만약 동일 능력치의 선수가 연속으로 나열된다면?
            dulpl = 0;
        }else{
            dulpl++;
        }

        result += (mergedList.size() - dulpl);

        return dulpl;
    }
}
