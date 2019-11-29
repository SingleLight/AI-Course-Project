import java.util.ArrayList;
import java.util.Random;

public class Agent {

    public static int[] stringToCor(String a)
    {

        char[] temp =a.toCharArray();
        int y=0;
        switch(temp[0]) {
            case 'A':
                y=0;
                break;
            case 'B':
                y=1;
                break;
            case 'C':
                y=2;
                break;
            case 'D':
                y=3;
                break;
            case 'E':
                y=4;
                break;
            case 'F':
                y=5;
                break;
            case 'G':
                y=6;
                break;
            case 'H':
                y=7;
                break;
        }
        int x=Character.getNumericValue(temp[1])-1;
        int x1=Character.getNumericValue(temp[4])-1;
        int y1=0;
        switch(temp[3]) {
            case 'A':
                y1=0;
                break;
            case 'B':
                y1=1;
                break;
            case 'C':
                y1=2;
                break;
            case 'D':
                y1=3;
                break;
            case 'E':
                y1=4;
                break;
            case 'F':
                y1=5;
                break;
            case 'G':
                y1=6;
                break;
            case 'H':
                y1=7;
                break;
        }
        int n=0;
        if(temp[2]=='-'){
            n=1;
        }else if(temp[2]=='x'){
            n=2;
        }
        int cor[]={x,y,x1,y1,n};
        return cor;
    }

    public static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }

    public static ChessBoard result(ChessBoard board, String action){
        if (action.length()==0){
            return board;
        }
        int[] cor = Agent.stringToCor(action);
        ChessBoard.types currType = board.board.get(cor[1]).get(cor[0]);
        board.board.get(cor[1]).set(cor[0], ChessBoard.types.empty);
        board.board.get(cor[3]).set(cor[2],currType);
        if (cor[4] == 2) {
            board.board.get((cor[1] + cor[3])/2).set((cor[0] + cor[2])/2, ChessBoard.types.empty);
        }
        return board;
    }

    public static ArrayList<String> actions(ChessBoard board, ChessBoard.types turn){
        ArrayList<String> captures = new ArrayList<>();
        ArrayList<String> moves = new ArrayList<>();
        switch (turn){
            case black:
                for (int i = 0; i < board.board.size() ; i++) {
                    for (int j = 0; j < board.board.size() ; j++) {
                        if (board.board.get(i).get(j) == ChessBoard.types.black){
                            String actionOne = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j);
                            String actionTwo = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j+2);
                            String actionThree = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j-1);
                            String actionFour = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j+3);
//                            System.out.println(actionOne);
//                            System.out.println(actionTwo);
//                            System.out.println(actionThree);
//                            System.out.println(actionFour);
                            if (ChessBoard.LegalMove(actionOne,board)){
                                moves.add(actionOne);
                            }
                            if (ChessBoard.LegalMove(actionTwo,board)){
                                moves.add(actionTwo);
                            }
                            if (ChessBoard.Legalcapture(actionThree,board)){
                                captures.add(actionThree);
                            }
                            if (ChessBoard.Legalcapture(actionFour,board)){
                                captures.add(actionFour);
                            }
                        }
                    }
                }

                for (int i = 0; i < board.board.size() ; i++) {
                    for (int j = 0; j < board.board.size() ; j++) {
                        if (board.board.get(i).get(j) == ChessBoard.types.blackKing){
                            String actionOne = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j);
                            String actionTwo = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j+2);
                            String actionThree = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j-1);
                            String actionFour = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j+3);
                            String actionFive = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j);
                            String actionSix = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j+2);
                            String actionSeven = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i-1) + (j+3);
                            String actionEight = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i-1) + (j-1);
                            if (ChessBoard.LegalMove(actionOne,board)){
                                moves.add(actionOne);
                            }
                            if (ChessBoard.LegalMove(actionTwo,board)){
                                moves.add(actionTwo);
                            }
                            if (ChessBoard.Legalcapture(actionThree,board)){
                                captures.add(actionThree);
                            }
                            if (ChessBoard.Legalcapture(actionFour,board)){
                                captures.add(actionFour);
                            }
                            if (ChessBoard.LegalMove(actionFive,board)){
                                moves.add(actionFive);
                            }
                            if (ChessBoard.LegalMove(actionSix,board)){
                                moves.add(actionSix);
                            }
                            if (ChessBoard.Legalcapture(actionSeven,board)){
                                captures.add(actionSeven);
                            }
                            if (ChessBoard.Legalcapture(actionEight,board)){
                                captures.add(actionEight);
                            }
                        }
                    }
                }
                if (captures.size() != 0){
                    return captures;
                }else if (moves.size() != 0){
                    return moves;
                }else return captures;
            case white:
                for (int i = 0; i < board.board.size() ; i++) {
                    for (int j = 0; j < board.board.size() ; j++) {
                        if (board.board.get(i).get(j) == ChessBoard.types.white){
                            String actionOne = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j);
                            String actionTwo = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j+2);
                            String actionThree = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+1) + (j-1);
                            String actionFour = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+1) + (j+3);
                            if (ChessBoard.LegalMove(actionOne,board)){
                                moves.add(actionOne);
                            }
                            if (ChessBoard.LegalMove(actionTwo,board)){
                                moves.add(actionTwo);
                            }
                            if (ChessBoard.Legalcapture(actionThree,board)){
                                captures.add(actionThree);
                            }
                            if (ChessBoard.Legalcapture(actionFour,board)){
                                captures.add(actionFour);
                            }
                        }
                    }
                }

                for (int i = 0; i < board.board.size() ; i++) {
                    for (int j = 0; j < board.board.size() ; j++) {
                        if (board.board.get(i).get(j) == ChessBoard.types.whiteKing){
                            String actionOne = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j);
                            String actionTwo = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j+2);
                            String actionThree = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j-1);
                            String actionFour = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j+3);
                            String actionFive = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j);
                            String actionSix = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j+2);
                            String actionSeven = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i-1) + (j+3);
                            String actionEight = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i-1) + (j-1);
                            if (ChessBoard.LegalMove(actionOne,board)){
                                moves.add(actionOne);
                            }
                            if (ChessBoard.LegalMove(actionTwo,board)){
                                moves.add(actionTwo);
                            }
                            if (ChessBoard.Legalcapture(actionThree,board)){
                                captures.add(actionThree);
                            }
                            if (ChessBoard.Legalcapture(actionFour,board)){
                                captures.add(actionFour);
                            }
                            if (ChessBoard.LegalMove(actionFive,board)){
                                moves.add(actionFive);
                            }
                            if (ChessBoard.LegalMove(actionSix,board)){
                                moves.add(actionSix);
                            }
                            if (ChessBoard.Legalcapture(actionSeven,board)){
                                captures.add(actionSeven);
                            }
                            if (ChessBoard.Legalcapture(actionEight,board)){
                                captures.add(actionEight);
                            }
                        }
                    }
                }
                if (captures.size() != 0){
                    return captures;
                }else if (moves.size() != 0){
                    return moves;
                }else return captures;
        }

        return null;
    }

    public static int utilityTest(ArrayList<ArrayList<ChessBoard.types>> board){
        int whiteNum = 0;
        int blackNum = 0;
        for (ArrayList<ChessBoard.types> row:board) {
            for (ChessBoard.types piece:row) {
                if (piece == ChessBoard.types.white || piece == ChessBoard.types.whiteKing){
                    whiteNum++;
                }
                if (piece == ChessBoard.types.black || piece == ChessBoard.types.blackKing){
                    blackNum++;
                }
            }
        }
        if (whiteNum > blackNum){
            return 0;
        }
        if (whiteNum == blackNum){
            return 1;
        }
        else {
            return 2;
        }
    }

    public static boolean terminalTest(ChessBoard board){
        int white = 0;
        int black = 0;
        for (ArrayList<ChessBoard.types> row:board.board) {
            for (ChessBoard.types piece:row) {
                if (piece == ChessBoard.types.white || piece == ChessBoard.types.whiteKing){
                    white++;
                }
                if (piece == ChessBoard.types.black || piece == ChessBoard.types.blackKing){
                    black++;
                }
            }
        }
        if (white == 0 || black == 0){
            return true;
        }
        else if (white == 1 && black == 1){
            return true;
        }
        else if (actions(board, ChessBoard.types.black).size() == 0 || actions(board, ChessBoard.types.white).size() == 0){
            return true;
        }
        return false;
    }
    public static int maxValue(ChessBoard board){
        board = ChessBoard.checkKing(board);
        int tempValue = -1;
        if (terminalTest(board)){
            return utilityTest(board.board);
        }
        else for (String action:actions(board, ChessBoard.types.black)) {
            tempValue = java.lang.Math.max(tempValue,minValue(Agent.result(board,action)));
        }
        return tempValue;
    }

    public static int minValue(ChessBoard board){
        board = ChessBoard.checkKing(board);
        int tempValue = Integer.MAX_VALUE;
        if (terminalTest(board)){
            return utilityTest(board.board);
        }
        else
            for (String action:actions(board, ChessBoard.types.white)) {
                tempValue = java.lang.Math.min(tempValue,maxValue(Agent.result(board,action)));
            }
        return tempValue;
    }

    public static String minMaxDecision(ChessBoard board){
        String bestAction = "";
        board = ChessBoard.checkKing(board);
        int maxUtility = - 1;
        for (String action:actions(board, ChessBoard.types.black)) {
            int temp = minValue(Agent.result(board,action));
            if (minValue(Agent.result(board,action)) > maxUtility){
                maxUtility = temp;
                bestAction = action;
            }
        }
        return bestAction;
    }

    public static String hMinMaxDecision(ChessBoard board,int depth){
        String bestAction = "";
        board = ChessBoard.checkKing(board);
        int maxUtility = Integer.MIN_VALUE;
        for (String action:actions(board, ChessBoard.types.black)) {
            int temp = hMinValue(Agent.result(board,action),0,depth);
            if ((temp) > maxUtility){
                maxUtility = temp;
                bestAction = action;
            }
        }
        return bestAction;
    }
    public static int hMaxValue(ChessBoard board, int currentDepth, int maxDepth){
        currentDepth++;
        if (currentDepth >= maxDepth){
            return eval(board);
        }
        board = ChessBoard.checkKing(board);
        int tempValue = Integer.MIN_VALUE;
        for (String action:actions(board, ChessBoard.types.black)) {
            tempValue = java.lang.Math.max(tempValue,hMinValue(Agent.result(board,action),currentDepth,maxDepth));
        }
        return tempValue;
    }

    public static int hMinValue(ChessBoard board,int currentDepth, int maxDepth){
        currentDepth++;
        if (currentDepth >= maxDepth){
            return eval(board);
        }
        board = ChessBoard.checkKing(board);
        int tempValue = Integer.MAX_VALUE;

        for (String action:actions(board, ChessBoard.types.white)) {
            tempValue = java.lang.Math.min(tempValue,hMaxValue(Agent.result(board,action),currentDepth,maxDepth));
        }
        return tempValue;
    }

    public static int eval(ChessBoard board){
        int result = 0;
        for (ArrayList<ChessBoard.types> row:board.board) {
            for (ChessBoard.types piece:row) {
                if (piece == ChessBoard.types.white){
                    result-=1;
                }
                else if (piece == ChessBoard.types.whiteKing){
                    result-=2;
                }
                else if (piece == ChessBoard.types.black){
                    result+=1;
                }
                else if (piece == ChessBoard.types.blackKing){
                    result+=2;
                }
            }
        }
        return result;
    }

    public static ChessBoard random(ChessBoard board){
        ArrayList<String> actions =  actions(board, ChessBoard.types.black);
        Random a=new Random();
        int rand = a.nextInt(actions.size());
        return ChessBoard.checkKing(result(board,actions.get(rand)));
    }

    public static int abmaxvalue(ChessBoard board, int alpha, int beta){
        board = ChessBoard.checkKing(board);
        int v=Integer.MIN_VALUE;
        if(terminalTest(board)){
            return utilityTest(board.board);
        }
        for(String action:actions(board,ChessBoard.types.black)){
            v=Math.max(v,abminvalue(result(board,action),alpha,beta));
            if(v >= beta){return v;}
            alpha=Math.max(alpha,v);
        }
        return v;
    }

    public static int abminvalue(ChessBoard board, int alpha, int beta){
        board=ChessBoard.checkKing(board);
        int v=Integer.MAX_VALUE;
        if(terminalTest(board)){
            return utilityTest(board.board);
        }
        for(String action:actions(board,ChessBoard.types.white)){
            v=Math.min(v,abmaxvalue(result(board,action),alpha,beta));
            if(v <= alpha){return v;}
            beta=Math.min(alpha,v);
        }
        return v;
    }
    public static String alphabeta(ChessBoard board){
        String move=" ";
        board=ChessBoard.checkKing(board);
        int max=Integer.MIN_VALUE;
        for (String action:actions(board,ChessBoard.types.black)) {
            int v = abminvalue(result(board,action),Integer.MIN_VALUE,Integer.MAX_VALUE);
            if (v>max){
                max=v;
                move = action;
            }
        }
        return move;
    }
}
