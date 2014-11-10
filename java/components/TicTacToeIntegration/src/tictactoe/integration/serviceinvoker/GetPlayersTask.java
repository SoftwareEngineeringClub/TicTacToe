// ##########################################################################
// # File Name:	GetPlayersTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class GetPlayersTask 
    extends AbstractTask
{
    private final IContainer           itsContainer;
    private final GetPlayersRequest    itsRequest;
    private final IPlayerReplyReceiver itsReceiver;
    
    /************************************************************************
     * Creates a new GetPlayersTask. 
     *
     * @param container
     * @param request
     * @param receiver
     */
    public 
    GetPlayersTask(
        IContainer           container,
        GetPlayersRequest    request,
        IPlayerReplyReceiver receiver)
    {
        super( "GetPlayersTask" );
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
            "Executing get players task for request: " + 
            itsRequest.getRequestId() );
        service.getPlayers( itsReceiver,itsRequest );
    }

}

// ##########################################################################
