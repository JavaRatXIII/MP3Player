package mp3player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import returnmp3s.*;
import Console.*;
import Interfaces.IReturnMP3sFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.player.*;
import javazoom.jl.decoder.*;
/**
 *
 * @author Jun
 */
public class SongControl implements SongInterface{
    private FileInputStream _fis;
    private BufferedInputStream _bis;
    private String _path;
    private boolean _paused = true;
    
    private final IConsole _console;
    private final IReturnMP3s _returnSongList;
    private final ArrayList<String> _songList;
    
    private Player _songPlayer;
    private long _pausePosition;
    private long _songLength;
    
    public SongControl(IReturnMP3sFactory returnMP3sFactory)
    {
        super();
        _console = new Console();
        _returnSongList = returnMP3sFactory.getReturnMP3s();
        _songList = _returnSongList.getAllFiles();
    }
    
    @Override
    public void Play()
    {
        Stop();
        
        ShowSongs();
        
        _console.WriteLine("Select the number of the song you wish to play");
        
        while(true)
        {
            try 
            {
                _path = "C:\\Users\\Jun\\Music\\"+_songList.get(Integer.parseInt(_console.ReadLine()));
                break;
            } 
            catch (Exception ex) 
            {
                _console.WriteLine(ex.getMessage());
                _console.WriteLine("Select the NUMBER WITHIN THE LIST of the song you wish to play");
            }
        }
        
        _paused = false;
        Resume();
    }
    
    private void ShowSongs()
    {
        _console.WriteLine("Here is a list of your songs");
        for(int i = 0; i < _songList.size(); i++)
        {
            _console.WriteLine(i+". "+_songList.get(i));
        }
    }
    
    @Override
    public void Stop()
    {
        if(_songPlayer != null)
        {
            _songPlayer.close();
            _pausePosition = 0;
            _songLength = 0;
        }
    }
    
    @Override
    public void Pause()
    {
        try 
        {
            _paused = true;
            _pausePosition = _fis.available();
            _songPlayer.close();
        } 
        catch (IOException ex) 
        {
            _console.WriteLine(ex.getMessage());
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
            _songPlayer = new Player(_bis);
            _songLength = _fis.available();
            
            if(_paused)
            {
                _fis.skip(_songLength-_pausePosition);
            }
        }
        catch (Exception ex) 
        {
            _console.WriteLine(ex.getMessage());
        }
        new Thread()
        {
            @Override
            public void run()
            {
                try 
                {
                    _songPlayer.play();
                } 
                catch (JavaLayerException ex) 
                {
                    _console.WriteLine(ex.getMessage());
                }
            }
        }.start();
    }
}
