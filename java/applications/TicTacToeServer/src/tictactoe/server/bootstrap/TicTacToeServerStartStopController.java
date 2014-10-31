// ##########################################################################
// # File Name:	TicTacToeServerStartStopController.java
// ##########################################################################

package tictactoe.server.bootstrap;

import tictactoe.integration.serviceinvoker.ServiceInvoker;

import strata1.common.task.ITaskDispatcher;
import strata1.injector.bootstrap.IApplicationStarter;
import strata1.injector.bootstrap.IStartStopController;
import strata1.injector.container.IContainer;

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
        ITaskDispatcher dispatcher = null;
        ServiceInvoker  invoker1   = null;
        ServiceInvoker  invoker2   = null;
        
        dispatcher = 
            itsContainer.getInstance( ITaskDispatcher.class );
        invoker1 = 
            itsContainer.getInstance(ServiceInvoker.class,"ServiceInvoker1");
        invoker2 = 
            itsContainer.getInstance(ServiceInvoker.class,"ServiceInvoker2");
        
        dispatcher.startDispatching();
        invoker1.startProducing();
        invoker2.startProducing();        
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stopApplication()
    {
        ITaskDispatcher dispatcher = null;
        ServiceInvoker  invoker1   = null;
        ServiceInvoker  invoker2   = null;
        
        dispatcher = 
            itsContainer.getInstance( ITaskDispatcher.class );
        invoker1 = 
            itsContainer.getInstance(ServiceInvoker.class,"ServiceInvoker1");
        invoker2 = 
            itsContainer.getInstance(ServiceInvoker.class,"ServiceInvoker2");
        
        dispatcher.stopDispatching();
        invoker1.stopProducing();
        invoker2.stopProducing();
    }

}

// ##########################################################################
