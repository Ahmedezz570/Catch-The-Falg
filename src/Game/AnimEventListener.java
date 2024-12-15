package Game;

import Game.Gui.Menu;
import Game.Gui.levels;
import Texture.TextureReader;
import com.sun.opengl.util.j2d.TextRenderer;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import Sound.Sound;


// switch
// 0 =>menu
//1=>leve
//2 =>game
public class AnimEventListener extends AnimationListener {
    double Xmouse,Ymouse;
    public static final int MAX_WIDTH = 100, MAX_HEIGHT = 100; // set max height and width to translate sprites using integers
    public static final double End_of_x = MAX_WIDTH - 4;
    public static final double start_of_x = 25;
    public static final double End_of_Y = MAX_HEIGHT - 29;
    public static final double start_of_y = 12;
    public String levelAsString = "Easy";

    Sound sound = new Sound();
    boolean isMultiPlayer = false;
    TextRenderer textRenderer = new TextRenderer(Font.decode("PLAIN 100"));
    public String username;
    double xPosition = 0, yPosition = 0;
    int whatdraw = 0;
    double xRB1 , yRB1 ;
    double xBB1 , yBB1 ;

    double xFlag2 =5,yFlag2=50;
    double xFlag1=95,yFlag1=50;
    boolean isfinished = false;
    private int timer = 0;
    private int timerHandler = 0;
        int timeToFollow=10;
    int stepIndex = 0;
    ArrayList<Ball> balls=new ArrayList<>();
    ArrayList<steps> xRB_steps=new ArrayList<>();

    boolean []onetime=new boolean[8];
    int []lower=new int[8];
    int []upper=new int[8];
    double []X_oldPosi=new double[8];
    double []Y_oldPosi=new double[8];
    boolean increaseX[]=new boolean[8];
    boolean increaseY[]=new boolean[8];
    public static String[] textureNames = {
            "Menu//PLAYBUTTON.png", "Menu//SETTINGS.png", "Menu//HOW  PLAY.png",
            "Menu//EXITBUTTON.png","Menu//SINGLE PLAYER.png", "Menu//MULITI PLAYERS .png",
            "Menu//soundOnWhite.png", "Menu//soundOffWhite.png","Menu//BACKBUTTON.png",
            "heart.png","Menu//HOW TO PLAY.png",
            "Menu//Box.png", "Menu//HighScore.png", "Menu//MAINMENU.png",
            "Menu//TRYAGAIN.png","Menu//EASY.png", "Menu//MEDIUM.png",
            "Menu//HARD.png","Menu//BACKBUTTON.png","flags//blueball.png",
            "flags//redball.png","flags//blueflag.png","flags//redflag.png",
            "Menu//Background.png","Menu//back.png"

    };

    Menu menu = new Menu();
    levels level = new levels();
    boolean mute = false;
    boolean show = false;
    boolean paused = false;

    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    public static int[] textures = new int[textureNames.length];



    public void drawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 2]);
        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void drawBack(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 1]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }


    public void drawSprite(GL gl, double x, double y, int index, float xScale, float yScale) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D,textures[index]);    // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (MAX_WIDTH / 2.0) - 1, y / (MAX_HEIGHT / 2.0) - 1, 0);
        gl.glScaled(0.01 * xScale, 0.01 * yScale, 1);
//        System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawDigits(GL gl, double x, double y, int digit, float xScale, float yScale) {
        if (digit >= 10) {
            int rightDigit = digit % 10;
            drawSprite(gl, x + 2, y, 70 + rightDigit, xScale, yScale);
            drawSprite(gl, x, y, 70 + digit / 10, xScale, yScale);
        } else {
            drawSprite(gl, x, y, 70 + digit, xScale, yScale);
        }
    }

    public boolean isColliding(double x1, double y1, double radius1, double x2, double y2, double radius2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double distanceSquare = Math.pow(dx, 2) + Math.pow(dy, 2);
        return distanceSquare <= Math.pow(radius1 + radius1, 2);
    }

    void handleTimer() {
        timerHandler++;
        if (timerHandler == 24) {
            timer++;
            timerHandler = 0;
        }
    }

//    public void DrawScore(GL gl, int x, int y) {
//        int[] array = {12, 13, 14, 15, 16};
//        for (int i = 0; i < 5; i++) {
//            drawSprite(gl, x + i * 2, y, array[i], 2, 2);
//        }
//    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(1.0f, 1f, 0f, 1.0f);    //This Will Clear The Background Color To Black
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Image data
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }


//        username = JOptionPane.showInputDialog("Enter your name: ");
//        if (username == null || username.trim().isEmpty()) {
//            username = "Guest";
//
//        }
//        xRB1=balls.get(0).x;
//        yRB1=balls.get(0).y;

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        TextRenderer textRenderer = null;
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

//        System.out.println(sqrdDistance((int) xRB1,(int) yRB1,(int) xFlag2,(int)yFlag2));
        switch (whatdraw) {
            case 0:
                menu.drawMenu(gl);
                if (mute == false) {
                    System.out.println("unmute");
//                    menu.playsound("StartSound.mp3");
//                    menu.mediaPlayer.setMute(false);
                } else {
//                    menu.mediaPlayer.setMute(true);
                    System.out.println("mute");
                }
                break;
            case 1:
                initializeBalls();
                handleKeyPress();
                handleTimer();
                drawBack(gl);
                resturnTheFlag(balls);
                for(Ball b:balls){
                    b.drawSprite(gl,b.x,b.y,textures.length-5,4,4);
                }
//        System.out.println(balls.get(1).x);
                holdingFlag(balls);
                animation(balls,0,"red");
                animation(balls,2,"red");
                animation(balls,4,"blue");
                animation(balls,6,"blue");
                Hori_animation(balls,7,"blue");
                Hori_animation(balls,3,"red");
//        Vert_animation(balls,7);
//        Vert_animation(balls,3);

                drawSprite(gl, xFlag2,yFlag2,textures.length-4,5,5);
                drawSprite(gl, xFlag1,yFlag1,textures.length-3,5,5);
                break;

            case 2:
                if (whatdraw == 2) {
                    menu.drawHowToPlay(gl,11 );
                }

                break;
            case 3: // High Score
                drawBackground(gl);

                drawSprite(gl, MAX_WIDTH - 10, 5, 13, 12, 6);
                break;

            case 4: // levels....
                level.drawlevels(gl);
                break;
        }

    }
    public void initializeBalls() {
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                if (i % 2 == 0) {
                    Ball b = new Ball(80, (i == 2) ? 80 : 25, textures.length - 5);
                    balls.add(b);
                } else {
                    Ball b = new Ball((i == 1) ? 55 : 75, 50, textures.length - 5);
                    balls.add(b);
                }
            } else {
                if (i % 2 == 0) {
                    Ball b = new Ball(20, (i == 6) ? 80 : 25, textures.length - 6);
                    balls.add(b);
                } else {
                    Ball b = new Ball((i == 5) ? 45 : 25, 50, textures.length - 6);
                    balls.add(b);
                }
            }
            X_oldPosi[i] = balls.get(i).x;
            Y_oldPosi[i] = balls.get(i).y;
            increaseX[i] = false;
            increaseY[i] = false;
            lower[i] = 21;
            upper[i] = 20;
            onetime[i] = true;
        }
    }

    public void drawHighScore(String highScore) {

    }
    public double sqrdDistance(double x, double y, double x1, double y1){
        return Math.pow(x-x1,2)+Math.pow(y-y1,2);
    }

    public boolean areTheyClose(double x, double y, double x1, double y1){
        if(sqrdDistance(x,y,x1,y1)<=20){
            return true;
        }
        return false;
    }
    public void holdingFlag(ArrayList <Ball> balls){
        if(areTheyClose(balls.get(1).x,balls.get(1).y,xFlag2,yFlag2)) {
            xFlag2 = balls.get(1).x;
            yFlag2 = balls.get(1).y;
        }
        if(balls.get(1).x>50) {
            xFlag2 = 5;
            yFlag2= 50;
        }
    }
    public void handleKeyPress() {
        // To track the current step in xRB_steps
        double lengthStep =0.5;
        if (true) {
            // Add the target's position to the steps list while following
            if (timeToFollow > 0) {
                xRB_steps.add(new steps(balls.get(1).x, balls.get(1).y));
                timeToFollow--;
            }
            xRB_steps.add(new steps(balls.get(1).x, balls.get(1).y));
            // Once the steps are captured, start moving the follower smoothly
            if (timeToFollow == 0 && stepIndex < xRB_steps.size()) {
                // Get the next step from the list
                steps step = xRB_steps.get(stepIndex);
                balls.get(5).x = step.x;
                balls.get(5).y = step.y;

                // Move to the next step in the next frame
                stepIndex++;

                // Reset timeToFollow if the tracking finishes
                if (stepIndex == xRB_steps.size()) {
//                    timeToFollow = 10;  // Reset for the next sequence
                    stepIndex = 0;      // Start again if needed
                    xRB_steps.clear();  // Clear the previous steps
                }
            }
        }

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            balls.get(1).x-=lengthStep;
            if( balls.get(1).x <= 2.5)
                balls.get(1).x =2.5;

        }
        if( isKeyPressed(KeyEvent.VK_RIGHT)){
            balls.get(1).x+=lengthStep;
            if( balls.get(1).x >= 97.5)
                balls.get(1).x =97.5;
        }
        if( isKeyPressed(KeyEvent.VK_UP)){
            balls.get(1).y+=lengthStep;
            if( balls.get(1).y >= 97.5)
                balls.get(1).y =97.5;

        }
        if( isKeyPressed(KeyEvent.VK_DOWN)){
            balls.get(1).y-=lengthStep;
            if( balls.get(1).y <= 2.5)
                balls.get(1).y =2.5;
        }

    }
    public void Hori_animation(ArrayList<Ball> balls,int index,String color){
        Ball b= balls.get(index);
        double step=0.6;
        ifblue(color,index);

        if(!increaseX[index])
            b.x-=step;

        if(b.x<=X_oldPosi[index]-upper[index] && b.x >= X_oldPosi[index]-lower[index] ) {
            increaseX[index]= (color == "blue")? false:true;
        }

        if(increaseX[index])
            b.x+=step;

        if(b.x<=X_oldPosi[index]+0.5 && b.x >= X_oldPosi[index]-0.5 )
            increaseX[index]= (color == "blue")? true:false;
//        System.out.println(b.x + " "+(X_oldPosi[index]-upper) +" "+ (  X_oldPosi[index]-lower));
        System.out.println(upper+" "+ lower);
//        System.out.println(increaseX[index]);
    }
    public void ifblue(String color ,int index){
        if(color == "blue"&& onetime[index]){
            upper[index]=-21;
            lower[index]=-20;
            increaseX[index]=true;
            onetime[index]=false;
        }
    }
    public void Vert_animation(ArrayList<Ball> balls,int index){
        Ball b= balls.get(index);
        double step=0.6;
        if(!increaseY[index])
            b.y-=step;

        if(b.y<=Y_oldPosi[index]-10 && b.y >= Y_oldPosi[index]-11 )
            increaseY[index]=true;

        if(increaseY[index])
            b.y+=step;

        if(b.y<=Y_oldPosi[index]+0.5 && b.y >= Y_oldPosi[index]-0.5 )
            increaseY[index]=false;

    }
    public void animation(ArrayList<Ball> balls,int index,String color) {
        double step=0.55;

        ifblue(color,index);

        Ball b = balls.get(index);
        if (!increaseX[index]) {
            b.x-=step;
            if (!increaseY[index]) {
                b.y-=step;
                if (b.y <= Y_oldPosi[index] - 8 && b.y >=Y_oldPosi[index] -9) {
                    increaseY[index] = true;
                }
            }
            else {
                b.y+=step;
                if (b.y <= Y_oldPosi[index]+0.75 && b.y >= Y_oldPosi[index]-0.75  ) {
                    increaseY[index] = false;
                }
            }

        }
        else {
            b.x+=step;
            if (!increaseY[index]) {
                b.y-=step;
                if (b.y <= Y_oldPosi[index] - 7 && b.y >=Y_oldPosi[index] -8) {
                    increaseY[index] = true;
                }
            }
            else {
                b.y+=step;
                if (b.y <= Y_oldPosi[index]+0.75 && b.y >= Y_oldPosi[index]-0.75  ) {
                    increaseY[index] = false;
                }
            }

        }
        if (b.x<= X_oldPosi[index]-upper[index] &&b.x>= X_oldPosi[index]-lower[index] ){
            increaseX[index]=(color=="blue")? false :true;
            System.out.println("hello");
        }

        if (b.x>= X_oldPosi[index]-0.75 &&b.x<= X_oldPosi[index]+0.75){
            increaseX[index]=(color=="blue")? true :false;;
        }
        System.out.println(b.x +" "+ (X_oldPosi[index]-upper[index])+" "+ ( X_oldPosi[index]-lower[index])+" "+increaseX[index] );

    }

    public void resturnTheFlag(ArrayList<Ball> balls){
        Ball ball = balls.get(1);
        for(Ball b: balls) {
            if (b == ball) continue;
            if (areTheyClose(b.x, b.y, ball.x, ball.y)) {
                xFlag2 = 5;
                yFlag2 = 50;
            }
        }
    }
    public void resetGame() {
    }

    private void drawBox(GL gl, TextRenderer textRenderer, String massege, int index) {

    }

    private void Render(TextRenderer textRenderer, int x, int y, String messege, int fontSize) {

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }


    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);

    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE && whatdraw == 1) {
            paused = !paused;
        }
    }

    @Override
    public void keyTyped(final KeyEvent event) {
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        double x = e.getX();
        double y = e.getY();

//        System.out.println(x + " " + y);

        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
//        System.out.println(width + " " + height);

        xPosition = (int) ((x / width) * 100);
        yPosition = ((int) ((y / height) * 100));
        yPosition = 100 - yPosition;

       System.out.println("x " + xPosition + " y " + yPosition);
//---------------------------------------------------------------------------------Levels
        if (whatdraw == 4) {
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 66 && yPosition <= 75) {
                playSE(3);
                whatdraw = 1;
                levelAsString = "Easy";
            }
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 55 && yPosition <= 63) {
                playSE(3);
             //   whatdraw = 1;
                levelAsString = "Medium";
            }
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 42 && yPosition <= 50) {
                playSE(3);
             //   whatdraw = 1;
                levelAsString = "Hard";
            }
        }
//-------------------------------------------------------------------------------------------Menu (Home Page )
        if (whatdraw == 0) {
        // High_Score
            if (xPosition >= 92 && xPosition <= 98 && yPosition >= 2 && yPosition <= 8) {
                whatdraw = 3;
            }
           // Exit
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 30 && yPosition <= 39) {
                playSE(3);
                System.exit(0);
            }
            // How To Play
            if (xPosition >= 92.5 && xPosition <= 97.5 && yPosition >= 92.5 && yPosition <= 97.5) {
                playSE(3);
                mute = !mute;
                if (menu.mute == 6) {
                    menu.mute = 7;
                } else {
                    menu.mute = 6;
                }
            }
            // Single_Player
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 66 && yPosition <= 75) {
                playSE(3);
                whatdraw = 4;       // ----- LEVELS
                isMultiPlayer = false;
            }
            // Multi_Player
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 55 && yPosition <= 63) {
                playSE(3);
                whatdraw = 4;
                isMultiPlayer = true;
            }
        }
//-------------------------------------------------------------------------------------------------------------------------------------------
        if (whatdraw == 4) {
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 30 && yPosition <= 39) {
                playSE(3);
                whatdraw = 0;
            }
        }

        ////***///
        if (whatdraw == 2) {
            if (xPosition >= 86 && xPosition <= 94 && yPosition >= 4 && yPosition <= 7) {
                whatdraw = 0;
                playSE(3);
            }
        }
        if (whatdraw == 3) {
            if (xPosition >= 86 && xPosition <= 94 && yPosition >= 4 && yPosition <= 7) {
                whatdraw = 0;
                playSE(3);
            }
        }
        if (whatdraw == 1) {
            if (paused) {
                if (xPosition >= 28 && xPosition <= 48 && yPosition >= 38 && yPosition <= 48) {
                    if (isMultiPlayer) {
//
                    } else {
//
                    }
                    playSE(3);
                    resetGame();
                    paused = false;
                }
                if (xPosition >= 52 && xPosition <= 72 && yPosition >= 38 && yPosition <= 48) {
                    if (isMultiPlayer) {
//
                    } else {
//
                    }
                    playSE(3);
                    whatdraw = 0;
                    paused = false;
                    resetGame();
                }
            }
            if (show) {
                if (xPosition >= 28 && xPosition <= 48 && yPosition >= 38 && yPosition <= 48) {
                    if (isMultiPlayer) {

                    } else {
                    }

                    playSE(3);
                    resetGame();
                    show = false;

                }
                if (xPosition >= 52 && xPosition <= 72 && yPosition >= 38 && yPosition <= 48) {
                    if (isMultiPlayer) {

                    } else {
                    }
                    playSE(3);
                    whatdraw = 0;
                    show = false;
                    resetGame();

                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Xmouse = convertX(e.getX(), e.getComponent().getWidth());
        Ymouse = convertY(e.getY(), e.getComponent().getHeight());
        System.out.println(Xmouse+" "+Ymouse);
    }
    private double convertX(double x, double width) {
        return (x / width) * 100;
    }

    private double convertY(double y, double height) {
        return (1 - y / height) * 100;
    }
}

class Ball{

    public Ball(double x, double y, int textureNumber) {
        this.x = x;
        this.y = y;
        this.textureNumber = textureNumber;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public void drawSprite(GL gl, double x, double y, int index, float xScale, float yScale) {
        int MAX_WIDTH=100;
        int MAX_HEIGHT=100;

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, AnimEventListener.textures[textureNumber]);    // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (MAX_WIDTH / 2.0) - 1, y / (MAX_HEIGHT / 2.0) - 1, 0);
        gl.glScaled(0.01 * xScale, 0.01 * yScale, 1);
//        System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    double x,y;

    int textureNumber;
}
class steps {
  double x,y;

    public steps(double x, double y) {
        this.x = x;
        this.y = y;
    }
}