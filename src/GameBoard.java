import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {

    private final int BOARD_SIZE = 10;
    private Cell[][] cells;
    private List<Ship> ships;

    public GameBoard() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                cells[r][c] = new Cell();
            }
        }

        ships = new ArrayList<>();
        placeShips();
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public boolean checkHit(int x, int y) {
        return cells[x][y].hasShip();
    }

    public void placeShips() {
        int[] shipSizes = {5, 4, 3, 3, 2};

        Random rnd = new Random();
        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                boolean vertical = rnd.nextBoolean();
                int row, col;

                if(vertical) {
                    row = rnd.nextInt(BOARD_SIZE - size +1);
                    col = rnd.nextInt(BOARD_SIZE);
                } else {
                    row = rnd.nextInt(BOARD_SIZE);
                    col = rnd.nextInt(BOARD_SIZE - size +1);
                }

                if(canPlaceShip(size, row, col, vertical)) {
                    placeShip(size, row, col, vertical);
                    Ship ship = new Ship(size);
                    ships.add(ship);
                    placed = true;
                }
            }
        }
    }

    public boolean canPlaceShip(int size, int row, int col, boolean vertical) {
        for (int r = 0; r < size; r++) {
            if (vertical) {
                if (cells[row +r][col].hasShip()) {
                    return false;
                } else {
                    if (cells[row][col + r].hasShip()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void placeShip(int size, int row, int col, boolean vertical) {
        for (int r = 0; r < size; r++) {
            if (vertical) {
                cells[row + r][col].placeShip();
            } else {
                cells[row][col + r].placeShip();
            }
        }
    }

    public List<Ship> getShips() {
        return ships;
    }
}
