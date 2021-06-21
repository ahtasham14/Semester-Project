/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.*;

/**
 *
 * @author Ahtasham Sahi
 */
public class TicTacToe {

    public static void main(String[] args) {

        // Game class object
        Game game = new Game();

        Scanner scan = new Scanner(System.in);

        // 3x3 char type gameBoard
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};

        // some welcome notes
        System.out.println("<<<<<<<<<<<<<Welcome to Tic Tac Toe Game>>>>>>>>>>>");
        System.out.println("\t A Project by Ahtasham(046) and Burhan(088)");
        System.out.println();
        //game.displayGameBoard(gameBoard);
        System.out.println();
        System.out.println();

        // choice of difficulty for user
        System.out.println("*****Choose a Difficulty Level*****\n"
                + "\t Press 1 for Beginner\n"
                + "\t Press 2 for Amature\n"
                + "\t Press 3 for Professional");

        int choice = scan.nextInt();

        if (choice == 1) {

            beginnerLevel(gameBoard, game); // static method call
        } else if (choice == 2) {

            AmateurLevel(gameBoard, game); // static method call
        } else if (choice == 3) {

            proLevel(gameBoard, game); // static method call
        }
    } // main method ends

    // static method
    // logic for a low level game
    static void beginnerLevel(char[][] gameBoard, Game game) {

        Random rand = new Random();

        while (true) {

            Scanner input = new Scanner(System.in);
            System.out.println("Your Turn...\nEnter your placement (1-9)");
            int userPos = input.nextInt(); // user position entered

            // checking that the position is already not taken
            while (Game.userPlayer.contains(userPos) || Game.cpuPlayer.contains(userPos)) {

                System.out.println("Position Already Taken!!! Try Again");
                userPos = input.nextInt();
            }

            // placing move in gameBoard
            game.placeMove(gameBoard, userPos, "user");

            // printing gameboard
            game.displayGameBoard(gameBoard);
            // checking game is won, lost or draw
            String result = game.checkWinner();

            // printing Result if player win
            if (result.length() > 0) {

                System.out.println(result);
                break;
            }

            // generate random no for cpu
            int cpuPos = rand.nextInt(9) + 1;

            // checking list
            while (Game.userPlayer.contains(cpuPos) || Game.cpuPlayer.contains(cpuPos)) {

                cpuPos = rand.nextInt(9) + 1;

            }

            // placing cpu move in gameboard
            System.out.println("Computer's Move...");
            game.placeMove(gameBoard, cpuPos, "cpu");
            game.displayGameBoard(gameBoard); // printing gameboard
            System.out.println();

            // checking game status
            result = game.checkWinner();

            // printing game result if cpu wins
            if (result.length() > 0) {

                System.out.println(result);
                break;
            }
        }
    }

    // static method
    // logic for a High level game
    static void proLevel(char[][] gameBoard, Game game) {

        while (true) {

            // user input for player move
            Scanner input = new Scanner(System.in);
            System.out.println("Your Turn...\nEnter your placement (1-9)");
            int userPos = input.nextInt();

            // checking gameboard status
            while (Game.userPlayer.contains(userPos) || Game.cpuPlayer.contains(userPos)) {

                System.out.println("Position Already Taken!!! Try Again");
                userPos = input.nextInt();
            }

            // placing move
            game.placeMove(gameBoard, userPos, "user");
            // printing board
            game.displayGameBoard(gameBoard);
            // checking game result status
            String result = game.checkWinner();

            // printing game result if player win
            if (result.length() > 0) {

                System.out.println(result);
                break;
            }

            // cpu move
            // best move using bestMove method
            int cpuPos = game.proMove(gameBoard);
            // checking list
            while (Game.userPlayer.contains(cpuPos) || Game.cpuPlayer.contains(cpuPos)) {

                cpuPos = game.proMove(gameBoard);
            }

            // placing cpu perfect move using minimax
            System.out.println("Computer's Turn...");
            game.placeMove(gameBoard, cpuPos, "cpu");
            // displaying gameboard
            game.displayGameBoard(gameBoard);
            System.out.println();
            // checking game status
            result = game.checkWinner();

            // printing result if cpu win
            if (result.length() > 0) {

                System.out.println(result);
                break;
            }
        }
    }

    // static method
    // logic for a medium level game
    static void AmateurLevel(char[][] gameBoard, Game game) {

        while (true) {

            // user input for player move
            Scanner input = new Scanner(System.in);
            System.out.println("Your Turn...\nEnter your placement (1-9)");
            int userPos = input.nextInt();

            // checking gameboard status
            while (Game.userPlayer.contains(userPos) || Game.cpuPlayer.contains(userPos)) {

                System.out.println("Position Already Taken!!! Try Again");
                userPos = input.nextInt();
            }

            // placing move
            game.placeMove(gameBoard, userPos, "user");
            // printing board
            game.displayGameBoard(gameBoard);
            // checking game result status
            String result = game.checkWinner();

            // printing game result if player win
            if (result.length() > 0) {

                System.out.println(result);
                break;
            }

            // cpu move
            // greedy move using greedyMove method
            int cpuPos = game.greedyMove(gameBoard);
            // checking list
            while (Game.userPlayer.contains(cpuPos) || Game.cpuPlayer.contains(cpuPos)) {

                cpuPos = game.greedyMove(gameBoard);
            }

            // placing cpu greedy move for only one turn
            System.out.println("Computer's Turn...");
            game.placeMove(gameBoard, cpuPos, "cpu");
            // displaying gameboard
            game.displayGameBoard(gameBoard);
            System.out.println();
            // checking game status
            result = game.checkWinner();

            // printing result if cpu win
            if (result.length() > 0) {

                System.out.println(result);
                break;
            }
        }
    }
} // TEST CLASS ENDED

//********************* Class Move ***************************//
// class to store a perfect move
// using minimax
class Move {

    int row, col;
}

//******************** Class Game **************************//
// Game class
// methods with logics to play game
class Game {

    // 2 arraylists to store the positions chossen by 2 players
    // user player and cpu player
    static ArrayList<Integer> userPlayer = new ArrayList<>();
    static ArrayList<Integer> cpuPlayer = new ArrayList<>();

    // Method to print GameBoard
    public void displayGameBoard(char[][] gameBoard) {

        // transversing 2d array
        for (char[] row : gameBoard) {

            for (char b : row) {

                System.out.print(b);
            }
            System.out.println();
        }
    }

    // Method to place moves in gameBoard
    public void placeMove(char[][] gameBoard, int pos, String player) {

        char symbol = ' ';

        // giving symbols to user and cpu
        if (player.equals("user")) {
            symbol = 'x';
            userPlayer.add(pos);
        } else if (player.equals("cpu")) {
            symbol = 'o';
            cpuPlayer.add(pos);
        }
        // switch statement
        // knowing position to place in gameBoard
        switch (pos) {

            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    // Method to Check Game status
    public String checkWinner() {

        // definning conditions of winning
        // rows
        List toprow = Arrays.asList(1, 2, 3);
        List midrow = Arrays.asList(4, 5, 6);
        List botrow = Arrays.asList(7, 8, 9);
        // columns
        List leftcol = Arrays.asList(1, 4, 7);
        List midcol = Arrays.asList(2, 5, 8);
        List rightcol = Arrays.asList(3, 6, 9);
        // diagnols
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        // list to add winning scenario
        List<List> winning = new ArrayList<>();
        winning.add(toprow);
        winning.add(midrow);
        winning.add(botrow);
        winning.add(leftcol);
        winning.add(midcol);
        winning.add(rightcol);
        winning.add(cross1);
        winning.add(cross2);

        // printing statement of win, lose or draw
        for (List list : winning) {

            if (userPlayer.containsAll(list)) {
                return "Congratulations You Won!";
            } else if (cpuPlayer.containsAll(list)) {
                return "Hard Luck You Lost!";
            } else if (userPlayer.size() + cpuPlayer.size() == 9) {
                return "Draw!!!";
            }
        }
        return "";
    }

    // Method to check any available moves
    public Boolean isMoveAvailable(char[][] gameBoard) {

        // checkinf row and columns
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to evaluate game
    // returns +10 if cpu win
    // return -10 if user win
    // return 0 on draw
    public int evaluate(char[][] gameBoard) {

        // Checking rows for x or o win.
        for (int row = 0; row < 5; row++) {
            if (gameBoard[row][0] == gameBoard[row][2]
                    && gameBoard[row][2] == gameBoard[row][4]) {
                if (gameBoard[row][0] == 'o') {
                    return +10;
                } else if (gameBoard[row][0] == 'x') {
                    return -10;
                }
            }
        }

        // Checking columns for x or o win
        for (int col = 0; col < 5; col++) {
            if (gameBoard[0][col] == gameBoard[2][col]
                    && gameBoard[2][col] == gameBoard[4][col]) {
                if (gameBoard[0][col] == 'o') {
                    return +10;
                } else if (gameBoard[0][col] == 'x') {
                    return -10;
                }
            }
        }

        // Checking diagonals for x or o victory.
        if (gameBoard[0][0] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[4][4]) {
            if (gameBoard[0][0] == 'o') {
                return +10;
            } else if (gameBoard[0][0] == 'x') {
                return -10;
            }
        }

        if (gameBoard[0][4] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[4][0]) {
            if (gameBoard[0][4] == 'o') {
                return +10;
            } else if (gameBoard[0][4] == 'x') {
                return -10;
            }
        }

        // return 0 if no one wins
        return 0;
    }

    // minimax Method
    // considers all possible ways the game can go
    // cpu (o) is maximizer and user (x) is minimizer
    // returns value of the board
    public int minimax(char[][] gameBoard, int depth, Boolean isMax) {

        // variable to store value returned by evaluation method
        int result = evaluate(gameBoard);

        // if Maximizer has won game
        // return evaluated score
        if (result == 10) {
            return result;
        }

        // if Minimizer has won game
        // return evaluated score
        if (result == -10) {
            return result;
        }

        // if its tie return 0
        if (isMoveAvailable(gameBoard) == false) {
            return 0;
        }

        // if maximizer's move
        if (isMax) {
            // taking best score negative infinity
            int maxScore = -1000;

            // traversing all cells
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    // Check if cell is empty
                    if (gameBoard[i][j] == ' ') {
                        // Making the move
                        gameBoard[i][j] = 'o';

                        // Calling minimax recursively
                        int score = minimax(gameBoard, depth - 1, false);

                        // Undoing move
                        gameBoard[i][j] = ' ';

                        // finding best move for maximizer
                        maxScore = Math.max(score, maxScore);                      
                    }
                }
            }
            return maxScore; // returning
        } // if minimizer's move
        else {
            // taking best score positive infinity
            int maxScore = 1000;

            // traverse all cells
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    // Checking if cell is empty
                    if (gameBoard[i][j] == ' ') {
                        // Making move
                        gameBoard[i][j] = 'x';

                        // Calling minimax recursively
                        int score = minimax(gameBoard, depth - 1, true);

                        // Undoing move
                        gameBoard[i][j] = ' ';

                        // finding best score for minimizer
                        maxScore = Math.min(score, maxScore);
                    }
                }
            }
            return maxScore;// returning
        }
    }

    // Method to return best possible move for cpu
    public int proMove(char[][] gameBoard) {

        int maxScore = -1000;
        Move move = new Move();
        move.row = -1;
        move.col = -1;

        // traversing all cells
        //evaluating minimax function for all empty cells
        // returning the cell with optimal score
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Check if cell is empty
                if (gameBoard[i][j] == ' ') {
                    // Making the move
                    gameBoard[i][j] = 'o';

                    // computing evaluation function for this move
                    int score = minimax(gameBoard, 0, false);

                    // Unding the move
                    gameBoard[i][j] = ' ';

                    // if value of current move is more than the best score
                    // then update best score
                    if (score > maxScore) {
                        move.row = i;
                        move.col = j;
                        maxScore = score;
                    }
                }
            }
        }

        // converting arrays index into a number of gameBoard
        int pos;
        if (move.row == 0 && move.col == 0) {

            return pos = 1;

        } else if (move.row == 0 && move.col == 2) {

            return pos = 2;
        } else if (move.row == 0 && move.col == 4) {

            return pos = 3;
        } else if (move.row == 2 && move.col == 0) {

            return pos = 4;
        } else if (move.row == 2 && move.col == 2) {

            return pos = 5;
        } else if (move.row == 2 && move.col == 4) {

            return pos = 6;
        } else if (move.row == 4 && move.col == 0) {

            return pos = 7;
        } else if (move.row == 4 && move.col == 2) {

            return pos = 8;
        } else if (move.row == 4 && move.col == 4) {

            return pos = 9;
        }

        return 0;
    }

    // greedy Method
    // considers only next move
    // returns value of the board
    public int greedy(char board[][], int depth, Boolean isMax) {
        
         int score = evaluate(board);

        // if game has won
        // return evaluated score
        if (score == 10) {
            return score;
        }
        if (score == -10) {
            return score;
        }
        if (isMoveAvailable(board) == false) {
            return 0;
        }

        // for maximizers move
        if (isMax) {
            int val = -1000;

            // traverse all cells
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    // Check if cell is empty
                    if (board[i][j] == ' ') {
                        // Make  move
                        board[i][j] = 'o';

                        // Call greedy recursively 
                        val = Math.max(val, greedy(board, depth - 1, false));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return val;
        } // for minimizer's move
        else {
            int val = 1000;

            // traverse all cells
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    // Check if cell is empty
                    if (board[i][j] == ' ') {
                        // Make the move
                        board[i][j] = 'x';

                        // Call greedy recursively 
                        val = Math.min(val, greedy(board, depth - 1, true));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return val;
        }
    }

    // Method for a greedy move
    // return  possible next mov
    public int greedyMove(char board[][]) {
        
        int val = -1000;
        Move greedyMove = new Move();
        greedyMove.row = -1;
        greedyMove.col = -1;

        // Traverse all cells, evaluate greedy method for all empty cells
        // return a optimal value
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Check if cell is empty
                if (board[i][j] == ' ') {
                    // Make the move
                    board[i][j] = 'x';

                    // compute evaluation method
                    int moveVal = greedy(board, 0, false);

                    // Undoing the move
                    board[i][j] = ' ';

                    // if value of current move is more than next value
                    //then update the value
                    if (moveVal > val) {
                        greedyMove.row = i;
                        greedyMove.col = j;
                        val = moveVal;
                    }
                }
            }
        }

        // converting indexes into a position of gameboard
        int pos;
        if (greedyMove.row == 0 && greedyMove.col == 0) {

            return pos = 1;

        } else if (greedyMove.row == 0 && greedyMove.col == 2) {

            return pos = 2;
        } else if (greedyMove.row == 0 && greedyMove.col == 4) {

            return pos = 3;
        } else if (greedyMove.row == 2 && greedyMove.col == 0) {

            return pos = 4;
        } else if (greedyMove.row == 2 && greedyMove.col == 2) {

            return pos = 5;
        } else if (greedyMove.row == 2 && greedyMove.col == 4) {

            return pos = 6;
        } else if (greedyMove.row == 4 && greedyMove.col == 0) {

            return pos = 7;
        } else if (greedyMove.row == 4 && greedyMove.col == 2) {

            return pos = 8;
        } else if (greedyMove.row == 4 && greedyMove.col == 4) {

            return pos = 9;
        }

        return 0;
    }

}// GAME CLASS ENDED


//*************************** END *******************************//
