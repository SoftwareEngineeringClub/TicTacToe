// ##########################################################################
// # File Name:	PlayerProxyModule.java
// ##########################################################################

package tictactoe.integration.playerproxy;

import tictactoe.integration.sessionproxy.SessionMessagingProxyProvider;
import tictactoe.service.playerservice.IPlayerService;
import tictactoe.service.sessionservice.ISessionService;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessagingSession;

/****************************************************************************
 * 
 */
public 
class PlayerProxyModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new PlayerProxyModule. 
     *
     */
    public 
    PlayerProxyModule()
    {
        super( "PlayerProxyModule" );

    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    initialize(IContainer container)
    {
        if (!container.hasBinding(IMessagingSession.class,"CommandSession"))
            throw new IllegalStateException("CommandSession is null");
        
        if (!container.hasBinding(IMessagingSession.class,"EventSession"))
            throw new IllegalStateException("EventSession is null");
          
        container
            .insertBinding(
                bindType( IPlayerService.class )
                    .toProvider( 
                        new PlayerMessagingProxyProvider( container ) ) );
    }

}

// ##########################################################################
