package algorithm.sds;

import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Scanner;

class Bfs2 {

	static int N;
	static int[][] Input = new int[30][30];
	static int Answer;

	// 발견한 칸의 이동횟수를 저장하는 배열
	static int[][] discovered = new int[30][30];

	// 발견 정점 큐(row * N + col 로 칸을 표현)
	static ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이
		 * 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에
		 * 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래
		 * 주석을 지우고 이 메소드를 사용하셔도 좋습니다. 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석
		 * 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("eval_input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; ++test_case) {
			N = sc.nextInt();

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					Input[i][j] = sc.nextInt();
				}
			}

			bfs();
			System.out.println("#" + test_case + " " + Answer);
		}
	}

	public static void bfs() {
		// 초기화
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				discovered[i][j] = 0;
			}
		}
		queue.clear();

		// 큐에 시작 칸을 추가(0 * N + 0 = 0)
		queue.add(0);

		int tmp;
		int row, col;

		loop : while(queue.isEmpty() == false) {
			// 큐에서 방문할 칸을 poll
			tmp = queue.pollFirst();

			row = tmp / N;
			col = tmp % N;

			// 아래쪽 인접 칸들을 탐색
			for(int i = 1; i <= Input[row][col]; i++) {
				if(row + i >= N) {
					break;
				}

				// 인접 칸이 아직 방문하지 않은 상태라면
				if(discovered[row + i][col] == 0) {
					// 발견한 지점을 큐에 추가
					queue.add((row + i) * N + col);

					// 발견한 지점의 이동횟수는 현재 칸의 이동횟수  + 1 이 된다.
					discovered[row + i][col] = discovered[row][col] + 1;

					// 최종지점 발견
					if(row + i == N - 1 && col == N - 1) {
						break loop;
					}
				}
			}

			// 오른쪽 인접 칸들을 탐색
			for(int i = 1; i <= Input[row][col]; i++) {
				if(col + i >= N) {
					break;
				}

				// 인접 칸이 아직 방문하지 않은 상태라면
				if(discovered[row][col + i] == 0) {
					// 발견한 지점을 큐에 추가
					queue.add(row * N + col + i);

					// 발견한 지점의 이동횟수는 현재 칸의 이동횟수  + 1 이 된다.
					discovered[row][col + i] = discovered[row][col] + 1;

					// 최종지점 발견
					if(row == N - 1 && col + i == N - 1) {
						break loop;
					}
				}
			}
		}

		Answer = discovered[N - 1][N - 1];
	}
}