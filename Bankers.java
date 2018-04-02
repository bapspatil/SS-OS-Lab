import java.util.Scanner;
public class Bankers{
     
     
    static int[][] calc_need(int need[][],int allocate[][],int max[][],int avail[][],int np,int nr){
    	for(int i=0;i<np;i++)
    	{	
         for(int j=0;j<nr;j++)  //calculating need matrix
        	 need[i][j]=max[i][j]-allocate[i][j];
         System.out.println();
    	}        
       return need;
    }
  
    static boolean check(int i, int avail[][], int need[][], int nr){
       //checking if all resources for ith process can be allocated
       for(int j=0;j<nr;j++) 
       if(avail[0][j]<need[i][j])
          return false;
    
    return true;
    }
 
    public static void isSafe(int need[][],int allocate[][],int max[][],int avail[][],int np,int nr){
       boolean done[]=new boolean[np];
       int j=0;
 
       while(j<np){  //until all process allocated
       boolean allocated=false;
       for(int i=0;i<np;i++)
        if(!done[i] && check(i, avail, need, nr)){  //trying to allocate
            for(int k=0;k<nr;k++)
            avail[0][k]=avail[0][k]-need[i][k]+max[i][k];
         System.out.println("Allocated process : "+i);
         allocated=done[i]=true;
               j++;
             }
          if(!allocated) break;  //if no allocation
       }
       if(j==np)  //if all processes are allocated
        System.out.println("\nSafely allocated");
       else
        System.out.println("All proceess cannot be allocated safely");
    }
    
     
    public static void main(String[] args) {
    	int need[][],allocate[][],max[][],avail[][],np,nr;
    	int need2[][],allocate2[][],max2[][],avail2[][];
    	Scanner sc=new Scanner(System.in);
        System.out.print("Enter no. of processes and resources : ");
        np=sc.nextInt();  //no. of process
        nr=sc.nextInt();  //no. of resources
        need2 = need=new int[np][nr];  //initializing arrays
        max2 = max =new int[np][nr];
        allocate2 = allocate =new int[np][nr];
        avail2 = new int[1][nr];
        avail=new int[1][nr];
         
        System.out.println("Enter allocation matrix -->");
        for(int i=0;i<np;i++)
             for(int j=0;j<nr;j++)
            	 allocate2[i][j] = allocate[i][j]=sc.nextInt();  //allocation matrix
             
          
        System.out.println("Enter max matrix -->");
        for(int i=0;i<np;i++)
             for(int j=0;j<nr;j++)
                max2[i][j] = max[i][j]=sc.nextInt();  //max matrix
          
        System.out.println("Enter available matrix -->");
        for(int j=0;j<nr;j++)
        	avail2[0][j] = avail[0][j]=sc.nextInt();  //available matrix
            
           
       need = calc_need(need,allocate,max,avail,np,nr);
    
       isSafe(need,allocate,max,avail,np,nr);
       
       int n;
   	System.out.println("Enter the process number that is requesting");  
   	n = sc.nextInt();
   	System.out.println("Enter the request");
   	int req[] = new int[nr];
   	for(int i = 0; i < nr; i++)
   		req[i] = sc.nextInt();
   	for(int i = 0; i < nr; i++)
   	{
   		avail2[0][i] -= req[i];
   		allocate2[n][i] += req[i];
   	}
   	need2 = calc_need(need2,allocate2,max2,avail2,np,nr);
   	isSafe(need2,allocate2,max2,avail2,np,nr);
   	sc.close();
    }
}