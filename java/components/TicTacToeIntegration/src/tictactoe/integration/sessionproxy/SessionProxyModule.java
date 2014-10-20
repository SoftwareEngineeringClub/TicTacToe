// ##########################################################################
// # File Name:	SessionProxyModule.java
// ##########################################################################

package tictactoe.integration.sessionproxy;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;

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
        container
            .insertBinding(
                bindType( ISessionMessagingProxy.class)
                    .toProvider( 
                        new SessionMessagingProxyProvider( container ) ) );
    }

}

// ##########################################################################
