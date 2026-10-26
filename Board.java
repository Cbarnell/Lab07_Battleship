import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private static final int SIZE = 10;
    private final int[][] grid; // 0 = Empty, 1 = Ship, 2 = Miss, 3 = Hit
    private final List<Ship> ships;
    private final Random random;

    public Board() {
        grid = new int[SIZE][SIZE];
        ships = new ArrayList<>();
        random = new Random();
        placeShips();
    }

    private void placeShips() {
        int[] shipSizes = {5, 4, 3, 3, 2};
        for (int size : shipSizes) {
            Ship ship = new Ship(size);
            boolean placed = false;
            while (!placed) {
                boolean horizontal = random.nextBoolean();
                int row = random.nextInt(SIZE);
                int col = random.nextInt(SIZE);

                if (canPlace(ship, row, col, horizontal)) {
                    ship.setPosition(row, col, horizontal);
                    applyPlacement(ship, row, col, horizontal);
                    ships.add(ship);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlace(Ship ship, int row, int col, boolean horizontal) {
        int len = ship.getSize();
        if (horizontal) {
            if (col + len > SIZE) return false;
            for (int c = col; c < col + len; c++) {
                if (grid[row][c] != 0) return false;
            }
        } else {
            if (row + len > SIZE) return false;
            for (int r = row; r < row + len; r++) {
                if (grid[r][col] != 0) return false;
            }
        }
        return true;
    }

    private void applyPlacement(Ship ship, int row, int col, boolean horizontal) {
        int len = ship.getSize();
        if (horizontal) {
            for (int c = col; c < col + len; c++) {
                grid[row][c] = 1;
            }
        } else {
            for (int r = row; r < row + len; r++) {
                grid[r][col] = 1;
            }
        }
    }

    public Ship fire(int row, int col) {
        for (Ship ship : ships) {
            if (ship.occupies(row, col)) {
                ship.hit();
                grid[row][col] = 3;
                return ship;
            }
        }
        grid[row][col] = 2;
        return null;
    }

}