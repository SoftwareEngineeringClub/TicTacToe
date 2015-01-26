// ##########################################################################
// # File Name:	TimerModule.java
// ##########################################################################

package tictactoe.application.timer;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.SingletonScope;

/****************************************************************************
 * 
 */
public 
class TimerModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new TimerModule. 
     *
     */
    public 
    TimerModule()
    {
        super( "TimerModule" );

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
                bindType(DeadSessionTimer.class)
                    .toProvider( new DeadSessionTimerProvider(container) )
                    .withScope( new SingletonScope<DeadSessionTimer>() ) );
    }

}

// ##########################################################################
