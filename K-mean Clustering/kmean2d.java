
import java.io.*;
import java.util.*;


class kmean2d
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
	
	float getDistance(String x1,String x2,String y1,String y2)
	{
		return (float)Math.sqrt( (Float.parseFloat(x2)-Float.parseFloat(x1))*(Float.parseFloat(x2)-Float.parseFloat(x1)) + (Float.parseFloat(y2)-Float.parseFloat(y1))*(Float.parseFloat(y2)-Float.parseFloat(y1)) );
	}

	void process()throws Exception
	{
		
		//read from file

		FileReader fr=new FileReader("kmean2dsrc.txt");
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
		fr=new FileReader("kmean2dsrc.txt");		//NEW (update fr to start of file again)
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
	
		int k;

		System.out.print("\nEnter the total number of clusters to form: ");
		k=sc1.nextInt();


		String clust[][]=new String[k][row];	//rows=number of clusters and columns=n, i.e total elements
		String copy[][]=new String[k][row];	//copy used to check if cluster has changed or not
		String cent[][]=new String[k][3];	//center; 1st column x, 2nd y and 3rd distance of current element(x,y) and center(x,y)
		
		for(i=0;i<k;i++)			//initially assign center as k elements
		{	
			cent[i][0]=arr[i+1][1];
			cent[i][1]=arr[i+1][2];
		}
		
		int saveindx[]=new int[k];
		int flag=1;				//use flag to check if cluster changed or not to terminate looping, set =1 for first iteration
		int pos=0;
		int c[]=new int[k];			//this is the index array used for each cluster
		int y;
		float min;
		float sum;
		
		while(flag==1)			//check if flag=1, ie cluster has changed if yes then loop else break, initially flag=1 by default for first iteration
		{			
			for(i=0;i<k;i++)
			for(j=0;j<row;j++)
			{
			copy[i][j]=clust[i][j];
			}
		
			//traverse from 1st no to last
			for(i=1;i<=row;i++)
			{
				min=999;

				//form clusters
				for(j=0;j<k;j++)
				{	
					//calculate distance between center and current element and find cluster number it is to be assigned to
					cent[j][2]=Float.toString(getDistance(arr[i][1],cent[j][0],arr[i][2],cent[j][1]));
					
					//find the lowest distance
					if(Float.parseFloat(cent[j][2])<min)
					{
						min=Float.parseFloat(cent[j][2]);
						pos=j;
					}
						
				}

				//assign the element to a particular cluster
				clust[pos][c[pos]]=arr[i][0];		//assign element to pos'th cluster and c[pos]'th position, eg 1st cluster 1st position, values will be clust[0][0]
				c[pos]++;				//increment index
			}					//this loop will assign n elements to k clusters as per the basic algo
			
			
			/////////////////////////this is main logic loop////////////////////////////////////////////////
			int z;
			int cnt;
			for(i=0;i<2;i++)					//0:x  1:y
			{
				for(j=0;j<k;j++)				//from 1st cluster to kth cluster
				{	
					sum=0;
					cnt=0;
					for(z=0;z<c[j];z++)			//from first element of cluster to last element of cluster
					{
						sum=sum+Integer.parseInt(arr[getIndex(clust[j][z])][i+1]);	//calculate sum of x or y of the element of a particular cluster
						cnt++;
					}					
					sum=sum/cnt;			//find average
					cent[j][i]=Float.toString(sum);		//assign avg as new center x or y for a particular cluster
				}
			}
		
			
			//save current cluster size for later use
			for(y=0;y<k;y++)
			saveindx[y]=c[y];
			
			//reset cluster indices for traversal in later iterations
			for(y=0;y<k;y++)
			c[y]=0;

			flag=0;
			
			//now set flag only if cluster has changed, i.e compare current cluster with copy made in the beginning
			for(i=0;i<k;i++)
			{
				for(j=0;j<row;j++)
				{	
					if(copy[i][j]!=clust[i][j])	//if element does not match declare directly that cluster has changed and stop checking
					{
						flag=1;
						break;
					}
					
				}
			
			}

			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			

		}	//this while loop will run as long as medoid changes and will terminate when medoid and tempmedoid are same
		
		//display final resultant clusters
		System.out.println("\n\nFinal Clusters formed are as follows");
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
		kmean2d obj=new kmean2d();
		obj.process();
	}

}
