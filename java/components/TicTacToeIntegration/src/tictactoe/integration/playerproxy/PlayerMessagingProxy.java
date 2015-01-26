// ##########################################################################
// # File Name:	PlayerMessagingProxy.java
// ##########################################################################

package tictactoe.integration.playerproxy;

import tictactoe.service.playerservice.AcceptChallengeReply;
import tictactoe.service.playerservice.AcceptChallengeRequest;
import tictactoe.service.playerservice.ChallengeEvent;
import tictactoe.service.playerservice.DeclineChallengeReply;
import tictactoe.service.playerservice.DeclineChallengeRequest;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IssueChallengeReply;
import tictactoe.service.playerservice.IssueChallengeRequest;
import tictactoe.service.playerservice.PlayerChangeEvent;
import tictactoe.service.playerservice.PlayerEvent;
import tictactoe.service.playerservice.PlayerEventListenerId;
import tictactoe.service.playerservice.PlayerException;
import tictactoe.service.playerservice.PlayerReply;
import tictactoe.service.playerservice.StartListeningReply;
import tictactoe.service.playerservice.StartListeningRequest;

import strata1.common.logger.ILogger;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessageSender;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;
import strata1.integrator.messagingproxy.RequestReplyAndEventMessagingProxy;

import java.util.concurrent.TimeoutException;

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
        
        request.setReturnAddress( getReturnAddress() );
        
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
    issueChallenge(
        IPlayerReplyReceiver  receiver,
        IssueChallengeRequest request)
    {
        IObjectMessage   message  = createObjectMessage();
        IMessageSender   sender   = createMessageSender();
        String           correlationId = toString(request.getRequestId());
        
        request.setReturnAddress( getReturnAddress() );
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","IssueChallengeRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver);
        
        itsLogger.logInfo( 
            "Sending issue challenge request: " + request.getRequestId() );
        sender.send( message );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    acceptChallenge(
        IPlayerReplyReceiver   receiver,
        AcceptChallengeRequest request)
    {
        IObjectMessage   message  = createObjectMessage();
        IMessageSender   sender   = createMessageSender();
        String           correlationId = toString(request.getRequestId());
        
        request.setReturnAddress( getReturnAddress() );
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","AcceptChallengeRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver);
        
        itsLogger.logInfo( 
            "Sending issue challenge request: " + request.getRequestId() );
        sender.send( message );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    declineChallenge(
        IPlayerReplyReceiver    receiver,
        DeclineChallengeRequest request)
    {
        IObjectMessage   message  = createObjectMessage();
        IMessageSender   sender   = createMessageSender();
        String           correlationId = toString(request.getRequestId());
        
        request.setReturnAddress( getReturnAddress() );
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","AcceptChallengeRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver);
        
        itsLogger.logInfo( 
            "Sending issue challenge request: " + request.getRequestId() );
        sender.send( message );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startListening(
        IPlayerReplyReceiver  receiver,
        StartListeningRequest request)
    {
        IObjectMessage message       = null;
        IMessageSender sender        = null;
        String         correlationId = null;
        
        if ( hasEventListener(getReturnAddress()) )
            receiver.onStartListening( 
                new StartListeningReply(request).setListening( true ) );
         
        message       = createObjectMessage();
        sender        = createMessageSender();
        correlationId = toString(request.getRequestId());
        receiver      = new BlockingPlayerReplyReceiver(request,20*1000);
        
        request.setReturnAddress( getReturnAddress() );
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","StartListeningRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver );
        insertEventListener(
            request.getReturnAddress(),
            request.getListener() );
        
        itsLogger.logInfo( 
            "Sending start listening request: " + request.getRequestId() );
        sender.send( message );
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
        else if ( payload instanceof IssueChallengeReply )
            receiver.onIssueChallenge( (IssueChallengeReply)payload );
        else if ( payload instanceof AcceptChallengeReply )
            receiver.onAcceptChallenge( (AcceptChallengeReply)payload );
        else if ( payload instanceof DeclineChallengeReply )
            receiver.onDeclineChallenge( (DeclineChallengeReply)payload );
        else if ( payload instanceof StartListeningReply )
            receiver.onStartListening( (StartListeningReply)payload );
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
        else if ( payload instanceof ChallengeEvent )
        {
            System
                .out
                .println( "debug: received ChallengeEvent" );
            
            for (IPlayerEventListener listener : getEventListeners() )
                listener.onChallenge(
                    (ChallengeEvent)payload);
        }
        else
            for (IPlayerEventListener listener : getEventListeners() )
                listener.onPlayerException(
                    new PlayerException(null,"Unknown event type." ) );
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
