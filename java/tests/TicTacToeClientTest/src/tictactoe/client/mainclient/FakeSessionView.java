// ##########################################################################
// # File Name:	FakeSessionView.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.command.ExecutionException;
import strata1.client.command.ICommand;
import strata1.client.command.ICommandProvider;
import strata1.client.event.IChangeEvent;
import strata1.common.authentication.ICredential;
import strata1.common.authentication.UserNameAndPasswordCredential;

/****************************************************************************
 * 
 */
public 
class FakeSessionView 
    implements ISessionView
{
    private ICommandProvider itsProvider;
    
    /************************************************************************
     * Creates a new FakeSessionView. 
     *
     */
    public 
    FakeSessionView()
    {
        itsProvider = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    getViewName()
    {
        return "FakeSessionView";
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
    invoke(String commandName) throws ExecutionException
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
    public void 
    reset()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    displayMessage(String message)
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ICredential 
    getLoginCredential()
    {
        return new UserNameAndPasswordCredential("John","Foobar");
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ICredential 
    getRegisterCredential()
    {
        return getLoginCredential();
    }

}

// ##########################################################################
