public class BattleshipGame {
    private Board board;
    private int missCounter;
    private int strikeCounter;
    private int totalMissCounter;
    private int totalHitCounter;
    private boolean gameOver;

    public BattleshipGame() {
        resetGame();
    }

    public void resetGame() {
        board = new Board();
        missCounter = 0;
        strikeCounter = 0;
        totalMissCounter = 0;
        totalHitCounter = 0;
        gameOver = false;
    }

    public String fireAt(int row, int col) {
        if (gameOver) return "GAMEOVER";

        Ship hitShip = board.fire(row, col);
        if (hitShip != null) {
            totalHitCounter++;
            missCounter = 0;
            if (hitShip.isSunk()) {
                if (totalHitCounter == 17) {
                    gameOver = true;
                    return "WIN";
                }
                return "SUNK";
            }
            return "HIT";
        } else {
            missCounter++;
            totalMissCounter++;
            if (missCounter >= 5) {
                strikeCounter++;
                missCounter = 0;
                if (strikeCounter >= 3) {
                    gameOver = true;
                    return "LOSS";
                }
                return "STRIKE";
            }
            return "MISS";
        }
    }

    public int getMissCounter() { return missCounter; }
    public int getStrikeCounter() { return strikeCounter; }
    public int getTotalMissCounter() { return totalMissCounter; }
    public int getTotalHitCounter() { return totalHitCounter; }
    public boolean isGameOver() { return gameOver; }
}