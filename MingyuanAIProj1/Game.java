import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game of checkers");
        System.out.println("Your move input would be of the form D1-C2, captures moves would be of the form D1xB3");
        System.out.println("What size of a board do you wish to play on?");
        int boardSize = scanner.nextInt();
        while (true){
            if (boardSize!=4 && boardSize!=8){
                System.out.println("We only support size 4 or 8");
                boardSize = scanner.nextInt();
            }
            else break;
        }

        if (boardSize == 4) {
            System.out.println("You have the choice of MINIMAX(1) H-MINIMAX(2) RANDOM(3), ABPruning(4), which opponent would you play?");
            int option = scanner.nextInt();
            if (option == 1) {
                ChessBoard board = new ChessBoard(4);
                while (true) {
                    board = board.checkKing(board);
                    board.printBoard();

                    ChessBoard copy = new ChessBoard(4);
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            copy.board.get(i).set(j, board.board.get(i).get(j));
                        }
                    }
                    String aiMove = Agent.minMaxDecision(board);
                    if (aiMove.equals("")){
                        aiMove = Agent.actions(copy, ChessBoard.types.black).get(0);
                    }
                    System.out.println("Your opponent made the move " + aiMove);
                    copy = Agent.result(copy, aiMove);
                    copy = ChessBoard.checkKing(copy);
                    copy.printBoard();
                    board = copy;
                    if (Agent.terminalTest(board)) {
                        System.out.println("The game has concluded");
                        break;
                    }
                    System.out.println("Please make a move");
                    String move = scanner.next();
                    board = Agent.result(board, move);
                    System.out.println(Agent.actions(board, ChessBoard.types.black));


                }

            } else if (option == 2) {
                System.out.println("The default search depth is 10");
                ChessBoard board = new ChessBoard(4);
                while (true) {
                    board = board.checkKing(board);
                    board.printBoard();

                    ChessBoard copy = new ChessBoard(4);
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            copy.board.get(i).set(j, board.board.get(i).get(j));
                        }
                    }
                    String aiMove = Agent.hMinMaxDecision(board, 10);
                    if (aiMove.equals("")){
                        aiMove = Agent.actions(copy, ChessBoard.types.black).get(0);
                    }
                    System.out.println("Your opponent made the move " + aiMove);
                    copy = Agent.result(copy, aiMove);
                    copy = ChessBoard.checkKing(copy);
                    copy.printBoard();
                    board = copy;
                    if (Agent.terminalTest(board)) {
                        System.out.println("The game has concluded");
                        break;
                    }
                    System.out.println("Please make a move");
                    String move = scanner.next();
                    board = Agent.result(board, move);
                    System.out.println(Agent.actions(board, ChessBoard.types.black));


                }
            } else if (option == 3) {
                ChessBoard board = new ChessBoard(4);
                while (true) {
                    board = board.checkKing(board);
                    board.printBoard();
                    board = Agent.random(board);
                    System.out.println("The agent has made a random move");
                    board.printBoard();
                    if (Agent.terminalTest(board)) {
                        System.out.println("The game has concluded");
                        break;
                    }
                    System.out.println("Please make a move");
                    String move = scanner.next();
                    board = Agent.result(board, move);
                    System.out.println(Agent.actions(board, ChessBoard.types.black));

                }
            }else if (option == 4) {
                ChessBoard board = new ChessBoard(4);
                while (true) {
                    board = board.checkKing(board);
                    board.printBoard();

                    ChessBoard copy = new ChessBoard(4);
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            copy.board.get(i).set(j, board.board.get(i).get(j));
                        }
                    }
                    String aiMove = Agent.alphabeta(board);
                    if (aiMove.equals("")){
                        aiMove = Agent.actions(copy, ChessBoard.types.black).get(0);
                    }
                    System.out.println("Your opponent made the move " + aiMove);
                    copy = Agent.result(copy, aiMove);
                    copy = ChessBoard.checkKing(copy);
                    copy.printBoard();
                    board = copy;
                    if (Agent.terminalTest(board)) {
                        System.out.println("The game has concluded");
                        break;
                    }
                    System.out.println("Please make a move");
                    String move = scanner.next();
                    board = Agent.result(board, move);
                    System.out.println(Agent.actions(board, ChessBoard.types.black));
                }
            }
        }else if (boardSize == 8){
            System.out.println("You have the choice of H-MINIMAX(1) RANDOM(2) which opponent would you play?");
            int option = scanner.nextInt();
            if (option == 1){
                System.out.println("We would use H-MINIMAX to test the moves, the default search depth is 10");
                ChessBoard board = new ChessBoard(8);
                while (true) {
                    board = board.checkKing(board);

                    ChessBoard copy = new ChessBoard(8);
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            copy.board.get(i).set(j, board.board.get(i).get(j));
                        }
                    }
                    String aiMove = Agent.hMinMaxDecision(board, 10);
                    if (aiMove.equals("")){
                        aiMove = Agent.actions(copy, ChessBoard.types.black).get(0);
                    }
                    System.out.println("Your opponent made the move " + aiMove);
                    copy = Agent.result(copy, aiMove);
                    copy = ChessBoard.checkKing(copy);
                    copy.printBoard();
                    board = copy;
                    if (Agent.terminalTest(board)) {
                        System.out.println("The game has concluded");
                        break;
                    }
                    System.out.println("Please make a move");
                    String move = scanner.next();
                    board = Agent.result(board, move);
                    board.printBoard();
                    System.out.println(Agent.actions(board, ChessBoard.types.black));
                }
            }
            else if (option == 2){
                ChessBoard board = new ChessBoard(8);
                while (true) {
                    board = board.checkKing(board);
                    board.printBoard();
                    board = Agent.random(board);
                    System.out.println("The agent has made a random move");
                    board.printBoard();
                    if (Agent.terminalTest(board)) {
                        System.out.println("The game has concluded");
                        break;
                    }
                    System.out.println("Please make a move");
                    String move = scanner.next();
                    board = Agent.result(board, move);
                    System.out.println(Agent.actions(board, ChessBoard.types.black));
                }
            }
        }
    }
}
