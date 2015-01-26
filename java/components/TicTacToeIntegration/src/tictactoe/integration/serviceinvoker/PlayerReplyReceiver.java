// ##########################################################################
// # File Name:	SessionReplyReceiver.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.AcceptChallengeReply;
import tictactoe.service.playerservice.DeclineChallengeReply;
import tictactoe.service.playerservice.IssueChallengeReply;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.PlayerException;
import tictactoe.service.playerservice.PlayerReply;
import tictactoe.service.playerservice.StartListeningReply;
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
class PlayerReplyReceiver 
    implements IPlayerReplyReceiver
{
    private final IMessagingSession itsSession;
    private final String            itsReplyChannelId;
    private final ILogger           itsLogger;
    
    /************************************************************************
     * Creates a new SessionReplyReceiver. 
     *
     */
    public 
    PlayerReplyReceiver(
        IMessagingSession session,
        String            replyChannelId,
        ILogger           logger)
    {
        itsSession        = session;
        itsReplyChannelId = replyChannelId;
        itsLogger         = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onGetPlayers(GetPlayersReply reply)
    {
        itsLogger.logInfo( 
            "Sending get players reply: " + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        sendMessage( createMessage( reply ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onIssueChallenge(IssueChallengeReply reply)
    {
        itsLogger.logInfo( 
            "Sending challenge player reply: " + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        sendMessage( createMessage( reply ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onAcceptChallenge(AcceptChallengeReply reply)
    {
        itsLogger.logInfo( 
            "Sending accept challenge reply: " + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        sendMessage( createMessage( reply ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onDeclineChallenge(DeclineChallengeReply reply)
    {
        itsLogger.logInfo( 
            "Sending decline challenge reply: " + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        sendMessage( createMessage( reply ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onStartListening(StartListeningReply reply)
    {
        itsLogger.logInfo( 
            "Sending start listening reply: " + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        sendMessage( createMessage( reply ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onPlayerException(PlayerException exception)
    {
        itsLogger.logInfo( 
            "Sending player exception: " + exception.getMessage() );
        sendMessage( createMessage( exception ) );
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
        
        
        //sendMessage( createMessage( throwable ) );
    }

    /************************************************************************
     *  
     *
     * @return
     */
    private IObjectMessage
    createMessage(PlayerReply reply)
    {
        return 
            itsSession
                .createObjectMessage()
                .setReturnAddress(reply.getReturnAddress())
                .setCorrelationId(toString(reply.getOriginatingRequestId()))
                .setPayload(reply);
    }

    /************************************************************************
     *  
     *
     * @return
     */
    private IObjectMessage
    createMessage(PlayerException exception)
    {
        return 
            itsSession
                .createObjectMessage()
                .setReturnAddress(exception.getReturnAddress())
                .setCorrelationId(toString(exception.getOriginatingRequestId()))
                .setPayload(exception);
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
        
        sender.setTimeToLive( 60*1000 );
        sender.send( message );
    }

    /************************************************************************
     *  
     *
     * @param originatingRequestId
     * @return
     */
    private String 
    toString(long originatingRequestId)
    {
        return new Long(originatingRequestId).toString();
    }
}

// ##########################################################################
