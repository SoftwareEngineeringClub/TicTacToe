// ##########################################################################
// # File Name:	SessionServiceImp.java
// ##########################################################################

package tictactoe.application.sessionapp;

import tictactoe.service.playerservice.ChangeKind;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerNotifierHost;
import tictactoe.service.playerservice.PlayerChangeEvent;
import tictactoe.service.playerservice.PlayerData;
import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.LoginReply;
import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.LogoutRequest;
import tictactoe.service.sessionservice.RegisterReply;
import tictactoe.service.sessionservice.RegisterRequest;
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
class SessionServiceImp 
    implements ISessionService,IPlayerNotifierHost
{
    private final IRepositoryContext itsContext;
    private final ISessionProcessor  itsProcessor;
    private final ILogger            itsLogger;
    private IPlayerEventListener     itsPlayerNotifier;
    
    /************************************************************************
     * Creates a new SessionServiceImp. 
     *
     * @param context
     * @param repository
     */
    @Inject
    public 
    SessionServiceImp(
        IRepositoryContext context,
        ISessionProcessor  processor,
        ILogger            logger)
    {
        itsContext        = context;
        itsProcessor      = processor;
        itsLogger         = logger;
        itsPlayerNotifier = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     * @throws RollbackFailedException 
     */
    @Override
    public void 
    register(ISessionReplyReceiver receiver,RegisterRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            Pair<RegisterReply,PlayerChangeEvent> pair = 
                itsProcessor.register( request );
            
            itsLogger.logDebug( "Committing register request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed register request." );
            receiver.onRegister( pair.getFirst() );
            
            if ( pair.getSecond() != null )
                itsPlayerNotifier.onPlayerChange( pair.getSecond() );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( getStackTrace(e1) );
            receiver.onSessionException( 
                new SessionException( getStackTrace(e1) ) );           
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            itsLogger.logError( getStackTrace(t) );
            receiver.onThrowable( new Exception(getStackTrace(t)) );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    login(ISessionReplyReceiver receiver,LoginRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            Pair<LoginReply,PlayerChangeEvent> pair = 
                itsProcessor.login( request );
            
            itsLogger.logDebug( "Committing login request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed login request." );
            receiver.onLogin( pair.getFirst() );
            itsPlayerNotifier.onPlayerChange( pair.getSecond() );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( getStackTrace(e1) );
            receiver.onSessionException( 
                new SessionException( getStackTrace(e1) ) );            
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            itsLogger.logError( getStackTrace(t) );
            receiver.onThrowable( new Exception(getStackTrace(t)) );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    logout(ISessionReplyReceiver receiver,LogoutRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            Pair<LogoutReply,PlayerChangeEvent> pair = 
                itsProcessor.logout( request );
            
            itsLogger.logDebug( "Committing logout request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed logout request." );
            receiver.onLogout( pair.getFirst() );
            itsPlayerNotifier.onPlayerChange( pair.getSecond() );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( getStackTrace(e1) );
            receiver.onSessionException( 
                new SessionException( getStackTrace(e1) ) );
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            itsLogger.logError(getStackTrace(t));
            receiver.onThrowable( new Exception(getStackTrace(t)) );
            rollback( unitOfWork );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    setPlayerNotifier(IPlayerEventListener notifier)
    {
        itsPlayerNotifier = notifier;
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
