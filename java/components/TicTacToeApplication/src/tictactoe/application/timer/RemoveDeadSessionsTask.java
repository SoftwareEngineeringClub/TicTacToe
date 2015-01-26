// ##########################################################################
// # File Name:	RemoveDeadSessionsTask.java
// ##########################################################################

package tictactoe.application.timer;

import tictactoe.application.sessionapp.ISessionManager;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTask;
import strata1.injector.container.IContainer;

/****************************************************************************
 * 
 */
public 
class RemoveDeadSessionsTask 
    extends AbstractTask
{
    private final IContainer itsContainer;
   
    /************************************************************************
     * Creates a new RemoveDeadSessionsTask. 
     *
     * @param name
     */
    public 
    RemoveDeadSessionsTask(IContainer container)
    {
        super( "RemoveDeadSessionsTask" );
        itsContainer = container;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    execute()
    {
        ISessionManager manager = 
            itsContainer.getInstance( ISessionManager.class );
        ILogger logger = 
            itsContainer.getInstance( ILogger.class );
        
        logger.logInfo( "Executing remove dead sessions task." );
        manager.removeDeadSessions();
    }

}

// ##########################################################################
