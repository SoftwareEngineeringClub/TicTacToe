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
    private final String     itsCommandSession;
    private final String     itsRequestChannelId;
    private final String     itsReplyChannelId;
    private final String     itsEventSession;
    private final String     itsEventChannelId;
    
    /************************************************************************
     * Creates a new ServiceInvokerProvider. 
     * @param eventChannelId TODO
     *
     */
    public 
    ServiceInvokerProvider(
        IContainer container,
        String     commandSession,
        String     requestChannelId,
        String     replyChannelId, 
        String     eventSession,
        String     eventChannelId)
    {
        itsContainer        = container;
        itsCommandSession   = commandSession;
        itsRequestChannelId = requestChannelId;
        itsReplyChannelId   = replyChannelId;
        itsEventSession     = eventSession;
        itsEventChannelId   = eventChannelId;
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
                itsCommandSession,
                itsRequestChannelId,
                itsReplyChannelId,
                itsEventSession,
                itsEventChannelId);
    }

}

// ##########################################################################
