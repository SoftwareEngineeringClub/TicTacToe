// ##########################################################################
// # File Name:	SessionMessagingProxyProvider.java
// ##########################################################################

package tictactoe.integration.sessionproxy;

import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class SessionMessagingProxyProvider 
    implements ISessionMessagingProxyProvider
{
    private final IContainer itsContainer;
    
    /************************************************************************
     * Creates a new SessionMessagingProxyProvider. 
     *
     * @param container
     */
    public 
    SessionMessagingProxyProvider(IContainer container)
    {
        itsContainer = container;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ISessionMessagingProxy 
    get()
    {
        return new SessionMessagingProxy( itsContainer );
    }

}

// ##########################################################################
