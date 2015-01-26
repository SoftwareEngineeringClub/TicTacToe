// ##########################################################################
// # File Name:	KeepAliveTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.KeepAliveRequest;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class KeepAliveTask 
    extends AbstractTask
{
    private final IContainer             itsContainer;
    private final KeepAliveRequest itsRequest;
    
    /************************************************************************
     * Creates a new LogoutTask. 
     *
     * @param container
     * @param request
     */
    public 
    KeepAliveTask(
        IContainer             container,
        KeepAliveRequest request)
    {
        super( "LogoutTask" );
        itsContainer = container;
        itsRequest   = request;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    execute()
    {
        ISessionService       service  = null;
        ILogger               logger   = null;
       
        service = 
            itsContainer.getInstance( 
                ISessionService.class,
                "SessionServiceImplementation" );
        logger = 
            itsContainer.getInstance( ILogger.class );
        
        logger.logInfo( 
            "Executing keep alive task for request: " + 
            itsRequest.getRequestId() );
        
        service.keepAlive( itsRequest );
    }

}

// ##########################################################################
