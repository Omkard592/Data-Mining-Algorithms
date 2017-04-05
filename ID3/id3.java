

import java.io.*;
import java.util.*;

 
class id3
{
	public static double logg(int num,int den)
	{
		if(den==0)
		return(0);
		else
		return(Math.log(num)/Math.log(2)-Math.log(den)/Math.log(2));
	}

	public static void main(String args[])throws Exception
 	{


 		//read from file

		FileReader fr=new FileReader("id3src.txt");

		Scanner sc=new Scanner(fr);
 		Scanner sc1=new Scanner(System.in);

		String arr[][]=new String[10][4];
		int n=0,i=0,j=0;
		

 		while(sc.hasNextLine())
 		{
		
			j=0;
 			Scanner sc2 = new Scanner(sc.nextLine());
 			boolean b;

 			while(b=sc2.hasNext())
 			{	
 				arr[i][j]=sc2.next();
				j++;
 			}

			i++;
			n++;
		}	

 		n--;
		
		int mno=0,fno=0;
		for(i=1;i<=n;i++)
 		{
 			if(arr[i][2].equalsIgnoreCase("m"))
			mno++;
			else
			fno++;

		}
		
		
		int m1=0,m2=0,m3=0;
		int f1=0,f2=0,f3=0;
		int x=0,y=0,z=0;

		for(i=1;i<=n;i++)
 		{
			if(arr[i][2].equalsIgnoreCase("m"))
			{
 				if(Float.parseFloat(arr[i][1])<1.6)
				m1++;	
			
				if(Float.parseFloat(arr[i][1])>=1.6 && Float.parseFloat(arr[i][1])<=2.0)
				m2++;
			
				if(Float.parseFloat(arr[i][1])>2.0)
				m3++;
			}

			if(arr[i][2].equalsIgnoreCase("f"))
			{
 				if(Float.parseFloat(arr[i][1])<1.6)
				f1++;	
			
				if(Float.parseFloat(arr[i][1])>=1.6 && Float.parseFloat(arr[i][1])<=2.0)
				f2++;
			
				if(Float.parseFloat(arr[i][1])>2.0)
				f3++;
			}

			if(arr[i][3].equalsIgnoreCase("s"))
			x++;
			if(arr[i][3].equalsIgnoreCase("m"))
			y++;
			if(arr[i][3].equalsIgnoreCase("t"))
			z++;		
 				

		}
		
			
		
		double ea1=(m1*logg(mno,m1))/mno +  (m2*logg(mno,m2))/mno+ (m3*logg(mno,m3))/mno;
		if(Double.isNaN(ea1))
		ea1=0;

		double ea2=(f1*logg(fno,f1))/fno +  (f2*logg(fno,f2))/fno+ (f3*logg(fno,f3))/fno;
		if(Double.isNaN(ea2))
		ea2=0;
		
		double ea3=(mno*ea1)/n+(fno*ea2)/n;

		System.out.println("\nEntropy of Male is "+ea1);
		System.out.println("\nEntropy of Female is "+ea2);
		System.out.println("\nEntropy of Gender is "+ea3);



		double eb1=(m1*logg(m1+f1,m1))/(m1+f1)+(f1*logg(m1+f1,f1))/(m1+f1);
		if(Double.isNaN(eb1))
		eb1=0;

		double eb2=(m2*logg(m2+f2,m2))/(m2+f2)+(f2*logg(m2+f2,f2))/(m2+f2);
		if(Double.isNaN(eb2))
		eb2=0;

		double eb3=(m3*logg(m3+f3,m3))/(m3+f3)+(f3*logg(m3+f3,f3))/(m3+f3);
		if(Double.isNaN(eb3))
		eb3=0;

		double eb4=((m1+f1)*eb1)/n+((m2+f2)*eb2)/n+((m3+f3)*eb3)/n;


		System.out.println("\nEntropy of Height is "+eb4);


		double ig1=(x*logg(n,x))/n +  (y*logg(n,y))/n+ (z*logg(n,z))/n;
		if(Double.isNaN(ig1))
		ig1=0;
		
		Double ig2=ig1-ea3;
		Double ig3=ig1-eb4;
		
		System.out.println("\nInformation gain is "+ig1);
		System.out.println("\nInformation gain of Gender is "+ig2);
		System.out.println("\nInformation gain of Height is "+ig3);


 
	}

}
