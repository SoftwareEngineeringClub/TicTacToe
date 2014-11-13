// ##########################################################################
// # File Name:	PlayersModel.java
// ##########################################################################

package tictactoe.client.playersclient;

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

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * 
 */
public 
class PlayersModel 
    extends    AbstractModel 
    implements IPlayersModel,IPlayerReplyReceiver
{
    private final IPlayerService   itsService;
    private final ILogger          itsLogger;
    private Long                   itsSessionId;
    private Long                   itsUserId;
    private final List<PlayerData> itsPlayerData;
    
    /************************************************************************
     * Creates a new PlayersModel. 
     *
     */
    @Inject
    public 
    PlayersModel(IPlayerService service,ILogger logger)
    {
        itsService    = service;
        itsLogger     = logger;
        itsSessionId  = 0L;
        itsUserId     = 0L;
        itsPlayerData = new ArrayList<PlayerData>();
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
    onChallengePlayer(ChallengePlayerReply reply)
    {
        onThrowable( 
            new UnsupportedOperationException(
                "PlayersModel.onChallengePlayer") );
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
