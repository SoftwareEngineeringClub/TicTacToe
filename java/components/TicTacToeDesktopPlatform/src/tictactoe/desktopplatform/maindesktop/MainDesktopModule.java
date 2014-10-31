// ##########################################################################
// # File Name:	MainDesktopModule.java
// ##########################################################################

package tictactoe.desktopplatform.maindesktop;

import tictactoe.client.mainclient.IMainModel;
import tictactoe.client.mainclient.IMainView;
import tictactoe.client.mainclient.ISessionModel;
import tictactoe.client.mainclient.ISessionView;
import tictactoe.client.mainclient.MainClientModule;
import tictactoe.client.mainclient.MainModel;
import tictactoe.client.mainclient.SessionModel;

/****************************************************************************
 * 
 */
public 
class MainDesktopModule 
    extends MainClientModule
{

    /************************************************************************
     * Creates a new MainDesktopModule. 
     *
     * @param name
     */
    public 
    MainDesktopModule()
    {
        super( "MainDesktopModule" );

    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends ISessionView> 
    getSessionViewType()
    {
        return SwtSessionView.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends ISessionModel> 
    getSessionModelType()
    {
        return SessionModel.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IMainView> 
    getMainViewType()
    {
        return SwtMainView.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IMainModel> 
    getMainModelType()
    {
        return MainModel.class;
    }

}

// ##########################################################################
