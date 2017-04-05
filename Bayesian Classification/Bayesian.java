
import java.io.*;
import java.util.*;

 
class Bayesian
{

	public static void main(String args[])throws Exception
 	{


 		//read from file

		FileReader fr=new FileReader("baysrc3.txt");

		Scanner sc=new Scanner(fr);
 		Scanner sc1=new Scanner(System.in);
		int k,i=0,j=0,x;
		String xyz="";		//NEW

 		while(sc.hasNextLine())	//NEW (this loop is just used to count rows and cols in file
 		{	
			j=0;
			Scanner sc2 = new Scanner(sc.nextLine());

 			while(sc2.hasNext())
 			{	
 				xyz=sc2.next();	//NEW (does nothing but traverse the whole file)
				j++;
 			}

			i++;
			
		}	
		i--;
		int col=j,row=i;

		String arr[][]=new String[row+1][col+1];		//NEW
		fr=new FileReader("baysrc3.txt");		//NEW (update fr to start of file again)
		Scanner scan=new Scanner(fr);			//NEW
		i=0;					//NEW
		j=0;					//NEW

		while(scan.hasNextLine())			//NEW (loop same as old one)
 		{	
			j=0;
			Scanner sc2 = new Scanner(scan.nextLine());
 			boolean b;

 			while(b=sc2.hasNext())
 			{	
 				arr[i][j]=sc2.next();
				j++;
 			}

			i++;
			
		}
		
		String pat="";
		String cls[]=new String[row];
		j=0;
		for(i=1;i<=row;i++)
		{
			if(pat.indexOf(arr[i][col-1])==-1)
			{
				pat+=arr[i][col-1];
				cls[j]=arr[i][col-1];
				j++;
			}
		}
		
		int nocls=j;
		int clscnt[]=new int[j];

		for(k=0;k<nocls;k++)
		{
			for(i=1;i<=row;i++)
			{
			
				if(arr[i][col-1].equals(cls[k]))
				{
					clscnt[k]++;
				}
			}
		}
				
		String ip[]=new String[col-1];
	
		for(i=0;i<col-1;i++)
		{
			System.out.println("Enter unknown sample "+arr[0][i]+" ");
			ip[i]=sc1.next();
			
		}

		System.out.println();

		int paci[][]=new int[nocls][col-1];						//no. of rows=no. of classes and no. of columns=1 column less than ip file
		float pxi[]=new float[nocls];
		float pci=0;

		for(x=0;x<nocls;x++)							//loop from 1st class to the last
		{
			for(k=1;k<col-1;k++)						//loop from actual 2nd column(skipping 1st column id,name,etc) to second last column	
			{
				for(i=1;i<=row;i++)						//loop from 1st record to the last
				{
		
					if(arr[i][col-1].equals(cls[x]) && arr[i][k].equals(ip[k]))	//if some class and that row's kth attribute is equal to kth input
					{
						paci[x][k]++;				//increment that class' kth attribute count
					}
				}
			}
		}

		

		for(x=0;x<nocls;x++)							//initialise array with 1 for multiplication
		{
			pxi[x]=1;
		}	
		

		for(x=0;x<nocls;x++)							//loop from 1st class to the last
		{
			for(k=1;k<col-1;k++)						//loop from actual 2nd column(skipping 1st column id,name,etc) to second last column
			{
				pxi[x]=(pxi[x]*paci[x][k])/clscnt[x];				//multiply probabilities of each attribute for a particular class and store them	
				
			}
		}

		for(x=0;x<nocls;x++)
		{
			pxi[x]=(pxi[x]*clscnt[x])/row;						//finally multiply obtained probability by probability of its corresponding class
		}

		
		
		for(x=0;x<nocls;x++)
		{
			pci=(clscnt[x]*1000)/row;						//multiply by 1000 to improve precision; eg: 5/6 = 0 for java but 0.8334 in acutality
			System.out.println("Probability of "+cls[x]+" is "+pci/1000);			//divide by 1000 to restore original result
		}

		float max=0;
		int pos=0;
		
		for(x=0;x<nocls;x++)							//display result with highest probability and the corresponding class the given sample belongs to
		{
			if(pxi[x]>max)
			{
				max=pxi[x];
				pos=x;
			}
			System.out.println("Probability of "+cls[x]+" for given sample is "+pxi[x]);
		}

		System.out.println("Hence given unknown tuple belongs to "+cls[pos]);
 
	}

}

