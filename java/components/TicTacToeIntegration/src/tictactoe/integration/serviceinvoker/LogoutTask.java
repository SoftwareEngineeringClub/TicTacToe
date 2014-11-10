// ##########################################################################
// # File Name:	LogoutTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.LogoutRequest;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class LogoutTask 
    extends AbstractTask
{
    private final IContainer            itsContainer;
    private final LogoutRequest         itsRequest;
    private final ISessionReplyReceiver itsReceiver;
    
    /************************************************************************
     * Creates a new LogoutTask. 
     *
     * @param container
     * @param request
     */
    public 
    LogoutTask(
        IContainer    container,
        LogoutRequest request,
        ISessionReplyReceiver receiver)
    {
        super( "LogoutTask" );
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
        ISessionService       service  = null;
        ILogger               logger   = null;
       
        service = 
            itsContainer.getInstance( 
                ISessionService.class,
                "SessionServiceImplementation" );
        logger = 
            itsContainer.getInstance( ILogger.class );
        
        logger.logInfo( 
            "Executing logout task for request: " + 
            itsRequest.getRequestId() );
        service.logout( itsReceiver,itsRequest );
    }

}

// ##########################################################################
