// ##########################################################################
// # File Name:	MainController.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.command.ExecutionException;
import strata1.client.command.ICommand;
import strata1.client.controller.AbstractController;
import strata1.client.controller.IHandler;
import strata1.client.event.IChangeEvent;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class MainController 
    extends    AbstractController 
    implements IMainController
{
    private final IMainView    itsMainView;
    private final IMainModel   itsMainModel;
    private IMainSubController itsSessionController;
    private IMainSubController itsHomeController;
    private IMainSubController itsPlayersController;
    private IMainSubController itsGameController;
    private ILogger            itsLogger;
    
    /************************************************************************
     * Creates a new MainController. 
     *
     */
    @Inject
    public 
    MainController(
        IMainView  mainView,
        IMainModel mainModel,
        ILogger    logger)
    {
        itsMainView          = mainView;
        itsMainModel         = mainModel;
        itsSessionController = null;
        itsHomeController    = null;
        itsPlayersController = null;
        itsGameController    = null;
        itsLogger            = logger;
        
        itsMainView.setProvider( this );
        itsMainModel.setProcessor( this );
        
        setCommands();
        setHandlers();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
        itsLogger.logInfo( "Starting session controller." );
        itsMainView
            .setActiveTab(itsMainModel.getActiveTab())
            .start();
        itsSessionController.start();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainController 
    setSessionController(IMainSubController sessionController)
    {
        itsSessionController = sessionController;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainController 
    setHomeController(IMainSubController homeController)
    {
        itsHomeController = homeController;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainController 
    setPlayersController(IMainSubController playersController)
    {
        itsPlayersController = playersController;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainController 
    setGameController(IMainSubController gameController)
    {
        itsGameController = gameController;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainController 
    setSession(ISessionModel session)
    {
        itsLogger.logInfo( "Setting new session." );
        itsMainModel.setSession( session );
        itsHomeController
            .setSessionId( session.getSessionId() )
            .setUserId( session.getUserId() );
        itsPlayersController
            .setSessionId( session.getSessionId() )
            .setUserId( session.getUserId() );
        /*
        itsGameController
            .setSessionId( session.getSessionId() )
            .setUserId(  session.getUserId() );
            */
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    completeLogin()
    {
        itsLogger.logInfo( "Completing login." );
        itsMainModel.setActiveTab(MainTabKind.HOME_TAB);
        itsMainView.setActiveTab( itsMainModel.getActiveTab() );
        itsHomeController.showView();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    exit()
    {
        ICommand command = itsSessionController.getCommand( "Logout" );
        
        itsLogger.logInfo( "Exiting application." );
        
        try
        {
            if ( mustLogout() )
                command.execute();
            
            System.exit( 0 );
        }
        catch (ExecutionException e)
        {
            itsLogger.logError( e.getMessage() );
            System.exit( -1 );
        }     
    }

    /************************************************************************
     *  
     *
     */
    protected void
    setCommands()
    {
        setCommand(
            "SelectFile",
            new ICommand()
            {
                @Override
                public void 
                execute() throws ExecutionException
                {
                    itsLogger.logInfo( "Selecting file tab." );
                    itsMainModel.setActiveTab( MainTabKind.FILE_TAB );
                }         
            });
        setCommand(
            "SelectHome",
            new ICommand()
            {
                @Override
                public void 
                execute() throws ExecutionException
                {
                    itsLogger.logInfo( "Selecting home tab." );
                    itsMainModel.setActiveTab( MainTabKind.HOME_TAB );
                    itsHomeController.showView();
                }         
            });
        setCommand(
            "SelectPlayers",
            new ICommand()
            {
                @Override
                public void 
                execute() throws ExecutionException
                {
                    itsLogger.logInfo( "Selecting players tab." );
                    itsMainModel.setActiveTab( MainTabKind.PLAYERS_TAB );
                    itsPlayersController.showView();
                }         
            });
        setCommand(
            "SelectGame",
            new ICommand()
            {
                @Override
                public void 
                execute() throws ExecutionException
                {
                    itsLogger.logInfo( "Selecting game tab." );
                    itsMainModel.setActiveTab( MainTabKind.GAME_TAB );
                }         
            });
        setCommand(
            "SelectHelp",
            new ICommand()
            {
                @Override
                public void 
                execute() throws ExecutionException
                {
                    itsLogger.logInfo( "Selecting help tab." );
                    itsMainModel.setActiveTab( MainTabKind.HELP_TAB );
                }         
            });
        setCommand(
            "Exit",
            new ICommand()
            {
                @Override
                public void 
                execute() throws ExecutionException
                {
                    exit();
                }         
            });
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    setHandlers()
    {
        setHandler(
            TabEvent.class,
            new IHandler<IChangeEvent>()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doHandleTab( (TabEvent)event );
                } 
            });
    }
    
    /************************************************************************
     *  
     *
     * @param event
     */
    protected void
    doHandleTab(TabEvent event)
    {
        IMainModel sender = event.getSender( IMainModel.class );
        
        itsMainView.setActiveTab( sender.getActiveTab() );
    }

    /************************************************************************
     *  
     *
     * @return
     */
    private boolean 
    mustLogout()
    {
        return 
            itsMainModel.getSession() != null && 
            itsMainModel.getSession().isLoggedIn();
    }
}

// ##########################################################################
