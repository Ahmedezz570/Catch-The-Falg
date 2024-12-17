package Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL[] soundURL = new URL[10];
    private FloatControl volumeControl;
    private boolean isMuted = false;
    private float previousVolume = 0.0f;

    public Sound() {

        soundURL[3] = getClass().getResource("../Assets/Sound/Click.wav");

        soundURL[9] = getClass().getResource("../Assets/Sound/The Verdant Grove LOOP.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop(); // إيقاف الصوت فورًا
            clip.flush(); // تفريغ البيانات المؤقتة
            clip.close(); // إغلاق الـ Clip لضمان الإيقاف
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void mute() {
        if (clip != null && volumeControl != null) {
            if (!isMuted) {
                previousVolume = volumeControl.getValue();
                clip.stop(); // إيقاف التشغيل
                volumeControl.setValue(volumeControl.getMinimum()); // خفض الصوت
                isMuted = true;
            } else {
                volumeControl.setValue(previousVolume); // استعادة مستوى الصوت
                clip.start(); // تشغيل الصوت
                isMuted = false;
            }
        }
    }
}
