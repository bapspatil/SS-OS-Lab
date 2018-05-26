import java.util.Scanner;
import java.io.*;

public class ModifiedLRU 
{
	public static int find_min(int nf, int counter[])
	{
		int min = counter[0], pos = 0;
		for(int i = 0; i < nf; i++)
		{
			if(counter[i] < min)
			{
				pos = i;
				min = counter[i];
			}
		}
		return pos;
	}
	
	public static void main(String args[]) throws Exception
	{
		 //Create a file object(for input file). Input file path to be specified with double back slash
		File file = new File("/Users/bapspatil/Downloads/input.txt");
													
		//Pass file object to scanner class(to parse through input file)
		Scanner sf = new Scanner(file); 
		
		//Create a FileWrite object to write output to file(path to be mentioned)
		FileWriter fw = new FileWriter("/Users/bapspatil/Downloads/output.txt"); 
		
		/*Input file format
		 * 1. Number of pages (20)
		 * 2. Number of frames (3)
		 * 3. The entire page string separated by spaces (7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1)
		 */
		
		int n = Integer.parseInt(sf.nextLine()); //Converting String to Integer
		int nf = Integer.parseInt(sf.nextLine());
		String str = sf.nextLine();
		
		String s[] = str.split(" "); //Splitting the page string through spaces and storing it in array
		int page[] = new int[n]; //Page string in integer format
		for(int i = 0; i < n; i++)
			page[i] = Integer.parseInt(s[i]);
		
		System.out.println("The number of pages = "+n);
		System.out.println("The number of frames = "+nf);
		System.out.print("The page string is ");
		
		for(int i = 0; i < n; i++)
			System.out.print(page[i]+" ");
		System.out.println();
		int frame[] = new int[nf];
		int counter[] = new int[nf];
		
		for(int i = 0; i < nf; i++)
		{
			frame[i] = -1;
			counter[i] = 0;
		}
		int recent = 0, fault = 0;
		for(int i = 0; i < n; i++)
		{
			int flag = 0;
			for(int j = 0; j < nf; j++)
			{
				if(frame[j] == page[i])
				{
					flag = 1;
					counter[j] = recent++;
					break;
				}
			}
			
			if(flag == 0)
			{
				for(int j = 0; j < nf; j++)
				{
					if(frame[j] == -1)
					{
						flag = 1;
						frame[j] = page[i];
						counter[j] = recent++;
						fault++;
						break;
					}
				}
			}
			
			if(flag == 0)
			{
				int pos = find_min(nf, counter);
				frame[pos] = page[i];
				counter[pos] = recent++;
				fault++;
			}
			
			//FileWriter object used to write output
			for(int j = 0; j < nf; j++)
				fw.write(frame[j]+"  ");
			
			fw.write("\r\n"); // '\r\n' indicates a new line
		}
		fw.write("TOTAL PAGE FAULTS : "+fault);
		System.out.println("Output written in output.txt");
		
		//Closing both the references
		sf.close(); 
		fw.close();
	}
}
