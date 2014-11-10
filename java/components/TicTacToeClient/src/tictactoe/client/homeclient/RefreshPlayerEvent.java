// ##########################################################################
// # File Name:	RefreshPlayerEvent.java
// ##########################################################################

package tictactoe.client.homeclient;

import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class RefreshPlayerEvent 
    implements IChangeEvent
{
    private final IHomeModel itsSender;
    
    /************************************************************************
     * Creates a new LoginEvent. 
     *
     */
    public 
    RefreshPlayerEvent(IHomeModel sender)
    {
        itsSender = sender;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T 
    getSender(Class<T> type)
    {
        if ( type != IHomeModel.class )
            return null;
        
        return (T)itsSender;
    }

}

// ##########################################################################
