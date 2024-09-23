import java.util.Scanner;

public class TicTacToe {
  int cols = 3;
  int rows = 3;
  int totalTurns = 0;
  int winner = -1;
  
  enum GameState {
      OVER, RUNNING
  }
  static GameState currentState = GameState.OVER;
  
  GridSquare[][] board;
  
  public void setup() {
      board = new GridSquare[rows][cols]; 
      int position = 1;
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) { 
           board[r][c] = new GridSquare(position);
           position++; 
        }
      }
        //System.out.println(board[1][2].pos);
      currentState = GameState.RUNNING;
      playGame();
  } 
  
  public void playGame(){
    while (currentState == GameState.RUNNING) {
      displayBoard();
      makeMove();
    }
    if (currentState == GameState.OVER) {
      displayGameOver();
    }
  }
  
  public void displayBoard() {
    System.out.println("\n  " + board[0][0].drawSpace() + " | " + board[0][1].drawSpace() + "  | " + board[0][2].drawSpace());
    System.out.println(" ___|____|___ "); 

    System.out.println("\n  " + board[1][0].drawSpace() + " | " + board[1][1].drawSpace() + "  | " + board[1][2].drawSpace());
    System.out.println(" ___|____|___ "); 

    System.out.println("\n  " + board[2][0].drawSpace() + " | " + board[2][1].drawSpace() + "  | " + board[2][2].drawSpace());
    System.out.println(" ___|____|___ "); 

    System.out.println("\n");
  }
  
  public void makeMove() {
    System.out.println("Player " + getPlayer() + " choose a position: ");
    Scanner in = new Scanner(System.in); 
    int spot = in.nextInt();

    for (int i = 0; i < cols; i++) { 
      for (int j = 0; j < rows; j++) { 
         if (board[i][j].state == -1 && board[i][j].pos == spot) {
            board[i][j].state = totalTurns % 2; 
            totalTurns++;
            checkWin(i, j, board[i][j].state);
        }
      }
    }
  }
  
  public void displayGameOver() {
    displayBoard();
    System.out.println("Game over");

    if (winner == 0){
      System.out.println("Play O wins");
    }
    if (winner == 2) {
      System.out.println("Player X wins");
    }
    else if (totalTurns == 9) {
      System.out.println("Tie game");
    }
  }

  public char getPlayer() { 
    if (totalTurns % 2 == 0) { 
      return 'O'; 
    }
    else{
     return 'X';
    }
  } 

  public void checkWin(int x, int y, int turn){ 
    int colWin = 0;
    int rowWin = 0;
    int diagWinR = 0;
    int diagWinL = 0;

    for (int i = 0; i < 3; i++){
     if (board[x][i].state == turn){
        colWin++;
     }
     if (board[i][y].state == turn){
        rowWin++;
     }
     if (board[i][i].state == turn){
        diagWinR++;
     }
     if (board[i][2 - i].state == turn){
        diagWinL++;      
     }
    }

    if(colWin == 3 || rowWin == 3 || diagWinR == 3 || diagWinL == 3) { 
      winner = turn; 
      if (winner != -1) {
        currentState = GameState.OVER;
      }
    } 
    if (totalTurns == 9) { 
      currentState = GameState.OVER;
    } 
  }
}
