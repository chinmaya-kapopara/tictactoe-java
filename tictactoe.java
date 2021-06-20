import java.util.*;
import java.util.Random; 

class tictac
{
	int value,min=1000,max=-1000;
	int row[]=new int[9];
	int col[]=new int[9];
	int maximum[]=new int[9];
	char[] p={'x','o'};
	Random rand=new Random();
	
	
	void tttttt(char board[][])
	{
		int k=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(board[i][j]=='_')
				{
					board[i][j]=p[0];
					
					tictac ob=new tictac();
					value=ob.ttt(board,1,1);
					//System.out.println(value);
						maximum[k]=value;
						row[k]=i;
						col[k++]=j;
					//ob.print_board(board);
					board[i][j]='_';
				}
				
			}
		}
		bubble_sort(maximum,row,col,k);
		int r=rand.nextInt(k);
	    if(r<=(5*k)/8)
	        r=0;
	    else
	        r=1;
		board[row[r]][col[r]]='x';
	}
	
	void bubble_sort(int a[],int b[],int c[],int n)
	{
		int i,j,k,temp;
		for(i=0;i<n-1;i++)
		{
			for(j=n-1;j>i;j--)
				if(a[j-1]<a[j])
				{
					temp=a[j];
					a[j]=a[j-1];
					a[j-1]=temp;
					temp=b[j];
					b[j]=b[j-1];
					b[j-1]=temp;
					temp=c[j];
					c[j]=c[j-1];
					c[j-1]=temp;
				}
		}
	}
	
	int ttt(char board[][],int depth,int p_index)
	{
		if(win(board,p[1-p_index])==true)
		{
			if(p_index==1)
				return 10-depth;
			else
				return -10+depth;
		}
			
		if(full(board)==true)
		{
				return 0;
		}
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(board[i][j]=='_')
				{
					board[i][j]=p[p_index];
					
					tictac ob1=new tictac();
					value=ob1.ttt(board,depth+1,1-p_index);
					
					if(p_index==0&&value>max)
						max=value;
					
					if(p_index==1&&value<min)
						min=value;
					
					//ob1.print_board(board);
					board[i][j]='_';
				}
			}	
		}
		if(p_index==0)
			return max;
		else
			return min;
	}
	
	boolean win(char board[][],char player)
	{
		if((board[0][0]==player&&board[0][1]==player&&board[0][2]==player)||(board[1][0]==player&&board[1][1]==player&&board[1][2]==player)||(board[2][0]==player&&board[2][1]==player&&board[2][2]==player)||(board[0][0]==player&&board[1][0]==player&&board[2][0]==player)||(board[0][1]==player&&board[1][1]==player&&board[2][1]==player)||(board[0][2]==player&&board[1][2]==player&&board[2][2]==player)||(board[0][0]==player&&board[1][1]==player&&board[2][2]==player)||(board[0][2]==player&&board[1][1]==player&&board[2][0]==player))
			return true;
		else
			return false;
	}
	
	boolean full(char board[][])
	{
		if(board[0][0]!='_'&&board[0][1]!='_'&&board[0][2]!='_'&&board[1][0]!='_'&&board[1][1]!='_'&&board[1][2]!='_'&&board[2][0]!='_'&&board[2][1]!='_'&&board[2][2]!='_')
			return true;
		else
			return false;
	}
	
	void print_board(char board[][])
	{
		System.out.println();
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.print(board[i][j]+"   ");
			}
			System.out.println();
			System.out.println();
		}
	}
}

class ttt_main
{
    public static void main(String args[])
    {
		int row,col,i1=0,;
		char f;
		char[][] board={{'_','_','_'},{'_','_','_'},{'_','_','_'}};
		String[] s={"You","Computer"};
		char[] p={'x','o'};
		
		Scanner sc=new Scanner(System.in);
		tictac ob=new tictac();
		System.out.print("\nDo you want first turn (y/n)? ");
		f=sc.next().charAt(0);
		
		if(f=='n')
			i1++;
		
		ob.print_board(board);				
		//System.out.print("\n-----I dare you to win-----\n");
		
		for(i=i1;i<i1+9;i++)
		{
			if(i%2==0)
			{
				System.out.print("\nEnter your move: ");
				row=sc.nextInt();
				col=sc.nextInt();
				
				if(board[row-1][col-1]=='_')
					board[row-1][col-1]='o';
				else
					{
						System.out.println("Invalid Move!");
						i--;
					}
			}
			else
			{
				//System.out.println("Computer's move:\n");
				tictac ob1=new tictac();
				ob1.tttttt(board);
				ob.print_board(board);
			}
			
			
			if(ob.win(board,p[1-i%2])==true)
			{
				System.out.println(s[i%2]+" won!!!\n");
				return;
			}
		}
		System.out.println("Tie!!!\n");
    }
}
