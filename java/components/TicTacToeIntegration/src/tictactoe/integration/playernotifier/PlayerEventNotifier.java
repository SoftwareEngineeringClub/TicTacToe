// ##########################################################################
// # File Name:	PlayerEventNotifier.java
// ##########################################################################

package tictactoe.integration.playernotifier;

import tictactoe.service.playerservice.ChallengeAcceptedEvent;
import tictactoe.service.playerservice.ChallengeDeclinedEvent;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.PlayerChangeEvent;
import tictactoe.service.playerservice.PlayerException;

import strata1.common.logger.ILogger;
import strata1.integrator.messaging.IMessageSender;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;

import javax.inject.Inject;
import javax.inject.Named;

/****************************************************************************
 * 
 */
public 
class PlayerEventNotifier 
    implements IPlayerEventListener
{
    private final IMessagingSession itsSession;
    private final String            itsEventChannelId;
    private final ILogger           itsLogger;

    /************************************************************************
     * Creates a new PlayerEventNotifier. 
     *
     */
    public 
    PlayerEventNotifier(
        IMessagingSession session,
        String            eventChannelId,
        ILogger           logger)
    {
        itsSession        = session;
        itsEventChannelId = eventChannelId;
        itsLogger         = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onPlayerChange(PlayerChangeEvent event)
    {
        itsLogger.logInfo( 
            "Sending player change event: " + event.getEventId() );
        sendMessage( 
            createMessage()
                .setStringProperty( "EventType","PlayerChangeEvent" )
                .setPayload( event ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onChallengeAccepted(ChallengeAcceptedEvent event)
    {
        itsLogger.logInfo( 
            "Sending challenge accepted event: " + event.getEventId() );
        sendMessage( 
            createMessage()
                .setStringProperty( "EventType","ChallengeAcceptedEvent" )
                .setLongProperty("Challenger",event.getChallengerUserId())
                .setLongProperty("Challenged",event.getChallengedUserId())
                .setPayload( event ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onChallengeDeclined(ChallengeDeclinedEvent event)
    {
        itsLogger.logInfo( 
            "Sending challenge declined event: " + event.getEventId() );
        sendMessage( 
            createMessage()
                .setStringProperty( "EventType","ChallengeDeclinedEvent" )
                .setLongProperty("Challenger",event.getChallengerUserId())
                .setLongProperty("Challenged",event.getChallengedUserId())
                .setPayload( event ) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onPlayerException(PlayerException exception)
    {
        itsLogger.logInfo( "Sending player exception." );
        sendMessage( createMessage().setPayload( exception ) );
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
                .createObjectMessage();
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
            itsSession.createMessageSender( itsEventChannelId );
        
        sender.setTimeToLive( 60*1000 );
        sender.send( message );
    }

}

// ##########################################################################
