// ##########################################################################
// # File Name:	TicTacToeDesktopApp.java
// ##########################################################################

package tictactoe.desktop.main;

import tictactoe.desktop.bootstrap.TicTacToeDesktopFactory;

import strata1.client.application.DesktopApplication;
import strata1.injector.bootstrap.Bootstrapper;

/****************************************************************************
 * 
 */
public 
class TicTacToeDesktopApp 
    extends DesktopApplication
{

    /************************************************************************
     * Creates a new TicTacToeDesktopApp. 
     *
     * @param bootstrapper
     * @param factory
     */
    public 
    TicTacToeDesktopApp()
    {
        super( 
            new Bootstrapper(),
            new TicTacToeDesktopFactory() );
    }
    
    /************************************************************************
     *  
     *
     * @param args
     */
    public static void 
    main(String[] args)
    {
        TicTacToeDesktopApp application = 
            new TicTacToeDesktopApp();
        
        try
        {
            application.run( args );
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            System.exit( -1 );
        }
    }

}

// ##########################################################################
