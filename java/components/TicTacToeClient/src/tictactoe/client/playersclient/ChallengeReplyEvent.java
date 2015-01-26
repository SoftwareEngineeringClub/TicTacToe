// ##########################################################################
// # File Name:	RefreshPlayersEvent.java
// ##########################################################################

package tictactoe.client.playersclient;

import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class ChallengeReplyEvent 
    implements IChangeEvent
{
    private final IPlayersModel itsSender;
    
    /************************************************************************
     * Creates a new LoginEvent. 
     *
     */
    public 
    ChallengeReplyEvent(IPlayersModel sender)
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
