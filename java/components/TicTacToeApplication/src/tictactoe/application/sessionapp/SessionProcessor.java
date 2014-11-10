// ##########################################################################
// # File Name:	SessionProcessor.java
// ##########################################################################

package tictactoe.application.sessionapp;

import tictactoe.domain.playerdomain.IPlayerRepository;
import tictactoe.domain.playerdomain.Player;
import tictactoe.domain.playerdomain.PlayerStatus;
import tictactoe.domain.sessiondomain.ISessionRepository;
import tictactoe.domain.sessiondomain.IUserRepository;
import tictactoe.domain.sessiondomain.Session;
import tictactoe.domain.sessiondomain.User;
import tictactoe.service.sessionservice.LoginReply;
import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.LogoutRequest;
import tictactoe.service.sessionservice.RegisterReply;
import tictactoe.service.sessionservice.RegisterRequest;

import strata1.common.logger.ILogger;
import strata1.entity.repository.RepositoryException;

import javax.inject.Inject;

import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public 
class SessionProcessor 
    implements ISessionProcessor
{
    private final IUserRepository    itsUserRepository;
    private final ISessionRepository itsSessionRepository;
    private final IPlayerRepository  itsPlayerRepository;
    private final ILogger            itsLogger;

    /************************************************************************
     * Creates a new SessionProcessor. 
     *
     */
    @Inject
    public 
    SessionProcessor(
        IUserRepository    userRepository,
        ISessionRepository sessionRepository,
        IPlayerRepository  playerRepository,
        ILogger            logger)
    {
        itsUserRepository    = userRepository;
        itsSessionRepository = sessionRepository;
        itsPlayerRepository  = playerRepository;
        itsLogger            = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public RegisterReply 
    register(RegisterRequest request) 
        throws RepositoryException,Exception
    {
        String        userName = request.getUserName();
        String        password = request.getPassword();
        User          user     = null;
        Player        player   = null;
        RegisterReply reply    = new RegisterReply(request);
        
        itsLogger.logInfo( 
            "Processing register request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );
        
        if ( itsUserRepository.hasUserWithName(userName) )
        {
            reply
                .setRegistered( false )
                .setMessage( 
                    "UserName: " + userName + 
                    " is currently taken.\n" +
                    "Please choose another UserName." );
            return reply;
        }
        
        user = new User();
        user.setUserName( userName );
        user.setPassword( password );
        user = itsUserRepository.insertUser( user );
        
        player = new Player();
        player.setUserId( user.getUserId() );
        player = itsPlayerRepository.insertPlayer( player );
        
        reply.setRegistered( true );
        return reply;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public LoginReply 
    login(LoginRequest request) 
        throws RepositoryException,Exception
    {
        String     userName = request.getUserName();
        String     password = request.getPassword();
        User       user     = itsUserRepository.getUserWithName(userName);
        Player     player   = itsPlayerRepository.getPlayerFor( user );
        Session    session  = new Session();
        LoginReply reply    = new LoginReply(request);
      
        itsLogger.logInfo( 
            "Processing login request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );

        if ( !(user.getPassword().equals( password )) )
        {
            reply.setMessage( "Incorrect password" );
            return reply;
        }
        
        session.setUserId( user.getUserId() );
        session = itsSessionRepository.insertSession( session );
        player.setStatus( PlayerStatus.ONLINE );
        player = itsPlayerRepository.updatePlayer( player );
        
        reply
            .setSessionId( session.getSessionId() )
            .setUserId( session.getUserId() );
        
        return reply;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public LogoutReply 
    logout(LogoutRequest request) throws RepositoryException,Exception
    {
        Long        sessionId = request.getSessionId();
        Session     session   = itsSessionRepository.getSession(sessionId);
        User        user      = itsUserRepository.getUserFor(session);
        Player      player    = itsPlayerRepository.getPlayerFor(user);
        LogoutReply reply     = new LogoutReply( request );

        itsLogger.logInfo( 
            "Processing logout request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );
        
        itsSessionRepository.removeSession( session );
        player.setStatus( PlayerStatus.OFFLINE );
        player = itsPlayerRepository.updatePlayer( player );
        reply.setLoggedOut( true );
        
        return reply;
    }

}

// ##########################################################################
