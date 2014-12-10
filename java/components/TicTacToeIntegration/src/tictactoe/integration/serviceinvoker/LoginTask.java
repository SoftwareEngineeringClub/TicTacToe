// ##########################################################################
// # File Name:	LoginTask.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerNotifierHost;
import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.LoginRequest;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class LoginTask 
    extends AbstractTask
{
    private final IContainer            itsContainer;
    private final LoginRequest          itsRequest;
    private final ISessionReplyReceiver itsReceiver;
    private final IPlayerEventListener  itsNotifier;
    
    /************************************************************************
     * Creates a new LoginTask. 
     *
     * @param container
     * @param request
     */
    public 
    LoginTask(
        IContainer            container,
        LoginRequest          request,
        ISessionReplyReceiver receiver,
        IPlayerEventListener  notifier)
    {
        super( "LoginTask" );
        itsContainer = container;
        itsRequest   = request;
        itsReceiver  = receiver;
        itsNotifier  = notifier;
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
            "Executing login task for request: " + 
            itsRequest.getRequestId() );        
        
        ((IPlayerNotifierHost)service).setPlayerNotifier( itsNotifier );
        service.login( itsReceiver,itsRequest );
    }

}

// ##########################################################################
