import java.util.*;
public class Sudoku_Solver
{
    int size = 9;
    public void solveSudoku(char[][] board) {
        solveBoard(board);
        print_board(board);
    }
    private void print_board(char board[][])
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                System.out.print("  "+board[i][j]);
            }
            System.out.println();
        }
    }
    private boolean isNumberInRow(char board[][],int number,int row)
    {
        for(int i=0;i<size;i++)
        {
            if(board[row][i] == (char)('0' + number))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isNumberInColumn(char board[][],int number,int column)
    {
        for(int i=0;i<size;i++)
        {
            if(board[i][column] == (char)('0' + number))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isNumberInBox(char board[][],int number,int row,int column)
    {
        int Local_Row = row - row % 3;
        int Local_Column = column - column % 3;
        for(int i=Local_Row;i<Local_Row+3;i++)
        {
            for(int j=Local_Column;j<Local_Column+3;j++)
            {
                if(board[i][j] == (char)('0' + number))
                {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValidPlacement(char board[][],int number,int row,int column)
    {
        return(!isNumberInRow(board,number,row) && !isNumberInColumn(board,number,column) && !isNumberInBox(board,number,row,column));
    }
    private boolean solveBoard(char board[][])
    {
        for(int row=0;row<size;row++)
        {
            for(int column=0;column<size;column++)
            {
                if(board[row][column] == '.')
                {
                    for(int NumberToTry=1;NumberToTry<=size;NumberToTry++)
                    {
                        if(isValidPlacement(board,NumberToTry,row,column))
                        {
                            board[row][column] = (char)('0' + NumberToTry);
                            if(solveBoard(board))
                            {
                                return true;
                            }
                            else
                            {
                                board[row][column] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String [] args)
    {
        Scanner s1 = new Scanner(System.in);
        char input[][] = new char[9][9];
        System.out.println("Enter the Suduko Question type . for the place where the number is not given");
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                char ch = s1.next().charAt(0);
                if((Character.isDigit(ch) && ch != '0') || ch == '.')
                {
                    input[i][j] = ch;
                }
                else
                {
                    System.out.println("Invalid Input please enter a digit or .");
                    j--;
                }
            }
        }
        Sudoku_Solver obj = new Sudoku_Solver();
        obj.solveSudoku(input);
        s1.close();
    }
}