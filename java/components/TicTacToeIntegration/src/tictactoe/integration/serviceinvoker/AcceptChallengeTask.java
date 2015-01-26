// ##########################################################################
// # File Name:	AcceptChallengeTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.AcceptChallengeRequest;
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
class AcceptChallengeTask 
    extends AbstractTask
{
    private final IContainer             itsContainer;
    private final AcceptChallengeRequest itsRequest;
    private final IPlayerReplyReceiver   itsReceiver;
    private final IPlayerEventListener   itsRouter;
    
    /************************************************************************
     * Creates a new AcceptChallengeTask. 
     *
     * @param container
     * @param request
     * @param receiver
     */
    public 
    AcceptChallengeTask(
        IContainer             container,
        AcceptChallengeRequest request,
        IPlayerReplyReceiver   receiver,
        IPlayerEventListener   router)
    {
        super( "AcceptChallengeTask" );
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
            "Executing accept challenge task for request: " + 
            itsRequest.getRequestId() );
        
        ((IPlayerNotifierHost)service).setPlayerNotifier( itsRouter );
        
        if ( itsRequest == null )
            throw new NullPointerException("AcceptChallengeRequest is null." );
        
        service.acceptChallenge( itsReceiver,itsRequest );
    }

}

// ##########################################################################
