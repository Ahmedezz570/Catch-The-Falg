package Game;

import Game.Gui.Menu;

import Texture.TextureReader;
import com.sun.opengl.util.j2d.TextRenderer;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.BitSet;
import Sound.Sound;
import javax.swing.JOptionPane;




public class AnimEventListener extends AnimationListener {

    public static final int MAX_WIDTH = 100, MAX_HEIGHT = 100;

    Sound sound = new Sound();
    boolean isMultiPlayer = false;  
    TextRenderer textRenderer = new TextRenderer(Font.decode("PLAIN 100"));
    public String username;
    double xPosition = 0, yPosition = 0;
    int whatdraw = 0;



    private int timer = 0;
    private int timerHandler = 0;
    public static String[] textureNames = {
        "Menu//PLAYBUTTON.png", "Menu//SETTINGS.png", "Menu//HOW  PLAY.png",
        "Menu//EXITBUTTON.png","Menu//SINGLE PLAYER.png", "Menu//MULITI PLAYERS .png",
        "Menu//soundOnWhite.png", "Menu//soundOffWhite.png","Menu//BACKBUTTON.png",
        "heart.png", "Menu//back.png","Menu//HOW TO PLAY.png",
        "Menu//Box.png", "Menu//HighScore.png", "Menu//MAINMENU.png",
        "Menu//TRYAGAIN.png","Menu//EASY.png", "Menu//MEDIUM.png", 
        "Menu//HARD.png","Menu//BACKBUTTON.png","flags//blueball.png",
        "flags//redball.png","flags//blueflag.png","flags//redflag.png",
        "Menu//Background.png"

    };


    Menu menu = new Menu();

    boolean mute = false;
    boolean show = false;
    boolean paused = false;

    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    public static int[] textures = new int[textureNames.length];

    public void drawSprite(GL gl, double x, double y, int index, float xScale, float yScale) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, AnimEventListener.textures[index]);    // Turn Blending On

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

    public void drawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 1]);	// Turn Blending On

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
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 15]);	// Turn Blending On

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

    public void drawRedFlag(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 2]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-.99f, -.1f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-0.89f, -0.1f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-0.89f, 0.1f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-0.99f, 0.1f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }



    public void drawRedBall1(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 4]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-0.6f, -0.1f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-.7f, -0.1f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-.7f, 0.1f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-.6f, 0.1f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }



    public void drawRedBall2(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 4]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-0.3f, -0.1f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-.4f, -0.1f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-.4f, 0.1f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-.3f, 0.1f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }



    public void drawRedBall3(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 4]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-0.6f, 0.4f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-.7f, 0.4f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-.7f, 0.6f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-.6f, 0.6f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }



    public void drawRedBall4(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 4]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-0.6f, -0.4f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-.7f, -0.4f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-.7f, -0.6f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-.6f, -0.6f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }





    void handleTimer() {
        timerHandler++;
        if (timerHandler == 24) {
            timer++;
            timerHandler = 0;
        }
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

        username = JOptionPane.showInputDialog("Enter your name: ");
        if (username == null || username.trim().isEmpty()) {
            username = "Guest";

        }
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        TextRenderer textRenderer = null;
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        handleKeyPress();

        switch (whatdraw) {
            case 0:
                menu.drawMenu(gl);
                if (mute == false) {
                    System.out.println("unmute");

                } else {

                    System.out.println("mute");
                }
                break;
            case 1:
   
                handleTimer();
                drawBack(gl);
                drawRedFlag(gl);
                drawRedBall1(gl);
                drawRedBall2(gl);
                drawRedBall3(gl);
                drawRedBall4(gl);


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

            case 4: // For Class Levels .

                break;

        }


    }

    public void drawHighScore(String highScore) {

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

    public void handleKeyPress() {

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

        System.out.println(x + " " + y);

        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
        System.out.println(width + " " + height);

        xPosition = (int) ((x / width) * 100);
        yPosition = ((int) ((y / height) * 100));
        yPosition = 100 - yPosition;

        System.out.println("x " + xPosition + " y " + yPosition);




        if (whatdraw == 0) {
            if (xPosition >= 92 && xPosition <= 98 && yPosition >= 2 && yPosition <= 8) {
                whatdraw = 3;
            }
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 30 && yPosition <= 39) {
                playSE(3);
                System.exit(0);
            }

            if (xPosition >= 92.5 && xPosition <= 97.5 && yPosition >= 92.5 && yPosition <= 97.5) {
                playSE(3);
                mute = !mute;
                if (menu.mute == 6) {
                    menu.mute = 7;
                } else {
                    menu.mute = 6;
                }
            }

            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 66 && yPosition <= 75) {
                playSE(3);
                whatdraw = 4;//levels
                isMultiPlayer = false;
            }
            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 55 && yPosition <= 63) {
                playSE(3);
                whatdraw = 4;
                isMultiPlayer = true;
            }

        }
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
//                        menu.writeToFile("HighScore.txt", username + ": " + player1.getScore() + " " + player2.getScore() + "\n" + menu.readFromFile("HighScore.txt"));

                    } else {
//                        menu.writeToFile("HighScore.txt", username + ": " + player1.getScore() + "\n" + menu.readFromFile("HighScore.txt"));
                    }
                    playSE(3);
                    resetGame();
                    paused = false;
                }
                if (xPosition >= 52 && xPosition <= 72 && yPosition >= 38 && yPosition <= 48) {
                    if (isMultiPlayer) {
//                        menu.writeToFile("HighScore.txt", username + ": " + player1.getScore() + " " + player2.getScore() + "\n" + menu.readFromFile("HighScore.txt"));

                    } else {
//                        menu.writeToFile("HighScore.txt", username + ": " + player1.getScore() + "\n" + menu.readFromFile("HighScore.txt"));
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
//                        menu.writeToFile("HighScore.txt", username + ": " + player1.getScore() + " " + player2.getScore() + "\n" + menu.readFromFile("HighScore.txt"));

                    } else {
//                        menu.writeToFile("HighScore.txt", username + ": " + player1.getScore() + "\n" + menu.readFromFile("HighScore.txt"));
                    }

                    playSE(3);
                    resetGame();
                    show = false;

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

}
