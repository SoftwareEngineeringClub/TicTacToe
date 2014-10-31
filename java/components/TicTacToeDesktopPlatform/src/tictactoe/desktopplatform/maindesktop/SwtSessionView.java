// ##########################################################################
// # File Name:	SwtMainView.java
// ##########################################################################

package tictactoe.desktopplatform.maindesktop;

import tictactoe.client.mainclient.IMainView;
import tictactoe.client.mainclient.ISessionView;

import strata1.client.command.ExecutionException;
import strata1.client.view.AbstractView;
import strata1.common.authentication.ICredential;
import strata1.common.authentication.UserNameAndPasswordCredential;
import strata1.swtclient.swtview.ISwtView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.SWTResourceManager;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SwtSessionView 
    extends    AbstractView 
    implements ISessionView,ISwtView
{
    private TabFolder itsFolder;
    private TabItem   itsItem;
    private Composite itsFileComposite;
    private Composite itsSelectorsComposite;
    private Button    itsLoginButton;
    private Button    itsRegisterButton;
    private Button    itsLogoutButton;
    private Button    itsExitButton;
    private Composite itsControlsComposite;
    private Composite itsLoginControls;
    private Text      itsLoginUserNameField;
    private Label     itsLoginPasswordLabel;
    private Text      itsLoginPasswordField;
    private Composite itsLoginButtonComposite;
    private Button    itsLoginPushButton;
    private Button    itsLoginCancelPushButton;
    private Composite itsLogoutControls;
    private Button    itsLogoutPushButton;
    private Button    itsLogoutCancelPushButton;
    private Composite itsRegisterControls;
    private Text      itsRegisterUserNameField;
    private Text      itsRegisterPasswordField;
    private Button    itsRegisterPushButton;
    private Button    itsRegisterCancelPushButton;

    /************************************************************************
     * Creates a new SwtMainView. 
     *
     */
    @Inject
    public 
    SwtSessionView(IMainView mainView)
    {
        this(
            ((SwtMainView)mainView).getTabFolder(),
            ((SwtMainView)mainView).getFileTab() );
    }

    /************************************************************************
     * Creates a new SwtMainView. 
     * @wbp.parser.constructor 
     */
    public 
    SwtSessionView(TabFolder folder,TabItem item)
    {
        itsFolder = folder;
        itsItem   = item;
        
        createFileComposite();       
        createSelectorsComposite();  
        createControlsComposite();

        createLoginSelectorButton();     
        createLogoutSelectorButton();      
        createRegisterSelectorButton();
        createExitPushButton();     
        
        createLoginControls();
        createLogoutControls();       
        createRegisterControls();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    hide()
    {
        itsFileComposite.setVisible( false );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    show()
    {
        itsFileComposite.setVisible( true );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
        StackLayout layout = 
            (StackLayout)itsControlsComposite.getLayout();
        
        itsLogoutButton.setSelection( false );
        itsRegisterButton.setSelection( false );
        layout.topControl = itsLoginControls;
        itsControlsComposite.layout();

        show();        
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stop()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Composite 
    getComposite()
    {
        return itsFileComposite;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Widget 
    getWidget()
    {
        return itsFileComposite;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    reset()
    {
        StackLayout layout = 
            (StackLayout)itsControlsComposite.getLayout();
        
        itsLogoutButton.setSelection( false );
        itsRegisterButton.setSelection( false );
        layout.topControl = itsLoginControls;
        itsControlsComposite.layout();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    displayMessage(String message)
    {
        MessageBox dialog = 
            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
        
          dialog.setText("Error");
          dialog.setMessage(message);
          dialog.open();                     
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ICredential 
    getLoginCredential()
    {
        String userName = itsLoginUserNameField.getText();
        String password = itsLoginPasswordField.getText();
        
        return new UserNameAndPasswordCredential(userName,password);
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ICredential 
    getRegisterCredential()
    {
        String userName = itsRegisterUserNameField.getText();
        String password = itsRegisterPasswordField.getText();
        
        return new UserNameAndPasswordCredential(userName,password);
    }

    /************************************************************************
     *  
     *
     */
    private void 
    createFileComposite()
    {
        itsFileComposite = new Composite(itsFolder, SWT.NONE);
        itsFileComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
        itsFileComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsItem.setControl(itsFileComposite);
        GridLayout gl_itsFileComposite = new GridLayout(2, false);
        gl_itsFileComposite.horizontalSpacing = 20;
        gl_itsFileComposite.verticalSpacing = 10;
        itsFileComposite.setLayout(gl_itsFileComposite);
    }

    /************************************************************************
     *  
     *
     */
    private void 
    createSelectorsComposite()
    {
        itsSelectorsComposite = new Composite(itsFileComposite, SWT.NONE);
        itsSelectorsComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
        itsSelectorsComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsSelectorsComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        RowLayout rl_itsSelectorsComposite = new RowLayout(SWT.VERTICAL);
        rl_itsSelectorsComposite.spacing = 15;
        itsSelectorsComposite.setLayout(rl_itsSelectorsComposite);
    }

    /************************************************************************
     *  
     *
     */
    private void 
    createLoginSelectorButton()
    {
        StackLayout layout = (StackLayout)itsControlsComposite.getLayout();
        
        itsLoginButton = new Button(itsSelectorsComposite, SWT.TOGGLE | SWT.CENTER);
        itsLoginButton.setSelection(true);
        itsLoginButton.setImage(SWTResourceManager.getImage(SwtSessionView.class, "/resources/login-icon-small.png"));
        itsLoginButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsLoginButton.setLayoutData(new RowData(90, 30));
        itsLoginButton.setText("Login");
        itsLoginButton.addSelectionListener( 
            new SelectionListener() 
            {
                @Override
                public void 
                widgetDefaultSelected(SelectionEvent arg0)
                {
                }
    
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    StackLayout layout = 
                        (StackLayout)itsControlsComposite.getLayout();
                    
                    itsLogoutButton.setSelection( false );
                    itsRegisterButton.setSelection( false );
                    layout.topControl = itsLoginControls;
                    itsControlsComposite.layout();
                }
            } );
        layout.topControl = itsLoginControls;
        itsControlsComposite.layout();
    }

    /************************************************************************
     *  
     *
     */
    private void 
    createLogoutSelectorButton()
    {
        itsLogoutButton = new Button(itsSelectorsComposite, SWT.TOGGLE);
        itsLogoutButton.setImage(SWTResourceManager.getImage(SwtSessionView.class, "/resources/logout-icon-small.png"));
        itsLogoutButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsLogoutButton.setLayoutData(new RowData(90, 30));
        itsLogoutButton.setText("Logout");
        itsLogoutButton.addSelectionListener( 
            new SelectionListener() 
            {
                @Override
                public void 
                widgetDefaultSelected(SelectionEvent arg0)
                {
                }

                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    StackLayout layout = 
                        (StackLayout)itsControlsComposite.getLayout();
                    
                    itsLoginButton.setSelection( false );
                    itsRegisterButton.setSelection( false );
                    layout.topControl = itsLogoutControls;
                    itsControlsComposite.layout();
                }                
            });
    }

    /************************************************************************
     *  
     *
     */
    private void 
    createRegisterSelectorButton()
    {
        itsRegisterButton = new Button(itsSelectorsComposite, SWT.TOGGLE | SWT.CENTER);
        itsRegisterButton.setAlignment(SWT.LEFT);
        itsRegisterButton.setImage(SWTResourceManager.getImage(SwtSessionView.class, "/resources/register-icon-small.png"));
        itsRegisterButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsRegisterButton.setLayoutData(new RowData(90, 30));
        itsRegisterButton.setText("Register");
        itsRegisterButton.addSelectionListener( 
            new SelectionListener() 
            {
                @Override
                public void 
                widgetDefaultSelected(SelectionEvent arg0)
                {
                }

                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    StackLayout layout = 
                        (StackLayout)itsControlsComposite.getLayout();
                    
                    itsLoginButton.setSelection( false );
                    itsLogoutButton.setSelection( false );
                    layout.topControl = itsRegisterControls;
                    itsControlsComposite.layout();
                }                
            });

    }

    /************************************************************************
     *  
     *
     */
    private void 
    createExitPushButton()
    {
        itsExitButton = new Button(itsSelectorsComposite, SWT.NONE);
        itsExitButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsExitButton.setAlignment(SWT.LEFT);
        itsExitButton.setImage(SWTResourceManager.getImage(SwtSessionView.class, "/resources/exit-icon-small.png"));
        itsExitButton.setLayoutData(new RowData(90, 30));
        itsExitButton.setText("Exit");
        itsExitButton.addSelectionListener(
            new SelectionListener() 
            {
                @Override
                public void 
                widgetDefaultSelected(SelectionEvent arg0)
                {
                }

                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    System.exit( 0 );
                }               
            } );
    }

    /************************************************************************
     *  
     *
     */
    private void 
    createControlsComposite()
    {
        itsControlsComposite = new Composite(itsFileComposite, SWT.NO_BACKGROUND);
        itsControlsComposite.setLayout(new StackLayout());
        itsControlsComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
    }

    /************************************************************************
     *  
     *
     */
    private void 
    createLoginControls()
    {
        itsLoginControls = new Composite(itsControlsComposite, SWT.NONE);
        itsLoginControls.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsLoginControls.setLayout(new GridLayout(2, false));
        
        Label itsLoginUserNameLabel = new Label(itsLoginControls, SWT.NONE);
        itsLoginUserNameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsLoginUserNameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsLoginUserNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsLoginUserNameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsLoginUserNameLabel.setBounds(0, 0, 55, 15);
        itsLoginUserNameLabel.setText("User Name");
        
        itsLoginUserNameField = new Text(itsLoginControls, SWT.BORDER);
        itsLoginUserNameField.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        GridData gd_itsLoginUserNameField = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gd_itsLoginUserNameField.widthHint = 202;
        itsLoginUserNameField.setLayoutData(gd_itsLoginUserNameField);
        itsLoginUserNameField.setBounds(0, 0, 76, 21);
        
        itsLoginPasswordLabel = new Label(itsLoginControls, SWT.NONE);
        itsLoginPasswordLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsLoginPasswordLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsLoginPasswordLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsLoginPasswordLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsLoginPasswordLabel.setText("Password");
        
        itsLoginPasswordField = new Text(itsLoginControls, SWT.BORDER);
        itsLoginPasswordField.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        itsLoginPasswordField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        new Label(itsLoginControls, SWT.NONE);
        new Label(itsLoginControls, SWT.NONE);
        new Label(itsLoginControls, SWT.NONE);
        
        itsLoginButtonComposite = new Composite(itsLoginControls, SWT.NONE);
        itsLoginButtonComposite.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsLoginButtonComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsLoginButtonComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
        
        itsLoginPushButton = new Button(itsLoginButtonComposite, SWT.FLAT);
        itsLoginPushButton.setLayoutData(new RowData(70, SWT.DEFAULT));
        itsLoginPushButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsLoginPushButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        itsLoginPushButton.setText("Login");
        itsLoginPushButton.addSelectionListener( 
            new SelectionAdapter()
            {
                /************************************************************************
                 * {@inheritDoc} 
                 */
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    try
                    {
                        invoke( "Login" );
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox dialog = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                          dialog.setText("Error");
                          dialog.setMessage(e.getMessage());
                          dialog.open();                     
                    }
                }
            } );
        
        itsLoginCancelPushButton = new Button(itsLoginButtonComposite, SWT.NONE);
        itsLoginCancelPushButton.setLayoutData(new RowData(70, SWT.DEFAULT));
        itsLoginCancelPushButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsLoginCancelPushButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        itsLoginCancelPushButton.setText("Cancel");
        itsLoginCancelPushButton.addSelectionListener( 
            new SelectionAdapter()
            {
                /************************************************************************
                 * {@inheritDoc} 
                 */
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    try
                    {
                        invoke( "LoginCancel" );
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox dialog = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                          dialog.setText("Error");
                          dialog.setMessage(e.getMessage());
                          dialog.open();                     
                    }
                }
            } );
    }

    /************************************************************************
     *  
     *
     */
    private void 
    createLogoutControls()
    {
        itsLogoutControls = new Composite(itsControlsComposite, SWT.NONE);
        GridLayout gl_itsLogoutControls = new GridLayout(2, false);
        gl_itsLogoutControls.verticalSpacing = 10;
        itsLogoutControls.setLayout(gl_itsLogoutControls);
        
        Label itsLogoutLabel = new Label(itsLogoutControls, SWT.NONE);
        itsLogoutLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsLogoutLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
        itsLogoutLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
        itsLogoutLabel.setText("Are you sure you want to logout?");
        
        itsLogoutPushButton = new Button(itsLogoutControls, SWT.NONE);
        itsLogoutPushButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        GridData gd_itsLogoutPushButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsLogoutPushButton.widthHint = 70;
        itsLogoutPushButton.setLayoutData(gd_itsLogoutPushButton);
        itsLogoutPushButton.setText("Logout");
        itsLogoutPushButton.addSelectionListener( 
            new SelectionAdapter()
            {
                /************************************************************************
                 * {@inheritDoc} 
                 */
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    try
                    {
                        invoke( "Logout" );
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox dialog = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                          dialog.setText("Error");
                          dialog.setMessage(e.getMessage());
                          dialog.open();                     
                    }
                }
            } );
        
        itsLogoutCancelPushButton = new Button(itsLogoutControls, SWT.NONE);
        itsLogoutCancelPushButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        GridData gd_itsLogoutCancelPushButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsLogoutCancelPushButton.widthHint = 70;
        itsLogoutCancelPushButton.setLayoutData(gd_itsLogoutCancelPushButton);
        itsLogoutCancelPushButton.setText("Cancel");
        itsLogoutCancelPushButton.addSelectionListener( 
            new SelectionAdapter()
            {
                /************************************************************************
                 * {@inheritDoc} 
                 */
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    try
                    {
                        invoke( "LogoutCancel" );
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox dialog = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                          dialog.setText("Error");
                          dialog.setMessage(e.getMessage());
                          dialog.open();                     
                    }
                }
            } );

    }

    /************************************************************************
     *  
     *
     */
    private void 
    createRegisterControls()
    {
        itsRegisterControls = new Composite(itsControlsComposite, SWT.NONE);
        itsRegisterControls.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        GridLayout gl_itsRegisterControls = new GridLayout(2, false);
        itsRegisterControls.setLayout(gl_itsRegisterControls);
        
        Label itsRegisterUserNameLabel = new Label(itsRegisterControls, SWT.RIGHT);
        itsRegisterUserNameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsRegisterUserNameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsRegisterUserNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsRegisterUserNameLabel.setAlignment(SWT.RIGHT);
        itsRegisterUserNameLabel.setText("User Name");
        itsRegisterUserNameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsRegisterUserNameLabel.setBounds(0, 0, 58, 15);
        
        itsRegisterUserNameField = new Text(itsRegisterControls, SWT.BORDER);
        itsRegisterUserNameField.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        GridData gd_itsRegisterUserNameField = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
        gd_itsRegisterUserNameField.widthHint = 224;
        itsRegisterUserNameField.setLayoutData(gd_itsRegisterUserNameField);
        itsRegisterUserNameField.setBounds(0, 0, 214, 21);
        
        Label itsRegisterPasswordLabel = new Label(itsRegisterControls, SWT.RIGHT);
        itsRegisterPasswordLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsRegisterPasswordLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsRegisterPasswordLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsRegisterPasswordLabel.setAlignment(SWT.RIGHT);
        itsRegisterPasswordLabel.setText("Password");
        itsRegisterPasswordLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsRegisterPasswordLabel.setBounds(0, 0, 50, 15);
        
        itsRegisterPasswordField = new Text(itsRegisterControls, SWT.BORDER);
        itsRegisterPasswordField.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        GridData gd_itsRegisterPasswordField = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gd_itsRegisterPasswordField.widthHint = 213;
        itsRegisterPasswordField.setLayoutData(gd_itsRegisterPasswordField);
        itsRegisterPasswordField.setBounds(0, 0, 214, 21);
        new Label(itsRegisterControls, SWT.NONE);
        new Label(itsRegisterControls, SWT.NONE);
        new Label(itsRegisterControls, SWT.NONE);
        
        Composite itsRegisterButtonComposite = new Composite(itsRegisterControls, SWT.NONE);
        itsRegisterButtonComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsRegisterButtonComposite.setBounds(0, 0, 99, 31);
        itsRegisterButtonComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
        
        itsRegisterPushButton = new Button(itsRegisterButtonComposite, SWT.NONE);
        itsRegisterPushButton.setLayoutData(new RowData(70, SWT.DEFAULT));
        itsRegisterPushButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        itsRegisterPushButton.setText("Register");
        itsRegisterPushButton.addSelectionListener( 
            new SelectionAdapter()
            {
                /************************************************************************
                 * {@inheritDoc} 
                 */
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    try
                    {
                        invoke( "Register" );
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox dialog = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                          dialog.setText("Error");
                          dialog.setMessage(e.getMessage());
                          dialog.open();                     
                    }
                }               
            } );
        
        itsRegisterCancelPushButton = new Button(itsRegisterButtonComposite, SWT.NONE);
        itsRegisterCancelPushButton.setLayoutData(new RowData(70, SWT.DEFAULT));
        itsRegisterCancelPushButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        itsRegisterCancelPushButton.setText("Cancel");
        itsRegisterCancelPushButton.addSelectionListener( 
            new SelectionAdapter()
            {
                /************************************************************************
                 * {@inheritDoc} 
                 */
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    try
                    {
                        invoke( "RegisterCancel" );
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox dialog = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                          dialog.setText("Error");
                          dialog.setMessage(e.getMessage());
                          dialog.open();                     
                    }
                }
            } );

    }
}

// ##########################################################################
