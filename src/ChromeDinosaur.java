/*
    Game is finished but.
    TO:DO
        - implement new big cactus into the game
        - implement bird animations to appear above the cactus
        - implement duck animation for the dino
        - implement high score to be displayed on the screen
            and make it update if surpassed

 */



// This file is a JPanel, which will allows us to draw and render graphics
// This class will inherit JPanel
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // This will store all the cactus in our game
import javax.swing.*;


// with inheritance using the extends keyword we are saying that
// ChromeDinosaur is going to take all the properties from JPanel class
public class ChromeDinosaur extends JPanel implements ActionListener, KeyListener {
    // if we don't add the properties in this JPanel it will make the window small
    int boardWidth = 750;
    int boardHeight = 250;

    // images will go here
    Image dinosaurImg; // running animated image (gif)
    Image dinosaurDeadImg;
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

    // cactus
    int cactus1Width = 34;
    int cactus2Width = 69;
    int cactus3Width = 102;

    int cactusHeight = 70;
    int cactusX = 700;
    int cactusY = boardHeight - cactusHeight;
    ArrayList<Block> cactusArray;

    // physics
    int velocityX = -12;
    int velocityY = 0; // dinosaur jump speed
    int gravity = 1;

    boolean gameOver = false;
    int score = 0;


    Timer gameLoop;
    Timer placeCactusTimer;

    public ChromeDinosaur(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);

        // Short version, this functions gets the image
        // getClass() to get into ChromeDinosaur class, getResource() this get the img folder into the Chrome... class
        // and it uses the url to choose the image, getImage() gets the actual image
        dinosaurImg = new ImageIcon(getClass().getResource("./img/dino-run.gif")).getImage();
        dinosaurDeadImg = new ImageIcon(getClass().getResource("./img/dino-dead.png")).getImage();
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
        // cactus
        cactusArray = new ArrayList<Block>();

        // game timer
        gameLoop = new Timer(1000/60, this); // 1000/60 = 60 frames per 1000ms (1s), update
        gameLoop.start();

        // place cactus timer
        placeCactusTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                placeCactus();
            }
        });
        placeCactusTimer.start();

    }

    void placeCactus(){
        if(gameOver){
            return;
        }

        double placeCactusChance = Math.random(); // the number is 0 - 0.999999
        if(placeCactusChance > .90){ // 10% chance of getting cactus3
            Block cactus = new Block(cactusX, cactusY, cactus3Width, cactusHeight, cactus3Img);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance > .70){ // 20% chance of getting cactus2
            Block cactus = new Block(cactusX, cactusY, cactus2Width, cactusHeight, cactus2Img);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance > .50){ // 20% chance of getting cactus1
            Block cactus = new Block(cactusX, cactusY, cactus3Width, cactusHeight, cactus1Img);
        }

        if(cactusArray.size() > 10){
            cactusArray.remove(0);  // remove the first cactus from the ArrayList, this is to ensure that
                                          //    the program won't slow down as the array grows;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        // dinosaur
        g.drawImage(dinosaur.img, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);

        // cactus
        for(int i = 0; i < cactusArray.size(); i++){
            Block cactus = cactusArray.get(i);
            g.drawImage(cactus.img, cactus.x, cactus.y, cactus.width, cactus.height, null);
        }
        
        // score
        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.PLAIN, 32));
        if(gameOver){
            g.drawString("Game Over: " + String.valueOf(score), 10, 35);
        }
        else{
            g.drawString(String.valueOf(score), 10, 35);
        }
    }

    public void move(){
        velocityY += gravity;
        dinosaur.y += velocityY;

        if(dinosaur.y > dinosaurY){
            dinosaur.y = dinosaurY;
            velocityY = 0;
            dinosaur.img = dinosaurImg;
        }

        // cactus
        for(int i = 0; i < cactusArray.size(); i++){
            Block cactus = cactusArray.get(i);
            cactus.x += velocityX;

            if(collision(dinosaur, cactus)){
                gameOver = true;
                dinosaur.img = dinosaurDeadImg;
            }
        }

        // score
        score++;

    }

    // This formula is going to help us find the intersection of 2 rectangles,
    //  and will help us with the collision physics of the game.
    boolean collision(Block a, Block b){
        return  a.x < b.x + b.width &&      // a's top left corner doesn't reach b's top right corner
                a.x + a.width > b.x &&      // a's top right corner passes b's top left corner
                a.y < b.y + b.height &&     // a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y;       // a's bottom left corner passes b's top left corner
    }


    // The actionPerformed will be performed for 60 times per second using the gameLoop timer
    // @Override it is used to indicate that a method is overriding a method in a superclass
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint(); // this will call paintComponent() which will call draw() which will use the drawImage();
        if(gameOver){
            placeCactusTimer.stop();
            gameLoop.stop();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            // System.out.println("jump!");
            if(dinosaur.y  == dinosaurY){
                velocityY = -17;
                dinosaur.img = dinosaurJumpImg;
            }
            if(gameOver){
                // This will help us reset the game, by pressing space key
                dinosaur.y = dinosaurY;
                dinosaur.img = dinosaurImg;
                velocityY = 0;
                cactusArray.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placeCactusTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


}
