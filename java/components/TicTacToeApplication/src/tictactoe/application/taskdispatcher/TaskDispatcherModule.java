// ##########################################################################
// # File Name:	TaskDispatcherModule.java
// ##########################################################################

package tictactoe.application.taskdispatcher;

import strata1.common.task.ITaskConsumer;
import strata1.common.task.ITaskDispatcher;
import strata1.common.task.TaskConsumer;
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
        container
            .insertBinding( 
                bindType(ITaskConsumer.class)
                    .withKey( "Consumer1" )
                    .toProvider( new TaskConsumerProvider(container) )
                    .withScope( new SingletonScope<ITaskConsumer>() ) )                
            .insertBinding( 
                bindType(ITaskConsumer.class)
                    .withKey( "Consumer2" )
                    .toType( TaskConsumer.class )
                    .withScope( new SingletonScope<ITaskConsumer>() ) )                 
            .insertBinding( 
                bindType(ITaskConsumer.class)
                    .withKey( "Consumer3" )
                    .toType( TaskConsumer.class )
                    .withScope( new SingletonScope<ITaskConsumer>() ) )                 
            .insertBinding( 
                bindType(ITaskConsumer.class)
                    .withKey( "Consumer4" )
                    .toType( TaskConsumer.class )
                    .withScope( new SingletonScope<ITaskConsumer>() ) )                 
            .insertBinding( 
                bindType(ITaskDispatcher.class)
                    .toProvider( TaskDispatcherProvider.class )
                    .withScope( new SingletonScope<ITaskDispatcher>() ) );
    }

}

// ##########################################################################
