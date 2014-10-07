// ##########################################################################
// # File Name:	TaskDispatcherProvider.java
// ##########################################################################

package tictactoe.application.taskdispatcher;

import strata1.common.task.ITaskDispatcher;
import strata1.common.task.TaskDisruptorDispatcher;

/****************************************************************************
 * 
 */
public 
class TaskDispatcherProvider 
    implements ITaskDispatcherProvider
{

    /************************************************************************
     * Creates a new TaskDispatcherProvider. 
     *
     */
    public 
    TaskDispatcherProvider() {}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ITaskDispatcher 
    get()
    {
        return new TaskDisruptorDispatcher(4096);
    }

}

// ##########################################################################
