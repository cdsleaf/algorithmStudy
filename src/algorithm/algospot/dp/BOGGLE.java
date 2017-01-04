package algorithm.algospot.dp;

import java.io.FileInputStream;
import java.util.Scanner;

/*
 * BOGGLE
 * 
 * 
---input

1
URLPM
XPRET
GIAET
XTNZY
XOQRS
6
PRETTY
GIRL
REPEAT
KARA
PANDORA
GIAZAPX

---output

PRETTY YES
GIRL YES
REPEAT YES
KARA NO
PANDORA NO
GIAZAPX YES
 */
public class BOGGLE {

	static int T, // 전체 테스트케이스 수.
				N = 5, //게임판의 크기는 5*5 로 고정.
				WC; //찾아야할 단어의 갯수.
	static int board[][] = new int[N][N]; //게임판의 알파벳을 저장할 배열
	static String words[][] = new String[10][2]; //찾을 단어들을 저장할 배열
	static String scanWord;

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("./input_txt/algospot/BOGGLE.txt"));
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int test_case = 0; test_case<T; test_case++){
			
			for(int by = 0; by<5; by++){
				scanWord = sc.next();
				for(int bx = 0; bx<5; bx++){
					board[by][bx] = scanWord.charAt(bx);
				}
			}
			
			WC = sc.nextInt();

			for(int wordCount=0; wordCount<WC; wordCount++){
				words[wordCount][0] = sc.next();
				words[wordCount][1] = "NO";
			}

			//여기부터 알고리즘 시작.
			
			
			//출력.
			for(int wordCount=0; wordCount<WC; wordCount++){
				System.out.println(words[wordCount][0]+" "+words[wordCount][1]);
			}	
		}
		
	}

}
