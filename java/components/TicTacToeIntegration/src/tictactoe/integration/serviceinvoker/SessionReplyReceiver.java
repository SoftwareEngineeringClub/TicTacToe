// ##########################################################################
// # File Name:	SessionReplyReceiver.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.LoginReply;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.RegisterReply;
import tictactoe.service.sessionservice.SessionException;

import strata1.common.logger.ILogger;
import strata1.integrator.messaging.IMessageSender;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SessionReplyReceiver 
    implements ISessionReplyReceiver
{
    private final IMessagingSession itsSession;
    private final String            itsReplyChannelId;
    private final String            itsReturnAddress;
    private final String            itsCorrelationId;
    private final ILogger           itsLogger;
    
    /************************************************************************
     * Creates a new SessionReplyReceiver. 
     *
     */
    public 
    SessionReplyReceiver(
        IMessagingSession session,
        String            replyChannelId,
        String            returnAddress,
        String            correlationId,
        ILogger           logger)
    {
        itsSession        = session;
        itsReplyChannelId = replyChannelId;
        itsReturnAddress  = returnAddress;
        itsCorrelationId  = correlationId;
        itsLogger         = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onRegister(RegisterReply reply)
    {
        itsLogger.logInfo( 
            "Sending register reply: " + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        sendMessage( createMessage().setPayload( reply ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onLogin(LoginReply reply)
    {
        itsLogger.logInfo( 
            "Sending login reply: " + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        sendMessage( createMessage().setPayload( reply ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onLogout(LogoutReply reply)
    {
        itsLogger.logInfo( 
            "Sending logout reply: " + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        sendMessage( createMessage().setPayload( reply ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onSessionException(SessionException exception)
    {
        itsLogger.logInfo( 
            "Sending session exception: " + exception.getMessage() );
        sendMessage( createMessage().setPayload( exception ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onThrowable(Throwable throwable)
    {
        itsLogger.logInfo( 
            "Sending throwable: " + throwable.getMessage() );
        sendMessage( createMessage().setPayload( throwable ) );
    }

    /************************************************************************
     *  
     *
     * @return
     */
    private IObjectMessage
    createMessage()
    {
        return
            itsSession
                .createObjectMessage()
                .setReturnAddress( itsReturnAddress )
                .setCorrelationId( itsCorrelationId );
    }
    
    /************************************************************************
     *  
     *
     * @param message
     */
    private void
    sendMessage(IObjectMessage message)
    {
        IMessageSender sender = 
            itsSession.createMessageSender( itsReplyChannelId );
        
        sender.send( message );
    }
}

// ##########################################################################
