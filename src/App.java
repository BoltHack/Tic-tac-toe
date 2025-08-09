import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class App extends JFrame {
    private JButton[] button = new JButton[9];
    private boolean turn;
    char currentTurn;

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
        setBackground(new Color(0x16171d));
        setTitle();

        Border border = BorderFactory.createLineBorder(new Color(0x16171d));

        for (int i = 0; i < 9; i++) {
            int index = i;
            button[i] = new JButton("");
            button[i].setBorder(border);
            button[i].setFont(new Font("Arial", Font.BOLD, 70));
            button[i].setContentAreaFilled(false);
            button[i].setFocusPainted(false);
            button[i].setBackground(new Color(0xECF1FD));
            add(button[i]);

            button[i].addActionListener(e -> {
                System.out.println("button" + index + " " + turn);
                if (button[index].getText().equals("")) {
                    turn = !turn;
                    currentTurn = turn ? 'x' : 'o';
                    setTitle();
                    button[index].setText(String.valueOf(currentTurn));
                    board[index] = currentTurn;
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
                System.out.println(currentTurn + " Победил!");
                JOptionPane.showMessageDialog(null, currentTurn + " Победил!", "Игра закончилась!", JOptionPane.INFORMATION_MESSAGE);
                clearBoard();
                return;
            }
        }
        boolean draw = true;
        for (int j = 0; j < 9; j++) {
            if (button[j].getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(null, "У вас ничья!", "Игра закончилась!", JOptionPane.INFORMATION_MESSAGE);
            clearBoard();
        }

    }

}
