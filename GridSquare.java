public class GridSquare {
  public int state; 
  public int pos;

  public GridSquare(int x) {
    pos = x; 
    state = -1; 
  }

  public char drawSpace() { 
    if (state == 0) {
      return 'O'; 
    }
    if (state == 1) { 
      return 'X';
    }  
    return Integer.toString(pos).charAt(0); 
  }
  
}