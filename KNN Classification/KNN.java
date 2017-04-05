
import java.io.*;
import java.util.*;

 
class KNN
{

	public static void main(String args[])throws Exception
 	{


 		//read from file

		FileReader fr=new FileReader("knnsrc1.txt");

		Scanner sc=new Scanner(fr);
 		Scanner sc1=new Scanner(System.in);

		float input=0,max=0;
		int k,i=0,j=0,pos=0;
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
		fr=new FileReader("knnsrc1.txt");		//NEW (update fr to start of file again)
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
		int clscnt[]=new int[nocls];
		
		System.out.println("\nEnter the unknown sample "+arr[0][1]);
 		input=sc1.nextFloat();
 
  		k=(int)Math.sqrt(row);
		
		
		for(i=1;i<=row;i++)
 		{
 			arr[i][col]=Float.toString(Math.abs(input-Float.parseFloat(arr[i][1])));


		}
		
		arr[0][col]="distance";
		System.out.println();

		for(i=0;i<=row;i++)
 		{

 			for(j=0;j<=col;j++)
 			{
 				System.out.print(arr[i][j]+"\t");
 			}

			System.out.println();

 		}
		System.out.println();
	
		for(i=k+1;i<=row;i++)
		{
			max=0;
 			for(j=1;j<=k;j++)
			{
			
				if(Float.parseFloat(arr[j][col])>max)
				{
					max=Float.parseFloat(arr[j][col]);
					pos=j;
				}

			 }

			if(Float.parseFloat(arr[i][col])<max)
			{
				for(int x=0;x<=col;x++)
				arr[pos][x]=arr[i][x];
						
			}

		}

		for(i=0;i<=k;i++)
 		{

 			for(j=0;j<col;j++)
 			{
 				System.out.print(arr[i][j]+"\t");
 			}

			System.out.println();

 		}
		System.out.println();
	
		for(i=0;i<nocls;i++)
 		{
			for(j=1;j<=k;j++)
			{
			
				if(arr[j][col-1].equals(cls[i]))
				clscnt[i]++;
			}
			
 		}

		System.out.println();
		
		max=0;
		for(i=0;i<nocls;i++)
		{
			if(clscnt[i]>max)
			{
				max=clscnt[i];
				pos=i;
			}
		}

		System.out.println("Given sample belongs to "+cls[pos]);
 
	}

}
