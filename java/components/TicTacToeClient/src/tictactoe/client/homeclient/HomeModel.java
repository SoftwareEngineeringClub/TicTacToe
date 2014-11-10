// ##########################################################################
// # File Name:	HomeModel.java
// ##########################################################################

package tictactoe.client.homeclient;

import tictactoe.service.playerservice.ChallengePlayerReply;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;
import tictactoe.service.playerservice.PlayerData;
import tictactoe.service.playerservice.PlayerException;

import strata1.client.model.AbstractModel;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class HomeModel 
    extends    AbstractModel 
    implements IHomeModel,IPlayerReplyReceiver
{
    private final IPlayerService itsService;
    private final ILogger        itsLogger;
    private Long                 itsSessionId;
    private Long                 itsUserId;
    private PlayerData           itsPlayerData;
    
    /************************************************************************
     * Creates a new HomeModel. 
     *
     */
    @Inject
    public 
    HomeModel(IPlayerService service,ILogger logger)
    {
        itsService    = service;
        itsLogger     = logger;
        itsSessionId  = 0L;
        itsUserId     = 0L;
        itsPlayerData = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IHomeModel 
    setSessionId(Long sessionId)
    {
        itsLogger.logDebug( "Setting home model sessionId = " + sessionId );
        itsSessionId = sessionId;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IHomeModel 
    setUserId(Long userId)
    {
        itsLogger.logDebug( "Setting home model userId = " + userId );
        itsUserId = userId;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public PlayerData 
    getPlayerData()
    {
        return itsPlayerData;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    refreshPlayerData()
    {
        GetPlayersRequest request = 
            new GetPlayersRequest(itsSessionId,itsUserId)
                .setSinglePlayer( true );
        
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
        itsPlayerData = reply.getOne();
        notifyChange( new RefreshPlayerEvent(this) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onChallengePlayer(ChallengePlayerReply reply)
    {
        onThrowable( 
            new UnsupportedOperationException(
                "HomeModel.onChallengePlayer") );
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

}

// ##########################################################################
