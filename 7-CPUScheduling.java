import java.util.*;

public class CPUScheduling {
	public static void sjf(int n, int bt[], int at[]) {
		int count = 0, small = 0;
		int rt[] = new int[n];
		float time, endtime, wt = 0, tat = 0;
		for (int i = 0; i < n; i++)
			rt[i ] = bt[i];
		System.out.println("Process\tBurst Time\tArrival Time\tWaiting Time\tTurn Around Time");
		for (time = 0; count != n; time++) {
			int val = 999;
			for (int i = 0; i < n; i++)
				if (rt[i] < val && rt[i] > 0 && at[i] <= time) {
					val = rt[i];
					small = i;
				}
			rt[small]--;
			if (rt[small] == 0) {
				count++;
				endtime = time + 1;
				System.out.println((small + 1) + "\t" + bt[small] + "\t\t" + at[small] + "\t\t" + (endtime - at[small] - bt[small]) + "\t\t" + (endtime - at[small]));
				wt += (endtime  - at[small] - bt[small]);
				tat += (endtime - at[small]);
			}
		}
		System.out.println("Avg waiting time = " + (float) wt / n);
		System.out.println("Average turnaround time  = " + (float) tat / n);
	}
	
	public static void findWT(int n, int bt[], int wt[], int q) {
		int time = 0;
		int rembt[] = new int[n];
		for (int i = 0; i < n; i++)
			rembt[i] = bt[i];
		while(true) {
			boolean done  = true;
			for (int i = 0; i < n; i++) {
				if (rembt[i] > 0) {
					done  = false;
					if (rembt[i] > q) {
						rembt[i] -= q;
						time += q;
					}
					else {
						time += rembt[i];
						wt[i] = time - bt[i];
						rembt[i] = 0;
					}
				}
			}
			if (done == true) 
				break;
		}
	}
	
	public static void rr(int n, int bt[], int q) {
		int wt[] = new int[n];
		int totalwt = 0, totaltat = 0;
		findWT (n, bt, wt, q);
		System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
		for(int i = 0; i < n; i++) {
			totalwt += wt[i];
			totaltat += wt[i] + bt[i];
			System.out.println((i + 1) + "\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + (wt[i] + bt[i]));
		}
		System.out.println("Avg waiting time  = " + (float) totalwt / n);
		System.out.println("Avg turnaround time = " + (float) totaltat / n);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number of processes: ");
		int n = scan.nextInt();
		int bt[] = new int[n];
		System.out.println("Enter the burst time of each process:");
		for (int i = 0; i < n; i++) {
			System.out.print("p[" + (i + 1) + "]: ");
			bt[i] = scan.nextInt();
		}
		System.out.println("Enter 1 for SJF\nEnter 2 for Round Robin");
		int choice = scan.nextInt();
		if (choice == 1) {
			System.out.println("Enter the arrival time of each process: ");
			int at[] = new int[n];
			for (int i = 0; i < n; i++) {
				System.out.print("p[" + (i + 1) + "]: ");
				at[i] = scan.nextInt();
			}
			sjf(n, bt, at);
		}
		else if (choice == 2) {
			System.out.println("Enter the time quantam:");
			int q = scan.nextInt();
			rr(n, bt, q);
		}
		else
			System.out.println("Invalid choice");
		scan.close();
	}
}
