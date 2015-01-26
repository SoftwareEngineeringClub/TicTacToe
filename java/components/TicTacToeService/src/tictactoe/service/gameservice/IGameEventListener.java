// ##########################################################################
// # File Name:	IGameEventListener.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
interface IGameEventListener
{
    public void
    onGameUpdated(GameUpdatedEvent event);
    
    public void
    onGameException(GameException exception);
}

// ##########################################################################
