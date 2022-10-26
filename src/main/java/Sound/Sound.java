package Sound;

import javax.sound.sampled.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import static Control.Menu.loseGame;
import static Control.Menu.winGame;
import static GameRunner.RunBomberman.player;
import static GameRunner.RunBomberman.wait;

public class Sound extends JFrame{
    public static Clip music;
    public static Clip bombExplosion;
    public static Clip justDied;
    public static Clip putBomb;
    public static Clip welcome;

    public static boolean isSoundTitle = false;
    public static boolean isSoundDied = false;

    public static boolean isSoundComplete = false;
    public static boolean hasBomb = false;
    public Sound(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            final String path = "res/sound/" + name;
            File file = new File(path);
            AudioInputStream audio_input = AudioSystem.getAudioInputStream(file);
            if (sound.equals("music")) {
                music = AudioSystem.getClip();
                music.open(audio_input);
                FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                music.loop(10);
            }

            if (sound.equals("welcome")) {
                welcome = AudioSystem.getClip();
                welcome.open(audio_input);
                FloatControl gainControl = (FloatControl) welcome.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                welcome.loop(10);
            }

            if (sound.equals("explosion")) {
                bombExplosion = AudioSystem.getClip();
                bombExplosion.open(audio_input);
                FloatControl gainControl = (FloatControl) bombExplosion.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                bombExplosion.start();
                hasBomb = true;
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
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateSound() {
        if (!isSoundTitle) {
            welcome.close();
            new Sound("music.wav", "music");
            isSoundTitle = true;
        }
        if (player.getLife() == 0) {
            music.close();
            if (hasBomb) {
                bombExplosion.close();
            }
            if (!isSoundDied) {
                new Sound("Died.wav", "justDied");
                isSoundDied = true;
            }
        }
    }
}
