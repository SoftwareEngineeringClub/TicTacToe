// ##########################################################################
// # File Name:	TicTacToeDesktopApp.java
// ##########################################################################

package tictactoe.desktop.main;

import tictactoe.desktop.bootstrap.TicTacToeDesktopBootstrapper;
import tictactoe.desktop.bootstrap.TicTacToeDesktopFactory;

import strata1.client.application.ClientApplication;
import strata1.client.bootstrap.IClientBootstrapper;
import strata1.client.bootstrap.IClientFactory;

/****************************************************************************
 * 
 */
public 
class TicTacToeDesktopApp 
    extends ClientApplication
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
            new TicTacToeDesktopBootstrapper(),
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
        try
        {
            new TicTacToeDesktopApp().run( args );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

// ##########################################################################
