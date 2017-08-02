package mp3player;

import Console.*;
import Utilities.ReturnMP3sFactory;
import java.io.IOException;
/**
 *
 * @author Tamanna
 */
public class MP3Player {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        SongInterface song = new SongControl(new ReturnMP3sFactory());
        IConsole console = new Console();
        boolean paused = false;
        
        console.WriteLine("State Play, Stop, Rewind, Rsume or Exit");
        String input = console.ReadLine();
        input = input.toLowerCase();
        
        while(!(input.equals("exit")))
        {
            if(input.equals("play"))
            {
                song.Play();
                paused = false;
            }
            else if(input.equals("stop"))
            {
                song.Stop();
            }
            else if(input.equals("rewind"))
            {
                song.Rewind();
            }
            else if(input.equals("pause"))
            {
                if(paused == false)
                {
                    song.Pause();
                    paused = true;
                }
                else
                {
                    song.Resume();
                    paused = false;
                }
            }
            input = console.ReadLine().toLowerCase();
        }
        
        if(input.equals("exit"))
        {
            song.Stop();
        }
    }
}
