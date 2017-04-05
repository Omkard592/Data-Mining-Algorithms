

import java.io.*;
import java.util.*;

 
class Regression
{

	public static void main(String args[])throws Exception
 	{


 		//read from file

		FileReader fr=new FileReader("regsrc.txt");

		Scanner sc=new Scanner(fr);
 		Scanner sc1=new Scanner(System.in);
		int i=0,j=0;
		String xyz="";		//NEW

 		while(sc.hasNextLine())	//NEW (this loop is just used to count rows and cols in file
 		{
			j=0;
			Scanner sc2 = new Scanner(sc.nextLine());

 			while(sc2.hasNext())
 			{	
 				xyz=sc2.next();
				j++;
 			}

			i++;
			
		}	

 		
		i--;
		int row=i;					//NEW
		String arr[][]=new String[row+1][6];		//NEW (only 6columns needed because source file will ALWAYS have 2columns)
		fr=new FileReader("regsrc.txt");		//NEW (update fr to start of file again)
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

		System.out.println("Enter the "+arr[0][0]+" ");
		Float input=sc1.nextFloat();
		float xbar=0,ybar=0;
		
		for(i=1;i<=row;i++)
		{
			xbar=xbar+Float.parseFloat(arr[i][0]);
			ybar=ybar+Float.parseFloat(arr[i][1]);
			
		}
		
		xbar=xbar/row;
		
		ybar=ybar/row;
	
		
		float sum1=0,sum2=0;
		float s1=0;

		for(i=1;i<=row;i++)
		{
			
			
				arr[i][2]=Float.toString(Float.parseFloat(arr[i][0])-xbar);
				arr[i][3]=Float.toString(Float.parseFloat(arr[i][1])-ybar);
				arr[i][4]=Float.toString(Float.parseFloat(arr[i][2])*Float.parseFloat(arr[i][3]));
				arr[i][5]=Float.toString(Float.parseFloat(arr[i][2])*Float.parseFloat(arr[i][2]));
				
					
			

		}
		
		for(i=1;i<=row;i++)
		{
			sum1=sum1+Float.parseFloat(arr[i][4]);
			sum2=sum2+Float.parseFloat(arr[i][5]);
		}

		float beta=sum1/sum2;
		float alpha=ybar-beta*xbar;
		

		float y=alpha+beta*input;

		System.out.println(arr[0][1]+" is "+y);
		
 
	}

}
