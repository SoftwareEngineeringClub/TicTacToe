// ##########################################################################
// # File Name:	Player.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/****************************************************************************
 * 
 */
public 
class Player
{
    private Long         itsPlayerId;
    private Long         itsUserId;
    private PlayerStatus itsStatus;
    private Integer      itsWins;
    private Integer      itsLosses;
    private Integer      itsTies;
    private BigDecimal   itsCurrentRank;
    private BigDecimal   itsAverageRank;
    private Integer      itsVersion;
    
    /************************************************************************
     * Creates a new Player. 
     *
     */
    public 
    Player()
    {
        itsPlayerId    = 0L;
        itsUserId      = 0L;
        itsStatus      = PlayerStatus.OFFLINE;
        itsWins        = 0;
        itsLosses      = 0;
        itsTies        = 0;
        itsCurrentRank = new BigDecimal( "0.00" );
        itsAverageRank = new BigDecimal( "0.00" );
        itsVersion     = 0;
    }
    
    /************************************************************************
     * Creates a new Player. 
     *
     * @param player
     */
    public 
    Player(Player player)
    {
        itsPlayerId    = player.itsPlayerId;
        itsUserId      = player.itsUserId;
        itsStatus      = player.itsStatus;
        itsWins        = player.itsWins;
        itsLosses      = player.itsLosses;
        itsTies        = player.itsTies;
        itsCurrentRank = player.itsCurrentRank;
        itsAverageRank = player.itsAverageRank;
        itsVersion     = 0;
    }

    /************************************************************************
     *  
     *
     * @param playerId
     */
    public void
    setPlayerId(Long playerId)
    {
        itsPlayerId = playerId;
    }
    
    /************************************************************************
     *  
     *
     * @param userId
     */
    public void
    setUserId(Long userId)
    {
        itsUserId = userId;
    }
    
    /************************************************************************
     *  
     *
     * @param status
     */
    public void
    setStatus(PlayerStatus status)
    {
        itsStatus = status;
    }

    /************************************************************************
     *  
     *
     * @param wins
     */
    public void
    setWins(Integer wins)
    {
        itsWins = wins;
    }
    
    /************************************************************************
     *  
     *
     * @param losses
     */
    public void
    setLosses(Integer losses)
    {
        itsLosses = losses;
    }
    
    /************************************************************************
     *  
     *
     * @param ties
     */
    public void
    setTies(Integer ties)
    {
        itsTies = ties;
    }
    
    /************************************************************************
     *  
     *
     * @param currentRank
     */
    public void
    setCurrentRank(BigDecimal currentRank)
    {
        itsCurrentRank = currentRank;
    }
    
    /************************************************************************
     *  
     *
     * @param averageRank
     */
    public void
    setAverageRank(BigDecimal averageRank)
    {
        itsAverageRank = averageRank;
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
     * @return
     */
    public Long
    getPlayerId()
    {
        return itsPlayerId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getUserId()
    {
        return itsUserId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public PlayerStatus
    getStatus()
    {
        return itsStatus;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Integer
    getWins()
    {
        return itsWins;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Integer
    getLosses()
    {
        return itsLosses;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Integer
    getTies()
    {
        return itsTies;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public BigDecimal
    getCurrentRank()
    {
        return itsCurrentRank;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public BigDecimal
    getAverageRank()
    {
        return itsAverageRank;
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
     */
    public void
    incrementWins()
    {
        setWins( getWins() + 1);
        setCurrentRank( calculateCurrentRank() );
        setAverageRank( calculateAverageRank() );
    }
    
    /************************************************************************
     *  
     *
     */
    public void
    incrementLosses()
    {
        setLosses( getLosses() + 1 );
        setCurrentRank( calculateCurrentRank() );
        setAverageRank( calculateAverageRank() );
    }
      
    /************************************************************************
     *  
     *
     */
    public void
    incrementTies()
    {
        setTies( getTies() + 1 );
        setCurrentRank( calculateCurrentRank() );
        setAverageRank( calculateAverageRank() );
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected BigDecimal
    calculateCurrentRank()
    {
        double currentRank = 
            2*getWins() +
            1*getTies();
        
        return 
            new BigDecimal(currentRank)
                .setScale( 2,BigDecimal.ROUND_HALF_UP );
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected BigDecimal
    calculateAverageRank()
    {
        double total       = getWins() + getTies() + getLosses();
        double averageRank = 
            total != 0.0 
                ? getCurrentRank().doubleValue() / total 
                : 0.0;
        
        return 
            new BigDecimal(averageRank)
                .setScale( 2,BigDecimal.ROUND_HALF_UP );
    }
}

// ##########################################################################
