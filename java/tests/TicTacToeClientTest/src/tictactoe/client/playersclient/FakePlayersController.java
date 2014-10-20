// ##########################################################################
// # File Name:	FakePlayersController.java
// ##########################################################################

package tictactoe.client.playersclient;

import strata1.client.command.ICommand;
import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class FakePlayersController 
    implements IPlayersController
{

    /************************************************************************
     * Creates a new FakePlayersController. 
     *
     */
    public 
    FakePlayersController()
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

}

// ##########################################################################
