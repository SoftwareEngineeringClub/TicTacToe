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

import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessageSender;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;
import strata1.integrator.messagingproxy.AbstractMessagingProxy;

/****************************************************************************
 * 
 */
public 
class SessionMessagingProxy 
    extends    AbstractMessagingProxy<String,ISessionReplyReceiver>
    implements ISessionMessagingProxy
{
    
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
            container.getInstance(String.class,"EventChannelId"),
            container.getInstance(IMessagingSession.class,"CommandSession"),
            container.getInstance(IMessagingSession.class,"EventSession"));
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
            .setPayload( request );

        insertPendingReceiver( correlationId,receiver);
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
            .setPayload( request );

        insertPendingReceiver( correlationId,receiver);
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
            .setPayload( request );

        insertPendingReceiver( correlationId,receiver);
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
        
        receiver = removePendingReceiver(correlationId);
        
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
