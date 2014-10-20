// ##########################################################################
// # File Name:	MainController.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.command.ExecutionException;
import strata1.client.command.ICommand;
import strata1.client.controller.AbstractController;
import strata1.client.controller.IHandler;
import strata1.client.event.IChangeEvent;

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
    
    /************************************************************************
     * Creates a new MainController. 
     *
     */
    @Inject
    public 
    MainController(IMainView mainView,IMainModel mainModel)
    {
        itsMainView          = mainView;
        itsMainModel         = mainModel;
        itsSessionController = null;
        itsHomeController    = null;
        itsPlayersController = null;
        itsGameController    = null;
        
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
        itsMainView
            .setActiveTab(itsMainModel.getActiveTab())
            .show();
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
        itsMainModel.setSession( session );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    completeLogin()
    {
        itsMainModel.setActiveTab(MainTabKind.HOME_TAB);
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
                    itsMainModel.setActiveTab( MainTabKind.HOME_TAB );
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
                    itsMainModel.setActiveTab( MainTabKind.PLAYERS_TAB );
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
                    itsMainModel.setActiveTab( MainTabKind.HELP_TAB );
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
}

// ##########################################################################
