// ##########################################################################
// # File Name:	FakeMainView.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.command.ExecutionException;
import strata1.client.command.ICommand;
import strata1.client.command.ICommandProvider;
import strata1.client.event.IChangeEvent;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class FakeMainView 
    implements IMainView
{
    private ICommandProvider itsProvider;
    private MainTabKind      itsActiveTab;
    private ISessionView     itsSessionView;
   
    /************************************************************************
     * Creates a new FakeMainView. 
     *
     */
    @Inject
    public 
    FakeMainView(ISessionView sessionView)
    {
        itsProvider = null;
        itsActiveTab = MainTabKind.FILE_TAB;
        itsSessionView = sessionView;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    getViewName()
    {
        return "FakeMainView";
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    hide()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    show()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stop()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    setProvider(ICommandProvider provider)
    {
        itsProvider = provider;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ICommandProvider 
    getProvider()
    {
        return itsProvider;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    getInvokerName()
    {
        return getViewName();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    invoke(String commandName) 
        throws ExecutionException
    {
        ICommand command = getProvider().getCommand( commandName );
        
        command.execute();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    processChange(IChangeEvent event)
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainView 
    setActiveTab(MainTabKind activeTab)
    {
        itsActiveTab = activeTab;
        return this;
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
