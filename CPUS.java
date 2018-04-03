package calculator;

import java.util.*;

public class CPU_Scheduling {
	static int wT[];
	static int tAT[];
	static int rem_bt[];
	static int processes[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, burst_time[];
		System.out.println("Enter the number of processes");
		n = sc.nextInt();
		burst_time = new int[n];
		wT = new int[n];
		tAT = new int[n];
		rem_bt = new int[n];
		processes = new int[n];
		System.out.println("Enter the burst time of each process");
		for (int i = 0; i < n; i++) {
			burst_time[i] = sc.nextInt();
			rem_bt[i] = burst_time[i];
			processes[i] = i + 1;
		}
		System.out.println("Select the scheduling algorithm");
		System.out.print("1.Round Robin\n2.Non Premptive SJF \n3.Premptive SJF\n");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			System.out.println("Enter the quantum time");
			int quantum = sc.nextInt();
			findRRWaitingTime(n, burst_time, quantum);
			break;
		case 2:
			for (int i = 0; i < n; i++)
				processes[i] = i + 1;
			for (int i = 0; i < n - 1; i++) {
				int min_index = i;
				for (int j = i + 1; j < n; j++) {
					if (burst_time[j] < burst_time[min_index]) {
						min_index = j;
					}
				}
				int temp = burst_time[min_index];
				burst_time[min_index] = burst_time[i];
				burst_time[i] = temp;

				temp = processes[min_index];
				processes[min_index] = processes[i];
				processes[i] = temp;
			}

			findSJFNPWaitingTime(n, burst_time);
			break;
		case 3:
			int arrival_time[] = new int[n];
			System.out.println("Enter the arrival time of each process");
			for (int i = 0; i < n; i++)
				arrival_time[i] = sc.nextInt();
			findSJFPWaitingTime(n, burst_time, arrival_time);
			break;
		default:
			System.out.println("Invalid choice");

		}
		findTurnAroundTime(n, burst_time);
		findAverageWaTATime(n, burst_time);
		sc.close();
	}

	private static void findAverageWaTATime(int n, int[] burst_time) {
		int total_wt = 0, total_tat = 0;
		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wT[i];
			total_tat = total_tat + tAT[i];
		}
		System.out.println("\nAverage waiting time = " + (float) total_wt / n);
		System.out.println("Average turn around time = " + (float) total_tat / n);
	}

	private static void findSJFPWaitingTime(int n, int[] burst_time, int[] arrival_time) {
		int count = 0, t = 0, min = Integer.MAX_VALUE, min_index = 0;
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
				System.out.println("process " + processes[min_index] + " finishes at " + (t + 1) + " unit");
				if (wT[min_index] < 0)
					wT[min_index] = 0;
			}
			t++;
		}

	}

	private static void findSJFNPWaitingTime(int n, int[] burst_time) {
		wT[0] = 0;
		int totalWT = 0;
		System.out.println("process " + processes[0] + " finishes at " + burst_time[0] + " unit");
		for (int i = 1; i < n; i++) {
			totalWT += burst_time[i - 1];
			wT[i] = totalWT;
			System.out.println("process " + processes[i] + " finishes at " + (wT[i] + burst_time[i]) + " unit");
		}
	}

	private static void findRRWaitingTime(int n, int[] burst_time, int quantum) {
		int t = 0;
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

						wT[i] = t - burst_time[i];

						rem_bt[i] = 0;
						System.out.println("process " + processes[i] + " finishes at time " + t + " unit");
					}
				}
			}

			if (done)
				break;
		}
	}

	private static void findTurnAroundTime(int n, int bt[]) {
		for (int i = 0; i < n; i++)
			tAT[i] = bt[i] + wT[i];
	}
}
