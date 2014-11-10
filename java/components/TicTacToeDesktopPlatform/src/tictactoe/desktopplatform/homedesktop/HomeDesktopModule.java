// ##########################################################################
// # File Name:	HomeDesktopModule.java
// ##########################################################################

package tictactoe.desktopplatform.homedesktop;

import tictactoe.client.homeclient.HomeClientModule;
import tictactoe.client.homeclient.HomeController;
import tictactoe.client.homeclient.HomeModel;
import tictactoe.client.homeclient.IHomeController;
import tictactoe.client.homeclient.IHomeModel;
import tictactoe.client.homeclient.IHomeView;

/****************************************************************************
 * 
 */
public 
class HomeDesktopModule 
    extends HomeClientModule
{

    /************************************************************************
     * Creates a new HomeDesktopModule. 
     *
     * @param name
     */
    public 
    HomeDesktopModule()
    {
        super( "HomeDesktopModule" );

    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IHomeView> 
    getHomeViewType()
    {
        return SwtHomeView.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IHomeModel> 
    getHomeModelType()
    {
        return HomeModel.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IHomeController> 
    getHomeControllerType()
    {
        return HomeController.class;
    }

}

// ##########################################################################
