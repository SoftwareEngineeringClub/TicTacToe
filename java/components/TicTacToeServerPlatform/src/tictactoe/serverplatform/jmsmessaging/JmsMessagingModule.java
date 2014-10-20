// ##########################################################################
// # File Name:	JmsMessagingModule.java
// ##########################################################################

package tictactoe.serverplatform.jmsmessaging;

import tictactoe.integration.messaging.MessagingModule;

import strata1.integrator.messaging.IMessagingSession;
import strata1.jmsintegrator.jmsmessaging.JmsQueueMessagingSession;
import strata1.jmsintegrator.jmsmessaging.JmsTopicMessagingSession;

import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.fusesource.stomp.jms.StompJmsConnectionFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/****************************************************************************
 * 
 */
public 
class JmsMessagingModule 
    extends MessagingModule
{
    private final ActiveMQSslConnectionFactory itsFactory1;
    private final StompJmsConnectionFactory    itsFactory2;
    
    /************************************************************************
     * Creates a new JmsMessagingModule. 
     *
     */
    public 
    JmsMessagingModule()
    {
        super( "JmsMessagingModule" );
        itsFactory1 = createFactory1();
        itsFactory2 = createFactory2();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createMessagingSession1a()
    {
        return new JmsQueueMessagingSession(itsFactory1);
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createMessagingSession1b()
    {
        return new JmsTopicMessagingSession(itsFactory1);
    }


    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createMessagingSession2a()
    {
        return new JmsQueueMessagingSession(itsFactory2);
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createMessagingSession2b()
    {
        return new JmsTopicMessagingSession(itsFactory2);
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected ActiveMQSslConnectionFactory
    createFactory1()
    {
        String host =
            //"ssl://localhost:61617";
            "ssl://ec2-54-68-199-128.us-west-2.compute.amazonaws.com:61617";
        
        ActiveMQSslConnectionFactory factory = null;
        TrustManager[]               manager = null;
        
        factory = new ActiveMQSslConnectionFactory( host );

        manager = 
            new TrustManager[] 
            { 
                new X509TrustManager()
                {
                    public X509Certificate[] 
                    getAcceptedIssuers()
                    {
                        return null;
                    }
        
                    public void 
                    checkClientTrusted(
                        X509Certificate[] certificates,
                        String authType) {}
        
                    public void 
                    checkServerTrusted(
                        X509Certificate[] certificates,
                        String authType) {}
                } 
            };
        
        
        factory.setKeyAndTrustManagers(
            null, 
            manager, 
            new SecureRandom()); 
        
        factory.setUserName( "strata-activemq-user" );
        factory.setPassword( "Dbr6pzyX" );
        
        return factory;

    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected StompJmsConnectionFactory
    createFactory2()
    {
        String host =
            "ssl://ec2-54-68-199-128.us-west-2.compute.amazonaws.com:61618";
            //"ssl://localhost:61618";
        
        StompJmsConnectionFactory factory = null;
        TrustManager[]            manager = null;
        
        factory = new StompJmsConnectionFactory();
        factory.setBrokerURI( host );

        manager = 
            new TrustManager[] 
            { 
                new X509TrustManager()
                {
                    public X509Certificate[] 
                    getAcceptedIssuers()
                    {
                        return null;
                    }
        
                    public void 
                    checkClientTrusted(
                        X509Certificate[] certificates,
                        String authType) {}
        
                    public void 
                    checkServerTrusted(
                        X509Certificate[] certificates,
                        String authType) {}
                } 
            };
                
        try
        {
            SSLContext context;

            context = SSLContext.getInstance("SSL");
            context.init( null,manager,new SecureRandom() );
            factory.setSslContext( context );
        }
        catch(Exception e)
        {
            throw new IllegalStateException( e );
        }        
        
        factory.setUsername( "strata-activemq-user" );
        factory.setPassword( "Dbr6pzyX" );
        
        return factory;
        
    }
}

// ##########################################################################
