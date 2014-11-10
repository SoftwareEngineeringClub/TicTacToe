// ##########################################################################
// # File Name:	ChallengePlayerTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.ChallengePlayerRequest;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class ChallengePlayerTask 
    extends AbstractTask
{
    private final IContainer             itsContainer;
    private final ChallengePlayerRequest itsRequest;
    private final IPlayerReplyReceiver   itsReceiver;
    
    /************************************************************************
     * Creates a new GetPlayersTask. 
     *
     * @param container
     * @param request
     * @param receiver
     */
    public 
    ChallengePlayerTask(
        IContainer             container,
        ChallengePlayerRequest request,
        IPlayerReplyReceiver   receiver)
    {
        super( "ChallengePlayerTask" );
        itsContainer = container;
        itsRequest   = request;
        itsReceiver  = receiver;
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
        service.challengePlayer( itsReceiver,itsRequest );
    }

}

// ##########################################################################
