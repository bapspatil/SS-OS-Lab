// LRU
import java.util.Scanner;

public class LRU {

    public static int min(int counter[],int nFrames) {
        int minimum = counter[0];
        int pos = 0;
        for(int i=0;i<nFrames;i++) 
            if(minimum > counter[i]) {
                minimum = counter[i];
                pos = i;
            }
        return pos;   
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        int n,recent = 0,pageFault = 0,nFrames;
       
    System.out.print("Enter the number of pages: ");
    n = s.nextInt();
    int pageString[] = new int[n];
    System.out.print("Enter the page reference string: ");
    for(int i=0;i<n;i++)
        pageString[i]=s.nextInt();
      
     System.out.print("\nEnter the number of frames: "); 
     nFrames = s.nextInt();
     int frames[] = new int[nFrames];
     int counter[] = new int[nFrames];
   
     for(int i=0;i<nFrames;i++) {        
        frames[i] = 0; 
        counter[i] = 0;//here 0 referes an empty space in frame
    }
    
    for(int i=0;i<n;i++)
    {
        int flag =0;
        for(int j=0;j<nFrames;j++) {
            if(frames[j] == pageString[i]) {
                flag=1;
                counter[j] = recent++; //counter holds which frame is recently used,
                                    //recently used page in frame will have a bigger number
                                    //and least recently used page in frame will have a lower number
                break;
            }
        }
        
        if(flag == 0) {
            for(int j=0;j<nFrames;j++)
            {
                if(frames[j] == 0)
                {   
                    frames[j] = pageString[i];
                    counter[j] = recent++;
                    flag=1;
                    pageFault++;
                    break;
                }
            }
        }
        
        if(flag == 0 ){
            int PositionToreplace = min(counter,nFrames);
            frames[PositionToreplace] = pageString[i];
            counter[PositionToreplace] = recent++;
            pageFault++;
        }
        
        //print frames
       System.out.println();
      for(int j=0;j<nFrames;j++)
           System.out.print(frames[j]+" ");
    }
     
      System.out.print("\nPage Fault: "+pageFault);
     
    }
    
}

// FIFO
import java.io.*;
import java.util.Scanner;

class FIFO {
    public static void main(String args[]) throws IOException {
        int fifo[] = new int[3];
        int n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of inputs :");
        n = Integer.parseInt(br.readLine());
        int inp[] = new int[n];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the inputs:");
        for (int i = 0; i < n; i++)
            inp[i] = sc.nextInt();
        System.out.println("—————");
        for (int i = 0; i < 3; i++)
            fifo[i] = -1;
        int Hit = 0;
        int Fault = 0;
        int j = 0;
        boolean check;
        for (int i = 0; i < n; i++) {
            check = false;
            for (int k = 0; k < 3; k++)
                if (fifo[k] == inp[i]) {
                    check = true;
                    Hit = Hit + 1;
                }
            if (check == false) {
                fifo[j] = inp[i];
                j++;
                if (j >= 3)
                    j = 0;
                Fault = Fault + 1;
            }
            for(int k=0;k<3;k++)
            	System.out.print(fifo[k]);
            System.out.println();
        }
        System.out.println(" FAULT:" + Fault);
    }
}
