// ##########################################################################
// # File Name:	SessionProxyModule.java
// ##########################################################################

package tictactoe.integration.sessionproxy;

import tictactoe.service.sessionservice.ISessionService;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessagingSession;

/****************************************************************************
 * 
 */
public 
class SessionProxyModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new SessionProxyModule. 
     *
     */
    public 
    SessionProxyModule()
    {
        super( "SessionProxyModule" );

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
                bindType( ISessionService.class)
                    .toProvider( 
                        new SessionMessagingProxyProvider( container ) ) );
    }

}

// ##########################################################################
