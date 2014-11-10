// ##########################################################################
// # File Name:	AnyTaskSelector.java
// ##########################################################################

package tictactoe.application.taskdispatcher;

import strata1.common.task.ITask;
import strata1.common.task.ITaskSelector;

/****************************************************************************
 * 
 */
public 
class AnyTaskSelector 
    implements ITaskSelector
{

    /************************************************************************
     * Creates a new AnyTaskSelector. 
     *
     */
    public 
    AnyTaskSelector() {}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    match(ITask task)
    {
        return true;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    equals(Object other)
    {
        return other instanceof AnyTaskSelector;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public int 
    hashCode()
    {
        return 123;
    }

}

// ##########################################################################
