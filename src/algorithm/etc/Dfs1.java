package algorithm.etc;

import java.util.ArrayList;

class Dfs1 {

	// 정점의 개수
	static int V = 5;

	// 방문한 정점을 표시하는 배열
	static int[] visited = new int[V];

	// 인접 리스트
	static ArrayList<Integer>[] adjList = new ArrayList[V];

	public static void main(String args[]) {

	// 예제 그래프 간선 정보 입력
		adjList[0] = new ArrayList<Integer>();
		adjList[0].add(1);
		adjList[0].add(2);

		adjList[1] = new ArrayList<Integer>();
		adjList[1].add(0);
		adjList[1].add(2);

		adjList[2] = new ArrayList<Integer>();
		adjList[2].add(0);
		adjList[2].add(1);
		adjList[2].add(3);
		adjList[2].add(4);

		adjList[3] = new ArrayList<Integer>();
		adjList[3].add(2);

		adjList[4] = new ArrayList<Integer>();
		adjList[4].add(2);
		
		// 방문 배열 초기화
		for(int i = 0; i < V; i++) {
			visited[i] = 0;
		}
		
		// 1번 정점부터 방문
		dfs(0);
	}

	public static void dfs(int node) {
		
		// 방문 정점 표시
		visited[node] = 1;
		
		// 방문 정점 처리
		processVertex(node);
		
		if(adjList[node] != null) {
			
			// 모든 인접 정점들을 방문
			for(int adjacent : adjList[node]) {
				// 방문한 정점이면 건너 뜀
				if(visited[adjacent] == 1) {
					continue;
				}
				
				// 재귀호출
				dfs(adjacent);
			}
		}
	}
	public static void processVertex(int node) {
		// 방문 정점 출력
		System.out.println(node + 1);
	}
}