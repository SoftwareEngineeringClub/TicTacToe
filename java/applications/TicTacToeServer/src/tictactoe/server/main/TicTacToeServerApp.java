// ##########################################################################
// # File Name:	TicTacToeServerApp.java
// ##########################################################################

package tictactoe.server.main;

import tictactoe.server.bootstrap.TicTacToeServerFactory;

import strata1.injector.bootstrap.ApplicationBootstrapper;
import strata1.injector.bootstrap.Bootstrapper;
import strata1.injector.bootstrap.IApplicationBootstrapper;
import strata1.injector.bootstrap.IApplicationFactory;
import strata1.injector.bootstrap.IBootstrapper;

/****************************************************************************
 * 
 */
public 
class TicTacToeServerApp
{
    private IApplicationFactory itsFactory;
    private IBootstrapper       itsBootstrapper;
    
    /************************************************************************
     * Creates a new TicTacToeServerApp. 
     *
     */
    public 
    TicTacToeServerApp()
    {
        itsFactory      = new TicTacToeServerFactory();
        itsBootstrapper = new Bootstrapper();
    }

    /************************************************************************
     *  
     *
     * @param arguments
     * @throws Exception
     */
    public void
    start()
        throws Exception
    {
        itsBootstrapper.run( itsFactory );
        itsBootstrapper
            .getStartStopController()
            .startApplication();
    }

    public void
    stop()
    {
        itsBootstrapper
            .getStartStopController()
            .stopApplication();
    }
}

// ##########################################################################
