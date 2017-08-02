package Utilities;

import Interfaces.ISongListFactory;
import java.util.ArrayList;
import returnmp3s.*;

/**
 *
 * @author Jun
 */
public class SongListFactory implements ISongListFactory
{
    @Override
    public ArrayList<String> getSongList()
    {
        IReturnMP3s _returnSongList;
        _returnSongList = new ReturnMP3sFactory().getReturnMP3s();
        return _returnSongList.getAllFiles();
    }
}
