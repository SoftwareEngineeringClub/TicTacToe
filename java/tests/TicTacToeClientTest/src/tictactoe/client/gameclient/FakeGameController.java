// ##########################################################################
// # File Name:	FakePlayersController.java
// ##########################################################################

package tictactoe.client.gameclient;

import strata1.client.command.ICommand;
import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class FakeGameController 
    implements IGameController
{

    /************************************************************************
     * Creates a new FakePlayersController. 
     *
     */
    public 
    FakeGameController()
    {
    }

    @Override
    public void 
    start()
    {
    }

    @Override
    public ICommand 
    getCommand(String arg0)
    {
        return null;
    }

    @Override
    public boolean 
    hasCommand(String arg0)
    {
        return false;
    }

    @Override
    public void 
    processChange(IChangeEvent arg0)
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startGame(Long newGameId)
    {
    }

}

// ##########################################################################
