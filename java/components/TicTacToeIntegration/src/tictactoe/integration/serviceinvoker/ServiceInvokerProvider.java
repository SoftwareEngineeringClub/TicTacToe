// ##########################################################################
// # File Name:	ServiceInvokerProvider.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import strata1.injector.container.IContainer;


/****************************************************************************
 * 
 */
public 
class ServiceInvokerProvider 
    implements IServiceInvokerProvider
{
    private final IContainer itsContainer;
    private final String     itsSession;
    private final String     itsRequestChannelId;
    
    /************************************************************************
     * Creates a new ServiceInvokerProvider. 
     *
     */
    public 
    ServiceInvokerProvider(
        IContainer container,
        String     session,
        String     requestChannelId)
    {
        itsContainer        = container;
        itsSession          = session;
        itsRequestChannelId = requestChannelId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ServiceInvoker 
    get()
    {
        return 
            new ServiceInvoker(
                itsContainer,
                itsSession,
                itsRequestChannelId );
    }

}

// ##########################################################################
