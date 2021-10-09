import java.util.*;
import java.lang.*;

/*

					board
		1	2	3	4	5	6	7	8
		
	1	x	x	x	x	x	x	x	x
	
	2	x	x	x	x	x	x	x	x	
	
	3	x	x	x	x	x	x	x	x
	
	4	x	x	x	x	x	x	x	x
	
	5	x	x	x	x	x	x	x	x
	
	6	x	x	x	x	x	x	x	x
	
	7	x	x	x	x	x	x	x	x
	
	8	x	x	x	x	x	x	x	x
*/


class Othello{
	
int size = 8;
int[][] board = new int[size][size];

ArrayList list = new ArrayList();
ArrayList list_two = new ArrayList();
ArrayList list_three = new ArrayList();

int white_count = 0;
int black_count = 0;

//-----------board initialization--------------//
public  Othello()
{
	
	int i,j;
	for( i=0 ; i<size ; i++)
	{
		for( j=0;j<size;j++)
		{
			//null position
			board[i][j] = -1;
		}
		
	}
	
	//Whites
	board[3][3] = 1;
	board[4][4] = 1;
	
	//Blacks
	board[3][4] = 0;
	board[4][3] = 0;
	
}

//----------board insatance initialization--------------//
public  Othello(int[][] board )
{
	this.board = board;
}


//-----------board display---------------//
public void display_board()
{
	for( int i=0 ; i<size ; i++)
	{
		for( int j=0;j<size;j++)
		{
			if(board[i][j] == -1)
				System.out.print(i+""+j+"	 ");
			else if(board[i][j] == 0)
				System.out.print("B "+"	 ");
			else
				System.out.print("W "+"	 ");
		}
		System.out.println();
		System.out.println();
	}
	
	System.out.println();
	System.out.println("Black count = "+black_count+"			White count = "+white_count);
	System.out.println();
}



//-----------check possibilities accross row--------------//
public void check_row(int color)
{
int i, j;

for(i=0;i<size;i++)	
{
	for(j=0;j<size;j++)
	{
		//-----------storing blacks and whites count--------------//
		if(board[i][j] == 1-color || board[i][j] == color)
		{
			if(board[i][j] == 0)
				black_count = black_count + 1;
			else
				white_count = white_count + 1;
		}
		
		
		if(board[i][j] == color)
		{
			//------------checking possibility to the left of the current peice----------------//
			int found = 0;
			for(int left = j-1; left >= 0; left--)
			{
				if(found == 1 && board[i][left] == -1)
				{
					list.add(i*10+left);
					list_two.add(i*10+j);
					break;
				}
				else if(board[i][left] == 1-color) //&& board[i][j+1] == -1 )
					found = 1;
				else
					break;
					
			}
			
			//------------checking possibility to the right of the current peice----------------//
			found = 0;
			for(int right = j+1; right < size; right++)
			{
				if(found == 1 && board[i][right] == -1)
				{
					list.add(i*10+right);
					list_two.add(i*10+j);
					break;
				}
				else if(board[i][right] == 1-color)// && board[i][j-1] == -1)
					found = 1;
				else
					break;
			}
			
		}
		
	}
}
}

//------------checking possibility accross column----------------//
public void check_column(int color)
{
int i,j;
for(i=0;i<size;i++)	
{
	for(j=0;j<size;j++)
	{
		if(board[i][j] == color)
		{
		
			int found = 0;
			//------------checking possibility in the top of the current peice----------------//
			for(int up = i-1; up >= 0; up--)
			{
				if(found == 1 && board[up][j] == -1)
				{
					list.add(up*10+j);
					list_two.add(i*10+j);
					break;
				}
				else if(board[up][j] == 1-color)// && board[i+1][j] == -1)
					found = 1;
				else
					break;
					
			}
			
			found = 0;
			//------------checking possibility in the bottom of the current peice----------------//
			for(int down = i+1; down < size; down++)
			{
				if(found == 1 && board[down][j] == -1)
				{
					list.add(down*10+j);
					list_two.add(i*10+j);
					break;
				}
				else if(board[down][j] == 1-color)// && board[i-1][j] == -1)
					found = 1;
				else
					break;
			}
			
		}
		
	}
}
}

public void check_cross(int color)
{
int i,j;
for(i=0;i<size;i++)	
{
	for(j=0;j<size;j++)
	{
		if(board[i][j] == color)
		{
		
			int found = 0;
			int up_i = i-1,up_j = j-1;
			//------------checking possibility in the first diagonal, to the left of the current peice----------------//
			while(up_i>=0 && up_j>=0 )
			{
				if(found == 1 && board[up_i][up_j] == -1)
				{
					list.add(up_i*10+up_j);
					list_two.add(i*10+j);
					break;
				}
				else if(board[up_i][up_j] == 1-color)
					found = 1;
				else
					break;
					
			up_i--;
			up_j--;
			}
			
			found = 0;
			int down_i = i+1,down_j = j+1;
			//------------checking possibility in the first diagonal, to the right of the current peice----------------//
			while(down_i< size && down_j < size)
			{
				if(found == 1 && board[down_i][down_j] == -1)
				{
					list.add(down_i*10+down_j);
					list_two.add(i*10+j);
					break;
				}
				else if(board[down_i][down_j] == 1-color)
					found = 1;
				else
					break;
					
			down_i++;
			down_j++;
			}
			
			
			found = 0;
			up_i = i-1;up_j = j+1;
			//------------checking possibility in the second diagonal, to the left of the current peice----------------//
			while(up_i>=0  && up_j<size)
			{
				if(found == 1 && board[up_i][up_j] == -1)
				{
					list.add(up_i*10+up_j);
					list_two.add(i*10+j);
					break;
				}
				else if(board[up_i][up_j] == 1-color)
					found = 1;
				else
					break;
					
			up_i--;
			up_j++;
			}
			
			found = 0;
		    down_i = i+1;down_j = j-1;
			//------------checking possibility in the second diagonal, to the right of the current peice----------------//
			while(down_i< size && down_j >= 0)
			{
				if(found == 1 && board[down_i][down_j] == -1)
				{
					list.add(down_i*10+down_j);
					list_two.add(i*10+j);
					break;
				}
				else if(board[down_i][down_j] == 1-color)
					found = 1;
				else
					break;
					
			down_i++;
			down_j--;
			}
			
		}
		
	}
}
}

public void find_possible_moves()
{
	for(int var = 0;var < list.size(); var++)
	{
		if(!list_three.contains(list.get(var)))
		list_three.add(list.get(var));
	}
	
}

public void update_board(int position,int turn)
{
	//--------updating board according after players choice---------//
		for(int index = 0; index < list.size(); index++)
		{
			int start_position  = (int) list.get(index);
		
			if(position == start_position)
			{
				int end_position =(int) list_two.get(index);
		
		
				int begin_i = position/10,begin_j = position%10;
				
				int end_i = end_position/10, end_j = end_position%10;
				
				int j = begin_i, k= begin_j;
				if(begin_i <= end_i && begin_j <= end_j)
				{
						while(j <= end_i && k <= end_j)
						{
							board[j][k] = turn;
							if( begin_i == end_i)
								k++;
							else if(begin_j == end_j)
								j++;
							else{
								j++;
								k++;
							}
						}
				}
				if(begin_i <= end_i && begin_j >= end_j)
				{
						while(j <= end_i && k >= end_j)
						{
							board[j][k] = turn;
							
							if( begin_i == end_i)
								k--;
							else if(begin_j == end_j)
								j++;
							else{
								j++;
								k--;
							}
							
						}
				}
				if(begin_i >= end_i &&  begin_j <= end_j)
				{
						while(j >= end_i && k <= end_j)
						{
							board[j][k] = turn;
							if( begin_i == end_i)
								k++;
							else if(begin_j == end_j)
								j--;
							else{
								j--;
								k++;
							}
						}
				}
				if(begin_i >= end_i && begin_j >= end_j)
				{
						while(j >= end_i && k >= end_j)
						{
							board[j][k] = turn;
							if( begin_i == end_i)
								k--;
							else if(begin_j == end_j)
								j--;
							else{
								j--;
								k--;
							}
						}
				}
			}
		}
}


public static void main(String args[])
{
	Scanner scan = new Scanner(System.in);
	
	Othello start = new Othello();
	

	//-----------initializing board---------------//
	Othello instance = new Othello(start.board);
	int i = 1;
	int turn = 0;
	
	System.out.println("////------------Welcome to Othello Game-----------//// ");
	System.out.println();
	
	System.out.println("Enter 0 to exit game at any point ");
	System.out.println();
	
	int check = 0;
	while(true)
	{
		
		
		//------------creating new board for each turn--------------//
		instance = new Othello(instance.board);
		instance.check_row(turn);
		instance.check_column(turn);
		instance.check_cross(turn);
		instance.display_board();
		
		//------------checking if there are no possible moves-------//
		if(instance.list.isEmpty() && check == 0)
		{
			check = 1;
			continue;
		}
		
		//-----------checking if end of game is reached-----------//
		if(instance.list.isEmpty())
		{
			if(instance.white_count>instance.black_count)
				System.out.println("player 1(Black) is winner");
			else
				System.out.println("player 2(White) is winner");
				
			System.exit(0);
		}
		
		if(turn == 0)
		System.out.println("player - 1(Black) turn");
		else
		System.out.println("player - 2(White) turn");	
		
		//---------storing the possible moves------------//
		
		
		instance.find_possible_moves();
		System.out.println("choices are : "+instance.list_three);
		int position = scan.nextInt();
		
		
		//----------if invalid choice was made by player----------//
		while(!instance.list.contains(position))
		{
			if(position == -1)
				System.exit(0);
			System.out.println("Invalid choice!!!!!!!!!  Please choose correct choice");
			position = scan.nextInt();
		}
		
		instance.update_board(position,turn);
		
		
		
		System.out.println();
		turn = 1-turn;
		//i--;
		
		
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	
}

}