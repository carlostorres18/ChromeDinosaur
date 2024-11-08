// This file is a JPanel, which will allows us to draw and render graphics
// This class will inherit JPanel
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // This will store all the cactus in our game
import javax.swing.*;


// with inheritance using the extends keyword we are saying that
// ChromeDinosaur is going to take all the properties from JPanel class
public class ChromeDinosaur extends JPanel implements ActionListener {
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
    // Create a class for the position of the items
    class Block{
        int x;
        int y;
        int width;
        int height;
        Image img;

        Block(int x, int y, int width, int height, Image img){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }

    }

    // Variables for the Dinosaur for its position and its size
    int dinosaurWidth = 88;
    int dinosaurHeight = 94;
    int dinosaurX = 50;
    int dinosaurY = boardHeight - dinosaurHeight;

    Block dinosaur;

    // physics
    int velocityY = 0; // dinosaur jump speed


    Timer gameLoop;

    public ChromeDinosaur(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);

        // Short version, this functions gets the image
        // getClass() to get into ChromeDinosaur class, getResource() this get the img folder into the Chrome... class
        // and it uses the url to choose the image, getImage() gets the actual image
        dinosaurImg = new ImageIcon(getClass().getResource("./img/dino-run.gif")).getImage();
        dinosaurDead = new ImageIcon(getClass().getResource("./img/dino-dead.png")).getImage();
        dinosaurJumpImg = new ImageIcon(getClass().getResource("./img/dino-jump.png")).getImage();
        cactus1Img = new ImageIcon(getClass().getResource("./img/cactus1.png")).getImage();
        cactus2Img = new ImageIcon(getClass().getResource("./img/cactus2.png")).getImage();
        cactus3Img = new ImageIcon(getClass().getResource("./img/cactus3.png")).getImage();
        bigCactus1Img = new ImageIcon(getClass().getResource("./img/big-cactus1.png")).getImage();
        bigCactus2Img = new ImageIcon(getClass().getResource("./img/big-cactus2.png")).getImage();
        bigCactus3Img = new ImageIcon(getClass().getResource("./img/big-cactus3.png")).getImage();

        // dinosaur
        // We use the Block class to keep track of all the attributes that are going through the class
        dinosaur = new Block(dinosaurX, dinosaurY, dinosaurWidth, dinosaurHeight, dinosaurImg);

        // game timer
        gameLoop = new Timer(1000/60, this); // 1000/60 = 60 frames per 1000ms (1s), update
        gameLoop.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(dinosaur.img, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);
    }


    // The actionPerformed will be performed for 60 times per second using the gameLoop timer
    // @Override it is used to indicate that a method is overriding a method in a superclass
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // this will call paintComponent() which will call draw() which will use the drawImage();
    }


}
