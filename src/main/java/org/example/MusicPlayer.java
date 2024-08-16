package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\dell\\Downloads\\VictorySound.wav";  // Change this to your file path
        playAudio(filePath);
    }

    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            audioClip.start();
            System.out.println("Audio clip started.");

            // Keep the program running until the audio finishes playing
            while (audioClip.isRunning()) {
                Thread.sleep(1000);
            }
            System.out.println("Audio clip finished.");
            audioClip.close();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O error while playing audio: " + filePath);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Audio line unavailable.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted while playing audio.");
            e.printStackTrace();
        }
    }
}

