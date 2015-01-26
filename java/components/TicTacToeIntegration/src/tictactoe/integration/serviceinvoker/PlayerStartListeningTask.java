// ##########################################################################
// # File Name:	PlayerStartListeningTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerNotifierHost;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;
import tictactoe.service.playerservice.StartListeningRequest;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class PlayerStartListeningTask 
    extends AbstractTask
{
    private final IContainer            itsContainer;
    private final StartListeningRequest itsRequest;
    private final IPlayerReplyReceiver  itsReceiver;
    private final IPlayerEventListener  itsRouter;
    
    /************************************************************************
     * Creates a new PlayerStartListeningTask. 
     *
     * @param container
     * @param request
     * @param receiver
     */
    public 
    PlayerStartListeningTask(
        IContainer            container,
        StartListeningRequest request,
        IPlayerReplyReceiver  receiver,
        IPlayerEventListener  router)
    {
        super( "PlayerStartListeningTask" );
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
        
        service.startListening( itsReceiver,itsRequest );
    }

}

// ##########################################################################
