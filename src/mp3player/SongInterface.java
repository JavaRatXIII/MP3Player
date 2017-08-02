/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3player;

/**
 *
 * @author Jun
 */
public interface SongInterface {
    public void Play();
    
    public void Stop();
    
    public void Pause();
    
    public void Resume();
    
    public void Rewind();
    
    public void Forward();
}
