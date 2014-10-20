// ##########################################################################
// # File Name:	FakeMainModel.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.model.AbstractModel;

/****************************************************************************
 * 
 */
public 
class FakeMainModel 
    extends    AbstractModel 
    implements IMainModel
{
    private ISessionModel itsSession;
    private MainTabKind   itsActiveTab;
    
    /************************************************************************
     * Creates a new FakeMainModel. 
     *
     */
    public 
    FakeMainModel()
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
        super.notifyChange( new TabEvent(this) );
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
