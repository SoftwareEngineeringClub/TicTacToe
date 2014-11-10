// ##########################################################################
// # File Name:	PlayersClientTestModule.java
// ##########################################################################

package tictactoe.client.homeclient;

/****************************************************************************
 * 
 */
public 
class HomeClientTestModule 
    extends HomeClientModule
{

    /************************************************************************
     * Creates a new PlayersClientTestModule. 
     *
     */
    public 
    HomeClientTestModule()
    {
        super( "PlayersClientTestModule" );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IHomeController> 
    getHomeControllerType()
    {
        return FakeHomeController.class;
    }

    @Override
    protected Class<? extends IHomeView> getHomeViewType()
    {
        return null;
    }

    @Override
    protected Class<? extends IHomeModel> getHomeModelType()
    {
        return null;
    }

}

// ##########################################################################
