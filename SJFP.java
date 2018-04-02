import java.util.*;

public class SJFP {
	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of processes");
		n = sc.nextInt();
		System.out.println("Enter the burst time and arrival time of each process");
		int burst_time[] = new int[n];
		int arrival_time[] = new int[n];
		for (int i = 0; i < n; i++) {
			burst_time[i] = sc.nextInt();
			arrival_time[i] = sc.nextInt();
		}
		findAverageWaitingTime(n, burst_time, arrival_time);
	}

	private static void findAverageWaitingTime(int n, int[] burst_time, int[] arrival_time) {
		int wT[] = new int[n];
		int tAT[] = new int[n];
		int total_wt = 0, total_tat = 0;
		findWaitingTime(n, burst_time, arrival_time, wT);
		findTurnAroundTime(n, burst_time, arrival_time, wT, tAT);
		System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wT[i];
			total_tat = total_tat + tAT[i];
			System.out.println(" " + (i + 1) + "\t\t" + burst_time[i] + "\t " + wT[i] + "\t\t " + tAT[i]);
		}
		System.out.println("Average waiting time = " + (float) total_wt / n);
		System.out.println("Average turn around time = " + (float) total_tat / n);

	}

	private static void findTurnAroundTime(int n, int[] burst_time, int[] arrival_time, int[] wT, int[] tAT) {
		for (int i = 0; i < n; i++)
			tAT[i] = wT[i] + burst_time[i];
	}

	private static void findWaitingTime(int n, int[] burst_time, int[] arrival_time, int[] wT) {
		int count = 0, t = 0, min = Integer.MAX_VALUE, min_index = 0;
		int rem_bt[] = new int[n];
		for (int i = 0; i < n; i++)
			rem_bt[i] = burst_time[i];
		while (count != n) {
			for (int i = 0; i < n; i++) {
				if ((arrival_time[i] <= t) && (rem_bt[i] < min) && (rem_bt[i] > 0)) {
					min = rem_bt[i];
					min_index = i;
				}
			}
			rem_bt[min_index] -= 1;
			min = rem_bt[min_index];
			if (min == 0)
				min = Integer.MAX_VALUE;
			if (rem_bt[min_index] == 0) {
				count++;
				wT[min_index] = t + 1 - arrival_time[min_index] - burst_time[min_index];
				if (wT[min_index] < 0)
					wT[min_index] = 0;
			}
			t++;
		}
	}

}
