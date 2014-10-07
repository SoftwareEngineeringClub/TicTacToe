// ##########################################################################
// # File Name:	Game.java
// ##########################################################################

package tictactoe.domain.gamedomain;

import strata1.common.datetime.DateTime;

/****************************************************************************
 * 
 */
public 
class Game
{
    public static final char OPEN_MARKER = '-';
    public static final char X_MARKER    = 'X';
    public static final char O_MARKER    = 'O';
    
    private Long     itsGameId;
    private Integer  itsVersion;
    private Long     itsPlayerIdX;
    private Long     itsPlayerIdO;
    private Long     itsCurrentPlayerId;
    private DateTime itsStart;
    private DateTime itsFinish;
    private Integer  itsDimension;
    private String   itsState;
    
    /************************************************************************
     * Creates a new Game. 
     *
     */
    public 
    Game()
    {
        itsGameId = 0L;
        itsVersion = 0;
        itsPlayerIdX = 0L;
        itsPlayerIdO = 0L;
        itsCurrentPlayerId = 0L;
        itsStart = new DateTime();
        itsFinish = null;
        itsDimension = 3;
        itsState = initializeState();
    }

    public 
    Game(Game other)
    {
        itsGameId = other.getGameId();
        itsVersion = other.getVersion();
        itsPlayerIdX = other.getPlayerIdX();
        itsPlayerIdO = other.getPlayerIdO();
        itsCurrentPlayerId = other.getCurrentPlayerId();
        itsStart = other.getStart();
        itsFinish = other.getFinish();
        itsDimension = other.getDimension();
        itsState = other.getState();
    }

    /************************************************************************
     *  
     *
     * @param gameId
     */
    public void
    setGameId(Long gameId)
    {
        itsGameId = gameId;
    }
    
    /************************************************************************
     *  
     *
     * @param version
     */
    public void
    setVersion(Integer version)
    {
        itsVersion = version;
    }
    
    /************************************************************************
     *  
     *
     * @param playerIdX
     */
    public void
    setPlayerIdX(Long playerIdX)
    {
        itsPlayerIdX = playerIdX;
    }
    
    /************************************************************************
     *  
     *
     * @param playerIdO
     */
    public void
    setPlayerIdO(Long playerIdO)
    {
        itsPlayerIdO = playerIdO;
    }
    
    /************************************************************************
     *  
     *
     * @param currentPlayerId
     */
    public void
    setCurrentPlayerId(Long currentPlayerId)
    {
        itsCurrentPlayerId = currentPlayerId;
    }
    
    /************************************************************************
     *  
     *
     * @param start
     */
    public void
    setStart(DateTime start)
    {
        itsStart = start;
    }
    
    /************************************************************************
     *  
     *
     * @param finish
     */
    public void
    setFinish(DateTime finish)
    {
        itsFinish = finish;
    }
    
    /************************************************************************
     *  
     *
     * @param dimension
     */
    public void
    setDimension(Integer dimension)
    {
        itsDimension = dimension;
    }
    
    /************************************************************************
     *  
     *
     * @param state
     */
    public void
    setState(String state)
    {
        itsState = state;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getGameId()
    {
        return itsGameId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Integer
    getVersion()
    {
        return itsVersion;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getPlayerIdX()
    {
        return itsPlayerIdX;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getPlayerIdO()
    {
        return itsPlayerIdO;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getCurrentPlayerId()
    {
        return itsCurrentPlayerId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public DateTime
    getStart()
    {
        return itsStart;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public DateTime
    getFinish()
    {
        return itsFinish;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Integer
    getDimension()
    {
        return itsDimension;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public String
    getState()
    {
        return itsState;
    }
    
    /************************************************************************
     *  
     *
     * @param move
     * @throws IllegalMoveException
     */
    public void
    makeMove(Move move)
        throws IllegalMoveException
    {
        char[] state = null;
        
        checkPlayer( move );
        checkRange( move );
        checkOpen( move );
        checkMarker( move );
        
        state = getState().toCharArray();
        state[getIndex(move)] = move.getMarker();
        setState( new String(state) );
        swapCurrentPlayer();
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String
    toString()
    {
        StringBuilder builder = new StringBuilder();
        
        for (int row = 0;row < getDimension();row++)
        {
            for (int column = 0;column < getDimension();column++)
            {
                int  index = row*getDimension() + column;
                char cell = getState().charAt( index );
                
                builder.append( cell );
            }
            
            builder.append( '\n' );
        }
        
        return builder.toString();
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected String
    initializeState()
    {
        StringBuilder builder = new StringBuilder();
        
        for (int i=0;i<itsDimension*itsDimension;i++)
            builder.append( OPEN_MARKER );
        
        return builder.toString();
    }
    
    /************************************************************************
     *  
     *
     * @param move
     * @throws IllegalMoveException
     */
    protected void
    checkPlayer(Move move)
        throws IllegalMoveException
    {
        if ( move.getPlayerId() != getCurrentPlayerId() )
            throw 
                new IllegalMoveException( 
                    "Not player's: " + 
                    move.getPlayerId() + 
                    " turn to move." );        
    }

    /************************************************************************
     *  
     *
     * @param move
     * @throws IllegalMoveException
     */
    protected void
    checkRange(Move move)
        throws IllegalMoveException
    {
        int row    = move.getRow();
        int column = move.getColumn();
        
        if ( 
            (row < 0 || row >= getDimension()) || 
            (column < 0 || column >= getDimension()) )
            throw 
                new IllegalMoveException( 
                    "Move [" + row + "," + column + "] is out of range." );        
    }

    /************************************************************************
     *  
     *
     * @param move
     * @throws IllegalMoveException
     */
    protected void
    checkOpen(Move move)
        throws IllegalMoveException
    {
        char cell   = getState().charAt( getIndex(move) );
        int  row    = move.getRow();
        int  column = move.getColumn();
        
        if ( cell != OPEN_MARKER )
            throw 
                new IllegalMoveException( 
                    "Move [" + row + "," + column + "] is unavailable." );        
    }

    /************************************************************************
     *  
     *
     * @param move
     * @throws IllegalMoveException
     */
    protected void
    checkMarker(Move move)
        throws IllegalMoveException
    {
        switch ( move.getMarker() )
        {
        case X_MARKER:
        case O_MARKER:
            break;
            
        default:
            throw 
                new IllegalMoveException( 
                    "Marker " + move.getMarker() + " is illegal." );                   
        }
    }
    
    /************************************************************************
     *  
     *
     * @param move
     * @return
     */
    protected int
    getIndex(Move move)
    {
        int dimension = getDimension();
        
        return move.getRow()*dimension + move.getColumn();
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    swapCurrentPlayer()
    {
        if ( getCurrentPlayerId() == itsPlayerIdX )
            setCurrentPlayerId( itsPlayerIdO );
        else 
            setCurrentPlayerId( itsPlayerIdX );
             
    }
}

// ##########################################################################
