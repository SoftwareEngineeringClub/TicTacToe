// ##########################################################################
// # File Name:	PlayerTest.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/****************************************************************************
 * 
 */
public 
class PlayerTest
{
    private Player itsTarget;
    private BigDecimal zero = new BigDecimal( "0.00" );
    
    /************************************************************************
     *  
     *
     * @throws java.lang.Exception
     */
    @Before
    public void 
    setUp() throws Exception
    {
        itsTarget = new Player();
    }

    /************************************************************************
     *  
     *
     * @throws java.lang.Exception
     */
    @After
    public void 
    tearDown() throws Exception
    {
        itsTarget = null;
    }

    /**
     * Test method for {@link Player#getCurrentRank()}.
     */
    @Test
    public void 
    testGetCurrentRank()
    {
        assertEquals( zero,itsTarget.getCurrentRank() );
        itsTarget.incrementLosses();
        assertEquals( zero,itsTarget.getCurrentRank() );
        itsTarget.incrementTies();
        assertEquals( new BigDecimal( "1.00" ),itsTarget.getCurrentRank() );
        itsTarget.incrementWins();
        assertEquals( new BigDecimal( "3.00"),itsTarget.getCurrentRank() );
    }

    /**
     * Test method for {@link Player#getAverageRank()}.
     */
    @Test
    public void 
    testGetAverageRank()
    {
        assertEquals( zero,itsTarget.getAverageRank() );
        itsTarget.incrementLosses();
        assertEquals( zero,itsTarget.getAverageRank() );
        itsTarget.incrementTies();
        assertEquals( new BigDecimal( "0.50" ),itsTarget.getAverageRank() );
        itsTarget.incrementWins();
        assertEquals( new BigDecimal( "1.00"),itsTarget.getAverageRank() );
    }

}

// ##########################################################################
