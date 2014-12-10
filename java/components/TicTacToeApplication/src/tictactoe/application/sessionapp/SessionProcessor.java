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
import tictactoe.service.playerservice.ChangeKind;
import tictactoe.service.playerservice.PlayerChangeEvent;
import tictactoe.service.playerservice.PlayerData;
import tictactoe.service.sessionservice.LoginReply;
import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.LogoutRequest;
import tictactoe.service.sessionservice.RegisterReply;
import tictactoe.service.sessionservice.RegisterRequest;

import strata1.common.logger.ILogger;
import strata1.common.utility.Pair;
import strata1.entity.repository.RepositoryException;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SessionProcessor 
    implements ISessionProcessor
{
    private final IUserRepository      itsUserRepository;
    private final ISessionRepository   itsSessionRepository;
    private final IPlayerRepository    itsPlayerRepository;
    private final ILogger              itsLogger;

    /************************************************************************
     * Creates a new SessionProcessor. 
     *
     */
    @Inject
    public 
    SessionProcessor(
        IUserRepository      userRepository,
        ISessionRepository   sessionRepository,
        IPlayerRepository    playerRepository,
        ILogger              logger)
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
    public Pair<RegisterReply,PlayerChangeEvent> 
    register(RegisterRequest request) 
        throws RepositoryException,Exception
    {
        String            userName = request.getUserName();
        String            password = request.getPassword();
        User              user     = null;
        Player            player   = null;
        RegisterReply     reply    = new RegisterReply(request);
        PlayerChangeEvent event = new PlayerChangeEvent();
        
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
            return Pair.create( reply,null );
        }
        
        user = new User();
        user.setUserName( userName );
        user.setPassword( password );
        user = itsUserRepository.insertUser( user );
        
        player = new Player();
        player.setUserId( user.getUserId() );
        player = itsPlayerRepository.insertPlayer( player );
        
        reply.setRegistered( true );
        
        event
            .setChange( ChangeKind.PLAYER_REGISTERED )
            .setPlayerData( toPlayerData(user,player) );
        
        return Pair.create( reply,event );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Pair<LoginReply,PlayerChangeEvent> 
    login(LoginRequest request) 
        throws RepositoryException,Exception
    {
        String            name     = request.getUserName();
        String            password = request.getPassword();
        User              user     = itsUserRepository.getUserWithName(name);
        Player            player   = itsPlayerRepository.getPlayerFor(user);
        Session           session  = new Session();
        LoginReply        reply    = new LoginReply(request);
        PlayerChangeEvent event    = new PlayerChangeEvent();
        
        itsLogger.logInfo( 
            "Processing login request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );

        if ( !(user.getPassword().equals( password )) )
        {
            itsLogger.logWarning( "Incorrect password" );
            reply.setMessage( "Incorrect password" );
            return Pair.create(reply,null);
        }
        
        itsLogger.logDebug( "Setting session userId: " + user.getUserId() );
        session.setUserId( user.getUserId() );
        itsLogger.logDebug( "Saving session: " + session.getSessionId() );
        session = itsSessionRepository.insertSession( session );

        player.setStatus( PlayerStatus.ONLINE );
        itsLogger.logDebug( 
            "Updating player: " + player.getPlayerId() + 
            " with status: " +  player.getStatus() );
        player = itsPlayerRepository.updatePlayer( player );
        
        if ( player.getStatus() != PlayerStatus.ONLINE )
            itsLogger.logError( "Failed to update player status." );
        
        reply
            .setSessionId( session.getSessionId() )
            .setUserId( session.getUserId() );

        event
            .setChange( ChangeKind.PLAYER_LOGGED_IN )
            .setPlayerData( toPlayerData(user,player) );

        return Pair.create( reply,event );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Pair<LogoutReply,PlayerChangeEvent> 
    logout(LogoutRequest request) throws RepositoryException,Exception
    {
        Long              id      = request.getSessionId();
        Session           session = itsSessionRepository.getSession(id);
        User              user    = itsUserRepository.getUserFor(session);
        Player            player  = itsPlayerRepository.getPlayerFor(user);
        LogoutReply       reply   = new LogoutReply( request );
        PlayerChangeEvent event   = new PlayerChangeEvent();

        itsLogger.logInfo( 
            "Processing logout request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );
        
        itsSessionRepository.removeSession( session );
        player.setStatus( PlayerStatus.OFFLINE );
        player = itsPlayerRepository.updatePlayer( player );
        reply.setLoggedOut( true );
        
        event
            .setChange( ChangeKind.PLAYER_LOGGED_IN )
            .setPlayerData( toPlayerData(user,player) );

        return Pair.create( reply,event );
    }

    /************************************************************************
     *  
     *
     * @param user
     * @param player
     * @return
     */
    private PlayerData
    toPlayerData(User user,Player player)
    {
        return
            new PlayerData(
                user.getUserId(),
                user.getUserName(),
                player.getStatus().name(),
                player.getWins(),
                player.getLosses(),
                player.getTies(),
                player.getCurrentRank(),
                player.getAverageRank() );        
    }
}

// ##########################################################################
