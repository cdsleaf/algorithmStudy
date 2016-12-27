package algorithm.etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class maxHeap1 {
	static int N;
	static long[][] C;
	static long[] R;
	static final long M = 100000123;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tests = Integer.parseInt(br.readLine());
		C = new long[5000][5000];
		getCombination();
		getResult();
		for (int test = 1; test <= tests; test++) {
			N = Integer.parseInt(br.readLine());
			System.out.println("#" + test + " " + R[N]);
		}
	}

	static void getResult() {
		R = new long[12];
		int power = 8;
		R[0] = 1;
		R[1] = 2;
		for (int i = 2; i <= 11; i++) {
			int n = power - 2;
			long mid = 0;
			mid = (C[n][n / 2] * R[i - 1]) % M;
			R[i] = (mid * R[i - 1]) % M;
			power = power << 1;
		}
	}

	static void getCombination() {
		C[0][0] = 1;
		C[1][0] = 1;
		C[1][1] = 1;
		for (int n = 2; n <= 4096; n++) {
			for (int r = 0; r <= n / 2; r++) {
				if (r == 0 || n == r) {
					C[n][r] = 1;
				} else {
					C[n][r] = (C[n - 1][r - 1] + C[n - 1][r]) % M;
				}
			}
		}
	}
}
