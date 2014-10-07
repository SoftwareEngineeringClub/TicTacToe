// ##########################################################################
// # File Name:	TaskConsumerProvider.java
// ##########################################################################

package tictactoe.application.taskdispatcher;

import strata1.common.logger.ILogger;
import strata1.common.task.ITask;
import strata1.common.task.ITaskConsumer;
import strata1.common.task.ITaskSelector;
import strata1.common.task.TaskConsumer;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class TaskConsumerProvider 
    implements ITaskConsumerProvider
{
    private final IContainer itsContainer;
    
    /************************************************************************
     * Creates a new TaskConsumerProvider. 
     *
     */
    public 
    TaskConsumerProvider(IContainer container)
    {
        itsContainer = container;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ITaskConsumer 
    get()
    {
        ILogger logger = itsContainer.getInstance( ILogger.class );
        
        return new TaskConsumer(null,logger);
    }

}

// ##########################################################################
