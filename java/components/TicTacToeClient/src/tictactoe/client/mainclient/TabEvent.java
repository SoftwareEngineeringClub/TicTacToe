// ##########################################################################
// # File Name:	TabEvent.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class TabEvent implements IChangeEvent
{
    private final IMainModel itsSender;
    
    /************************************************************************
     * Creates a new TabEvent. 
     *
     */
    public 
    TabEvent(IMainModel sender)
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
        if ( type != IMainModel.class )
            return null;
        
        return (T)itsSender;
    }

}

// ##########################################################################
