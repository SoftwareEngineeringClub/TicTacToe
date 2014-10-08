// ##########################################################################
// # File Name:	RegisterTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.RegisterRequest;

import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class RegisterTask 
    extends AbstractTask
{
    private final IContainer      itsContainer;
    private final RegisterRequest itsRequest;
    
    /************************************************************************
     * Creates a new RegisterTask. 
     *
     * @param name
     */
    public 
    RegisterTask(
        IContainer      container,
        RegisterRequest request)
    {
        super( "RegisterTask" );
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
        ISessionService       service = null;
        ISessionReplyReceiver receiver = null;
       
        service = 
            itsContainer.getInstance( 
                ISessionService.class,
                "SessionServiceImplementation" );
        receiver = 
            itsContainer.getInstance( ISessionReplyReceiver.class );

        service.register( receiver,itsRequest );
    }

}

// ##########################################################################
