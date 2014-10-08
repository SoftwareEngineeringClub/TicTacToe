// ##########################################################################
// # File Name:	LogoutTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.LogoutRequest;

import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class LogoutTask 
    extends AbstractTask
{
    private final IContainer    itsContainer;
    private final LogoutRequest itsRequest;
    
    /************************************************************************
     * Creates a new LogoutTask. 
     *
     * @param container
     * @param request
     */
    public 
    LogoutTask(
        IContainer    container,
        LogoutRequest request)
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
        ISessionReplyReceiver receiver = null;
       
        service = 
            itsContainer.getInstance( 
                ISessionService.class,
                "SessionServiceImplementation" );
        receiver = 
            itsContainer.getInstance( ISessionReplyReceiver.class );

        service.logout( receiver,itsRequest );
    }

}

// ##########################################################################
