// ##########################################################################
// # File Name:	FakeSessionModel.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.model.AbstractModel;
import strata1.common.authentication.ICredential;

import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public 
class FakeSessionModel 
    extends    AbstractModel 
    implements ISessionModel
{
    private Long    itsSessionId;
    private Long    itsUserId;
    private String  itsLoginError;
    private String  itsRegisterError;
    private boolean itsLoggedInFlag;
    private boolean itsRegisteredFlag;
    private Random  itsGenerator;
    
    /************************************************************************
     * Creates a new FakeSessionModel. 
     *
     */
    public 
    FakeSessionModel()
    {
        itsGenerator = new SecureRandom();
        
        itsSessionId = itsGenerator.nextLong();
        itsUserId    = itsGenerator.nextLong();
        itsLoginError = null;
        itsRegisterError = null;
        itsLoggedInFlag = false;
        itsRegisteredFlag = false;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    login(ICredential credential)
    {
        System.out.println( "login(credential)");
        itsLoggedInFlag = true;
        notifyChange(new LoginEvent(this));
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    register(ICredential credential)
    {
        System.out.println( "register(credential)");
        itsRegisteredFlag = true;
        notifyChange(new RegisterEvent(this));
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getSessionId()
    {
        return itsSessionId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getUserId()
    {
        return itsUserId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    getLoginError()
    {
        return itsLoginError;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    getRegisterError()
    {
        return itsRegisterError;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    isLoggedIn()
    {
        return itsLoggedInFlag;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    isRegistered()
    {
        return itsRegisteredFlag;
    }

    @Override
    public void logout()
    {
    }

}

// ##########################################################################
