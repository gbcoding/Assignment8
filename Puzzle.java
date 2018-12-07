// Garrett Brumley
// CSCE 4430 Assign 8
// Cracker Barrel Peg Game (Puzzle.java)
// 12/6/18


import java.io.*;
import java.util.*;
public class Puzzle{

	private static class GameBoard{

		int[][] moves ={
				{0,1,3},
				{0,2,5},
				{1,3,6},
				{1,4,8},
				{2,4,7},
				{2,5,9},
				{3,6,10},
				{3,7,12},
				{4,7,11},
				{4,8,13},
				{5,8,12},
				{5,9,14},
				{3,4,5},
				{6,7,8},
				{7,8,9},
				{10,11,12},
				{11,12,13},
				{12,13,14}
		};
 
		GameBoard(){}

		// Solves puzzle from position start, returns the list of moves used to solve
		private int[][] solve(int start){

			// Variables to store moves
			int from, over, to;			
			int[][] templist = new int[14][3];
			int[] Cells = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			Cells[start]=0;
			int Cells_left = 14;
			// Move number
			int mn = 0;

			printBoard(Cells);
			while(true){
				// If only one cell left, puzzle is finished
				if(Cells_left < 2)
					return templist;

				else{
					boolean flag = false;
					// Iterate through movelist until a working move has been found
					for(int i=0; i<18; i++){
			
						from = moves[i][0]; 
						over = moves[i][1]; 
						to = moves[i][2];

						// Try move: from, over, to. If a valid move is returned, add to templist
						if(move(Cells, from, over, to)){
												
							templist[mn][0] = from;
							templist[mn][1] = over;
							templist[mn][2] = to;
							Cells_left = Cells_left-1;
							mn++;
							printBoard(Cells);
							flag = true;
						}

						// Try backwards move: to, over, from. If a valid move is returned, add to templist
						if(move(Cells, to, over, from)){
							templist[mn][0] = to;
							templist[mn][1] = over;
							templist[mn][2] = from;
							Cells_left = Cells_left-1;
							mn++;
							printBoard(Cells);
							flag = true;
						}
					}

					if( flag == false)
						return templist;
				}
			}			    			
		}


		// Perform move if possible and return move mapping, return empty ArrayList otherwise
		private boolean move(int[] Cells, int f, int o, int t){
			
			// If from cell has a peg, over cell has a peg, and to cell is empty, perform move
			if(Cells[f] == 1 && Cells[o] == 1 && Cells[t] == 0){
				Cells[f] = 0;
				Cells[o] = 0;
				Cells[t] = 1;
 
				return true;
			}
			return false;
		}


		// Run Game
		private void go(){

			for(int i=0; i<5; i++){
				System.out.println("==="+i+"===");
				solve(i);

			}
		}


		private void printBoard(int[] replay_cells){

			int[][] lines = {{4,0,0},{3,1,2},{2,3,5},{1,6,9},{0,10,14}};
			String c;
			for(int i=0; i<5; i++){
				int t = lines[i][0];
				int a = lines[i][1];
				int b = lines[i][2];
				// tab variable for each spacing
				String tab = new String(new char[t]).replace("\0", " ");

				System.out.print(tab);

				for(int j=a; j<b+1; j++){
					if(replay_cells[j]==0)
						c = new String(". ");
					else
						c = new String("x ");

					System.out.print(c);
				}	
				System.out.println(" ");
			}
			System.out.println(" ");
		}
	}


	public static void main(String[] args){

		GameBoard G = new GameBoard();

		G.go();
	}

}
