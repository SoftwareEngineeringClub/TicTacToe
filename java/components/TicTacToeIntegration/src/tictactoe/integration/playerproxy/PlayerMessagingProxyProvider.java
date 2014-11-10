// ##########################################################################
// # File Name:	PlayerMessagingProxyProvider.java
// ##########################################################################

package tictactoe.integration.playerproxy;

import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class PlayerMessagingProxyProvider 
    implements IPlayerMessagingProxyProvider
{
    private final IContainer itsContainer;
    
    /************************************************************************
     * Creates a new PlayerMessagingProxyProvider. 
     *
     */
    public 
    PlayerMessagingProxyProvider(IContainer container)
    {
        itsContainer = container;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayerMessagingProxy 
    get()
    {
        return new PlayerMessagingProxy(itsContainer);
    }

}

// ##########################################################################
