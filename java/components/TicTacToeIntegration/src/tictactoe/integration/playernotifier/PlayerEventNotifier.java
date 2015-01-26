// ##########################################################################
// # File Name:	PlayerEventNotifier.java
// ##########################################################################

package tictactoe.integration.playernotifier;

import tictactoe.service.playerservice.ChallengeEvent;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.PlayerChangeEvent;
import tictactoe.service.playerservice.PlayerException;

import strata1.common.logger.ILogger;
import strata1.common.utility.Pair;
import strata1.integrator.messaging.IMessageSender;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;

import javax.inject.Inject;
import javax.inject.Named;

/****************************************************************************
 * 
 */
public abstract
class PlayerEventNotifier 
    implements IPlayerEventListener
{
    private final IMessagingSession itsSession1;
    private final IMessagingSession itsSession2;
    private final String            itsEventChannelId1;
    private final String            itsEventChannelId2;
    private final ILogger           itsLogger;

    /************************************************************************
     * Creates a new PlayerEventNotifier. 
     *
     */
    @Inject
    protected 
    PlayerEventNotifier(
        @Named("EventSession1")
        IMessagingSession session1,
        @Named("EventSession2")
        IMessagingSession session2,
        @Named("EventChannelId1")
        String            eventChannelId1,
        @Named("EventChannelId2")
        String            eventChannelId2,
        ILogger           logger)
    {
        itsSession1        = session1;
        itsSession2        = session2;
        itsEventChannelId1 = eventChannelId1;
        itsEventChannelId2 = eventChannelId2;
        itsLogger          = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onPlayerChange(PlayerChangeEvent event)
    {
        Pair<IObjectMessage,IObjectMessage> pair = createMessage();
        
        itsLogger.logInfo( 
            "Sending player change event: " + event.getEventId() );
        
        pair
            .getFirst()
            .setStringProperty( "EventType","PlayerChangeEvent" )
            .setPayload( event );
        pair
            .getSecond()
            .setStringProperty( "EventType","PlayerChangeEvent" )
            .setPayload( event );
        
        sendMessage( pair );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onChallenge(ChallengeEvent event)
    {
        Pair<IObjectMessage,IObjectMessage> pair = createMessage();
        
        itsLogger.logInfo( 
            "Sending challenge event: " + event.getEventId() );
        
        pair
            .getFirst()
            .setStringProperty( "EventType","ChallengeEvent" )
            .setReturnAddress( event.getReturnAddress() )
            .setPayload( event );
        pair
            .getSecond()
            .setStringProperty( "EventType","ChallengeEvent" )
            .setPayload( event );
        
        sendMessage( pair );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onPlayerException(PlayerException exception)
    {
        Pair<IObjectMessage,IObjectMessage> pair = createMessage();
        
        itsLogger.logInfo( "Sending player exception." );
        
        pair
            .getFirst()
            .setStringProperty( "EventType","PlayerException" )
            .setPayload( exception );
        pair
            .getSecond()
            .setStringProperty( "EventType","PlayerException" )
            .setPayload( exception );
        
        sendMessage( pair );
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    private Pair<IObjectMessage,IObjectMessage>
    createMessage()
    {
        return
            Pair.create( 
                itsSession1.createObjectMessage(),
                itsSession2.createObjectMessage() );
    }
    
    /************************************************************************
     *  
     *
     * @param message
     */
    private void
    sendMessage(Pair<IObjectMessage,IObjectMessage> message)
    {
        IMessageSender sender1 = 
            itsSession1.createMessageSender( itsEventChannelId1 );
        IMessageSender sender2 = 
            itsSession2.createMessageSender( itsEventChannelId2 );
        
        sender1.setTimeToLive( 60*1000 );
        sender2.setTimeToLive( 60*1000 );
        
        sender1.send( message.getFirst() );
        sender2.send( message.getSecond() );
    }

}

// ##########################################################################
