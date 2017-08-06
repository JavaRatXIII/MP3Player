package mp3player;

import Console.*;
import Utilities.*;
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
        SongInterface song = new SongControl(new ConsoleFactory(), new SongListFactory());
        IConsole console = new ConsoleFactory().GetConsole();
        boolean paused = false;
        
        console.WriteLine("State Play, Stop, Rewind, Rsume or Exit");
        String input = console.ReadLine();
        input = input.toLowerCase();
        
        while(!(input.equals("exit")))
        {
            switch (input) {
                case "play":
                    song.Play();
                    paused = false;
                    break;
                case "stop":
                    song.Stop();
                    break;
                case "rewind":
                    song.Rewind();
                    break;
                case "forward":
                    song.Forward();
                    break;
                case "pause":
                    if(paused == false)
                    {
                        song.Pause();
                        paused = true;
                    }
                    else
                    {
                        song.Resume();
                        paused = false;
                    }   break;
            }
            input = console.ReadLine().toLowerCase();
        }
        
        if(input.equals("exit"))
        {
            song.Stop();
        }
    }
}
