// ##########################################################################
// # File Name:	TicTacToeDesktopBootstrapper.java
// ##########################################################################

package tictactoe.desktop.bootstrap;

import tictactoe.client.mainclient.IMainController;

import strata1.client.bootstrap.AbstractClientBootstrapper;

/****************************************************************************
 * 
 */
public 
class TicTacToeDesktopBootstrapper 
    extends AbstractClientBootstrapper
{

    /************************************************************************
     * Creates a new TicTacToeDesktopBootstrapper. 
     *
     */
    public 
    TicTacToeDesktopBootstrapper()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected void 
    configureRegionManager()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected void 
    startApplication()
    {
        IMainController controller = 
            getContainer().getInstance( IMainController.class );
        
        controller.start();
    }

}

// ##########################################################################
