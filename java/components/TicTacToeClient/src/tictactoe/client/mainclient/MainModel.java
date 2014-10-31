// ##########################################################################
// # File Name:	MainModel.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.model.AbstractModel;

/****************************************************************************
 * 
 */
public 
class MainModel 
    extends    AbstractModel
    implements IMainModel
{
    private ISessionModel itsSession;
    private MainTabKind   itsActiveTab;

    /************************************************************************
     * Creates a new MainModel. 
     *
     */
    public 
    MainModel()
    {
        itsSession = null;
        itsActiveTab = MainTabKind.FILE_TAB;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainModel 
    setSession(ISessionModel session)
    {
        itsSession = session;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainModel 
    setActiveTab(MainTabKind activeTab)
    {
        itsActiveTab = activeTab;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ISessionModel 
    getSession()
    {
        return itsSession;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public MainTabKind 
    getActiveTab()
    {
        return itsActiveTab;
    }

}

// ##########################################################################
