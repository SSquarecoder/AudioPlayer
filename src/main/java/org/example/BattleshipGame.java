package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class BattleshipGame {

    private static final int board_size = 10;
    private static final char empty_cell = 'X';
    private static final char hit_cell = 'H';
    private static final char miss_cell = 'M';
    private static final int ship_size = 3;

    private static char[][] player1Board = new char[board_size][board_size];
    private static char[][] player2Board = new char[board_size][board_size];
    private static String player1Name;
    private static String player2Name;

    private static int player1ShipsRemaining = 3;
    private static int player2ShipsRemaining = 3;

    public static void main(String[] args) {
        initializeBoard(player1Board);
        initializeBoard(player2Board);

        Scanner scanner = new Scanner(System.in);

        // Get player names
        System.out.print("Enter Player 1 name: ");
        setPlayer1Name(scanner.nextLine());
        System.out.print("Enter Player 2 name: ");
        setPlayer2Name(scanner.nextLine());

        // Place ships
        System.out.println(getPlayer1Name() + ", place your ships:");
        placeShips(scanner, player1Board);
        System.out.println(getPlayer2Name() + ", place your ships:");
        placeShips(scanner, player2Board);

        // Start game
        boolean gameOver = false;
        while (!gameOver) {
            gameOver = playerTurn(scanner, getPlayer1Name(), player2Board, getPlayer2Name(), getPlayer2ShipsRemaining());
            if (!gameOver) {
                gameOver = playerTurn(scanner, getPlayer2Name(), player1Board, getPlayer1Name(), getPlayer1ShipsRemaining());
            }
        }
        scanner.close();
    }

    private static void initializeBoard(char[][] board) {
        for (char[] row : board) {
            Arrays.fill(row, empty_cell);
        }
    }

    private static void placeShips(Scanner scanner, char[][] board) {
        for (int shipNum = 1; shipNum <= 3; shipNum++) {
            boolean validPlacement = false;
            while (!validPlacement) {
                System.out.print("Enter coordinates for ship " + shipNum + " (e.g., 1,1 1,2 1,3): ");
                String[] coordinates = scanner.nextLine().split(" ");
                validPlacement = placeShip(board, coordinates);
                if (!validPlacement) {
                    System.out.println("Invalid ship placement. Coordinates must be consecutive and within the board. Try again.");
                }
            }
        }
    }

    private static boolean placeShip(char[][] board, String[] coordinates) {
        if (coordinates.length != ship_size) return false;

        int[][] parsedCoordinates = new int[ship_size][2];

        for (int i = 0; i < ship_size; i++) {
            String[] coord = coordinates[i].split(",");
            if (coord.length != 2) return false;

            int x, y;
            try {
                x = Integer.parseInt(coord[0]) - 1;
                y = Integer.parseInt(coord[1]) - 1;
                if (x < 0 || x >= board_size || y < 0 || y >= board_size || board[x][y] != empty_cell) {
                    return false;
                }
                parsedCoordinates[i][0] = x;
                parsedCoordinates[i][1] = y;
            } catch (NumberFormatException e) {
                return false; // Invalid number format
            }
        }

        // Check if coordinates are consecutive
        boolean isValid = true;
        boolean isHorizontal = parsedCoordinates[0][0] == parsedCoordinates[1][0];
        boolean isVertical = parsedCoordinates[0][1] == parsedCoordinates[1][1];

        for (int i = 1; i < ship_size; i++) {
            if (isHorizontal) {
                if (parsedCoordinates[i][0] != parsedCoordinates[i - 1][0] || parsedCoordinates[i][1] != parsedCoordinates[i - 1][1] + 1) {
                    isValid = false;
                    break;
                }
            } else if (isVertical) {
                if (parsedCoordinates[i][1] != parsedCoordinates[i - 1][1] || parsedCoordinates[i][0] != parsedCoordinates[i - 1][0] + 1) {
                    isValid = false;
                    break;
                }
            } else {
                isValid = false;
                break;
            }
        }

        if (!isValid) return false;

        for (int i = 0; i < ship_size; i++) {
            board[parsedCoordinates[i][0]][parsedCoordinates[i][1]] = 'S'; // Mark ships as 'S'
        }
        return true;
    }

    private static boolean playerTurn(Scanner scanner, String currentPlayerName, char[][] opponentBoard, String opponentName, int opponentShipsRemaining) {
        boolean validAttack = false;
        while (!validAttack) {
            System.out.print(currentPlayerName + ", enter the coordinates to attack (e.g., 1,1): ");
            String[] input = scanner.nextLine().split(",");
            if (input.length != 2) {
                System.out.println("Invalid input format. Please enter coordinates as row,column.");
                continue;
            }

            int x;
            int y;
            try {
                x = Integer.parseInt(input[0]) - 1;
                y = Integer.parseInt(input[1]) - 1;
                if (x < 0 || x >= board_size || y < 0 || y >= board_size) {
                    System.out.println("Coordinates out of bounds. Please enter values between 1 and " + board_size + ".");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter valid integers.");
                continue;
            }

            if (opponentBoard[x][y] == 'S') { // Check for ship directly
                System.out.println("Hit!");
                opponentBoard[x][y] = hit_cell;
                if (checkForSunkShip(opponentBoard, x, y)) {
                    // Correctly update remaining ships count
                    if (currentPlayerName.equals(getPlayer1Name())) {
                        setPlayer2ShipsRemaining(getPlayer2ShipsRemaining() - 1);
                    } else {
                        setPlayer1ShipsRemaining(getPlayer1ShipsRemaining() - 1);
                    }
                    System.out.println("You sunk a ship! " + getRemainingShips(opponentName) + " ships remaining.");
                    if (getRemainingShips(opponentName) == 0) {
                        System.out.println(currentPlayerName + " wins!");
                        return true;
                    }
                }
            } else if (opponentBoard[x][y] == empty_cell) {
                System.out.println("Miss!");
                opponentBoard[x][y] = miss_cell;
            } else {
                System.out.println("You already attacked this coordinate. Try again.");
                continue;
            }

            validAttack = true; // End the loop after a successful attack
        }

        return false;
    }

    private static boolean checkForSunkShip(char[][] board, int hitX, int hitY) {
        return checkDirection(board, hitX, hitY, 1, 0) || // Horizontal
                checkDirection(board, hitX, hitY, 0, 1);  // Vertical
    }

    private static boolean checkDirection(char[][] board, int hitX, int hitY, int dx, int dy) {
        int hits = 0;

        // Check in both directions from the hit point
        for (int i = -2; i <= 2; i++) {
            int x = hitX + i * dx;
            int y = hitY + i * dy;

            if (x >= 0 && x < board_size && y >= 0 && y < board_size) {
                if (board[x][y] == hit_cell) {
                    hits++;
                } else if (board[x][y] == 'S') { // Check for remaining ship cells
                    return false; // Not all parts of the ship have been hit
                }
            }
        }
        return hits == ship_size; // The ship is sunk if all parts have been hit
    }

    // Getters and Setters

    public static String getPlayer1Name() {
        return player1Name;
    }

    public static void setPlayer1Name(String player1Name) {
        BattleshipGame.player1Name = player1Name;
    }

    public static String getPlayer2Name() {
        return player2Name;
    }

    public static void setPlayer2Name(String player2Name) {
        BattleshipGame.player2Name = player2Name;
    }

    public static int getPlayer1ShipsRemaining() {
        return player1ShipsRemaining;
    }

    public static void setPlayer1ShipsRemaining(int player1ShipsRemaining) {
        BattleshipGame.player1ShipsRemaining = player1ShipsRemaining;
    }

    public static int getPlayer2ShipsRemaining() {
        return player2ShipsRemaining;
    }

    public static void setPlayer2ShipsRemaining(int player2ShipsRemaining) {
        BattleshipGame.player2ShipsRemaining = player2ShipsRemaining;
    }

    private static int getRemainingShips(String playerName) {
        if (playerName.equals(getPlayer1Name())) {
            return getPlayer1ShipsRemaining();
        } else {
            return getPlayer2ShipsRemaining();
        }
    }
}