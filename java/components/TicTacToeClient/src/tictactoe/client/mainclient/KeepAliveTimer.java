// ##########################################################################
// # File Name:	KeepAliveTimer.java
// ##########################################################################

package tictactoe.client.mainclient;

import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.KeepAliveRequest;

import java.util.Timer;
import java.util.TimerTask;

/****************************************************************************
 * 
 */
public 
class KeepAliveTimer
{
    private static final long ONE_MINUTE = 60*1000;
    
    private final ISessionService itsService;
    private long                  itsSessionId;
    private Timer                 itsTimer;
    
    /************************************************************************
     * Creates a new KeepAliveTimer. 
     *
     */
    public 
    KeepAliveTimer(ISessionService service,long sessionId)
    {
        itsService   = service;
        itsSessionId = sessionId;
        itsTimer     = null;
    }
    
    public void
    start()
    {
        if ( isStarted() )
            return;
        
        itsTimer = new Timer();
        itsTimer.scheduleAtFixedRate( 
            new TimerTask() 
            {
                @Override
                public void 
                run()
                {
                    KeepAliveRequest request = new KeepAliveRequest();
                    
                    request.setSessionId( itsSessionId );
                    itsService.keepAlive( request );
                }     
            },
            ONE_MINUTE,
            ONE_MINUTE );
    }
    
    public void
    stop()
    {
        if ( !isStarted() )
            return;
        
        itsTimer.cancel();
        itsTimer = null;
    }
    
    public boolean
    isStarted()
    {
        return itsTimer != null;
    }

}

// ##########################################################################
