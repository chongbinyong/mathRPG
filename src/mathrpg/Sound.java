/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathrpg;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Chong Bin Yong
 */
public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;
    
    public Sound()
    {
        soundURL[0] = getClass().getResource("/sound/MathRPG.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/seal.wav");
        soundURL[3] = getClass().getResource("/sound/boss.wav");
        soundURL[4] = getClass().getResource("/sound/dwarf.wav");
        soundURL[5] = getClass().getResource("/sound/elf.wav");
        soundURL[6] = getClass().getResource("/sound/victory.wav");
        soundURL[7] = getClass().getResource("/sound/gameover.wav");
        soundURL[8] = getClass().getResource("/sound/attack.wav");
        soundURL[9] = getClass().getResource("/sound/healthpotion.wav");
        soundURL[10] = getClass().getResource("/sound/damagepotion.wav");
    }
    
    public void setFile(int i)
    {
        try {
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
            
        }catch(Exception e) {
            
        }
    }
    
    public void play()
    {
        clip.start();
    }
    
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop()
    {
        clip.close();
    }
    
    public void checkVolume()
    {
        switch(volumeScale)
        {
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
