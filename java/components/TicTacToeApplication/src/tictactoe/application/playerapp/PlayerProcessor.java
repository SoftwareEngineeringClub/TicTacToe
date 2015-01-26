// ##########################################################################
// # File Name:	PlayerProcessor.java
// ##########################################################################

package tictactoe.application.playerapp;

import tictactoe.domain.gamedomain.Game;
import tictactoe.domain.gamedomain.IGameRepository;
import tictactoe.domain.playerdomain.Challenge;
import tictactoe.domain.playerdomain.ChallengeStatus;
import tictactoe.domain.playerdomain.IChallengeRepository;
import tictactoe.domain.playerdomain.IPlayerRepository;
import tictactoe.domain.playerdomain.Player;
import tictactoe.domain.sessiondomain.ISessionRepository;
import tictactoe.domain.sessiondomain.IUserRepository;
import tictactoe.domain.sessiondomain.Session;
import tictactoe.domain.sessiondomain.User;
import tictactoe.service.playerservice.AcceptChallengeReply;
import tictactoe.service.playerservice.AcceptChallengeRequest;
import tictactoe.service.playerservice.ChallengeEvent;
import tictactoe.service.playerservice.DeclineChallengeReply;
import tictactoe.service.playerservice.DeclineChallengeRequest;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IssueChallengeReply;
import tictactoe.service.playerservice.IssueChallengeRequest;
import tictactoe.service.playerservice.PlayerData;
import tictactoe.service.playerservice.StartListeningReply;
import tictactoe.service.playerservice.StartListeningRequest;

import strata1.common.logger.ILogger;
import strata1.common.utility.Pair;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.UpdateFailedException;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class PlayerProcessor 
    implements IPlayerProcessor
{
    private final ISessionRepository   itsSessionRepository;
    private final IUserRepository      itsUserRepository;
    private final IPlayerRepository    itsPlayerRepository;
    private final IChallengeRepository itsChallengeRepository;
    private final IGameRepository      itsGameRepository;
    private final ILogger              itsLogger;

    /************************************************************************
     * Creates a new PlayerProcessor. 
     *
     */
    @Inject
    public 
    PlayerProcessor(
        ISessionRepository   sessionRepository,
        IUserRepository      userRepository,
        IPlayerRepository    playerRepository,
        IChallengeRepository challengeRepository,
        IGameRepository      gameRepository,
        ILogger              logger)
    {
        itsSessionRepository   = sessionRepository;
        itsUserRepository      = userRepository;
        itsPlayerRepository    = playerRepository;
        itsChallengeRepository = challengeRepository;
        itsGameRepository      = gameRepository;
        itsLogger              = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public GetPlayersReply 
    getPlayers(GetPlayersRequest request) 
        throws NotUniqueException,InvalidInputException
    {
        GetPlayersReply reply = new GetPlayersReply( request );
        
        itsLogger.logInfo( 
            "Processing get players request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );
        
        if ( request.isSinglePlayer() )
            return getPlayer( reply,request );
        else
            return getPlayers( reply,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ChallengeEvent 
    issueChallenge(IssueChallengeRequest request)
        throws 
            InsertFailedException, 
            InvalidInputException, 
            NotUniqueException
    {
        Challenge      challenge = new Challenge();
        ChallengeEvent event     = new ChallengeEvent(request);
        User           user      = null;
        Session        session   = null;
        
        itsLogger.logInfo( 
            "Processing issue challenge request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );

        session = itsSessionRepository.getSession( request.getSessionId() );
        
        challenge.setOriginatingRequestId( request.getRequestId() );
        challenge.setChallengerUserId( request.getUserId() );
        challenge.setChallengedUserId( request.getChallengedUserId() );
        challenge.setSessionKey( "" );
        challenge.setReplyChannelId( "" );
        challenge.setReturnAddress( session.getPlayerReturnAddress() );
        challenge.setCorrelationId( 
            new Long(request.getRequestId()).toString() );
        challenge = itsChallengeRepository.insertChallenge( challenge );

        user    = itsUserRepository.getUser( request.getChallengedUserId() );
        session = itsSessionRepository.getSessionFor( user );
        
        event.setChallengeId( challenge.getChallengeId() );
        event.setReturnAddress( session.getPlayerReturnAddress() );
        
        return event;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Pair<IssueChallengeReply,AcceptChallengeReply> 
    acceptChallenge(AcceptChallengeRequest request)
        throws InsertFailedException,UpdateFailedException
    {
        Challenge            challenge   = null;
        Game                 game        = null;
        IssueChallengeReply  issueReply  = null;
        AcceptChallengeReply acceptReply = null;
        
        issueReply  = new IssueChallengeReply( request );
        acceptReply = new AcceptChallengeReply( request ); 
        
        itsLogger.logInfo( 
            "Processing accept challenge request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );

        challenge = 
            itsChallengeRepository.getChallenge( request.getChallengeId() );
        challenge.setStatus( ChallengeStatus.ACCEPTED );
        challenge = 
            itsChallengeRepository.updateChallenge( challenge );
        
        game = new Game();
        game.setPlayerIdX( challenge.getChallengedUserId() );
        game.setPlayerIdO( challenge.getChallengerUserId() );
        game.setCurrentPlayerId( challenge.getChallengedUserId() );
        game = itsGameRepository.insertGame( game );
        
        issueReply
            .setReturnAddress( challenge.getReturnAddress() )
            .setGameId( game.getGameId() );
        
        acceptReply.setGameId( game.getGameId() );
        return Pair.create( issueReply,acceptReply );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Pair<IssueChallengeReply,DeclineChallengeReply> 
    declineChallenge(DeclineChallengeRequest request)
        throws UpdateFailedException
    {
        Challenge             challenge   = null;
        IssueChallengeReply   issueReply  = null;
        DeclineChallengeReply declineReply = null;
        
        issueReply  = new IssueChallengeReply( request );
        declineReply = new DeclineChallengeReply( request ); 
        
        itsLogger.logInfo( 
            "Processing decline challenge request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );

        challenge = 
            itsChallengeRepository.getChallenge( request.getChallengeId() );
        challenge.setStatus( ChallengeStatus.DECLINED );
        challenge = 
            itsChallengeRepository.updateChallenge( challenge );
        
        issueReply.setReturnAddress( challenge.getReturnAddress() );
        
        return Pair.create( issueReply,declineReply );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public StartListeningReply 
    startListening(StartListeningRequest request)
        throws InsertFailedException,UpdateFailedException
    {
        Session             session = null;
        StartListeningReply reply   = new StartListeningReply( request );
        
        itsLogger.logInfo( 
            "Processing start listening request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );
        
        session = itsSessionRepository.getSession( request.getSessionId() );
        session.setPlayerReturnAddress( request.getReturnAddress() );
        session = itsSessionRepository.updateSession( session );
        reply.setListening( true );
        
        return reply;
    }

    /************************************************************************
     *  
     *
     * @param reply
     * @param request
     * @return
     * @throws NotUniqueException
     * @throws InvalidInputException
     */
    private GetPlayersReply
    getPlayer(GetPlayersReply reply,GetPlayersRequest request)
        throws NotUniqueException,InvalidInputException
    {
        User   user   = null;
        Player player = null;
        
        itsLogger.logDebug( "Getting single player." );
        user   = itsUserRepository.getUser(request.getUserId());
        player = itsPlayerRepository.getPlayerFor( user );
        
        itsLogger.logDebug( "Returning single player." );
        reply.insertPlayerData( 
            new PlayerData(
                user.getUserId(),
                user.getUserName(),
                player.getStatus().name(),
                player.getWins(),
                player.getLosses(),
                player.getTies(),
                player.getCurrentRank(),
                player.getAverageRank() ) );           
        
        return reply;
    }
    
    /************************************************************************
     *  
     *
     * @param reply
     * @param request
     * @return
     * @throws NotUniqueException
     * @throws InvalidInputException
     */
    private GetPlayersReply
    getPlayers(GetPlayersReply reply,GetPlayersRequest request)
        throws NotUniqueException,InvalidInputException
    {
        itsLogger.logDebug( "Getting multiple players." );
        
        for (User user : itsUserRepository.getUsers())
        {
            Player player = itsPlayerRepository.getPlayerFor( user );
            
            reply.insertPlayerData( 
                new PlayerData(
                    user.getUserId(),
                    user.getUserName(),
                    player.getStatus().name(),
                    player.getWins(),
                    player.getLosses(),
                    player.getTies(),
                    player.getCurrentRank(),
                    player.getAverageRank() ) );                           
        }            

        itsLogger.logDebug( "Returning " +  reply.getSize() +" players." );
        return reply;
    }
}

// ##########################################################################
