import java.util.*;

public class RoundRobin {

	static void findWaitingTime(int n, int bt[], int wt[], int quantum) {
		int rem_bt[] = new int[n]; // Remaining burst time
		for (int i = 0; i < n; i++)
			rem_bt[i] = bt[i];

		int t = 0; // Elapsed time
		while (true) {
			boolean done = true;
			for (int i = 0; i < n; i++) {
				if (rem_bt[i] > 0) {
					done = false;
					if (rem_bt[i] > quantum) {
						t += quantum;
						rem_bt[i] -= quantum;
					}
					else {
						t += rem_bt[i];
						wt[i] = t - bt[i];
						rem_bt[i] = 0;
					}
				}
			}
			if (done)
				break;
		}
	}

	static void findTurnAroundTime( int n, int bt[], int wt[], int tat[]) {
		for (int i = 0; i < n; i++)
			tat[i] = bt[i] + wt[i];
	}

	static void findavgTime(int processes[], int n, int bt[], int quantum) {
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;
		findWaitingTime(n, bt, wt, quantum);
		findTurnAroundTime(n, bt, wt, tat);
		System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");
		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			System.out.println(" " + (i + 1) + "\t\t" + bt[i] + "\t " + wt[i] + "\t\t " + tat[i]);
		}
		System.out.println("Average waiting time = " + (float) total_wt / n);
		System.out.println("Average turn around time = " + (float) total_tat / n);
	}

	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of processes");
		n = sc.nextInt();
		int processes[] = new int[n];
		int burst_time[] = new int[n];
		System.out.println("Enter the burst times");
		for (int i = 0; i < n; i++) {
			burst_time[i] = sc.nextInt();
			processes[i] = i + 1;
		}
		System.out.println("Enter the quantum time");
		int quantum = sc.nextInt();
		findavgTime(processes, n, burst_time, quantum);
		sc.close();
	}
}
