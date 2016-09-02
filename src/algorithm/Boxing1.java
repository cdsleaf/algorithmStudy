package algorithm;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;

//장난감 박스 포장하기.
/*
 * #1 4
 * #2 5
 * #3 9
 * #4 21
 * #5 31
 * #6 29
 * #7 41
 * #8 50
 * #9 43
 * #10 50
 */
public class Boxing1 {
	
	static int T;
    static int N;
    static int K;
    static int cache[];
    static int toy[];
    static int box[];
	
	public static void main(String[] args) throws Exception {
		// Start time
		long startTime = System.nanoTime();
		
		System.setIn(new FileInputStream("C:/input/boxing.txt"));
		
		Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int t = 1; t <= T; ++t)
        {
            cache = new int[1001];
            toy = new int[1001];
            box = new int[20];

            N = sc.nextInt();
            K = sc.nextInt();

            for(int i=1; i <= N; ++i)
            {
                toy[i] = sc.nextInt();
            }

            for(int i=1; i <= N; ++i)
            {
                int cmp = Integer.MAX_VALUE;
                int cnt = 0;
                for(int j=0; j<20 && i-j > 0; ++j){
                    int k = 0;
                    for(k = 0; k<cnt; k++){
                        if(box[k] == toy[i-j])
                            break;
                    }
                    if(cnt == k){
                        box[cnt++] = toy[i-j];
                    }
                    cmp = Math.min(cmp, cnt*cnt + cache[i-j-1]);
                }
                cache[i] = cmp;
            }

            System.out.println("#"+t+" "+cache[N]);
			
		}
		
		// End time
		long endTime = System.nanoTime();
				
		// Total times
		long lTime = endTime - startTime;
		System.out.println("TIME : " + lTime/1000000.0 + "(ms)");
	}
	
}