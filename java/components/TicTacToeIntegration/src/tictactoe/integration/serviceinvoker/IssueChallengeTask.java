// ##########################################################################
// # File Name:	IssueChallengeTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerNotifierHost;
import tictactoe.service.playerservice.IssueChallengeRequest;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class IssueChallengeTask 
    extends AbstractTask
{
    private final IContainer            itsContainer;
    private final IssueChallengeRequest itsRequest;
    private final IPlayerReplyReceiver  itsReceiver;
    private final IPlayerEventListener  itsRouter;
    
    /************************************************************************
     * Creates a new GetPlayersTask. 
     *
     * @param container
     * @param request
     * @param receiver
     */
    public 
    IssueChallengeTask(
        IContainer            container,
        IssueChallengeRequest request,
        IPlayerReplyReceiver  receiver,
        IPlayerEventListener  router)
    {
        super( "IssueChallengeTask" );
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
            "Executing challenge player task for request: " + 
            itsRequest.getRequestId() );
        
        ((IPlayerNotifierHost)service).setPlayerNotifier( itsRouter );
        
        service.issueChallenge( itsReceiver,itsRequest );
    }

}

// ##########################################################################
