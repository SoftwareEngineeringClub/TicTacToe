// ##########################################################################
// # File Name:	FakePlayersController.java
// ##########################################################################

package tictactoe.client.homeclient;

import strata1.client.command.ICommand;
import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class FakeHomeController 
    implements IHomeController
{

    /************************************************************************
     * Creates a new FakePlayersController. 
     *
     */
    public 
    FakeHomeController()
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
