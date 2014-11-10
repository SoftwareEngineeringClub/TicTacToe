// ##########################################################################
// # File Name:	TicTacToeServerConsoleHost.java
// ##########################################################################

package tictactoe.server.main;

import java.io.IOException;

/****************************************************************************
 * 
 */
public 
class TicTacToeServerConsoleHost
{
    
    /************************************************************************
     * Creates a new TicTacToeServerConsoleHost. 
     *
     */
    public 
    TicTacToeServerConsoleHost()
    {
    }

    /************************************************************************
     *  
     *
     * @param args
     */
    public static void 
    main(String[] args)
    {
        TicTacToeServerApp application = new TicTacToeServerApp();
        
        try
        {
            System.out.println( "debug A" );
            application.start();
            
            System.out.print( "Hit enter to exit application:" );
            
            try
            {
                System.in.read();
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }

            System.out.println( "debug B" );
            application.stop();
            System.exit( 0 );
        }
        catch (Exception e)
        {
            e.printStackTrace( System.out );
            System.exit( -1 );
        }
    }

}

// ##########################################################################
