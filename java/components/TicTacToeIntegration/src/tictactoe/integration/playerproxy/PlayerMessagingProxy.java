// ##########################################################################
// # File Name:	PlayerMessagingProxy.java
// ##########################################################################

package tictactoe.integration.playerproxy;

import tictactoe.service.playerservice.ChallengeAcceptedEvent;
import tictactoe.service.playerservice.ChallengeDeclinedEvent;
import tictactoe.service.playerservice.ChallengePlayerReply;
import tictactoe.service.playerservice.ChallengePlayerRequest;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.PlayerChangeEvent;
import tictactoe.service.playerservice.PlayerEvent;
import tictactoe.service.playerservice.PlayerEventListenerId;
import tictactoe.service.playerservice.PlayerException;
import tictactoe.service.playerservice.PlayerReply;

import strata1.common.logger.ILogger;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessageSender;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;
import strata1.integrator.messagingproxy.RequestReplyAndEventMessagingProxy;

/****************************************************************************
 * 
 */
public 
class PlayerMessagingProxy 
    extends    RequestReplyAndEventMessagingProxy<
                   String,
                   IPlayerReplyReceiver,
                   IPlayerEventListener> 
    implements IPlayerMessagingProxy
{
    private ILogger itsLogger;
    private Long    itsUserId;
    
    /************************************************************************
     * Creates a new SessionMessagingProxy. 
     *
     */
    public 
    PlayerMessagingProxy(IContainer container)
    {
        super(
            "Player-",
            container.getInstance(String.class,"RequestChannelId"),
            container.getInstance(String.class,"ReplyChannelId"),
            container.getInstance(String.class,"EventChannelId"),
            container.getInstance(IMessagingSession.class,"CommandSession"),
            container.getInstance(IMessagingSession.class,"EventSession"));
        
        itsLogger = container.getInstance( ILogger.class );
        itsUserId = 0L;
        
        insertMessageReceiverForEventListeners( 
            "EventType='PlayerChangeEvent'" );
        
        activate();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    getPlayers(IPlayerReplyReceiver receiver,GetPlayersRequest request)
    {
        IObjectMessage   message  = createObjectMessage();
        IMessageSender   sender   = createMessageSender();
        String           correlationId = toString(request.getRequestId());
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","GetPlayersRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver);
        itsLogger.logInfo( 
            "Sending get players request: " + request.getRequestId() );
        sender.send( message );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    challengePlayer(
        IPlayerReplyReceiver   receiver,
        ChallengePlayerRequest request)
    {
        IObjectMessage   message  = createObjectMessage();
        IMessageSender   sender   = createMessageSender();
        String           correlationId = toString(request.getRequestId());
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","ChallengePlayerRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver);
        
        itsLogger.logInfo( 
            "Sending challenge player request: " + request.getRequestId() );
        sender.send( message );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public PlayerEventListenerId 
    startListeningForEvents(IPlayerEventListener listener)
    {
        PlayerEventListenerId id = new PlayerEventListenerId();
        
        insertEventListener( id.toString(),listener );
        return id;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stopListeningForEvents(PlayerEventListenerId listenerId)
    {
        removeEventListener( listenerId.toString() );
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onMessage(IObjectMessage message)
    {
        Object payload = message.getPayload();

        if ( payload instanceof PlayerReply )
            onPlayerReply( message );
        else if ( payload instanceof PlayerEvent )
            onPlayerEvent( message );        
        else 
            new IllegalStateException("Unknown payload type.");
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayerMessagingProxy 
    setUserId(Long userId)
    {
        String selector = 
            "Challenger=" + itsUserId + " OR Challenged=" + itsUserId;
        
        if ( hasMessageReceiverForEventListeners( selector ) )
            removeMessageReceiverForEventListeners( selector );
        
        itsUserId = userId;
        
        insertMessageReceiverForEventListeners(
            "Challenger=" + itsUserId + " OR Challenged=" + itsUserId );
        
        return this;
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
     *  
     *
     * @param message
     */
    private void
    onPlayerReply(IObjectMessage message)
    {
        Object               payload       = message.getPayload();
        String               correlationId = message.getCorrelationId();
        IPlayerReplyReceiver receiver      = null;
        
        receiver = removeReplyReceiver(correlationId);
        
        if ( payload instanceof GetPlayersReply )
            receiver.onGetPlayers( (GetPlayersReply)payload );
        else if ( payload instanceof ChallengePlayerReply )
            receiver.onChallengePlayer( (ChallengePlayerReply)payload );
        else if ( payload instanceof PlayerException )
            receiver.onPlayerException( (PlayerException)payload );
        else if ( payload instanceof Throwable )
            receiver.onThrowable( (Throwable)payload );
        else 
            receiver.onThrowable( 
                new IllegalStateException(
                    "CorrelationId=" + correlationId + 
                    ": Unknown payload type.") );       
    }
    
    /************************************************************************
     *  
     *
     * @param message
     */
    private void
    onPlayerEvent(IObjectMessage message)
    {
        Object payload  = message.getPayload();
        
        if ( payload instanceof PlayerChangeEvent )
            for (IPlayerEventListener listener : getEventListeners() )
                listener.onPlayerChange( (PlayerChangeEvent)payload );
        else if ( payload instanceof ChallengeAcceptedEvent )
            for (IPlayerEventListener listener : getEventListeners() )
                listener.onChallengeAccepted(
                    (ChallengeAcceptedEvent)payload);
        else if ( payload instanceof ChallengeDeclinedEvent )
            for (IPlayerEventListener listener : getEventListeners() )
                listener.onChallengeDeclined(
                    (ChallengeDeclinedEvent)payload);
        else
            for (IPlayerEventListener listener : getEventListeners() )
                listener.onPlayerException(
                    new PlayerException("Unknown event type." ) );
    }
    
    /************************************************************************
     *  
     *
     * @param input
     * @return
     */
    private String
    toString(long input)
    {
        return new Long(input).toString();
    }
}

// ##########################################################################
