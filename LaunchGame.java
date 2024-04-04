package Tic_tac_toe;

import java.util.Random;
import java.util.Scanner;

class TicTacToe{
    static char[][] board; // declare array with variable board
    public TicTacToe(){
        board=new char[3][3];
        intitboard();
                             //initialization of array
    }
    void intitboard(){
        for(int i=0;i<board.length;i++){   
// for i loop for rows and j loop to iterate throgh columns.and board initialize to null
            for(int j=0;j<board[i].length;j++)
            {
                board[i][j]=' ';

            }

        }
    }
    // create table of 3 x 3 matrix 
    static void displayBoard(){    
        System.out.println("-------------");
        for(int i=0;i<board.length;i++){  
            System.out.print("| "); //before row started print line
    
         for(int j=0;j<board[i].length;j++)
         {
             System.out.print(board[i][j]+" | ");//after that box close with |
         }
         System.out.println();
         System.out.println("-------------");
                    
        }

    }

    //
    static void placeMark(int row,int col,char mark){
        //check condition if we are entering at the correct position if enter index out of bounds it shows error
        if(row>=0 && row<=2 && col>=0 && col<=2){
            board[row][col]= mark;
        }
        else{
            System.out.println("Invalid position");
        }

    }
    //checking column win
    static boolean checkColwin(){
        for(int j=0;j<=2;j++){
            if(board[0][j]!=' '&& board[0][j]==board[1][j] && board[1][j]==board[2][j])
            //if row=0,col=0 or row=1,col=0,or row=3,col=3
            {
                return true;
            }
        }
        return false;   //nobody won in column wise
    }
    
    static boolean checkRowWin(){
        for(int i=0;i<=2;i++){
            if( board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]){
                return true;
            }

        }
        return false;
    }
    //checking diagnol win
    static boolean checkDiagWin()
    {
        if( board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] 
        || board[0][2]!= ' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0])
        {
            return true;
        }
        else{
            return false;
        }
}
    static boolean checkDraw(){
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }
}
abstract class Player{
    String name;
    char mark;

    abstract void makeMove();

    boolean isValidMove(int row,int col){
        if(row>=0 && row <=2 && col>=0 && col<=2)
        {
            if(TicTacToe.board[row][col]==' '){
                return true;
            }

        }
        return false;
    }
}
class HumanPlayer extends Player{
    
    HumanPlayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        Scanner scan=new Scanner(System.in);
        int row,col;
        do{
            System.out.println("Enter the row and col");
            row=scan.nextInt();
            col=scan.nextInt();

        }while(!isValidMove(row, col)); //it is true means it takes input from the user
        TicTacToe.placeMark(row, col, mark);

    }
   
   
}
class AIPlayer extends Player{
    
    AIPlayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        Scanner scan=new Scanner(System.in);
        int row,col;
        do{
            Random r=new Random();
            row=r.nextInt(3);
            col=r.nextInt(3);// y 3 means 3 boundary excluded in the random

        }while(!isValidMove(row, col)); //it is true means it takes input from the user
        TicTacToe.placeMark(row, col, mark);

    }
    
}
public class LaunchGame {
    public static void main(String[] args){
        TicTacToe t=new TicTacToe();
        HumanPlayer p1=new HumanPlayer("Chandana", 'X');
        AIPlayer p2=new AIPlayer("TAI", 'O');
        //refernece player not a object
        Player cp;
        cp=p1; // referencing current player to p1
        while(true){
            System.out.println(cp.name +" "+ "turn");
            cp.makeMove();
            TicTacToe.displayBoard();
            if(TicTacToe.checkColwin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin() ){
            System.out.println(cp.name+" " +"has won");
            break;
            }
            else if(TicTacToe.checkDraw()){
                System.out.println("Game is a draw");
                break;
            }
            else{
                if(cp==p1){
                cp = p2;

            }
                else{
                cp =p1;
            }
            }
        }
    }
    
}
