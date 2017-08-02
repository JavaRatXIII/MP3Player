package Utilities;

import Interfaces.IReturnMP3sFactory;
import returnmp3s.ReturnMP3s;

/**
 *
 * @author Jun
 */
public class ReturnMP3sFactory implements IReturnMP3sFactory
{
    @Override
    public ReturnMP3s getReturnMP3s() 
    {
        return new ReturnMP3s();
    }
}
