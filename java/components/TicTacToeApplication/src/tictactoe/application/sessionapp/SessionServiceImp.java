// ##########################################################################
// # File Name:	SessionServiceImp.java
// ##########################################################################

package tictactoe.application.sessionapp;

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
import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.entity.repository.RepositoryException;
import strata1.entity.repository.RollbackFailedException;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SessionServiceImp 
    implements ISessionService
{
    private final IRepositoryContext itsContext;
    private final ISessionProcessor  itsProcessor;
    private final ILogger            itsLogger;
    
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
        itsContext   = context;
        itsProcessor = processor;
        itsLogger    = logger;
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
            RegisterReply reply = itsProcessor.register( request );
            
            unitOfWork.commit();
            itsLogger.logDebug( "Committed register request." );
            receiver.onRegister( reply );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( e1.getMessage() );
            receiver.onSessionException( new SessionException( e1 ) );           
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            itsLogger.logError( t.getMessage() );
            receiver.onThrowable( t );
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
            LoginReply reply = itsProcessor.login( request );
            
            unitOfWork.commit();
            itsLogger.logDebug( "Committed login request." );
            receiver.onLogin( reply );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( e1.getMessage() );
            receiver.onSessionException( new SessionException( e1 ) );            
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            itsLogger.logError( t.getMessage() );
            receiver.onThrowable( t );
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
            LogoutReply reply = itsProcessor.logout( request );
            
            unitOfWork.commit();
            itsLogger.logDebug( "Committed logout request." );
            receiver.onLogout( reply );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( e1.getMessage() );
            receiver.onSessionException( new SessionException( e1 ) );
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            itsLogger.logError( t.getMessage() );
            receiver.onThrowable( t );
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

}

// ##########################################################################
