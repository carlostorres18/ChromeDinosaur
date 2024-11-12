import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        // This makes the windowsSize, they are based on pixels
        int boardWidth = 750;
        int boardHeight = 250;

        // This makes the frame of the window, and setVisible(true), makes the window visible
        JFrame frame = new JFrame("Chrome Dinodaur");
        // frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight); // this makes the previously stated sizes of the window
        frame.setLocationRelativeTo(null); // this makes the window centered into our screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // This line makes it so that when u close program, it closes properly

        ChromeDinosaur chromeDinosaur = new ChromeDinosaur();
        frame.add(chromeDinosaur);
        frame.pack();
        chromeDinosaur.requestFocus();
        frame.setVisible(true);
    }
}
