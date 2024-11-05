// This file is a JPanel, which will allows us to draw and render graphics
// This class will inherit JPanel
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // This will store all the cactus in our game
import javax.swing.*;


// with inheritance using the extends keyword we are saying that
// ChromeDinosaur is going to take all the properties from JPanel class
public class ChromeDinosaur extends JPanel {
    // if we don't add the properties in this JPanel it will make the window small
    int boardWidth = 750;
    int boardHeight = 250;

    

    public ChromeDinosaur(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);
    }
}
