
import java.io.*;
import java.util.*;


class kmedoid2d
{	
	int col,row;
	String indx[][];

	int getIndex(String s)
	{
		for(int x=1;x<=row;x++)
		{
			if(indx[x][0].equalsIgnoreCase(s))
			{
				return Integer.parseInt(indx[x][1]);
			}
		}
		return -1;
	}

	void process()throws Exception
	{
		
		//read from file

		FileReader fr=new FileReader("kmedoid2dsrc.txt");
		Scanner sc=new Scanner(fr);
		//String arr[][]=new String[50][50];
		int i=0,j=0;
		Scanner sc1=new Scanner(System.in);
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
		col=j;
		row=i;
		
		String arr[][]=new String[row+1][col+1];		//NEW
		fr=new FileReader("kmedoid2dsrc.txt");		//NEW (update fr to start of file again)
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

		indx=new String[row+1][2];

		for(i=1;i<=row;i++)
		{	
			indx[i][0]=arr[i][0];
			indx[i][1]=Integer.toString(i);
		}

		int dist[][]=new int[row+1][row+1];
		
		for(i=1;i<=row;i++)
		{	
			for(j=1;j<=row;j++)
			{
				dist[i][j]=Math.abs(Integer.parseInt(arr[i][1])-Integer.parseInt(arr[j][1]))+Math.abs(Integer.parseInt(arr[i][2])-Integer.parseInt(arr[j][2]));
			}
		}

	
		int k;

		System.out.print("\nEnter the total number of clusters to form: ");
		k=sc1.nextInt();


		String clust[][]=new String[k][row];
		String medoid[][]=new String[k][2];
		
		for(i=0;i<k;i++)			//initially assign medoid as k elements
		medoid[i][0]=arr[i+1][0];

		
		int saveindx[]=new int[k];
		int flag=1;				//use flag to check if cluster changed or not to terminate looping, set =1 for first iteration
		int pos=0;
		int c[]=new int[k];			//this is the index array used for each cluster
		int cost[]=new int[k];
		int y;
		int min;
		int sum;
		String tempmedoid[]=new String[k];

		while(flag==1)			//check if flag=1, ie cluster has changed if yes then loop else break, initially flag=1 by default for first iteration
		{			
			
			//traverse from 1st no to last
			for(i=1;i<=row;i++)
			{
				min=999;

				//form clusters
				for(j=0;j<k;j++)
				{	
					//calculate distance between medoid and current element and find cluster number it is to be assigned to
					medoid[j][1]=Integer.toString(dist[getIndex(medoid[j][0])][getIndex(arr[i][0])]);
					
					//find the lowest distance
					if(Integer.parseInt(medoid[j][1])<min)
					{
						min=Integer.parseInt(medoid[j][1]);
						pos=j;
					}
						
				}

				//assign the element to a particular cluster
				clust[pos][c[pos]]=arr[i][0];		//assign element to pos'th cluster and c[pos]'th position, eg 1st cluster 1st position, values will be clust[0][0]
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
						sum=sum+dist[getIndex(clust[i][j])][getIndex(clust[i][z])];
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
	
	public static void main(String args[])throws Exception
	{
		kmedoid2d obj=new kmedoid2d();
		obj.process();
	}

}
