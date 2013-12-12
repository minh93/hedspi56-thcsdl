package Utilities;


import java.io.File;

import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.Time;
import javax.media.format.AudioFormat;

public class mp3 {
public static void main(String[] args) {
    Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
    Format input2 = new AudioFormat(AudioFormat.MPEG);
    Format output = new AudioFormat(AudioFormat.LINEAR);
    PlugInManager.addPlugIn(
        "com.sun.media.codec.audio.mp3.JavaDecoder",
        new Format[]{input1, input2},
        new Format[]{output},
        PlugInManager.CODEC
    );
    try{
        Player player = Manager.createPlayer(new MediaLocator(new File("e://abetterday.wav").toURI().toURL()));
        player.start();
        player.syncStart(Time.TIME_UNKNOWN);
    }
    catch(Exception ex){
        ex.printStackTrace();
    }
}
}