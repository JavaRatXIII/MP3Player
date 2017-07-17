package mp3player;

import javax.swing.*;
import java.io.*;
import java.util.*;
import javazoom.jl.player.*;
import javazoom.jl.decoder.*;
/**
 *
 * @author Jun
 */
public class SongControl implements SongInterface{
    FileInputStream _fis;
    BufferedInputStream _bis;
    private String _path;
    private boolean _paused = true;
    
    public Player PPlayer;
    public long PausePosition;
    public long SongLength;
    
    @Override
    public void New_Song()
    {
        Stop();
        /*JFileChooser filechooser = new JFileChooser();
        int result = filechooser.showOpenDialog(null);
        File file = filechooser.getSelectedFile();
        _path = file.getAbsolutePath();*/
        _path = "C:\\Users\\Jun\\Music\\Fullmetal_alchemist_ending_1.mp3";
        
        _paused = false;
        Resume();
    }
    
    @Override
    public void Stop()
    {
        if(PPlayer != null)
        {
            PPlayer.close();
            PausePosition = 0;
            SongLength = 0;
        }
    }
    
    @Override
    public void Pause()
    {
        try 
        {
            _paused = true;
            PausePosition = _fis.available();
            PPlayer.close();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
    }
    
    @Override
    public void Rewind()
    {
        Stop();
        _paused = false;
        Resume();
    }
    
    @Override
    public void Resume()
    {
        try 
        {
            _fis = new FileInputStream(_path);
            _bis = new BufferedInputStream(_fis);
            PPlayer = new Player(_bis);
            SongLength = _fis.available();
            System.out.println(SongLength);
            
            if(_paused)
            {
                _fis.skip(SongLength-PausePosition);
            }
        }
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
        new Thread()
        {
            @Override
            public void run()
            {
                try 
                {
                    PPlayer.play();
                } 
                catch (JavaLayerException ex) 
                {
                    System.out.println(ex);
                }
            }
        }.start();
    }
}
