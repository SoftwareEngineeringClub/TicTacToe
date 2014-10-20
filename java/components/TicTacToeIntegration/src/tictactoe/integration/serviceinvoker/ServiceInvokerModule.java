// ##########################################################################
// # File Name:	ServiceInvokerModule.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class ServiceInvokerModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new ServiceInvokerModule. 
     *
     */
    public 
    ServiceInvokerModule()
    {
        super( "ServiceInvokerModule" );
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
                bindType(ServiceInvoker.class)
                    .withKey( "ServiceInvoker1" )
                    .toInstance( 
                        new ServiceInvoker(
                            container,
                            "MessagingSession1a") ) )
            .insertBinding( 
                bindType(ServiceInvoker.class)
                    .withKey( "ServiceInvoker2" )
                    .toInstance( 
                        new ServiceInvoker(
                            container,
                            "MessagingSession2a") ) );
    }

}

// ##########################################################################
