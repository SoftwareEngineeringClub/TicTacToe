// ##########################################################################
// # File Name:	AnyTaskSelectorTest.java
// ##########################################################################

package tictactoe.application.taskdispatcher;

import static org.junit.Assert.*;

import tictactoe.integration.serviceinvoker.LoginTask;
import tictactoe.integration.serviceinvoker.LogoutTask;
import tictactoe.integration.serviceinvoker.RegisterTask;

import strata1.common.task.ITaskSelector;
import strata1.common.utility.IMultiMap;
import strata1.common.utility.MultiMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/****************************************************************************
 * 
 */
public 
class AnyTaskSelectorTest
{
    private AnyTaskSelector itsTarget;
    
    /************************************************************************
     *  
     *
     * @throws Exception
     */
    @Before
    public void 
    setUp() throws Exception
    {
        itsTarget = new AnyTaskSelector();
    }

    /************************************************************************
     *  
     *
     * @throws Exception
     */
    @After
    public void 
    tearDown() throws Exception
    {
        itsTarget = null;
    }

    /**
     * Test method for {@link AnyTaskSelector#match(ITask)}.
     */
    @Test
    public void 
    testMatch()
    {
        assertTrue( itsTarget.match( new RegisterTask(null,null,null) ));
        assertTrue( itsTarget.match( new LoginTask(null,null,null) ));
        assertTrue( itsTarget.match( new LogoutTask(null,null,null) ));
    }

    /**
     * Test method for {@link AnyTaskSelector#equals(Object)}.
     */
    @Test
    public void 
    testEqualsObject()
    {
        IMultiMap<ITaskSelector,String> map = new MultiMap<ITaskSelector,String>();
        
        assertEquals( itsTarget,new AnyTaskSelector() );
        assertEquals( new AnyTaskSelector(),new AnyTaskSelector() );
        
        map.put( itsTarget,"Consumer1" );
        map.put( itsTarget,"Consumer2" );
        map.put(  new AnyTaskSelector(),"Consumer3" );
        
        assertEquals( 3,map.getCardinality( itsTarget ));
        assertEquals( 3,map.getCardinality( new AnyTaskSelector() ));
        
        assertEquals( "Consumer1",map.get( itsTarget,0 ) );
        assertEquals( "Consumer2",map.get( itsTarget,1 ) );
        assertEquals( "Consumer3",map.get( itsTarget,2 ) );
        
        assertEquals( "Consumer1",map.get( new AnyTaskSelector(),0 ) );
        assertEquals( "Consumer2",map.get( new AnyTaskSelector(),1 ) );
        assertEquals( "Consumer3",map.get( new AnyTaskSelector(),2 ) );
    }

}

// ##########################################################################
