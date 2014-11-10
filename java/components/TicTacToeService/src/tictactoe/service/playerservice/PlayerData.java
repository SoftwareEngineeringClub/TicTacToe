// ##########################################################################
// # File Name:	PlayerData.java
// ##########################################################################

package tictactoe.service.playerservice;

import java.io.Serializable;
import java.math.BigDecimal;

/****************************************************************************
 * 
 */
public 
class PlayerData 
    implements Serializable
{

    private static final long serialVersionUID = 7737742703085754683L;
    
    private Long         itsUserId;
    private String       itsUserName;
    private String       itsStatus;
    private Integer      itsWins;
    private Integer      itsLosses;
    private Integer      itsTies;
    private BigDecimal   itsCurrentRank;
    private BigDecimal   itsAverageRank;

    /************************************************************************
     * Creates a new PlayerData. 
     *
     */
    public 
    PlayerData(
        Long userId,
        String userName,
        String status,
        Integer wins,
        Integer losses,
        Integer ties,
        BigDecimal currentRank,
        BigDecimal averageRank)
    {
        itsUserId = userId;
        itsUserName = userName;
        itsStatus = status;
        itsWins = wins;
        itsLosses = losses;
        itsTies = ties;
        itsCurrentRank = currentRank;
        itsAverageRank = averageRank;
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
    public String
    getUserName()
    {
        return itsUserName;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public String
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
    getGamesPlayed()
    {
        return itsWins + itsLosses + itsTies;
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
}

// ##########################################################################
