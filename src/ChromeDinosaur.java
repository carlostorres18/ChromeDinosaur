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

    // images will go here
    Image dinosaurImg; // running animated image (gif)
    Image dinosaurDead;
    Image dinosaurJumpImg;
    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;
    Image bigCactus1Img;
    Image bigCactus2Img;
    Image bigCactus3Img;
    

    public ChromeDinosaur(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);

        // Short version, this functions gets the image
        // getClass() to get into ChromeDinosaur class, getResource() this get the img folder into the Chrome... class
        // and it uses the url to choose the image, getImage() gets the actual image
        dinosaurImg = new ImageIcon(getClass().getResource("./img/dino-run.gif")).getImage();
        dinosaurDead = new ImageIcon(getClass().getResource("./img/dino-dead.pgn")).getImage();
        dinosaurJumpImg = new ImageIcon(getClass().getResource("./img/dino-jump.png")).getImage();
        cactus1Img = new ImageIcon(getClass().getResource("./img/cactus1.png")).getImage();
        cactus2Img = new ImageIcon(getClass().getResource("./img/cactus2.png")).getImage();
        cactus3Img = new ImageIcon(getClass().getResource("./img/cactus3.png")).getImage();
        bigCactus1Img = new ImageIcon(getClass().getResource("./img/big-cactus1.png")).getImage();
        bigCactus2Img = new ImageIcon(getClass().getResource("./img/big-cactus2.png")).getImage();
        bigCactus3Img = new ImageIcon(getClass().getResource("./img/big-cactus3.png")).getImage();

    }
}
