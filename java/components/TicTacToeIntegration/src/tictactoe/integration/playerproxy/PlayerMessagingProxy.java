// ##########################################################################
// # File Name:	PlayerMessagingProxy.java
// ##########################################################################

package tictactoe.integration.playerproxy;

import tictactoe.service.playerservice.ChallengePlayerReply;
import tictactoe.service.playerservice.ChallengePlayerRequest;
import tictactoe.service.playerservice.ChangeListenerId;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerChangeListener;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.PlayerException;

import strata1.common.logger.ILogger;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessageSender;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;
import strata1.integrator.messagingproxy.AbstractMessagingProxy;

/****************************************************************************
 * 
 */
public 
class PlayerMessagingProxy 
    extends    AbstractMessagingProxy<String,IPlayerReplyReceiver> 
    implements IPlayerMessagingProxy
{
    private ILogger itsLogger;
    
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

        insertPendingReceiver( correlationId,receiver);
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

        insertPendingReceiver( correlationId,receiver);
        itsLogger.logInfo( 
            "Sending challenge player request: " + request.getRequestId() );
        sender.send( message );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ChangeListenerId 
    startListeningForChanges(IPlayerChangeListener listener)
    {
        return null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stopListeningForChanges(ChangeListenerId listenerId)
    {
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onMessage(IObjectMessage message)
    {
        String               correlationId = message.getCorrelationId();
        Object               payload       = message.getPayload();
        IPlayerReplyReceiver receiver      = null;
        
        receiver = removePendingReceiver(correlationId);
        
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
