import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class App extends JFrame {
    private JButton[] button = new JButton[9];
    private boolean turn;
    char currentTurnChar;

    int[][] winPositions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    public void setTitle() {
        char currentTurn = turn ? 'O' : 'X';
        setTitle("Крестики нолики | Очередь: " + currentTurn);
    }
    public void clearBoard() {
        for (int j = 0; j < 9; j++) {
            button[j].setText("");
            board[j] = '\0';
        }
        revalidate();
        repaint();
    }

    public App() {
        setLayout(new GridLayout(3, 3));
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle();

        Border border = BorderFactory.createLineBorder(new Color(0x16171d));

        for (int i = 0; i < 9; i++) {
            int index = i;
            button[i] = new JButton("");
            button[i].setBorder(border);
            button[i].setFont(new Font("Arial", Font.BOLD, 36));
            add(button[i]);

            button[i].addActionListener(e -> {
                System.out.println("button" + index + " " + turn);
                if (button[index].getText().equals("")) {
                    turn = !turn;
                    currentTurnChar = turn ? 'X' : 'O';
                    setTitle();
                    button[index].setText(String.valueOf(currentTurnChar));
                    board[index] = currentTurnChar;
                    checkWinner();
                }
            });
        }

        setVisible(true);
    }

    char[] board = new char[9];

    public void checkWinner() {
        for (int i = 0; i < winPositions.length; i++) {
            int pos1 = winPositions[i][0];
            int pos2 = winPositions[i][1];
            int pos3 = winPositions[i][2];

            if (board[pos1] != '\0' && board[pos1] == board[pos2] && board[pos2] == board[pos3]) {
                System.out.println(currentTurnChar + " Победил!");
                JOptionPane.showMessageDialog(null, currentTurnChar + " Победил!", "Игра закончилась!", JOptionPane.INFORMATION_MESSAGE);
                clearBoard();
                break;
            }
            boolean draw = true;
            for (int j = 0; j < 9; j++) {
                if (button[j].getText().equals("")) {
                    draw = false;
                }
            }
            if (draw) {
                JOptionPane.showMessageDialog(null, "У нас ничья!", "Игра закончилась!", JOptionPane.INFORMATION_MESSAGE);
                clearBoard();
                break;
            }
        }
    }

}
