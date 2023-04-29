public class K3 {
    private int[][] board;
    private int currentPlayer;

    public K3() {
        board = new int[3][3];
        currentPlayer = 1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getBoardValue(int row, int col) {
        return board[row][col];
    }

    public void setBoardValue(int row, int col, int value) {
        board[row][col] = value;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }
}
