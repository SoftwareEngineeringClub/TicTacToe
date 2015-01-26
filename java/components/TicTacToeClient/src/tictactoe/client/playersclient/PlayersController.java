// ##########################################################################
// # File Name:	PlayersController.java
// ##########################################################################

package tictactoe.client.playersclient;

import tictactoe.client.gameclient.IGameController;
import tictactoe.client.mainclient.IMainController;
import tictactoe.client.mainclient.IMainSubController;
import tictactoe.service.playerservice.PlayerData;

import strata1.client.command.ExecutionException;
import strata1.client.command.ICommand;
import strata1.client.controller.AbstractController;
import strata1.client.controller.IHandler;
import strata1.client.event.IChangeEvent;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

import java.util.List;

/****************************************************************************
 * 
 */
public 
class PlayersController 
    extends    AbstractController 
    implements IPlayersController
{
    private final IMainController itsMainController;
    private final IGameController itsGameController;
    private final IPlayersView    itsView;
    private final IPlayersModel   itsModel;
    private final ILogger         itsLogger;
    
    /************************************************************************
     * Creates a new PlayersController. 
     *
     */
    @Inject
    public 
    PlayersController(
        IMainController mainController,
        IGameController gameController,
        IPlayersView    view,
        IPlayersModel   model,
        ILogger         logger)
    {
        itsMainController = mainController;
        itsGameController = gameController;
        itsView           = view;
        itsModel          = model;
        itsLogger         = logger;
        
        itsMainController.setPlayersController( this );
        itsView.setProvider( this );
        itsModel.setProcessor( this );
        
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
        itsView.show();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainSubController 
    setSessionId(Long sessionId)
    {
        itsModel.setSessionId( sessionId );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainSubController 
    setUserId(Long userId)
    {
        itsModel.setUserId( userId );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startListening()
    {
        itsModel.startListening();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainController 
    getMainController()
    {
        return itsMainController;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainSubController 
    showView()
    {
        itsModel.refreshPlayerData();
        return this;
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    setCommands()
    {
        setCommand( 
            "IssueChallenge",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                    doIssueChallenge();
                }
            } );
        
        setCommand( 
            "AcceptChallenge",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                    doAcceptChallenge();
                }
            } );
        
        setCommand( 
            "DeclineChallenge",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                    doDeclineChallenge();
                }
            } );           
        
        setCommand( 
            "StartGame",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                    doStartGame();
                }
            } );           
    }

    /************************************************************************
     *  
     *
     */
    protected void
    setHandlers()
    {
        setHandler(
            RefreshPlayersEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doRefreshPlayerData((RefreshPlayersEvent)event);
                }

            } );
        setHandler(
            ProcessChallengeEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doProcessChallenge((ProcessChallengeEvent)event);
                }

            } );
        setHandler(
            ChallengeReplyEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doProcessChallengeReply((ChallengeReplyEvent)event);
                }

            } );
     }
  
    /************************************************************************
     *  
     *
     */
    private void
    doIssueChallenge()
    {
        itsModel
            .setChallengedUser( itsView.getSelectedPlayer() )
            .issueChallenge();
    }
    
    /************************************************************************
     *  
     *
     */
    private void
    doAcceptChallenge()
    {
        itsModel.acceptChallenge();
    }
    
    /************************************************************************
     *  
     *
     */
    private void
    doDeclineChallenge()
    {
        itsModel.declineChallenge();
    }
    
    /************************************************************************
     *  
     *
     */
    private void
    doStartGame()
    {
        itsGameController.startGame( itsModel.getNewGameId() );
    }
    
    /************************************************************************
     *  
     *
     * @param event
     */
    private void 
    doRefreshPlayerData(RefreshPlayersEvent event)
    {
        IPlayersModel model = event.getSender( IPlayersModel.class );
        long          userId = model.getUserId();
        
        itsView.removePlayers();
        
        for(PlayerData playerData : model.getPlayerData())
            if ( playerData.getUserId() != userId )
                itsView.insertPlayer( playerData );
        
        itsView.show();    
    }
    
    /************************************************************************
     *  
     *
     * @param event
     */
    private void 
    doProcessChallenge(ProcessChallengeEvent event)
    {
        IPlayersModel model      = event.getSender( IPlayersModel.class );
        PlayerData    challenger = model.getChallenger();

        itsView.displayChallenge( challenger );
    }
    
    /************************************************************************
     *  
     *
     * @param event
     */
    private void 
    doProcessChallengeReply(ChallengeReplyEvent event)
    {
        IPlayersModel model = event.getSender( IPlayersModel.class );

        if ( model.isChallengeAccepted() )
            itsView.displayChallengeAccepted( model.getChallenged() );
        else  
            itsView.displayChallengeDeclined( model.getChallenged() );
    }

}

// ##########################################################################
