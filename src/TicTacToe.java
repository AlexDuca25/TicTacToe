import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions =new ArrayList<>();
    static ArrayList<Integer> cpuPositions =new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameboard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '_', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameboard);

        while (true){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your placement (1-9): ");
        int playerPosition =scanner.nextInt();
        while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)){
            System.out.println("position taken! Enter a correct Position");
            playerPosition =scanner.nextInt();
        }

        placePiece(gameboard, playerPosition, "player");
        String result = checkWinner();
            if(result.length()> 0){
                System.out.println(result);
                break;
            }

        Random random= new Random();
        int cpuPosition = random.nextInt(9)+1;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                cpuPosition = random.nextInt(9)+1;
            }
        placePiece(gameboard, cpuPosition, "cpu");

        printGameBoard(gameboard);
        if(result.length()> 0){
            System.out.println(result);
            break;
        }

        }
    }
    public static void printGameBoard(char[][] gameboard){
        for (char[] row : gameboard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }

    }
    public static void placePiece(char[][] gameBoard, int position, String user){
        char symbol =' ';

        if(user.equals("player")){
            symbol='X';
            playerPositions.add(position);
        }else if(user.equals("cpu")){
            symbol ='O';
            cpuPositions.add(position);
        }
        switch (position){
            case 1:
                gameBoard[0][0]=symbol;
                break;
            case 2:
                gameBoard[0][2]=symbol;
                break;
            case 3:
                gameBoard[0][4]=symbol;
                break;
            case 4:
                gameBoard[2][0]=symbol;
                break;
            case 5:
                gameBoard[2][2]=symbol;
                break;
            case 6:
                gameBoard[2][4]=symbol;
                break;
            case 7:
                gameBoard[4][0]=symbol;
                break;
            case 8:
                gameBoard[4][2]=symbol;
                break;
            case 9:
                gameBoard[4][4]=symbol;
                break;
            default:
                break;
        }
    }
    public static String checkWinner(){

        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List bottonRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List> winningCondition = new ArrayList<>();
        winningCondition.add(topRow);
        winningCondition.add(midRow);
        winningCondition.add(bottonRow);
        winningCondition.add(leftCol);
        winningCondition.add(midCol);
        winningCondition.add(rightCol);
        winningCondition.add(cross1);
        winningCondition.add(cross2);

        for (List currentList : winningCondition){
            if(playerPositions.containsAll(currentList)){
                return "Congratulations you win!";
            } else if (cpuPositions.containsAll(currentList)) {
                return "Cpu wins! Sorry!";
            }
        }
             if(playerPositions.size()+cpuPositions.size()==9) {
                 return "Tie";
             }
        return "";
    }
}
