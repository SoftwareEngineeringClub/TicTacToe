// ##########################################################################
// # File Name:	TicTacToeDesktopFactory.java
// ##########################################################################

package tictactoe.desktop.bootstrap;

import tictactoe.clientplatform.jmsmessaging.JmsMessagingModule;
import tictactoe.integration.sessionproxy.SessionProxyModule;

import strata1.common.logger.ILogger;
import strata1.common.logger.PrintWriterLogEntryProcessor;
import strata1.injector.bootstrap.AbstractApplicationFactory;
import strata1.injector.bootstrap.IApplicationStarter;
import strata1.injector.container.IModule;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * 
 */
public 
class TicTacToeDesktopFactory 
    extends AbstractApplicationFactory
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
    public ILogger 
    createLogger()
    {
        ILogger logger = super.createLogger();
        
        logger.attachProcessor( 
            new PrintWriterLogEntryProcessor(
                new PrintWriter(System.out) ) );
        return logger;
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
    public IApplicationStarter 
    createApplicationStarter()
    {
        return new TicTacToeDesktopStarter();
    }

}

// ##########################################################################
