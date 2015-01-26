// ##########################################################################
// # File Name:	DeadSessionTimerProvider.java
// ##########################################################################

package tictactoe.application.timer;

import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class DeadSessionTimerProvider 
    implements IDeadSessionTimerProvider
{
    private final IContainer itsContainer;
    
    /************************************************************************
     * Creates a new DeadSessionTimerProvider. 
     *
     */
    public 
    DeadSessionTimerProvider(IContainer container)
    {
        itsContainer = container;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public DeadSessionTimer 
    get()
    {
        return new DeadSessionTimer(itsContainer);
    }

}

// ##########################################################################
