// ##########################################################################
// # File Name:	TicTacToeServerFactory.java
// ##########################################################################

package tictactoe.server.bootstrap;

import tictactoe.application.playerapp.PlayerAppModule;
import tictactoe.application.sessionapp.SessionAppModule;
import tictactoe.application.taskdispatcher.TaskDispatcherModule;
import tictactoe.domain.gamedomain.GameDomainModule;
import tictactoe.domain.playerdomain.PlayerDomainModule;
import tictactoe.domain.sessiondomain.SessionDomainModule;
import tictactoe.integration.serviceinvoker.ServiceInvokerModule;
import tictactoe.serverplatform.hibernatepersistence.HibernatePersistenceModule;
import tictactoe.serverplatform.jmsmessaging.JmsMessagingModule;

import strata1.common.logger.ILogger;
import strata1.common.logger.PrintWriterLogEntryProcessor;
import strata1.injector.bootstrap.AbstractApplicationFactory;
import strata1.injector.bootstrap.IApplicationStarter;
import strata1.injector.bootstrap.IStartStopController;
import strata1.injector.container.IModule;
import strata1.injector.property.PropertiesModule;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/****************************************************************************
 * 
 */
public 
class TicTacToeServerFactory 
    extends AbstractApplicationFactory
{

    /************************************************************************
     * Creates a new TicTacToeServerFactory. 
     *
     */
    public 
    TicTacToeServerFactory() {}
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ILogger 
    createLogger()
    {
        ILogger logger = super.createLogger();
        FileOutputStream output = null;
        
        try
        {
            output = new FileOutputStream("TicTacToeServer.log");
        }
        catch (FileNotFoundException e)
        {
            throw new IllegalStateException(e);
        }
        
        logger.attachProcessor( 
            new PrintWriterLogEntryProcessor(
                new PrintWriter(output) ) );
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
        
        modules.add( new PropertiesModule( createProperties() ) );
        modules.add(  new JmsMessagingModule() );
        modules.add(  new HibernatePersistenceModule() );
        modules.add(  new SessionDomainModule() );
        modules.add(  new PlayerDomainModule() );
        modules.add(  new GameDomainModule() );
        modules.add(  new SessionAppModule() );
        modules.add(  new PlayerAppModule() );
        modules.add(  new TaskDispatcherModule() );
        modules.add(  new ServiceInvokerModule() );
        
        return modules;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IStartStopController 
    createStartStopController()
    {
        return new TicTacToeServerStartStopController();
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    private Properties
    createProperties()
    {
        Properties  properties = new Properties();
        InputStream input = 
            ClassLoader
                .getSystemClassLoader()
                .getResourceAsStream( 
                    "tictactoe/server/main/tictactoeserver.properties" );
        
        try
        {
            properties.load( input );
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
        
        return properties;
    }

}

// ##########################################################################
