// ##########################################################################
// # File Name:	PlayerEventRouter.java
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
class PlayerEventRouter 
    extends PlayerEventNotifier
{

    /************************************************************************
     * Creates a new PlayerEventRouter. 
     *
     * @param session1
     * @param session2
     * @param replyChannelId1
     * @param replyChannelId2
     * @param logger
     */
    @Inject
    public 
    PlayerEventRouter(
        @Named("CommandSession1")
        IMessagingSession session1,
        @Named("CommandSession2")
        IMessagingSession session2,
        @Named("ReplyChannelId1")
        String            replyChannelId1,
        @Named("ReplyChannelId2")
        String            replyChannelId2,
        ILogger           logger)

    {
        super( session1,session2,replyChannelId1,replyChannelId2,logger );

    }

}

// ##########################################################################
