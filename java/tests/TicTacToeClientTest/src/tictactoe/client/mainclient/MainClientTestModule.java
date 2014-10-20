// ##########################################################################
// # File Name:	MainClientTestModule.java
// ##########################################################################

package tictactoe.client.mainclient;

/****************************************************************************
 * 
 */
public 
class MainClientTestModule 
    extends MainClientModule
{

    /************************************************************************
     * Creates a new MainClientTestModule. 
     *
     */
    public 
    MainClientTestModule()
    {
        super( "MainClientTestModule" );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends ISessionView> 
    getSessionViewType()
    {
        return FakeSessionView.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends ISessionModel> 
    getSessionModelType()
    {
        return FakeSessionModel.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IMainView> 
    getMainViewType()
    {
        return FakeMainView.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IMainModel> 
    getMainModelType()
    {
        return FakeMainModel.class;
    }

}

// ##########################################################################
