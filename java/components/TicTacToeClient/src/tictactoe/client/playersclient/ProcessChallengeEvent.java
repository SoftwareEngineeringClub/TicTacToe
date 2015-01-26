// ##########################################################################
// # File Name:	ProcessChallengeEvent.java
// ##########################################################################

package tictactoe.client.playersclient;

import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class ProcessChallengeEvent 
    implements IChangeEvent
{
    private final IPlayersModel itsSender;
    
    /************************************************************************
     * Creates a new ProcessChallengeEvent. 
     *
     */
    public 
    ProcessChallengeEvent(IPlayersModel sender)
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
        if ( type != IPlayersModel.class )
            return null;
        
        return (T)itsSender;
    }

}

// ##########################################################################
