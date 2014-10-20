// ##########################################################################
// # File Name:	TicTacToeDesktopFactory.java
// ##########################################################################

package tictactoe.desktop.bootstrap;

import tictactoe.clientplatform.jmsmessaging.JmsMessagingModule;
import tictactoe.integration.sessionproxy.SessionProxyModule;

import strata1.client.bootstrap.AbstractClientFactory;
import strata1.client.region.IRegionManager;
import strata1.client.shell.IDispatcher;
import strata1.client.view.ILoginView;
import strata1.client.view.ISplashView;
import strata1.client.view.NullLoginView;
import strata1.common.authentication.IAuthenticator;
import strata1.common.authentication.NullAuthenticator;
import strata1.common.logger.ILogger;
import strata1.common.logger.Logger;
import strata1.injector.container.Container;
import strata1.injector.container.IContainer;
import strata1.injector.container.IModule;
import strata1.swtclient.swtregion.SwtRegionManager;
import strata1.swtclient.swtshell.SwtDispatcher;
import strata1.swtclient.swtview.SwtSplashView;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * 
 */
public 
class TicTacToeDesktopFactory 
    extends AbstractClientFactory
{

    /************************************************************************
     * Creates a new TicTacToeDesktopFactory. 
     *
     */
    public 
    TicTacToeDesktopFactory()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IAuthenticator 
    createAuthenticator()
    {
        return new NullAuthenticator();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IContainer 
    createContainer()
    {
        return new Container();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IContainer 
    createContainer(String arg0)
    {
        return createContainer();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IDispatcher 
    createDispatcher()
    {
        return new SwtDispatcher();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ILogger 
    createLogger()
    {
        return new Logger();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ILoginView 
    createLoginView(IDispatcher dispatcher)
    {
        return new NullLoginView();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public List<IModule> 
    createModules()
    {
        List<IModule> modules = new ArrayList<IModule>();
        
        modules.add( new JmsMessagingModule() );
        modules.add( new SessionProxyModule() );
        return modules;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IRegionManager 
    createRegionManager()
    {
        return new SwtRegionManager();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ISplashView 
    createSplashView(IDispatcher dispatcher)
    {
        return new SwtSplashView(dispatcher,"Tic Tac Toe","1.0","");
    }

}

// ##########################################################################
