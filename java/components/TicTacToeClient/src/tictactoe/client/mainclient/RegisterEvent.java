// ##########################################################################
// # File Name:	RegisterEvent.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class RegisterEvent 
    implements IChangeEvent
{
    private final ISessionModel itsSender;
    
    /************************************************************************
     * Creates a new LoginEvent. 
     *
     */
    public 
    RegisterEvent(ISessionModel sender)
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
