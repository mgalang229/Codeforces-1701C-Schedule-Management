import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;

/*

worst-case: 2m (every task has a worker that is not proficient)
we will perform binary search on the time itself

2m = 2(13) = 26

13 tasks
1 2 2 2 2 2 3 4 4 5 5 5 5

1 2 3 4 5
1 5 1 2 4

 */

public class AuthorSol {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), m = fs.nextInt();
			int[] a = fs.readArray(m);
			int[] freq = new int[n];
			for (int i = 0; i < m; i++) {
				--a[i];
				freq[a[i]]++;
			}
			int low = 0, high = 2 * m, res = -1;
			while (low <= high) {
				int mid = low + (high - low) / 2;
				if (check(mid, freq, n)) {
					res = mid;
					high = mid - 1; //find lower bound time (t)
				} else {
					low = mid + 1;
				}
			}
			System.out.println(res);
		}
		out.close();
	}
	
	static boolean check(int t, int[] freq, int n) {
		long canComplete = 0, notComplete = 0;
		for (int i = 0; i < n; i++) {
			//every worker will work on the same time (t)
			if (t >= freq[i]) {
				//2-hr tasks that each worker can complete	
				canComplete += (t - freq[i]) / 2;
			} else {
				//tasks that each worker cannot complete
				notComplete += freq[i] - t;
			}
		}
		return notComplete <= canComplete;
	}
	
	static final Random rnd = new Random();
	
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newIndex = rnd.nextInt(n);
			int temp = a[newIndex]; //change this
			a[newIndex] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
