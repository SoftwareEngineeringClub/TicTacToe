// ##########################################################################
// # File Name:	TicTacToeServerStartStopController.java
// ##########################################################################

package tictactoe.server.bootstrap;

import tictactoe.application.timer.DeadSessionTimer;
import tictactoe.integration.serviceinvoker.ServiceInvoker;

import strata1.common.task.ITaskDispatcher;
import strata1.injector.bootstrap.IApplicationStarter;
import strata1.injector.bootstrap.IStartStopController;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessagingSession;

import java.io.IOException;

/****************************************************************************
 * 
 */
public 
class TicTacToeServerStartStopController 
    implements IStartStopController
{
    private IContainer itsContainer;
    
    /************************************************************************
     * Creates a new TicTacToeServerStartStopController. 
     *
     */
    public 
    TicTacToeServerStartStopController() 
    {
        itsContainer = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IStartStopController 
    setContainer(IContainer container)
    {
        itsContainer = container;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startApplication()
    {
        IMessagingSession session1   = null;
        IMessagingSession session2   = null;
        ITaskDispatcher   dispatcher = null;
        ServiceInvoker    invoker1   = null;
        ServiceInvoker    invoker2   = null;
        DeadSessionTimer  timer      = null;
        
        session1 = 
            itsContainer.getInstance(
                IMessagingSession.class,
                "CommandSession1" );
        session2 = 
            itsContainer.getInstance(
                IMessagingSession.class,
                "CommandSession2" );
        
        dispatcher = 
            itsContainer.getInstance( ITaskDispatcher.class );
        invoker1 = 
            itsContainer.getInstance(ServiceInvoker.class,"ServiceInvoker1");
        invoker2 = 
            itsContainer.getInstance(ServiceInvoker.class,"ServiceInvoker2");
        timer = 
            itsContainer.getInstance( DeadSessionTimer.class );
        
        session1.startReceiving();
        session2.startReceiving();
        dispatcher.startDispatching();
        invoker1.startProducing();
        invoker2.startProducing();
        timer.startProducing();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stopApplication()
    {
        ITaskDispatcher  dispatcher = null;
        ServiceInvoker   invoker1   = null;
        ServiceInvoker   invoker2   = null;
        DeadSessionTimer timer      = null;
        
        dispatcher = 
            itsContainer.getInstance( ITaskDispatcher.class );
        invoker1 = 
            itsContainer.getInstance(ServiceInvoker.class,"ServiceInvoker1");
        invoker2 = 
            itsContainer.getInstance(ServiceInvoker.class,"ServiceInvoker2");
        timer = 
            itsContainer.getInstance( DeadSessionTimer.class );
       
        dispatcher.stopDispatching();
        invoker1.stopProducing();
        invoker2.stopProducing();
        timer.stopProducing();
    }

}

// ##########################################################################
