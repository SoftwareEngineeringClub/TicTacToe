// ##########################################################################
// # File Name:	PlayersModel.java
// ##########################################################################

package tictactoe.client.playersclient;

import tictactoe.integration.playerproxy.IPlayerMessagingProxy;
import tictactoe.service.playerservice.AcceptChallengeReply;
import tictactoe.service.playerservice.AcceptChallengeRequest;
import tictactoe.service.playerservice.ChallengeEvent;
import tictactoe.service.playerservice.DeclineChallengeReply;
import tictactoe.service.playerservice.DeclineChallengeRequest;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;
import tictactoe.service.playerservice.IssueChallengeReply;
import tictactoe.service.playerservice.IssueChallengeRequest;
import tictactoe.service.playerservice.PlayerChangeEvent;
import tictactoe.service.playerservice.PlayerData;
import tictactoe.service.playerservice.PlayerException;
import tictactoe.service.playerservice.StartListeningReply;
import tictactoe.service.playerservice.StartListeningRequest;

import strata1.client.model.AbstractModel;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/****************************************************************************
 * 
 */
public 
class PlayersModel 
    extends    AbstractModel 
    implements IPlayersModel,IPlayerReplyReceiver,IPlayerEventListener
{
    private final IPlayerService   itsService;
    private final ILogger          itsLogger;
    private Long                   itsSessionId;
    private Long                   itsUserId;
    private Long                   itsChallengeRequestId;
    private Long                   itsChallengedUserId;
    private Long                   itsChallengerUserId;
    private Long                   itsChallengeId;
    private boolean                itsChallengeAcceptedFlag;
    private Long                   itsNewGameId;
    private final List<PlayerData> itsPlayerData;
    
    /************************************************************************
     * Creates a new PlayersModel. 
     *
     */
    @Inject
    public 
    PlayersModel(IPlayerService service,ILogger logger)
    {
        itsService               = service;
        itsLogger                = logger;
        itsSessionId             = 0L;
        itsUserId                = 0L;
        itsChallengeRequestId    = 0L;
        itsChallengerUserId      = 0L;
        itsChallengeId           = 0L;
        itsChallengeAcceptedFlag = false;
        itsNewGameId             = 0L;
        itsPlayerData            = new ArrayList<PlayerData>();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayersModel 
    setSessionId(Long sessionId)
    {
        itsLogger.logDebug( "Setting players model sessionId = " + sessionId );
        itsSessionId = sessionId;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayersModel 
    setUserId(Long userId)
    {
        itsLogger.logDebug( "Setting players model userId = " + userId );
        itsUserId = userId;
        ((IPlayerMessagingProxy)itsService).setUserId( itsUserId );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayersModel 
    setChallengedUser(String userName)
    {
        for (PlayerData p : itsPlayerData)
            if ( p.getUserName().equals( userName ) )
            {
                itsChallengedUserId = p.getUserId();
                return this;
            }
        
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getSessionId()
    {
        return itsSessionId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getUserId()
    {
        return itsUserId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public List<PlayerData> 
    getPlayerData()
    {
        return itsPlayerData;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getChallengeId()
    {
        return itsChallengeId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public PlayerData
    getChallenger()
    {
        if ( itsChallengerUserId == null )
            return null;
        
        for (PlayerData p : itsPlayerData)
            if ( p.getUserId().equals( itsChallengerUserId ) )
                return p;
        
        return null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public PlayerData
    getChallenged()
    {
        if ( itsChallengedUserId == null )
            return null;
        
        for (PlayerData p : itsPlayerData)
            if ( p.getUserId().equals( itsChallengedUserId ) )
                return p;
        
        return null;
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getNewGameId()
    {
        return itsNewGameId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    isChallengeAccepted()
    {
        return itsChallengeAcceptedFlag;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    issueChallenge()
    {
        IssueChallengeRequest request = 
            new IssueChallengeRequest(itsSessionId,itsUserId);
        
        resetChallengeData();
        
        request.setChallengedUserId( itsChallengedUserId );
        itsService.issueChallenge( this,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    acceptChallenge()
    {
        AcceptChallengeRequest request = 
            new AcceptChallengeRequest(
                itsSessionId,
                itsUserId,
                itsChallengeRequestId,
                itsChallengeId,
                itsChallengerUserId,
                itsUserId);
        
        itsService.acceptChallenge( this,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    declineChallenge()
    {
        DeclineChallengeRequest request = 
            new DeclineChallengeRequest(
                itsSessionId,
                itsUserId,
                itsChallengeRequestId,
                itsChallengeId,
                itsChallengerUserId,
                itsUserId);
        
        itsService.declineChallenge( this,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startListening()
    {
        StartListeningRequest request = 
            new StartListeningRequest(itsSessionId,itsUserId,this);
        
        itsService.startListening( this,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    refreshPlayerData()
    {
        GetPlayersRequest request = 
            new GetPlayersRequest(itsSessionId,itsUserId);
        
        itsLogger.logInfo( "Refreshing player data." );
        itsService.getPlayers( this,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onGetPlayers(GetPlayersReply reply)
    {
        itsLogger.logInfo( "Getting player data." );
        itsPlayerData.clear();
        itsPlayerData.addAll( reply.getAll() );
        notifyChange( new RefreshPlayersEvent(this) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onIssueChallenge(IssueChallengeReply reply)
    {
        itsChallengeAcceptedFlag = reply.isChallengeAccepted();
        
        if ( itsChallengeAcceptedFlag )
            itsNewGameId = reply.getGameId();            
            
        notifyChange( new ChallengeReplyEvent(this) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onAcceptChallenge(AcceptChallengeReply reply)
    {
        itsLogger.logInfo(
            "Receiving accept challenge reply: " + reply.getReplyId() );
        
        itsChallengeAcceptedFlag = true;
        itsNewGameId = reply.getGameId();        
            
        notifyChange( new NewGameEvent(this) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onDeclineChallenge(DeclineChallengeReply reply)
    {
        itsLogger.logInfo(
            "Receiving decline challenge reply: " + reply.getReplyId() );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onStartListening(StartListeningReply reply)
    {
        itsLogger.logInfo(
            "Receiving start listening reply: " + reply.getReplyId() );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onPlayerException(PlayerException exception)
    {
        itsLogger.logError( exception.getMessage() );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onThrowable(Throwable throwable)
    {
        itsLogger.logError( throwable.getMessage() );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onPlayerChange(PlayerChangeEvent event)
    {
        itsLogger.logInfo(
            "Receiving player change event: " + event.getEventId() );
        refreshPlayerData();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onChallenge(ChallengeEvent event)
    {
        itsLogger.logInfo(
            "Receiving challenge event: " + event.getEventId() );
        
        itsChallengeRequestId = event.getOriginatingRequestId();
        itsChallengerUserId   = event.getChallengerUserId();
        itsChallengeId        = event.getChallengeId();
        
        notifyChange( new ProcessChallengeEvent(this) );
    }

    /************************************************************************
     *  
     *
     */
    private void
    resetChallengeData()
    {
        itsChallengeRequestId    = 0L;
        itsChallengeId           = 0L;
        itsChallengeAcceptedFlag = false;
        itsNewGameId             = 0L;
    }
}

// ##########################################################################
