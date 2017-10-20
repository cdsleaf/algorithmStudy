package algorithm.etc.pro;

import java.io.FileInputStream;
import java.util.*;

/*
(출력)
#1 1 1
#2 0
#3 4 1 2 3 9
 */
public class ArticulationBridges {

    static int  T, nodeNum, lineNum, start, end, order, dfsCost, visited[] = new int[1001];
    static Map<Integer, List<NodeLineInfo>> connectedNodeInfoList = new HashMap<>();
    static List<NodeLineInfo> NodeLineInfoList;
    static Set<Integer> resultSet = new TreeSet<>();
    static StringBuilder result = new StringBuilder();

    static class NodeLineInfo{
        int node;
        int orderNum;
        int searchCost;

        NodeLineInfo(int node, int orderNum){
            this.node = node;
            this.orderNum = orderNum;
            this.searchCost = 0;
        }
    }

    static int dfs(int currentNode, int parentNode){

        int returnSearchCost = ++dfsCost;
        visited[currentNode] = returnSearchCost;

        for(NodeLineInfo childNode : connectedNodeInfoList.get(currentNode)){
            if(childNode.node == parentNode) continue;
            if(visited[childNode.node]==0){
                childNode.searchCost = dfs(childNode.node, currentNode);
                if(childNode.searchCost > visited[currentNode]){
                    resultSet.add(childNode.orderNum);
                }
                returnSearchCost = Math.min(returnSearchCost, childNode.searchCost);
            }else{
                returnSearchCost = Math.min(returnSearchCost, visited[childNode.node]);
            }
        }
        return returnSearchCost;
    }

    static void addNodeLineInfo(int s, int e, int o){
        if(connectedNodeInfoList.containsKey(s)){
            connectedNodeInfoList.get(s).add(new NodeLineInfo(e, o));
        }else{
            NodeLineInfoList = new ArrayList<>();
            NodeLineInfoList.add(new NodeLineInfo(e, o));
            connectedNodeInfoList.put(s, NodeLineInfoList);
        }
    }

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("C:/Users/SDS/git/algorithmStudy/input_txt/sds/ArticulationBridges.txt"));

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            nodeNum = sc.nextInt();
            lineNum = sc.nextInt();

            //초기화
            connectedNodeInfoList.clear();
            resultSet.clear();
            dfsCost = 0;
            order =0;
            for(int init=0; init<=nodeNum; init++){
                visited[init] = 0;
            }

            for(int lineCount=0; lineCount<lineNum; lineCount++){

                order++;
                start = sc.nextInt();
                end = sc.nextInt();

                addNodeLineInfo(start, end, order);
                addNodeLineInfo(end, start, order);
            }

            for(int i=1; i<=nodeNum; i++){
                if(visited[i]==0){
                    dfs(i, 0);
                }
            }

            result.setLength(0);
            Integer[] r = resultSet.toArray(new Integer[resultSet.size()]);
            for(int s=0; s<resultSet.size(); s++){
                result.append(" "+r[s]);
            }

            System.out.println("#"+test_case+" "+resultSet.size()+result);
        }
    }
}
