// ##########################################################################
// # File Name:	TicTacToeDesktopFactory.java
// ##########################################################################

package tictactoe.desktop.bootstrap;

import tictactoe.client.gameclient.GameClientTestModule;
import tictactoe.client.homeclient.HomeClientTestModule;
import tictactoe.client.playersclient.PlayersClientTestModule;
import tictactoe.desktopplatform.desktopuserinterface.DesktopUserInterfaceModule;
import tictactoe.desktopplatform.homedesktop.HomeDesktopModule;
import tictactoe.desktopplatform.jmsmessaging.JmsMessagingModule;
import tictactoe.desktopplatform.maindesktop.MainDesktopModule;
import tictactoe.desktopplatform.playersdesktop.PlayersDesktopModule;
import tictactoe.integration.playerproxy.PlayerProxyModule;
import tictactoe.integration.sessionproxy.SessionProxyModule;

import strata1.common.logger.ILogger;
import strata1.common.logger.PrintWriterLogEntryProcessor;
import strata1.injector.bootstrap.AbstractApplicationFactory;
import strata1.injector.bootstrap.IApplicationStarter;
import strata1.injector.bootstrap.IStartStopController;
import strata1.injector.container.IModule;
import strata1.injector.property.PropertiesModule;

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
class TicTacToeDesktopFactory 
    extends AbstractApplicationFactory
{

    /************************************************************************
     * Creates a new TicTacToeDesktopFactory. 
     *
     */
    public 
    TicTacToeDesktopFactory() {}

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
        modules.add( new PropertiesModule( createProperties() ) );
        modules.add( new JmsMessagingModule() );
        modules.add( new SessionProxyModule() );
        modules.add( new PlayerProxyModule() );
        modules.add( new DesktopUserInterfaceModule() );
        modules.add( new MainDesktopModule() );
        modules.add( new HomeDesktopModule() );
        modules.add( new PlayersDesktopModule() );
        modules.add( new GameClientTestModule() );
        return modules;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IStartStopController 
    createStartStopController()
    {
        return new TicTacToeDesktopStartStopController();
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
                    "tictactoe/desktop/main/tictactoedesktop.properties" );
        
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
