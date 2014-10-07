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
        ISessionProcessor  processor)
    {
        itsContext   = context;
        itsProcessor = processor;
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
            receiver.onRegister( reply );
        }
        catch (RepositoryException e1)
        {
            receiver.onSessionException( new SessionException( e1 ) );
            
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
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
            receiver.onLogin( reply );
        }
        catch (RepositoryException e1)
        {
            receiver.onSessionException( new SessionException( e1 ) );
            
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
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
            receiver.onLogout( reply );
        }
        catch (RepositoryException e1)
        {
            receiver.onSessionException( new SessionException( e1 ) );
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
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
            // log exception
        }
    }

}

// ##########################################################################
