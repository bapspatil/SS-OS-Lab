public class Bankers {
	static void isSafe(int np,int nr, int avail[], int need[][], int alloc[][]) {
		int count = 0,i,j;
		int finish[] = new int[np];
		int work[] = new int[nr];
		for(j=0;j<nr;j++)
			work[j] = avail[j];
		int safe[] = new int[np];
		while(count < np) {
			for(i=0;i<np;i++) {
				if(finish[i] == 0) {
					for(j=0;j<nr;j++) {
						if(need[i][j] > work[j]) {
							System.out.println("p["+i+"] : waiting..");
							break;
						}
					}
					if(j == nr){
						finish[i] = 1;
						for(int k=0;k<nr;k++) 
							work[k] += alloc[i][k];
						System.out.println("p["+i+"] : finished.");
						safe[count] = i;
						count++;
					}
				}
			}
		}
		System.out.println("\nsafe sequence:");
		for(i=0;i<np;i++) 
			System.out.print("p["+safe[i]+"]\t");
	}
	public static void main(String args[]) {
		int i,j;
		Scanner s = new Scanner(System.in);
		System.out.println("enter number of processes:");
		int np = s.nextInt();
		System.out.println("enter number of resources:");
		int nr = s.nextInt();
		int alloc[][] = new int[np][nr];
		System.out.println("enter allocation matrix:");
		for(i=0;i<np;i++) {
			for(j=0;j<nr;j++)
				alloc[i][j] = s.nextInt();
		}
		int max[][] = new int[np][nr];
		int need[][] = new int[np][nr];
		System.out.println("enter maximum resources matrix:");
		for(i=0;i<np;i++) {
			for(j=0;j<nr;j++) {
				max[i][j] = s.nextInt();
				need[i][j] = max[i][j] - alloc[i][j];
			}
		}
		int res[] = new int[nr];
		System.out.println("enter total resources matrix:");
		for(i=0;i<nr;i++)
			res[i] = s.nextInt();
		int avail[] = new int[nr];
		//available resources matrices
		int sum;
		for(i=0;i<nr;i++) {
			sum = 0;
			for(j=0;j<np;j++)
				sum += alloc[j][i];
			avail[i] = res[i] - sum;
		}
		
		isSafe(np,nr,avail,need,alloc);
		
		while(true) {
			System.out.println("is there any process that wants to make an additional request? \n(1 = yes) or(0 = no)");
			int choice = s.nextInt();
			if(choice == 1) {
				System.out.println("enter process number:");
				int pno = s.nextInt();
				int reqRes[] = new int[nr];
				System.out.println("enter requesting resources matrix:");
				for(i=0;i<nr;i++) {
					reqRes[i] = s.nextInt();
					if(reqRes[i] > need[pno][i]){
						System.out.println("the process has excecuted its maximum claim.");
						break;
					}
					if(reqRes[i] > avail[i]){
						System.out.println("the resources are unavailable.");
						break;
					}
				}
				if(i==nr) {
					int alloc2[][] = new int[np][nr];
					int need2[][] = new int[np][nr];
					for(i=0;i<np;i++) {
						for(j=0;j<nr;j++) {
							alloc2[i][j] = alloc[i][j];
							need2[i][j] = need[i][j];
						}
					}
					for(j=0;j<nr;j++) {
						alloc2[pno][j] += reqRes[j];
						avail[j] -= reqRes[j];
						need2[pno][j] -= reqRes[j];
					}
					isSafe(np,nr,avail,need2,alloc2);
				}
			}
			else
				System.exit(0);
		}
	}
}
