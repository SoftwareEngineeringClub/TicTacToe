// ##########################################################################
// # File Name:	TicTacToeDesktopStartStopController.java
// ##########################################################################

package tictactoe.desktop.bootstrap;

import tictactoe.client.gameclient.IGameController;
import tictactoe.client.homeclient.IHomeController;
import tictactoe.client.mainclient.IMainController;
import tictactoe.client.mainclient.ISessionController;
import tictactoe.client.playersclient.IPlayersController;
import tictactoe.service.sessionservice.ISessionService;

import strata1.client.shell.IDispatcher;
import strata1.injector.bootstrap.IStartStopController;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class TicTacToeDesktopStartStopController 
    implements IStartStopController
{
    private IContainer itsContainer;
    
    /************************************************************************
     * Creates a new TicTacToeDesktopStartStopController. 
     *
     */
    public 
    TicTacToeDesktopStartStopController() 
    {
        itsContainer = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IStartStopController 
    setContainer(IContainer container)
    {
        itsContainer = container;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startApplication()
    {
        IDispatcher dispatcher = 
            itsContainer.getInstance( IDispatcher.class );
        
        Runnable startUpTask = 
            new Runnable()
            {
                public void
                run()
                {   
                    try
                    {
                        doStartUp();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace(System.out);
                        System.exit( -1 );
                    }
                }
            };

        dispatcher.insertTask( startUpTask );
        dispatcher.start();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stopApplication()
    {
        System.exit( 0 );
    }

    /************************************************************************
     *  
     *
     */
    private void
    doStartUp()
    {  
        ISessionService    sessionService    = null;
        IMainController    mainController    = null;
        ISessionController sessionController = null;
        IHomeController    homeController    = null;
        IPlayersController playersController = null;
        IGameController    gameController    = null;
        
        System.out.println( "debug 1");
        sessionService = itsContainer.getInstance( ISessionService.class );
        System.out.println( "debug 2");
        mainController = itsContainer.getInstance(IMainController.class);
        sessionController = itsContainer.getInstance(ISessionController.class);
        homeController = itsContainer.getInstance(IHomeController.class);
        playersController = itsContainer.getInstance(IPlayersController.class);
        gameController = itsContainer.getInstance(IGameController.class);
        
        if ( sessionService == null )
            throw new NullPointerException( "SessionService is null.");
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
