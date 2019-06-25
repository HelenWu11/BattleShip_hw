package hw3;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is to use Java to simulate Conway’s Game of Life
 * @author rwu
 *
 */
public class GameProcess {
	private int row_count;
	private int col_count;
	private String output_pattern;
	private int score;
	private String name;
	private ArrayList<int[][]> t_grid;
	private ArrayList<int[][]> survive_grid;
	
	public GameProcess() {
		//default constructor
		row_count = 10;
		col_count = 10;
		output_pattern = "output";
		score = 0;
		name = "Player";

		t_grid = new ArrayList<int[][]>();
		survive_grid = new ArrayList<int[][]>();
	
		for(int i=0; i<=score; i++) {
			int grid[][] = new int[row_count][col_count];
			for(int k =0; k<row_count; k++) {
				for(int j=0; j<col_count; j++) {
					grid[k][j]=0;
				}
			}
			t_grid.add(grid);
		}
		
		for(int i=0; i<=score; i++) {
			int grid[][] = new int[row_count][col_count];
			for(int k =0; k<row_count; k++) {
				for(int j=0; j<col_count; j++) {
					grid[k][j]=0;
				}
			}
			survive_grid.add(grid);
		}
	}
	
	public GameProcess(int rc, int cc, String op, int tk, String input_name) {
		row_count = rc;
		col_count = cc;
		output_pattern = op;
		score = tk;
		name = input_name;
		t_grid = new ArrayList<int[][]>();
		survive_grid = new ArrayList<int[][]>();
		for(int i=0; i<=score; i++) {
			int grid[][] = new int[row_count][col_count];
			for(int k =0; k<row_count; k++) {
				for(int j=0; j<col_count; j++) {
					grid[k][j]=0;
				}
			}
			t_grid.add(grid);
		}
		
		for(int i=0; i<=score; i++) {
			int grid[][] = new int[row_count][col_count];
			for(int k =0; k<row_count; k++) {
				for(int j=0; j<col_count; j++) {
					grid[k][j]=0;
				}
			}
			survive_grid.add(grid);
		}
	}

	/**
	 * function to see whether there are transforming of each cell
	 * 
	 * rules:
	 * Any live cell with fewer than two live neighbors dies, as if caused by underpopulation.
	 * Any live cell with two or three live neighbors lives on to the next generation.
	 * Any live cell with more than three live neighbors dies, as if by overpopulation.
	 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	 * 
	 * @param step number of steps of simulation
	 * @param grid[][] as two dimensional arrays to store states of all cells
	 * @param row_count as rows number
	 * @param col_count as column number
	 * @param op output file to output all steps of simulation.
	 * @exception IOException throw exception if can't open output file
	 */
	public void transform(int step, int grid[][], int row_count, int col_count, String op)throws IOException {
		for(int n=0; n<=step; n++) {
			int copygrid[][] = new int[row_count][col_count];
			int copy_survive[][] = new int[row_count][col_count];
			for(int i=0; i<row_count; i++) {
				for(int j=0; j<col_count; j++) {
					copy_survive[i][j] = survive_grid.get(survive_grid.size()-1)[i][j];
				}
			}
			
			for(int i =0; i<row_count; i++) {
				for(int j=0; j<col_count; j++) {
					int num = findLifedNum(grid, j, i, row_count, col_count);
//					test to check 
//					System.out.println("The number of cells around "+i+", "+j+" is:");
//					System.out.println(num);
					
					//for living cells
					if(grid[i][j]==1) {
						//with fewer than two live neighbors 
						if(num<2) {
							copygrid[i][j]=0;
							copy_survive[i][j]=0;
						}
						//with more than three
						else if(num>3) {
							copygrid[i][j]=0;
							copy_survive[i][j]=0;
						}
						//with either two or three neighbors
						else if(num==2||num==3) {
							copygrid[i][j]=1;
							copy_survive[i][j]+=1;
						}
					}
					//for dead cells
					else if(grid[i][j]==0) {
						//with exactly three live neighbors
						if(num==3) {
							copygrid[i][j]=1;
							copy_survive[i][j]+=1;
						}
					}
				}//end of j iteration
			}//end of i iteration
			
			grid = copygrid;
			t_grid.add(grid);
			survive_grid.add(copy_survive);
	
		}//end of n iteration
		
	}
	
	public void savefile(String output, String outputname, int start_t, int end_t) throws IOException {
		
		for(int n=start_t; n<=end_t; n++) {
			File score_n = new File(output+"\\"+outputname+n+".txt");
			score_n.createNewFile(); 
			BufferedWriter out = new BufferedWriter(new FileWriter(score_n));
			String score = "score number: "+n;
			out.write(score);
			out.newLine();
			for(int i =0; i<row_count; i++) {
				for(int j=0; j<col_count; j++) {
					out.write(String.valueOf(t_grid.get(n)[i][j])+" ");
				}
				out.newLine();
			}
			out.flush();
			out.close(); 
		}
		
	}
	
	/**
     * count number of living cells around given cell
     * @param x which column the cell is located
     * @param y which row the cell is located
     * @param row_count total number of rows
     * @param col_count total count of columns
     * @param grid[][] as two dimensional arrays to store states of all cells
     * @return num number of living cells around the given cell
     */
    public int findLifedNum(int grid[][], int x, int y, int row_count, int col_count){
    	int num = 0;
    	
    	//check upper-left corner
    	num+=grid[(y+row_count-1)%row_count][(x+col_count-1)%col_count];
    	
    	//check left corner
    	num+=grid[y][(x+col_count-1)%col_count];
    	
    	//check lower-left corner
    	num+=grid[(y+1)%row_count][(x+col_count-1)%col_count];
    	
    	//check upper corner
    	num+=grid[(y+row_count-1)%row_count][x];
    	
    	//check lower corner
    	num+=grid[(y+1)%row_count][x];
    	
    	//check upper-right corner
    	num+=grid[(y+row_count-1)%row_count][(x+1)%col_count];
    	
    	//check lower-right corner
    	num+=grid[(y+1)%row_count][(x+1)%col_count];
    	
    	
    	return num;
    }
	
    /**
     * 
     * @param rc
     */
    public void SetRowCount(int rc) {
    	row_count = rc;
    }
    
    public void SetColCount(int cc) {
    	col_count = cc;
    }
    
    public void Setscore(int tk) {
    	score = tk;
    }
    
    public void SetOP(String op) {
    	output_pattern = op;
    }
    
    public void SetGrid(int[][] g, int t) {
    	t_grid.set(t, g);
    }
    
    public void SetSurvive(int[][] g, int t) {
    	survive_grid.set(t, g);
    }
    
    public int getRowCount() {
    	return row_count;
    }
    
    public int getColCount() {
    	return col_count;
    }
    
    public int getscore() {
    	return score;
    }
    
    public String getOP() {
    	return output_pattern;
    }
    
    public ArrayList<int[][]> getGrid() {
    	return t_grid;
    }
    
    public ArrayList<int[][]> getSurvive() {
    	return survive_grid;
    }
    
    public void clearGrid() {
    	t_grid.clear();
    }
    
    public void clearSurvive() {
    	survive_grid.clear();
    }

    
	
	public int readfile(File inputf) throws IOException {
		//1 for invalid grid 
		//2 for not matching column number
		//3 for not matching row number
		//0 for good
		/*
		 * make sure has three arguments: input file, output file, and number of steps
		 */
		
		//read input file
		Scanner input;
		int row_count = 0;
		int col_count = 0;
		int grid[][] = null;
//		int step = Integer.parseInt(args[2]);
//		String output_pattern = args[1];
		
//		String pathname = "D:\\twitter\\13_9_6\\dataset\\en\\input.txt"; //pathname 
//		File filename = new File(pathname); //read file from the path name above
		try {
			input = new Scanner(inputf);
			//input = new Scanner(new File(input));
			
			String first = input.nextLine();
			//find the row number 
			String[] row_c_col = first.split("[, ]+");
			
			if(row_c_col.length!=2) {
				return 1;
			}
			
			
			try {
				//change the row and column number from string to int
				row_count = Integer.parseInt(row_c_col[0]);
				col_count = Integer.parseInt(row_c_col[1]);
			}catch (NumberFormatException e) {
				return 1;
			}
			
			this.SetRowCount(row_count);
			
			this.SetColCount(col_count);
			//create a grid 
			grid = new int[row_count][col_count];
			
			int keep_row = 0;
			int keep_col = 0;
			
			//read each line from the input file
			while(input.hasNextLine()) 
			{
				String content=input.nextLine();
				for(int i=0; i<content.length(); i++) {
					if(content.charAt(i)!=',' && content.charAt(i)!=' ') {
						keep_col+=1;
					}
						
				}
				if(keep_col!=col_count) {
					return 2;
				}
				keep_col = 0;
				//fill the grid
				for(int i=0; i<content.length(); i++) {
					if(content.charAt(i)!=',' && content.charAt(i)!=' ') {
						grid[keep_row][keep_col]= Character.getNumericValue(content.charAt(i));
						keep_col+=1;
					}
				}
				
				keep_col = 0;
				keep_row+=1;
				//System.out.println(content);
			}
			if(keep_row!=row_count) {
				return 3;
			}
			//put the first grid into t_grid
			
			this.clearGrid();
			t_grid.add(grid);
			
//			//check the grid
//			for(int i =0; i<row_count; i++) {
//				for(int j=0; j<col_count; j++) {
//					System.out.print(grid[i][j]);
//					System.out.print(" ");
//				}
//				System.out.print("\n");
//			}
			input.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//transform(step, grid, row_count, col_count, output_pattern);

		return 0;
	}
	
}
