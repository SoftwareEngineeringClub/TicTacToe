// ##########################################################################
// # File Name:	LogoutEvent.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class LogoutEvent 
    implements IChangeEvent
{
    private final ISessionModel itsSender;
    
    /************************************************************************
     * Creates a new LogoutEvent. 
     *
     */
    public 
    LogoutEvent(ISessionModel sender)
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
        if ( type != ISessionModel.class )
            return null;
        
        return (T)itsSender;
    }

}

// ##########################################################################
