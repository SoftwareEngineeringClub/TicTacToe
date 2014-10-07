// ##########################################################################
// # File Name:	ISessionProcessor.java
// ##########################################################################

package tictactoe.application.sessionapp;

import tictactoe.service.sessionservice.LoginReply;
import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.LogoutRequest;
import tictactoe.service.sessionservice.RegisterReply;
import tictactoe.service.sessionservice.RegisterRequest;

import strata1.entity.repository.RepositoryException;

/****************************************************************************
 * Responsible for processing incoming session requests and generating
 * outgoing session replies.
 */
public 
interface ISessionProcessor
{
    /************************************************************************
     * Processes incoming request to register a new user with the system.
     *
     * @param   request request to register user with system
     * @return  reply that notifies requester of 
     *          {@code RegisterRequest} outcome
     * @throws  RepositoryException persistence exceptions
     * @throws  Exception all other exceptions
     */
    public RegisterReply
    register(RegisterRequest request)
        throws RepositoryException, Exception;
    
    /************************************************************************
     * Processes incoming request to login to the system. 
     *
     * @param   request request to login to the system
     * @return  reply that notifies requester of 
     *          {@code LogingRequest} outcome
     * @throws  RepositoryException persistence exceptions
     * @throws  Exception all other exceptions
     */
    public LoginReply
    login(LoginRequest request) 
        throws RepositoryException, Exception;
    
    /************************************************************************
     * Processes incoming request to logout of the system. 
     *
     * @param   request request to logout of the system
     * @return  reply that notifies requester of 
     *          {@code LogoutRequest} outcome
     * @throws  RepositoryException persistence exceptions
     * @throws  Exception all other exceptions
     */
    public LogoutReply
    logout(LogoutRequest request) 
        throws RepositoryException, Exception;
}

// ##########################################################################
