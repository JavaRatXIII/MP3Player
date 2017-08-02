package Utilities;

import Console.Console;
import Interfaces.IConsoleFactory;

/**
 *
 * @author Jun
 */
public class ConsoleFactory implements IConsoleFactory
{
    @Override
    public Console GetConsole()
    {
        return new Console();
    } 
}
