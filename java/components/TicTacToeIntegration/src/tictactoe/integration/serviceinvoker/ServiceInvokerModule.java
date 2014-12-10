// ##########################################################################
// # File Name:	ServiceInvokerModule.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.SingletonScope;

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
                    .toProvider( 
                        new ServiceInvokerProvider(
                            container,
                            "CommandSession1",
                            "RequestChannelId1",
                            "ReplyChannelId1",
                            "EventSession1",
                            "EventChannelId1") )
                    .withScope( new SingletonScope<ServiceInvoker>() ) )
            .insertBinding( 
                bindType(ServiceInvoker.class)
                    .withKey( "ServiceInvoker2" )
                    .toProvider( 
                        new ServiceInvokerProvider(
                            container,
                            "CommandSession2",
                            "RequestChannelId2",
                            "ReplyChannelId2",
                            "EventSession2",
                            "EventChannelId2") )
                    .withScope( new SingletonScope<ServiceInvoker>() ) );
    }

}

// ##########################################################################
