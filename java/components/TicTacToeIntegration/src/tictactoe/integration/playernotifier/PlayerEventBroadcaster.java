// ##########################################################################
// # File Name:	PlayerEventBroadcaster.java
// ##########################################################################

package tictactoe.integration.playernotifier;

import strata1.common.logger.ILogger;
import strata1.integrator.messaging.IMessagingSession;

import javax.inject.Inject;
import javax.inject.Named;

/****************************************************************************
 * 
 */
public 
class PlayerEventBroadcaster 
    extends PlayerEventNotifier
{

    /************************************************************************
     * Creates a new PlayerEventBroadcaster. 
     *
     * @param session1
     * @param session2
     * @param eventChannelId1
     * @param eventChannelId2
     * @param logger
     */
    @Inject
    public 
    PlayerEventBroadcaster(        
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
        super( session1,session2,eventChannelId1,eventChannelId2,logger );
    }

}

// ##########################################################################
