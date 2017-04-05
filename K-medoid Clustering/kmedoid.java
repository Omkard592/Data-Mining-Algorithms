
import java.io.*;
import java.util.*;


class kmedoid
{	
	
	public static void main(String args[])throws Exception
	{
		
		int n,k,i,j,x=0;

		FileReader fr=new FileReader("kmedoidip.txt");
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
		
		FileReader fr1=new FileReader("kmedoidip.txt");
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
		int medoid[][]=new int[k][2];
		
		for(i=0;i<k;i++)			//initially assign medoid as k elements
		medoid[i][0]=arr[i];

		
		/*for(i=0;i<k;i++)
		{
			System.out.print(medoid[i][0]+"\t");
			System.out.println(medoid[i][1]);
		}*/

		
		
		int saveindx[]=new int[k];
		int flag=1;				//use flag to check if cluster changed or not to terminate looping, set =1 for first iteration
		int pos=0;
		int c[]=new int[k];			//this is the index array used for each cluster
		int cost[]=new int[k];
		int y;
		int min;
		int sum;
		int tempmedoid[]=new int[k];

		while(flag==1)			//check if flag=1, ie cluster has changed if yes then loop else break, initially flag=1 by default for first iteration
		{			
			
			//traverse from 1st no to last
			for(i=0;i<n;i++)
			{
				min=999;

				//form clusters
				for(j=0;j<k;j++)
				{	
					//calculate distance between medoid and current element and find cluster number it is to be assigned to
					medoid[j][1]=Math.abs(arr[i]-medoid[j][0]);
					
					//find the lowest distance
					if(medoid[j][1]<min)
					{
						min=medoid[j][1];
						pos=j;
					}
						
				}

				//assign the element to a particular cluster
				clust[pos][c[pos]]=arr[i];		//assign element to pos'th cluster and c[pos]'th position, eg 1st cluster 1st position, values will be clust[0][0]
				c[pos]++;				//increment index
			}					//this loop will assign n elements to k clusters as per the basic algo

			
			System.out.println("\n\nClusters formed are as follows");
			for(i=0;i<k;i++)
			{
				System.out.print("Cluster no "+(i+1)+": ");
				for(j=0;j<c[i];j++)			
				{
					System.out.print(clust[i][j]+" ");
				}
			
				System.out.println();
			}
			

			/////////////////////////this is main logic loop////////////////////////////////////////////////
			int z;
			for(i=0;i<k;i++)
			{	
				System.out.println("\nFor Cluster no "+(i+1));
				cost[i]=999;
				for(j=0;j<c[i];j++)
				{
					sum=0;
					for(z=0;z<c[i];z++)
					{
						sum=sum+Math.abs(clust[i][j]-clust[i][z]);;
					}
					System.out.println("Cost when medoid is "+clust[i][j]+" is "+sum);
					if(cost[i]>sum)
					{
						cost[i]=sum;
						tempmedoid[i]=clust[i][j];
					}
				
				}
			}
			
			System.out.println("\nNew medoids");
			for(i=0;i<k;i++)
			{
				System.out.println("For Cluster no "+(i+1)+" is "+tempmedoid[i]);
			}
			System.out.println();

			//save current cluster size for later use
			for(y=0;y<k;y++)
			saveindx[y]=c[y];

			//reset cluster indices for traversal in later iterations
			for(y=0;y<k;y++)
			c[y]=0;

			flag=0;
			
			//check if medoids have changed or not
			for(i=0;i<k;i++)
			{
				if(medoid[i][0]!=tempmedoid[i])
				flag=1;
				break;
			}
			
			for(i=0;i<k;i++)
			{
				medoid[i][0]=tempmedoid[i];
			}

			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			

		}	//this while loop will run as long as medoid changes and will terminate when medoid and tempmedoid are same

		//display final resultant clusters
		System.out.println("Final Clusters formed are as follows");
		for(i=0;i<k;i++)
		{
			System.out.print("Cluster no "+(i+1)+": ");
			for(j=0;j<saveindx[i];j++)			//use the saved cluster index to print only till the index position stopped lastly
			{
				System.out.print(clust[i][j]+" ");
			}
			
			System.out.println();
		}
		
		
	}
	

}
