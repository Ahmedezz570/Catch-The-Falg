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

public class AnimEventListener extends AnimationListener {
    double Xmouse,Ymouse;
    public static final int MAX_WIDTH = 100, MAX_HEIGHT = 100; // set max height and width to translate sprites using integers
    public String levelAsString = "Easy";
    Sound sound = new Sound();
    boolean isMultiPlayer = false;
    double xPosition = 0, yPosition = 0;
    int whatdraw = 0;
    boolean isfinished = false;
    private int timer = 60;
    private int score =0;
    private int timerHandler = 0;
    int timeToFollow=10;
    int stepIndex = 0;
    ArrayList<Ball> balls=new ArrayList<>();
    ArrayList<steps> xRB_steps=new ArrayList<>();
    ArrayList<flag> flags =new ArrayList<>();
    entity e;
    Level level_;
    Level  level_2 ;
    Menu menu = new Menu();
    levels level = new levels();
    boolean mute = false;
    boolean show = false;
    boolean paused = false;
    public static String[] textureNames = {
            "Menu//PLAYBUTTON.png", "Menu//SETTINGS.png", "Menu//HOW  PLAY.png",
            "Menu//EXITBUTTON.png","Menu//SINGLE PLAYER.png", "Menu//MULITI PLAYERS .png",
            "Menu//soundOnWhite.png", "Menu//soundOffWhite.png","Menu//BACKBUTTON.png",
            "heart.png","Menu//HOW TO PLAY.png",
            "Menu//Box.png", "Menu//HighScore.png", "Menu//MAINMENU.png",
            "Menu//TRYAGAIN.png","Menu//EASY.png", "Menu//MEDIUM.png",
            "Menu//HARD.png","Menu//BACKBUTTON.png",
            "Digits//0.png", "Digits//1.png", "Digits//2.png", "Digits//3.png",
            "Digits//4.png", "Digits//5.png", "Digits//6.png", "Digits//7.png",
            "Digits//8.png", "Digits//9.png",
            "Alphabet//s.png", "Alphabet//c.png", "Alphabet//o.png",
            "Alphabet//r.png", "Alphabet//e.png", "Digits//slash.png","flags//blueball.png",
            "flags//redball.png","flags//blueflag.png","flags//redflag.png",
            "Menu//Background.png","Menu//back.png"

    };


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
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 1]);
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


    public void drawSprite(GL gl, double x, double y, int index, float xScale, float yScale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D,textures[index]);    // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (MAX_WIDTH / 2.0) - 1, y / (MAX_HEIGHT / 2.0) - 1, 0);
        gl.glScaled(0.01 * xScale, 0.01 * yScale, 1);
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
    public void drawHandleTimer(GL gl, double x, double y) {
        if (timer > 0 && !isfinished) {
            handleKeyPress();
            timerHandler++;
            if (timerHandler == 24) {  // Assuming 24 frames per second

                timer--;
                timerHandler = 0;
            }
        } else if (timer == 0 && !isfinished) {
            isfinished = true;
            score = 30;
        }
        TextRenderer textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 18));
        textRenderer.beginRendering(700, 700);
        textRenderer.draw( "" +timer,(int)  x,(int) y);
        textRenderer.endRendering();
    }

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
        for (int i=0; i<2 ;i++){
            flag f =new flag((i==1)?95:5,50,(i==1)?textures.length-3:textures.length-4);
            flags.add(f);
        }
    }
   boolean initiallevel = true ;
    public void levelll (int level){
        if (level == 1){
            Level level_01=new Level(balls,1);
            level_01.create(textures);
            e=new entity(balls.get(1),flags.get(0));
            initiallevel = false;
        }
        else if (level == 2){
            Level level_02=new Level(balls,2);
            level_02.create(textures);
            e=new entity(balls.get(1),flags.get(0));
            initiallevel = false;
        }
    }
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        TextRenderer textRenderer = null;
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

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
            case 1:    // Single (Easy)
                drawBack(gl);
                if (initiallevel){
                     levelll(1);
                     initiallevel= false ;
                }

                level_ =new Level(balls,flags,e,1);
                level_.init(gl,textures);
                handleKeyPress();
                DrawScore(gl ,3,93);
                drawHandleTimer(gl,320,650);
                DrawSlash(gl, 13 ,92 );
                DrawSlash(gl, 13 ,94 );

                break;
            case 2:    // Single (Medium)
                drawBack(gl);
                if (initiallevel){
                    levelll(2);
                    initiallevel= false ;
                }
                level_2 =new Level(balls,flags,e,2);
                level_2.init(gl,textures);
                handleKeyPress();
                DrawScore(gl ,3,93);
                drawHandleTimer(gl,320,650);
                DrawSlash(gl, 13 ,92 );
                DrawSlash(gl, 13 ,94 );
                break;
            case 3: // High Score
                drawBackground(gl);
                drawSprite(gl, MAX_WIDTH - 10, 5, 13, 12, 6);

                break;

            case 4: // levels....
                level.drawlevels(gl);
                break;

            case 10://how to play
                menu.drawHowToPlay(gl, 10);


            case 30 :  // Multi (Easy)
                 drawBack(gl);
                 if (initiallevel){
                     levelll(1);
                     initiallevel= false ;
                 }
                 level_ =new Level(balls,flags,e,1);
                 level_.init(gl,textures);
                 DrawScore(gl ,3,93);
                 drawHandleTimer(gl,300,620);
                 DrawSlash(gl, 13 ,92 );
                 DrawSlash(gl, 13 ,94 );
              break;
            case 31 :   // Multi (Medium)
                drawBack(gl);
                if (initiallevel){
                    levelll(2);
                    initiallevel= false ;
                }
                level_2 =new Level(balls,flags,e,2);
                level_2.init(gl,textures);
                handleKeyPress();
                DrawScore(gl ,3,93);
                drawHandleTimer(gl,320,650);
                DrawSlash(gl, 13 ,92 );
                DrawSlash(gl, 13 ,94 );
        }

    }
    public void handleKeyPress() {
        // To track the current step in xRB_steps
        double lengthStep =0.55;
        // AI
        if (whatdraw == 1 || whatdraw == 2){
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
        }
   // Player_1
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

// Player_2 Multi (Easy , Medium ).
if (whatdraw == 30 || whatdraw == 31){
        //----------------------------------------------
        if (isKeyPressed(KeyEvent.VK_A)) {
            balls.get(5).x-=lengthStep;
            if( balls.get(5).x <= 2.5)
                balls.get(5).x =2.5;
        }
        if( isKeyPressed(KeyEvent.VK_D)){
            balls.get(5).x+=lengthStep;
            if( balls.get(5).x >= 97.5)
                balls.get(5).x =97.5;
        }
        if( isKeyPressed(KeyEvent.VK_W)){
            balls.get(5).y+=lengthStep;
            if( balls.get(5).y >= 97.5)
                balls.get(5).y =97.5;
        }
        if( isKeyPressed(KeyEvent.VK_S)){
            balls.get(5).y-=lengthStep;
            if( balls.get(5).y <= 2.5)
                balls.get(5).y =2.5;
        }
    }
}

    public void DrawScore(GL gl, int x, int y) {

        int[] array = {29,30, 31, 32, 33, 34};
        for (int i = 0; i < 5; i++) {
            drawSprite(gl, x + i * 2, y, array[i], 2, 2);
        }
    }
    public void DrawSlash(GL gl, double x, double y) {
        int[] array1 = {34};
        drawSprite(gl, x , y, array1[0], 1, 1);
    }
    public void resetGame() {
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
        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
        xPosition = (int) ((x / width) * 100);
        yPosition = ((int) ((y / height) * 100));
        yPosition = 100 - yPosition;
//---------------------------------------------------------------------------------Levels
        if (whatdraw == 4) {
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 66 && yPosition <= 75) {
                playSE(3);
                if (!isMultiPlayer) {
                    whatdraw = 1;
                    levelAsString = "Easy";
                }
                else {
                    whatdraw = 30;
                    levelAsString = "Easy ";
                }
            }
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 55 && yPosition <= 63) {
                playSE(3);
                if (!isMultiPlayer) {
                    whatdraw = 2;
                    levelAsString = "Medium";
                }
                else {
                    whatdraw = 31;
                    levelAsString = "Medium ";
                }
            }
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 42 && yPosition <= 50) {
                playSE(3);
             //   whatdraw = 1;
                levelAsString = "Hard";
            }
        }
//-------------------------------------------------------------------------------------------Menu (Home Page )
            if (whatdraw == 3) {
            if (xPosition >= 80 && xPosition <= 99 && yPosition >= 4 && yPosition <= 7) {
                whatdraw = 0;
                playSE(3);
            }
        }
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
            // mute
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
        //how to play
        if (xPosition >= 40 && xPosition <= 60 && yPosition >= 42 && yPosition <= 50) {
            playSE(3);
            whatdraw = 10;
        }

        if (whatdraw == 10) {
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
