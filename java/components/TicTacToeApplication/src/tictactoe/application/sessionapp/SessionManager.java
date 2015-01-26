// ##########################################################################
// # File Name:	SessionManager.java
// ##########################################################################

package tictactoe.application.sessionapp;

import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.PlayerChangeEvent;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.SessionException;

import strata1.common.logger.ILogger;
import strata1.common.utility.Pair;
import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.entity.repository.RepositoryException;
import strata1.entity.repository.RollbackFailedException;

import javax.inject.Inject;

import java.io.PrintWriter;
import java.io.StringWriter;

/****************************************************************************
 * 
 */
public 
class SessionManager 
    implements ISessionManager
{
    private final IRepositoryContext   itsContext;
    private final ISessionProcessor    itsProcessor;
    private final IPlayerEventListener itsPlayerNotifier;
    private final ILogger              itsLogger;

    /************************************************************************
     * Creates a new SessionManager. 
     *
     */
    @Inject
    public 
    SessionManager(
        IRepositoryContext   context,
        ISessionProcessor    processor,
        IPlayerEventListener notifier,
        ILogger              logger)
    {
        itsContext        = context;
        itsProcessor      = processor;
        itsPlayerNotifier = notifier;
        itsLogger         = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    removeDeadSessions()
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            PlayerChangeEvent event = itsProcessor.removeDeadSessions();
            
            itsLogger.logDebug( "Committing remove dead sessions task." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed remove dead sessions task." );
            
            if ( event != null )
                itsPlayerNotifier.onPlayerChange( event );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( getStackTrace(e1) );
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            itsLogger.logError(getStackTrace(t));
            rollback( unitOfWork );
        }
    }
    
    /************************************************************************
     *  
     *
     * @param unitOfWork
     */
    private void 
    rollback(IUnitOfWork unitOfWork)
    {
        try
        {
            unitOfWork.rollback();
        }
        catch (RollbackFailedException e2)
        {
            itsLogger.logError( e2.getMessage() );
        }
    }

    /************************************************************************
     *  
     *
     * @param t
     * @return
     */
    private String 
    getStackTrace(Throwable t)
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter  printWriter  = new PrintWriter(stringWriter);
        
        t.printStackTrace( printWriter );
        return stringWriter.toString();
    }

}

// ##########################################################################
