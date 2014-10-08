// ##########################################################################
// # File Name:	LoginTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.LoginRequest;

import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class LoginTask 
    extends AbstractTask
{
    private final IContainer   itsContainer;
    private final LoginRequest itsRequest;
    
    /************************************************************************
     * Creates a new LoginTask. 
     *
     * @param container
     * @param request
     */
    public 
    LoginTask(
        IContainer   container,
        LoginRequest request)
    {
        super( "LoginTask" );
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

        service.login( receiver,itsRequest );
    }

}

// ##########################################################################
