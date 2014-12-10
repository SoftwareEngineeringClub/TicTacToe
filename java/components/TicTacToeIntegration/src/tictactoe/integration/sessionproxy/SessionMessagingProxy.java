// ##########################################################################
// # File Name:	SessionMessagingProxy.java
// ##########################################################################

package tictactoe.integration.sessionproxy;

import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.LoginReply;
import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.LogoutRequest;
import tictactoe.service.sessionservice.RegisterReply;
import tictactoe.service.sessionservice.RegisterRequest;
import tictactoe.service.sessionservice.SessionException;

import strata1.common.logger.ILogger;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessageSender;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;
import strata1.integrator.messagingproxy.AbstractMessagingProxy;
import strata1.integrator.messagingproxy.RequestReplyMessagingProxy;

/****************************************************************************
 * 
 */
public 
class SessionMessagingProxy 
    extends    RequestReplyMessagingProxy<String,ISessionReplyReceiver>
    implements ISessionMessagingProxy
{
    private final ILogger itsLogger;
    
    /************************************************************************
     * Creates a new SessionMessagingProxy. 
     *
     */
    public 
    SessionMessagingProxy(IContainer container)
    {
        super(
            "Session-",
            container.getInstance(String.class,"RequestChannelId"),
            container.getInstance(String.class,"ReplyChannelId"),
            container.getInstance(IMessagingSession.class,"CommandSession"));
        
        itsLogger = container.getInstance( ILogger.class );
        activate();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    register(ISessionReplyReceiver receiver,RegisterRequest request)
    {
        IObjectMessage   message  = createObjectMessage();
        IMessageSender   sender   = createMessageSender();
        String           correlationId = toString(request.getRequestId());
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","RegisterRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver);
        itsLogger.logInfo( 
            "Sending register request: " + request.getRequestId() );
        sender.setTimeToLive( 60*1000 );
        sender.send( message );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    login(ISessionReplyReceiver receiver,LoginRequest request)
    {
        IObjectMessage   message  = createObjectMessage();
        IMessageSender   sender   = createMessageSender();
        String           correlationId = toString(request.getRequestId());
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","LoginRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver);
        itsLogger.logInfo( 
            "Sending login request: " + request.getRequestId() );
        sender.setTimeToLive( 60*1000 );
        sender.send( message );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    logout(ISessionReplyReceiver receiver,LogoutRequest request)
    {
        IObjectMessage   message  = createObjectMessage();
        IMessageSender   sender   = createMessageSender();
        String           correlationId = toString(request.getRequestId());
        
        message
            .setCorrelationId( correlationId )
            .setStringProperty( "RequestType","LogoutRequest" )
            .setPayload( request );

        insertReplyReceiver( correlationId,receiver);
        itsLogger.logInfo( 
            "Sending logout request: " + request.getRequestId() );
        sender.setTimeToLive( 60*1000 );
        sender.send( message );
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onMessage(IObjectMessage message)
    {
        String                correlationId = message.getCorrelationId();
        Object                payload       = message.getPayload();
        ISessionReplyReceiver receiver      = null;
        
        receiver = removeReplyReceiver(correlationId);
        
        if ( payload instanceof RegisterReply )
            receiver.onRegister( (RegisterReply)payload );
        else if ( payload instanceof LoginReply )
            receiver.onLogin( (LoginReply)payload );
        else if ( payload instanceof LogoutReply )
            receiver.onLogout( (LogoutReply)payload );
        else if ( payload instanceof SessionException )
            receiver.onSessionException( (SessionException)payload );
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
