// ##########################################################################
// # File Name:	SwtMainView.java
// ##########################################################################

package tictactoe.desktopplatform.maindesktop;

import tictactoe.client.mainclient.IMainView;
import tictactoe.client.mainclient.MainTabKind;

import strata1.client.command.ExecutionException;
import strata1.client.shell.IDispatcher;
import strata1.client.view.AbstractView;
import strata1.swtclient.swtshell.ISwtDispatcher;
import strata1.swtclient.swtview.ISwtView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.graphics.Point;

/****************************************************************************
 * 
 */
public 
class SwtMainView 
    extends    AbstractView 
    implements IMainView,ISwtView
{
    private ISwtDispatcher                      itsDispatcher;
    private Shell                               itsShell;
    private TabFolder itsTabFolder;
    private TabItem itsFileTab;
    private TabItem itsHomeTab;
    private TabItem itsPlayersTab;
    private TabItem itsGameTab;
    private TabItem itsHelpTab;
    private Composite itsFileComposite;
    private Button itsLoginButton;
    private Button itsRegisterButton;
    private Button itsLogoutButton;
    private Button itsExitButton;
    private Composite itsSelectorsComposite;
    private Composite itsControlsComposite;
    private Composite itsLoginControls;
    private Composite itsLogoutControls;
    private Composite itsRegisterControls;
    private Text text;
    private Label lblPassword;
    private Text text_1;
    private Composite composite;
    private Text text_2;
    private Text text_3;
    private Table itsPlayersTable;

    /************************************************************************
     * Creates a new SwtMainView. 
     *
     */
    public 
    SwtMainView(IDispatcher dispatcher)
    {
        itsDispatcher = (ISwtDispatcher)dispatcher;
        
        itsShell = new Shell( itsDispatcher.getDisplay() );
        itsShell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsShell.setMinimumSize(new Point(770, 300));
        itsShell.setSize(new Point(770, 300));
        itsShell.setImage(SWTResourceManager.getImage("C:\\Users\\John\\Pictures\\tictactoe.png"));
        itsShell.addShellListener(
            new ShellAdapter() 
            {
                @Override
                public void 
                shellClosed(ShellEvent arg0) 
                {
                    System.exit( 0 );
                }
            });
        itsShell.setText("Tic Tac Toe");
        itsShell.setSize(755, 427);
        
        Monitor primary = itsDispatcher.getDisplay().getPrimaryMonitor();
        Rectangle bounds = primary.getBounds();
        Rectangle rect = itsShell.getBounds();
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        itsShell.setLocation(x, y);
        RowLayout rl_itsShell = new RowLayout(SWT.HORIZONTAL);
        itsShell.setLayout(rl_itsShell);
        
        itsTabFolder = new TabFolder(itsShell, SWT.NONE);
        itsTabFolder.setLayoutData(new RowData(740, 355));
        itsTabFolder.setBackgroundMode(SWT.INHERIT_DEFAULT);
        itsTabFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
        itsTabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        
        itsFileTab = new TabItem(itsTabFolder, SWT.NONE);
        itsFileTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/file-icon-small.png"));
        itsFileTab.setToolTipText("Login, Logout, Exit");
        itsFileTab.setText("File");
        
        itsFileComposite = new Composite(itsTabFolder, SWT.NONE);
        itsFileComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
        itsFileComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsFileTab.setControl(itsFileComposite);
        GridLayout gl_itsFileComposite = new GridLayout(2, false);
        gl_itsFileComposite.horizontalSpacing = 20;
        gl_itsFileComposite.verticalSpacing = 10;
        itsFileComposite.setLayout(gl_itsFileComposite);
        
        itsSelectorsComposite = new Composite(itsFileComposite, SWT.NONE);
        itsSelectorsComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
        itsSelectorsComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsSelectorsComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        RowLayout rl_itsSelectorsComposite = new RowLayout(SWT.VERTICAL);
        rl_itsSelectorsComposite.spacing = 15;
        itsSelectorsComposite.setLayout(rl_itsSelectorsComposite);
        
        itsLoginButton = new Button(itsSelectorsComposite, SWT.TOGGLE | SWT.CENTER);
        itsLoginButton.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/login-icon-small.png"));
        itsLoginButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsLoginButton.setLayoutData(new RowData(80, 30));
        itsLoginButton.setText("Login");
        
        itsLogoutButton = new Button(itsSelectorsComposite, SWT.TOGGLE);
        itsLogoutButton.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/logout-icon-small.png"));
        itsLogoutButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsLogoutButton.setLayoutData(new RowData(80, 30));
        itsLogoutButton.setText("Logout");
        
        itsRegisterButton = new Button(itsSelectorsComposite, SWT.TOGGLE | SWT.CENTER);
        itsRegisterButton.setAlignment(SWT.LEFT);
        itsRegisterButton.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/register-icon-small.png"));
        itsRegisterButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsRegisterButton.setLayoutData(new RowData(80, 30));
        itsRegisterButton.setText("Register");
        
        itsExitButton = new Button(itsSelectorsComposite, SWT.NONE);
        itsExitButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
        itsExitButton.setAlignment(SWT.LEFT);
        itsExitButton.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/exit-icon-small.png"));
        itsExitButton.setLayoutData(new RowData(80, 30));
        itsExitButton.setText("Exit");
        
        itsControlsComposite = new Composite(itsFileComposite, SWT.NO_BACKGROUND);
        itsControlsComposite.setLayout(new StackLayout());
        itsControlsComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
        
        itsLoginControls = new Composite(itsControlsComposite, SWT.NONE);
        itsLoginControls.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsLoginControls.setLayout(new GridLayout(2, false));
        
        Label lblNewLabel = new Label(itsLoginControls, SWT.NONE);
        lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        lblNewLabel.setBounds(0, 0, 55, 15);
        lblNewLabel.setText("User Name");
        
        text = new Text(itsLoginControls, SWT.BORDER);
        text.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gd_text.widthHint = 202;
        text.setLayoutData(gd_text);
        text.setBounds(0, 0, 76, 21);
        
        lblPassword = new Label(itsLoginControls, SWT.NONE);
        lblPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblPassword.setText("Password");
        
        text_1 = new Text(itsLoginControls, SWT.BORDER);
        text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        new Label(itsLoginControls, SWT.NONE);
        new Label(itsLoginControls, SWT.NONE);
        new Label(itsLoginControls, SWT.NONE);
        
        composite = new Composite(itsLoginControls, SWT.NONE);
        composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        composite.setLayout(new RowLayout(SWT.HORIZONTAL));
        
        Button btnNewButton = new Button(composite, SWT.NONE);
        btnNewButton.setText("Login");
        
        Button btnCancel = new Button(composite, SWT.NONE);
        btnCancel.setText("Cancel");
        
        itsLogoutControls = new Composite(itsControlsComposite, SWT.NONE);
        GridLayout gl_itsLogoutControls = new GridLayout(2, false);
        gl_itsLogoutControls.verticalSpacing = 10;
        itsLogoutControls.setLayout(gl_itsLogoutControls);
        
        Label lblNewLabel_1 = new Label(itsLogoutControls, SWT.NONE);
        lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
        lblNewLabel_1.setText("Are you sure you want to logout?");
        
        Button btnLogout = new Button(itsLogoutControls, SWT.NONE);
        btnLogout.setText("Logout");
        
        Button btnCancel_1 = new Button(itsLogoutControls, SWT.NONE);
        btnCancel_1.setText("Cancel");
        
        itsRegisterControls = new Composite(itsControlsComposite, SWT.NONE);
        itsRegisterControls.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        GridLayout gl_itsRegisterControls = new GridLayout(2, false);
        itsRegisterControls.setLayout(gl_itsRegisterControls);
        
        Label label = new Label(itsRegisterControls, SWT.RIGHT);
        label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label.setAlignment(SWT.RIGHT);
        label.setText("User Name");
        label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        label.setBounds(0, 0, 58, 15);
        
        text_2 = new Text(itsRegisterControls, SWT.BORDER);
        text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
        gd_text_2.widthHint = 224;
        text_2.setLayoutData(gd_text_2);
        text_2.setBounds(0, 0, 214, 21);
        
        Label label_1 = new Label(itsRegisterControls, SWT.RIGHT);
        label_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_1.setAlignment(SWT.RIGHT);
        label_1.setText("Password");
        label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        label_1.setBounds(0, 0, 50, 15);
        
        text_3 = new Text(itsRegisterControls, SWT.BORDER);
        text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        GridData gd_text_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gd_text_3.widthHint = 213;
        text_3.setLayoutData(gd_text_3);
        text_3.setBounds(0, 0, 214, 21);
        new Label(itsRegisterControls, SWT.NONE);
        new Label(itsRegisterControls, SWT.NONE);
        new Label(itsRegisterControls, SWT.NONE);
        
        Composite composite_1 = new Composite(itsRegisterControls, SWT.NONE);
        composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        composite_1.setBounds(0, 0, 99, 31);
        composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
        
        Button btnRegister = new Button(composite_1, SWT.NONE);
        btnRegister.setText("Register");
        
        Button button_1 = new Button(composite_1, SWT.NONE);
        button_1.setText("Cancel");
        
        itsHomeTab = new TabItem(itsTabFolder, SWT.NONE);
        itsHomeTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/home-icon-small.png"));
        itsHomeTab.setText("Home");
        
        Composite itsHomeComposite = new Composite(itsTabFolder, SWT.NONE);
        itsHomeTab.setControl(itsHomeComposite);
        itsHomeComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsHomeComposite.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        GridLayout gl_itsHomeComposite = new GridLayout(4, false);
        gl_itsHomeComposite.horizontalSpacing = 10;
        itsHomeComposite.setLayout(gl_itsHomeComposite);
        
        Label lblPlayerName = new Label(itsHomeComposite, SWT.NONE);
        lblPlayerName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblPlayerName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        lblPlayerName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblPlayerName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblPlayerName.setText("Player Name:");
        
        Label label_2 = new Label(itsHomeComposite, SWT.NONE);
        label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        label_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_2.setText("<name>");
        
        Label lblWins = new Label(itsHomeComposite, SWT.NONE);
        lblWins.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblWins.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        lblWins.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblWins.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblWins.setText("Wins:");
        
        Label label_5 = new Label(itsHomeComposite, SWT.NONE);
        label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        label_5.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_5.setText("<wins>");
        
        Label lblPlayerRank = new Label(itsHomeComposite, SWT.NONE);
        lblPlayerRank.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblPlayerRank.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        lblPlayerRank.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblPlayerRank.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblPlayerRank.setText("Player Rank:");
        
        Label label_3 = new Label(itsHomeComposite, SWT.NONE);
        label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        label_3.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_3.setText("<rank>");
        
        Label lblLosses = new Label(itsHomeComposite, SWT.NONE);
        lblLosses.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblLosses.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        lblLosses.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblLosses.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblLosses.setText("Losses:");
        
        Label label_6 = new Label(itsHomeComposite, SWT.NONE);
        label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        label_6.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_6.setText("<losses>");
        
        Label lblGamesPlayed = new Label(itsHomeComposite, SWT.NONE);
        lblGamesPlayed.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblGamesPlayed.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        lblGamesPlayed.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblGamesPlayed.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblGamesPlayed.setText("Games Played:");
        
        Label label_4 = new Label(itsHomeComposite, SWT.NONE);
        label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_4.setText("<gamesPlayed>");
        
        Label lblTies = new Label(itsHomeComposite, SWT.NONE);
        lblTies.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblTies.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        lblTies.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblTies.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblTies.setText("Ties:");
        
        Label label_7 = new Label(itsHomeComposite, SWT.NONE);
        label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        label_7.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_7.setText("<ties>");
        
        itsPlayersTab = new TabItem(itsTabFolder, SWT.NONE);
        itsPlayersTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/player-icon-small.png"));
        itsPlayersTab.setText("Players");
        
        Composite itsPlayersComposite = new Composite(itsTabFolder, SWT.BORDER);
        itsPlayersTab.setControl(itsPlayersComposite);
        itsPlayersComposite.setLayout(new RowLayout(SWT.VERTICAL));
        
        itsPlayersTable = new Table(itsPlayersComposite, SWT.BORDER | SWT.FULL_SELECTION);
        itsPlayersTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        itsPlayersTable.setHeaderVisible(true);
        itsPlayersTable.setLinesVisible(true);
        
        TableColumn tblclmnPlayer = new TableColumn(itsPlayersTable, SWT.NONE);
        tblclmnPlayer.setWidth(100);
        tblclmnPlayer.setText("Player");
        
        TableColumn tblclmnStatus = new TableColumn(itsPlayersTable, SWT.NONE);
        tblclmnStatus.setWidth(100);
        tblclmnStatus.setText("Status");
        
        TableColumn tblclmnWins = new TableColumn(itsPlayersTable, SWT.NONE);
        tblclmnWins.setWidth(100);
        tblclmnWins.setText("Wins");
        
        TableColumn tblclmnLosses = new TableColumn(itsPlayersTable, SWT.NONE);
        tblclmnLosses.setWidth(100);
        tblclmnLosses.setText("Losses");
        
        TableColumn tblclmnTies = new TableColumn(itsPlayersTable, SWT.NONE);
        tblclmnTies.setWidth(100);
        tblclmnTies.setText("Ties");
        
        TableColumn tblclmnRank = new TableColumn(itsPlayersTable, SWT.NONE);
        tblclmnRank.setWidth(100);
        tblclmnRank.setText("Rank");
        
        TableColumn tblclmnAvgRank = new TableColumn(itsPlayersTable, SWT.NONE);
        tblclmnAvgRank.setWidth(100);
        tblclmnAvgRank.setText("Avg Rank");
        
        Button btnChallenge = new Button(itsPlayersComposite, SWT.NONE);
        btnChallenge.setText("Challenge");
        
        itsGameTab = new TabItem(itsTabFolder, SWT.NONE);
        itsGameTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/game-icon-small.png"));
        itsGameTab.setText("Game");
        
        Composite itsGameComposite = new Composite(itsTabFolder, SWT.NONE);
        itsGameComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
        itsGameTab.setControl(itsGameComposite);
        itsGameComposite.setLayout(new GridLayout(3, false));
        
        Label lblStartTime = new Label(itsGameComposite, SWT.NONE);
        lblStartTime.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblStartTime.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblStartTime.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblStartTime.setText("Start Time:");
        
        Label label_8 = new Label(itsGameComposite, SWT.NONE);
        label_8.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_8.setText("<startTime>");
        
        Composite composite_2 = new Composite(itsGameComposite, SWT.NONE);
        composite_2.setLayout(new GridLayout(3, false));
        composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 4));
        
        Button button = new Button(composite_2, SWT.FLAT);
        GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button.heightHint = 50;
        gd_button.widthHint = 50;
        gd_button.minimumWidth = 15;
        gd_button.minimumHeight = 15;
        button.setLayoutData(gd_button);
        button.setSize(new Point(20, 20));
        button.setBounds(0, 0, 75, 25);
        button.setText(" ");
        
        Button button_2 = new Button(composite_2, SWT.FLAT);
        GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_2.heightHint = 50;
        gd_button_2.widthHint = 50;
        gd_button_2.minimumWidth = 50;
        gd_button_2.minimumHeight = 50;
        button_2.setLayoutData(gd_button_2);
        button_2.setSize(new Point(20, 20));
        button_2.setText(" ");
        
        Button button_3 = new Button(composite_2, SWT.FLAT);
        GridData gd_button_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_3.widthHint = 50;
        gd_button_3.minimumWidth = 50;
        gd_button_3.minimumHeight = 50;
        gd_button_3.heightHint = 50;
        button_3.setLayoutData(gd_button_3);
        button_3.setSize(new Point(20, 20));
        button_3.setText(" ");
        
        Button button_4 = new Button(composite_2, SWT.FLAT);
        GridData gd_button_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_4.widthHint = 50;
        gd_button_4.minimumWidth = 50;
        gd_button_4.minimumHeight = 50;
        gd_button_4.heightHint = 50;
        button_4.setLayoutData(gd_button_4);
        button_4.setSize(new Point(20, 20));
        button_4.setText(" ");
        
        Button button_5 = new Button(composite_2, SWT.FLAT);
        GridData gd_button_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_5.widthHint = 50;
        gd_button_5.minimumWidth = 50;
        gd_button_5.minimumHeight = 50;
        gd_button_5.heightHint = 50;
        button_5.setLayoutData(gd_button_5);
        button_5.setSize(new Point(20, 20));
        button_5.setText(" ");
        
        Button button_6 = new Button(composite_2, SWT.FLAT);
        GridData gd_button_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_6.widthHint = 50;
        gd_button_6.minimumWidth = 50;
        gd_button_6.minimumHeight = 50;
        gd_button_6.heightHint = 50;
        button_6.setLayoutData(gd_button_6);
        button_6.setSize(new Point(20, 20));
        button_6.setText(" ");
        
        Button button_7 = new Button(composite_2, SWT.FLAT);
        GridData gd_button_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_7.widthHint = 50;
        gd_button_7.minimumWidth = 50;
        gd_button_7.minimumHeight = 50;
        gd_button_7.heightHint = 50;
        button_7.setLayoutData(gd_button_7);
        button_7.setSize(new Point(20, 20));
        button_7.setText(" ");
        
        Button button_8 = new Button(composite_2, SWT.FLAT);
        GridData gd_button_8 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_8.widthHint = 50;
        gd_button_8.minimumWidth = 50;
        gd_button_8.minimumHeight = 50;
        gd_button_8.heightHint = 50;
        button_8.setLayoutData(gd_button_8);
        button_8.setSize(new Point(20, 20));
        button_8.setText(" ");
        
        Button button_9 = new Button(composite_2, SWT.FLAT);
        GridData gd_button_9 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_9.widthHint = 50;
        gd_button_9.minimumWidth = 50;
        gd_button_9.minimumHeight = 50;
        gd_button_9.heightHint = 50;
        button_9.setLayoutData(gd_button_9);
        button_9.setSize(new Point(20, 20));
        button_9.setText(" ");
        
        Label lblPlayerX = new Label(itsGameComposite, SWT.NONE);
        lblPlayerX.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblPlayerX.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblPlayerX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblPlayerX.setText("Player X:");
        
        Label label_9 = new Label(itsGameComposite, SWT.NONE);
        label_9.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_9.setText("<playerX>");
        
        Label lblPlayerO = new Label(itsGameComposite, SWT.NONE);
        lblPlayerO.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblPlayerO.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblPlayerO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblPlayerO.setText("Player O:");
        
        Label label_10 = new Label(itsGameComposite, SWT.NONE);
        label_10.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_10.setText("<playerO>");
        
        Label lblCurrentMove = new Label(itsGameComposite, SWT.NONE);
        lblCurrentMove.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        lblCurrentMove.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblCurrentMove.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblCurrentMove.setText("Current Move:");
        
        Label label_11 = new Label(itsGameComposite, SWT.NONE);
        label_11.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        label_11.setText("<currentPlayer>");
        
        itsHelpTab = new TabItem(itsTabFolder, SWT.NONE);
        itsHelpTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/help-icon-small.png"));
        itsHelpTab.setText("Help");      
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    hide()
    {
        itsTabFolder.setVisible( false );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    show()
    {
        itsTabFolder.setVisible( true );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
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
        return itsTabFolder;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Widget 
    getWidget()
    {
        return getComposite();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainView 
    setActiveTab(MainTabKind activeTab)
    {
        switch ( activeTab )
        {
        case FILE_TAB:
            itsTabFolder.setSelection( itsFileTab );
            break;
            
        case HOME_TAB:
            itsTabFolder.setSelection( itsHomeTab );
            break;
            
        case PLAYERS_TAB:
            itsTabFolder.setSelection( itsPlayersTab );
            break;
            
        case GAME_TAB:
            itsTabFolder.setSelection( itsGameTab );
            break;
            
        case HELP_TAB:
            itsTabFolder.setSelection( itsHelpTab );
            break;
        }
        
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public MainTabKind 
    getActiveTab()
    {
        switch ( itsTabFolder.getSelectionIndex() )
        {
        case 0: 
            return MainTabKind.FILE_TAB;
            
        case 1:
            return MainTabKind.HOME_TAB;
            
        case 2:
            return MainTabKind.PLAYERS_TAB;
            
        case 3:
            return MainTabKind.GAME_TAB;
            
        default:
            return MainTabKind.HELP_TAB;
        }
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabFolder
    getTabFolder()
    {
        return itsTabFolder;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabItem
    getFileTab()
    {
        return itsFileTab;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabItem
    getHomeTab()
    {
        return itsHomeTab;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabItem
    getPlayersTab()
    {
        return itsPlayersTab;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabItem
    getGameTab()
    {
        return itsGameTab;
    }
}

// ##########################################################################
