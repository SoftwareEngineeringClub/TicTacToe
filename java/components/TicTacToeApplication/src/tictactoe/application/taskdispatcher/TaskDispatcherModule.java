// ##########################################################################
// # File Name:	TaskDispatcherModule.java
// ##########################################################################

package tictactoe.application.taskdispatcher;

import strata1.common.logger.ILogger;
import strata1.common.task.ITaskConsumer;
import strata1.common.task.ITaskDispatcher;
import strata1.common.task.TaskConsumer;
import strata1.common.task.TaskDisruptorDispatcher;
import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.SingletonScope;
import strata1.injector.container.ThreadScope;

import javax.inject.Provider;

/****************************************************************************
 * 
 */
public 
class TaskDispatcherModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new TaskDispatcherModule. 
     *
     */
    public 
    TaskDispatcherModule()
    {
        super( "TaskDispatcherModule" );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    initialize(IContainer container)
    {
        ILogger         logger     = null;
        ITaskDispatcher dispatcher = null;
        ITaskConsumer   consumer1  = null;
        ITaskConsumer   consumer2  = null;
        ITaskConsumer   consumer3  = null;
        ITaskConsumer   consumer4  = null;
        
        logger     = container.getInstance( ILogger.class );
        dispatcher = new TaskDisruptorDispatcher(4096);
        consumer1  = new TaskConsumer(new AnyTaskSelector(),logger);
        consumer2  = new TaskConsumer(new AnyTaskSelector(),logger);
        consumer3  = new TaskConsumer(new AnyTaskSelector(),logger);
        consumer4  = new TaskConsumer(new AnyTaskSelector(),logger);
        
        dispatcher
            .attachConsumer( consumer1 )
            .attachConsumer( consumer2 )
            .attachConsumer( consumer3 )
            .attachConsumer( consumer4 );
        
        container
            .insertBinding( 
                bindType(ITaskConsumer.class)
                    .withKey( "Consumer1" )
                    .toInstance( consumer1 ) )
            .insertBinding( 
                bindType(ITaskConsumer.class)
                    .withKey( "Consumer2" )
                    .toInstance( consumer2 ) )
            .insertBinding( 
                bindType(ITaskConsumer.class)
                    .withKey( "Consumer3" )
                    .toInstance( consumer3 ) )
            .insertBinding( 
                bindType(ITaskConsumer.class)
                    .withKey( "Consumer4" )
                    .toInstance( consumer4 ) )
            .insertBinding( 
                bindType(ITaskDispatcher.class)
                    .toInstance( dispatcher ) );
    }

}

// ##########################################################################
