public class BattleshipGame {

    private GameBoard gameBoard;
    private int totalHits;
    private int totalMisses;
    private int currentMisses;
    private int strikes;

    public BattleshipGame() {
        gameBoard = new GameBoard();
        totalHits = 0;
        totalMisses = 0;
        currentMisses = 0;
        strikes = 0;
    }

    public boolean fireMissile(int x, int y) {
        Cell cell = gameBoard.getCell(x, y);
        if (cell.getDisplay().equals("")) {
            boolean hit = gameBoard.checkHit(x, y);
            if (hit) {
                cell.setDisplay("X");
                totalHits++;
                currentMisses = 0;
            } else {
                cell.setDisplay("M");
                totalMisses++;
                currentMisses++;
                if (currentMisses >= 5) {
                    strikes++;
                    currentMisses = 0;
                }
            }
            return hit;
        }
        return false;
    }

    public boolean allShipsSunk() {
        return gameBoard.getShips().stream().allMatch(Ship::isSunk);
    }

    public boolean isGameOver() {
        return strikes >= 3;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getTotalMisses() {
        return totalMisses;
    }

    public int getCurrentMisses() {
        return currentMisses;
    }

    public int getStrikes() {
        return strikes;
    }
}
