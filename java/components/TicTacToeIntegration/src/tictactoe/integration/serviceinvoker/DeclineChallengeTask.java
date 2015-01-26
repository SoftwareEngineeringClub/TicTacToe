// ##########################################################################
// # File Name:	DeclineChallengeTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.AcceptChallengeRequest;
import tictactoe.service.playerservice.DeclineChallengeRequest;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerNotifierHost;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class DeclineChallengeTask 
    extends AbstractTask
{
    private final IContainer              itsContainer;
    private final DeclineChallengeRequest itsRequest;
    private final IPlayerReplyReceiver    itsReceiver;
    private final IPlayerEventListener    itsRouter;
    
    /************************************************************************
     * Creates a new DeclineChallengeTask. 
     *
     * @param container
     * @param request
     * @param receiver
     */
    public 
    DeclineChallengeTask(
        IContainer              container,
        DeclineChallengeRequest request,
        IPlayerReplyReceiver    receiver,
        IPlayerEventListener    router)
    {
        super( "DeclineChallengeTask" );
        itsContainer = container;
        itsRequest   = request;
        itsReceiver  = receiver;
        itsRouter    = router;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    execute()
    {
        IPlayerService service  = null;
        ILogger        logger   = null;
       
        service = 
            itsContainer.getInstance( 
                IPlayerService.class,
                "PlayerServiceImplementation" );
        logger = 
            itsContainer.getInstance( ILogger.class );
        
        logger.logInfo( 
            "Executing decline challenge task for request: " + 
            itsRequest.getRequestId() );
        
        ((IPlayerNotifierHost)service).setPlayerNotifier( itsRouter );
        
        service.declineChallenge( itsReceiver,itsRequest );
    }

}

// ##########################################################################
