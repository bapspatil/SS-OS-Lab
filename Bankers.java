public class Bankers {
	static int n, m;
	static int need[][];
	static int work[];
	static int sSequence[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of processes");
		n = sc.nextInt();
		int p[] = new int[n];
		System.out.println("Enter the number of resource types");
		m = sc.nextInt();
		int avail[] = new int[n];
		System.out.println("Enter the available instances of each resource type");
		for (int i = 0; i < m; i++)
			avail[i] = sc.nextInt();
		int allocation[][] = new int[n][m];
		System.out.println("Enter the number of instance of each resource allocated to each process");
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				allocation[i][j] = sc.nextInt();
		System.out.println("Enter the maximum instances of each resource that can be allocated to each process");
		int maximum[][] = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				maximum[i][j] = sc.nextInt();
		boolean flag = isSafe(p, avail, allocation, maximum);
		if (flag) {
			System.out.println("System is in safe state.The safe sequence is");
			for (int i = 0; i < n; i++)
				System.out.println(sSequence[i]);
		} else
			System.out.println("System is not in safe state");
		System.out.println("Enter the process number requesting for additional resources");
		int pNo = sc.nextInt();
		System.out.println("Enter the instances of each resource requested");
		int rR[] = new int[m];
		for (int i = 0; i < m; i++)
			rR[i] = sc.nextInt();
		resourceRequest(p, avail, allocation, maximum, pNo, rR);

	}

	private static void resourceRequest(int[] p, int[] avail, int[][] allocation, int[][] maximum, int pNo, int[] rR) {
		int i;
		for (i = 0; i < m; i++)
			if (rR[i] > need[pNo][i])
				break;
		if (i == m) {
			for (i = 0; i < m; i++)
				if (rR[i] > work[i])
					break;
			if (i == m) {
				for (int j = 0; j < m; j++) {
					avail[j] -= rR[j];
					allocation[pNo][j] += rR[j];
				}
				boolean flag = isSafe(p, avail, allocation, maximum);
				if (flag) {
					System.out.println(
							"System is in safe state.Therefore the request can be granted.\nThe safe sequence is");
					for (i = 0; i < n; i++)
						System.out.println(sSequence[i]);
				} else
					System.out.println("System is not in safe state.Therefore request cannot be granted");
			} else
				System.out.println("Resources are not available at the moment");
		} else {
			System.out.println("The process has exceeded it's maximum claim");
			System.exit(0);
		}

	}

	private static boolean isSafe(int[] p, int[] avail, int[][] allocation, int[][] maximum) {
		calculateNeed(maximum, allocation);
		work = new int[m];
		for (int i = 0; i < m; i++)
			work[i] = avail[i];
		int finish[] = new int[n];
		sSequence = new int[n];
		boolean found = false;
		int j, count = 0;
		while (count < n) {
			found = false;
			for (int i = 0; i < n; i++) {
				if (finish[i] == 0) {
					for (j = 0; j < m; j++)
						if (need[i][j] > work[j])
							break;
					if (j == m) {
						for (int k = 0; k < m; k++)
							work[k] += allocation[i][k];
						sSequence[count++] = i;
						finish[i] = 1;
						found = true;
					}

				}
			}
			if (!found) {
				System.out.println("System is not in safe state");
				break;
			}
		}
		return found;
	}

	private static void calculateNeed(int maximum[][], int allocation[][]) {
		need = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				need[i][j] = maximum[i][j] - allocation[i][j];
	}
}
