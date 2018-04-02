import java.util.Scanner;

public class SJFNP {
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
		for (int i = 0; i < n - 1; i++) {
			int min_index = i;
			for (int j = i + 1; j < n; j++) {
				if (burst_time[j] < burst_time[min_index]) {
					min_index = j;
				}
				int temp = burst_time[min_index];
				burst_time[min_index] = burst_time[i];
				burst_time[i] = temp;

				temp = processes[min_index];
				processes[min_index] = processes[i];
				processes[i] = temp;
			}

		}
		findavgTime(processes, n, burst_time);
		sc.close();
	}

	public static void findavgTime(int processes[], int n, int burst_time[]) {
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;
		total_wt = findWaitingTime(processes, n, burst_time, wt);
		findTurnAroundTime(processes, n, burst_time, wt, tat);
		System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");
		for (int i = 0; i < n; i++){
			total_tat = total_tat + tat[i];
			System.out.println(" " + processes[i] + "\t\t" + burst_time[i] + "\t " + wt[i] + "\t\t " + tat[i]);
		}
		System.out.println("Average waiting time = " + (float) total_wt / n);
		System.out.println("Average turn around time = " + (float) total_tat / n);
	}

	private static void findTurnAroundTime(int[] processes, int n, int[] burst_time, int[] wt, int[] tat) {
		for (int i = 0; i < n; i++)
			tat[i] = wt[i] + burst_time[i];

	}

	private static int findWaitingTime(int[] processes, int n, int[] burst_time, int[] wT) {
		wT[0] = 0;
		int totalWT = 0;
		for (int i = 1; i < n; i++) {
			totalWT += burst_time[i - 1];
			wT[i] = totalWT;
		}
		return totalWT;
	}

}