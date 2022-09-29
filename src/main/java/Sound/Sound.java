package Sound;

import javax.sound.sampled.*;
import javax.swing.*;

import java.io.IOException;
import java.net.URL;

import static GameRunner.RunBomberman.player;

public class Sound extends JFrame{
    public static Clip titleScreen;
    public static Clip bombExplosion;
    public static Clip justDied;
    public static Clip putBomb;
    public static boolean isSoundDied;
    public static boolean isSoundTitle;

    public Sound(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL url = this.getClass().getClassLoader().getResource(name);
            assert url != null;
            AudioInputStream audio_input = AudioSystem.getAudioInputStream(url);
            if (sound.equals("title")) {
                titleScreen = AudioSystem.getClip();
                titleScreen.open(audio_input);
                FloatControl gainControl = (FloatControl) titleScreen.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                titleScreen.loop(10);
            }
            if (sound.equals("explosion")) {
                bombExplosion = AudioSystem.getClip();
                bombExplosion.open(audio_input);
                FloatControl gainControl = (FloatControl) bombExplosion.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                bombExplosion.start();
            }
            if (sound.equals("justDied")) {
                justDied = AudioSystem.getClip();
                justDied.open(audio_input);
                justDied.start();
            }
            if (sound.equals("putBomb")) {
                putBomb = AudioSystem.getClip();
                putBomb.open(audio_input);
                FloatControl gainControl = (FloatControl) putBomb.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(+6.0206f);
                putBomb.start();
            }
            if (sound.equals("default")) {
                Clip clip = AudioSystem.getClip();
                clip.open(audio_input);
                clip.start();
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateSound() {
        if (!isSoundTitle) {
            new Sound("sound/title.wav", "title");
            isSoundTitle = true;
        }
        if (!player.isLife()) {
            titleScreen.close();
            bombExplosion.close();
            if (!isSoundDied) {
                new Sound("sound/justDied.wav", "justDied");
                isSoundDied = true;
            }
        }
    }
}
