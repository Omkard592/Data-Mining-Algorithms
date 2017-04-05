//input is given from a text file containing numbers(elements)
//for worst case i have scattered elements anywhere in the file
//instead of putting them in one row or one column


import java.util.*;
import java.io.*;

class kmean
{
	public static void main(String args[])throws Exception
	{

		int n,k,x=0;

		FileReader fr=new FileReader("kmeanip.txt");
		Scanner sc=new Scanner(fr);

		while(sc.hasNextLine())
		{
			Scanner sc2=new Scanner(sc.nextLine());

			while(sc2.hasNext())
			{
				String s=sc2.next();
				x++;
			}
		}
	
		int arr[]=new int[x];
		x=0;
		
		FileReader fr1=new FileReader("kmeanip.txt");
		Scanner sc1=new Scanner(fr1);

		while(sc1.hasNextLine())
		{
			Scanner sc2=new Scanner(sc1.nextLine());
			boolean b;
			while(sc2.hasNext())
			{
				String s=sc2.next();
				arr[x]=Integer.parseInt(s);
				x++;
			}
		}
		
		n=x;
		
		System.out.print("\nEnter the total number of clusters to form: ");
		Scanner src=new Scanner(System.in);
		k=src.nextInt();

		int clust[][]=new int[k][n];
		int copy[][]=new int[k][n];
		float cent[][]=new float[k][2];


		for(int i=0;i<k;i++)			//initially set cluster centers arbitarily as the first k elements
		cent[i][0]=arr[i];
		
		int flag=1;				//use flag to check if cluster changed or not to terminate looping, set =1 for first iteration
		int pos=0;
		int c[]=new int[k];			//this is the index array used for each cluster
		int saveindx[]=new int[k];
		int y;
		float min,avg=0;


		while(flag==1)			//check if flag=1, ie cluster has changed if yes then loop else break, initially flag=1 by default for first iteration
		{	
			//make copy
			for(int i=0;i<k;i++)
			for(int j=0;j<n;j++)
			copy[i][j]=clust[i][j];					
			
			//traverse from 1st no to last
			for(int i=0;i<n;i++)
			{
				min=999;
				//form clusters
				for(int j=0;j<k;j++)
				{	
					//calculate difference between element and cluster center
					cent[j][1]=Math.abs(arr[i]-cent[j][0]);
					
					//find the lowest distance
					if(cent[j][1]<min)
					{
						min=cent[j][1];
						pos=j;
					}
						
				}

				//assign the element to a particular cluster
				clust[pos][c[pos]]=arr[i];		//assign element to pos'th cluster and c[pos]'th position, eg 1st cluster 1st position, values will be clust[0][0]
				c[pos]++;				//increment index
			}					//this loop will assign n elements to k clusters as per the kmean algo logic
			
			//find new values for center of each cluster
			for(int i=0;i<k;i++)
			{
				avg=0;
				for(int j=0;j<c[i];j++)	
				{	
					avg+=clust[i][j];
				}
				
				cent[i][0]=avg/(c[i]);
			}
			
		
			//save current cluster size for later use
			for(y=0;y<k;y++)
			saveindx[y]=c[y];

			//reset cluster indices for traversal in later iterations
			for(y=0;y<k;y++)
			c[y]=0;

			//reset flag
			flag=0;
			
			//now set flag only if cluster has changed, i.e compare current cluster with copy made in the beginning
			for(int i=0;i<k;i++)
			{
				for(int j=0;j<n;j++)
				{
					if(copy[i][j]!=clust[i][j])	//if element does not match declare directly that cluster has changed and stop checking
					{
						flag=1;
						break;
					}
				}
			
			}

		}	//this while loop will run as long as cluster changes and will terminate when cluster and copy will be same

		//display final resultant clusters
		System.out.println("Clusters formed are as follows");
		for(int i=0;i<k;i++)
		{
			System.out.print("Cluster no "+(i+1)+": ");
			for(int j=0;j<saveindx[i];j++)			//use the saved cluster index to print only till the index position stopped lastly
			{
				System.out.print(clust[i][j]+" ");
			}
			
			System.out.println();
		}
		
		
	}

}
