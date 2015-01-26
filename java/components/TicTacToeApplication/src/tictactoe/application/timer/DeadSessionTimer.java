// ##########################################################################
// # File Name:	DeadSessionTimer.java
// ##########################################################################

package tictactoe.application.timer;

import strata1.common.task.AbstractTaskProducer;
import strata1.common.task.ITaskDispatcher;
import strata1.injector.container.IContainer;

import javax.inject.Inject;

import java.util.Timer;
import java.util.TimerTask;

/****************************************************************************
 * 
 */
public 
class DeadSessionTimer 
    extends AbstractTaskProducer
{
    private static final long FIVE_MINUTES = 5*60*1000;
    
    private final IContainer itsContainer;
    private Timer            itsTimer;
    
    /************************************************************************
     * Creates a new DeadSessionTimer. 
     *
     */
    public 
    DeadSessionTimer(IContainer container)
    {
        itsTimer = null;
        itsContainer = container;
        setDispatcher( itsContainer.getInstance(ITaskDispatcher.class) );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startProducing()
    {
        if ( isProducing() )
            return;
        
        itsTimer = new Timer();
        itsTimer.scheduleAtFixedRate( 
            new TimerTask() 
            {
                @Override
                public void 
                run()
                {
                    getDispatcher()
                        .route( 
                            new RemoveDeadSessionsTask(itsContainer) );
                }
            },
            FIVE_MINUTES,
            FIVE_MINUTES );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stopProducing()
    {
        if ( !isProducing() )
            return;
        
        itsTimer.cancel();
        itsTimer = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    isProducing()
    {
        return itsTimer != null;
    }

}

// ##########################################################################
