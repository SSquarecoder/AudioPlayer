//package org.example;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;
//import javax.sound.sampled.*;
//
//public class AudioPlayer {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("C:\\Users\\dell\\Downloads\\oopswa.mp3");
//        String filePath = scanner.nextLine();
//
//        File audioFile = new File(filePath);
//
//        if (!audioFile.exists()) {
//            System.out.println("File not found at the specified location.");
//            return;
//        }
//
//        long startTime = System.currentTimeMillis();  // Start timing
//
//        // Initialize the audio system
//        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile)) {
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//
//            long loadTime = System.currentTimeMillis() - startTime;  // Calculate load time
//            System.out.println("Audio loaded in: " + loadTime + " ms");
//
//            String response = "";
//
//            while (!response.equals("Q")) {
//                System.out.println("P = Play, S = Stop, R = Reset, Q = Quit");
//                System.out.print("Enter your choice: ");
//
//                response = scanner.next().toUpperCase();
//
//                switch (response) {
//                    case "P":
//                        clip.start();
//                        break;
//                    case "S":
//                        clip.stop();
//                        break;
//                    case "R":
//                        clip.setMicrosecondPosition(0);
//                        break;
//                    case "Q":
//                        clip.close();
//                        break;
//                    default:
//                        System.out.println("Not a valid response");
//                }
//            }
//
//            System.out.println("Goodbye!");
//        } catch (UnsupportedAudioFileException e) {
//            System.out.println("The specified audio file is not supported.");
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("Error occurred while trying to read the audio file.");
//            e.printStackTrace();
//        } catch (LineUnavailableException e) {
//            System.out.println("Audio line for playing the file is unavailable.");
//            e.printStackTrace();
//        } finally {
//            scanner.close();
//        }
//    }
//}

//package org.example;
//import javazoom.jl.decoder.BitstreamException;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;
//
//import java.io.BufferedInputStream;
//import java.io.InputStream;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class MP3Player {
//
//    public static void main(String[] args) {
//        String urlString = "https://www.example.com/path/to/your/audiofile.mp3";
//
//        try {
//            URL url = new URL(urlString);
//            URLConnection conn = url.openConnection();
//            InputStream is = conn.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            Player player = new Player(bis);
//
//            System.out.println("Playing MP3 from URL: " + urlString);
//            player.play();
//
//        } catch (JavaLayerException | BitstreamException e) {
//            System.out.println("Error playing the MP3 file.");
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("Error connecting to the URL.");
//            e.printStackTrace();
//        }
//    }
//}
package org.example;
//import javazoom.jl.decoder.BitstreamException;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;
//
//import java.io.BufferedInputStream;
//import java.io.InputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class AudioPlayer {
//
//    public static void main(String[] args) {
//        String urlString = "https://www.voicy.network/sounds/cTxf5WLH0k_Yu1KdTuls5Q-oeps";
//
//        try {
//            URL url = new URL(urlString);
//            URLConnection conn = url.openConnection();
//            InputStream is = conn.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            Player player = new Player(bis);
//
//            System.out.println("Playing MP3 from URL: " + urlString);
//            player.play();
//
//        } catch (JavaLayerException e) {
//            System.out.println("Error playing the MP3 file.");
//            e.printStackTrace();
//        }
////        catch (BitstreamException e) {
////            System.out.println("Bitstream error.");
////            e.printStackTrace();
////        }
//        catch (IOException e) {
//            System.out.println("Error connecting to the URL.");
//            e.printStackTrace();
//        }
//    }
//}
//import javazoom.jl.decoder.BitstreamException;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;
//
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class AudioPlayer {
//
//    public static void main(String[] args) {
//        // URL of the MP3 file
//        String urlString = "https://www.voicy.network/sounds/cTxf5WLH0k_Yu1KdTuls5Q-oeps";
//
//
//        try {
//            // Create a URL object
//            URL url = new URL(urlString);
//
//            // Open a connection to the URL
//            URLConnection conn = url.openConnection();
//
//            // Get an InputStream from the connection
//            InputStream is = conn.getInputStream();
//
//            // Wrap the InputStream with BufferedInputStream for better performance
//            BufferedInputStream bis = new BufferedInputStream(is);
//
//            // Create a Player object with the BufferedInputStream
//            Player player = new Player(bis);
//
//            // Start playing the MP3 file
//            System.out.println("Playing MP3 from URL: " + urlString);
//            player.play();
//
//            // Optionally, wait for the player to finish playing
//            // The player will block until the audio has finished playing
//            System.out.println("Playback finished");
//
//        } catch (JavaLayerException e) {
//            System.out.println("Error playing the MP3 file.");
//            e.printStackTrace();
//        }
////        catch (BitstreamException e) {
////            System.out.println("Bitstream error.");
////            e.printStackTrace();
////        }
//        catch (IOException e) {
//            System.out.println("Error connecting to the URL.");
//            e.printStackTrace();
//        }
//    }
//}

import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AudioPlayer {

    public static void main(String[] args) {
        // URL of the MP3 file
        String urlString = "https://www.voicy.network/sounds/cTxf5WLH0k_Yu1KdTuls5Q-oeps";

        try {
            // Create a URL object
            URL url = new URL(urlString);

            // Open a connection to the URL
            URLConnection conn = url.openConnection();

            // Get an InputStream from the connection
            InputStream is = conn.getInputStream();

            // Wrap the InputStream with BufferedInputStream for better performance
            BufferedInputStream bis = new BufferedInputStream(is);

            // Create a Player object with the BufferedInputStream
            Player player = new Player(bis);

            // Create a new thread to play the MP3 file
            Thread playbackThread = new Thread(() -> {
                try {
                    System.out.println("Missed, here is a tip: " + urlString);
                    player.play();
                    System.out.println("Playback finished");
                } catch (JavaLayerException e) {
                    System.out.println("Error playing the MP3 file.");
                    e.printStackTrace();
                }
            });

            // Start the playback thread
            playbackThread.start();

        } catch (JavaLayerException e) {
            System.out.println("Error initializing the MP3 player.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error connecting to the URL.");
            e.printStackTrace();
        }
    }
}

