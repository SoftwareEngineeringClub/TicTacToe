// ##########################################################################
// # File Name:	TicTacToeDesktopStarter.java
// ##########################################################################

package tictactoe.desktop.bootstrap;

import tictactoe.client.gameclient.IGameController;
import tictactoe.client.homeclient.IHomeController;
import tictactoe.client.mainclient.IMainController;
import tictactoe.client.mainclient.ISessionController;
import tictactoe.client.playersclient.IPlayersController;

import strata1.injector.bootstrap.IApplicationStarter;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class TicTacToeDesktopStarter 
    implements IApplicationStarter
{

    /************************************************************************
     * Creates a new TicTacToeDesktopStarter. 
     *
     */
    public 
    TicTacToeDesktopStarter() {}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startApplication(IContainer container)
    {
        IMainController    mainController    = null;
        ISessionController sessionController = null;
        IHomeController    homeController    = null;
        IPlayersController playersController = null;
        IGameController    gameController    = null;
        
        mainController = container.getInstance(IMainController.class);
        sessionController = container.getInstance(ISessionController.class);
        homeController = container.getInstance(IHomeController.class);
        playersController = container.getInstance(IPlayersController.class);
        gameController = container.getInstance(IGameController.class);
        
        if ( mainController == null )
            throw new NullPointerException("MainController is null.");

        if ( sessionController == null )
            throw new NullPointerException("SessionController is null.");

        if ( homeController == null )
            throw new NullPointerException("HomeController is null.");

        if ( playersController == null )
            throw new NullPointerException("PlayersController is null.");

        if ( gameController == null )
            throw new NullPointerException("GameController is null.");

        mainController.start();
    }

}

// ##########################################################################
