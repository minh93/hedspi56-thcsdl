package UI;

import DButitilies.Logging;
import DButitilies.RetrieveData;
import Entities.ClassStu;
import Entities.Department;
import Entities.Event;
import Entities.Organization;
import Entities.Student;
import Entities.User;
import File.FileIO;
import MVCmodel.ClassStudentModel;
import MVCmodel.DepartmentModel;
import MVCmodel.EventModel;
import MVCmodel.GEventModel;
import MVCmodel.GEventTodayModel;
import MVCmodel.LogModel;
import MVCmodel.OrganizationModel;
import MVCmodel.StudentModel;
import MVCmodel.UserModel;
import Network.GetLastestNew;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author PhamDucMinh
 */
public class MainForm extends javax.swing.JFrame {

    private int role;
    private String usrName;
    private ArrayList<Student> stuList;
    private StudentModel stm;
    private OrganizationModel om;
    private UserModel um;
    private LogModel lm;
    private GEventModel ge;
    private GEventTodayModel getoday;

    /**
     * Creates new form MainForm
     */
    public MainForm(int role, String usrName) {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Hi! " + usrName);
        this.usrName = usrName;
        txtUserName.setText(usrName);
        Logging.login(usrName);
        switch (role) {
            case 1:
                loadDataForAdmin();
                break;
            case 2:
                loadDataForManager();
                break;
            case 3:
                loadDataForGuest();
                break;
            case 4:
                loadDataForStudent();
                break;
            default:

        }

        new Thread() {
            @Override
            public void run() {
                getLastestNews();
            }
        }.start();
    }

    private void loadDataForAdmin() {
        loadDataStudentTBL();
        loadDataOrganizationTBL();
        loadDataForGEvent();
        loadDataForGEventToday();
        setNewMessage();
        um = new UserModel(RetrieveData.getAllUser());
        lm = new LogModel(RetrieveData.getLogRecords());
        tblAdmin.setModel(um);
        btnRegister.setEnabled(false);
        createEvent();
        loadAdminDataView(0);
    }

    private void loadDataForManager() {
        loadDataStudentTBL();
        loadDataOrganizationTBL();
        loadDataForGEvent();
        loadDataForGEventToday();
        createEvent();
        setNewMessage();
        settingPanel.setVisible(false);
        btnRegister.setEnabled(false);
        mainTabbed.remove(4);
    }

    private void loadDataForStudent() {
        loadDataStudentTBL();
        loadDataOrganizationTBL();
        loadDataForGEvent();
        loadDataForGEventToday();
        btnAddStudent.setVisible(false);
        btnDeleteStudent.setVisible(false);
        menuBar.setVisible(false);
        btnRegister.setVisible(true);
        mainTabbed.remove(4);
        btnRegister.setVisible(true);
    }

    private void loadDataForGuest() {
        loadDataStudentTBL();
        loadDataOrganizationTBL();
        loadDataForGEvent();
        loadDataForGEventToday();
        btnAddStudent.setVisible(false);
        btnDeleteStudent.setVisible(false);
        menuBar.setVisible(false);
        btnRegister.setVisible(false);
        mainTabbed.remove(4);
    }

    private void createEvent() {
        /*
         * Event double click for tblStudent
         */
        tblStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    tblStudentMouseDoubleClicked(evt);
                }
            }
        });

        tblAdmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    tblAdminMouseDoubleClicked(evt);
                }
            }
        });

        tblEvent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    tblEventMouseDoubleClicked(evt);
                }
            }
        });
        tblAdminDataView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    tblAdminDataViewMouseDoubleClicked(evt);
                }
            }
        });
    }

    public UserModel getUsermodel() {
        return (UserModel) tblAdmin.getModel();
    }

    public ArrayList<Student> getListStudent() {
        return this.stuList;
    }

    public void addStudentToModel(Student s) {
        stm.addStudent(s);
    }

    public void loadDataStudentTBL() {
        stuList = RetrieveData.getAllStudent();
        stm = new StudentModel(stuList);
        tblStudent.setModel(stm);
        txtStuTblPage.setText("" + stm.getCurrentPage());
        tblStudent.setAutoCreateRowSorter(true);
    }

    private void loadDataOrganizationTBL() {
        om = new OrganizationModel(RetrieveData.getAllOrganization());
        tblOrganization.setModel(om);
    }

    private void loadAdminDataView(int mode) {
        switch (mode) {
            case 0:
                ClassStudentModel csm = new ClassStudentModel(RetrieveData.getAllClass());
                tblAdminDataView.setModel(csm);
                break;
            case 1:
                DepartmentModel dm = new DepartmentModel(RetrieveData.getAllDepartMent());
                tblAdminDataView.setModel(dm);
                break;
            case 2:
                EventModel em = new EventModel(RetrieveData.getAllEvent());
                tblAdminDataView.setModel(em);
                break;
            default:
        }
        tblAdmin.setAutoCreateRowSorter(true);
    }

    public void getLastestNews() {
        ArrayList<String> listTitle;
        GetLastestNew net = new GetLastestNew("http://ctsv.hust.edu.vn/tabid/506/default.aspx",
                "(<a href=\"http://ctsv.hust.edu.vn/TabId/562/ArticleId/[0-9]++/PreTabId/506/Default.aspx\">(.*?)<span>)");
        net.getHTML();
        listTitle = net.getNewsContents(2);
        StringBuilder sb = new StringBuilder();
        for (String s : listTitle) {
            sb.append(s + "\n");
        }
        epLastestNews.setText(sb.toString());
        epLastestNews.setEditable(false);
    }

    public void setNewMessage() {
        if (getoday.getRowCount() != 0) {
            lblNewMessage.setText("You has new Message need to solve !");
        } else {
            lblNewMessage.setText("No task!");
        }
    }

    public void loadDataForGEvent() {
        ge = new GEventModel(RetrieveData.getGEvent());
        tblEvent.setModel(ge);
        tblEvent.setAutoCreateRowSorter(true);
    }

    public void loadDataForGEventToday() {
        getoday = new GEventTodayModel(RetrieveData.getGEventToday());
        tblEventToday.setModel(getoday);
        tblEventToday.setAutoCreateRowSorter(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHeader = new javax.swing.JPanel();
        txtSearchField = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jMainPanel = new javax.swing.JPanel();
        mainTabbed = new javax.swing.JTabbedPane();
        homePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        epLastestNews = new javax.swing.JEditorPane();
        jPanel4 = new javax.swing.JPanel();
        txtUserName = new javax.swing.JLabel();
        lblNewMessage = new javax.swing.JLabel();
        studentPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        btnAddStudent = new javax.swing.JButton();
        btnDeleteStudent = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnStuPrevious = new javax.swing.JButton();
        btnStuNext = new javax.swing.JButton();
        txtStuTblPage = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        orgPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblOrganization = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtOrgName = new javax.swing.JLabel();
        txtOrgManager = new javax.swing.JLabel();
        txtOrgTel = new javax.swing.JLabel();
        txtOrgMail = new javax.swing.JLabel();
        txtOrgParent = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        eventPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEvent = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEventToday = new javax.swing.JTable();
        cbShowallOption = new javax.swing.JCheckBox();
        settingPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdmin = new javax.swing.JTable();
        adminPanel = new javax.swing.JPanel();
        btnAddUser = new javax.swing.JButton();
        cmbViewControl = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbDataViewMode = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAdminDataView = new javax.swing.JTable();
        txtStatus = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        miFile = new javax.swing.JMenu();
        miOpenFile = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        miView = new javax.swing.JMenu();
        miViewRequest = new javax.swing.JMenuItem();
        miEdit = new javax.swing.JMenu();
        miAddClassStudent = new javax.swing.JMenuItem();
        miAddDept = new javax.swing.JMenuItem();
        miAddEvent = new javax.swing.JMenuItem();
        miAbout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtSearchField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchFieldCaretUpdate(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jHeaderLayout = new javax.swing.GroupLayout(jHeader);
        jHeader.setLayout(jHeaderLayout);
        jHeaderLayout.setHorizontalGroup(
            jHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderLayout.createSequentialGroup()
                .addContainerGap(566, Short.MAX_VALUE)
                .addComponent(txtSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnSearch)
                .addGap(14, 14, 14))
        );
        jHeaderLayout.setVerticalGroup(
            jHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Latest News"));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jScrollPane8.setViewportView(epLastestNews);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Infomation"));

        txtUserName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtUserName.setText("Sample usrname");

        lblNewMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNewMessage.setText("Sample message");
        lblNewMessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewMessageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserName)
                    .addComponent(lblNewMessage))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUserName)
                .addGap(18, 18, 18)
                .addComponent(lblNewMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        mainTabbed.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/Home.png")), homePanel); // NOI18N
        homePanel.getAccessibleContext().setAccessibleDescription("");

        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblStudent);

        btnAddStudent.setText("Add ");
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        btnDeleteStudent.setText("Delete");
        btnDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStudentActionPerformed(evt);
            }
        });

        jButton5.setText("Refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnStuPrevious.setText("Previous");
        btnStuPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStuPreviousActionPerformed(evt);
            }
        });

        btnStuNext.setText("Next");
        btnStuNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStuNextActionPerformed(evt);
            }
        });

        jLabel1.setText("/");

        jButton4.setText("Advance");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout studentPanelLayout = new javax.swing.GroupLayout(studentPanel);
        studentPanel.setLayout(studentPanelLayout);
        studentPanelLayout.setHorizontalGroup(
            studentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStuPrevious)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStuTblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(btnStuNext, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(studentPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        studentPanelLayout.setVerticalGroup(
            studentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(studentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStuPrevious)
                    .addComponent(txtStuTblPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnStuNext))
                .addGap(20, 20, 20)
                .addGroup(studentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddStudent)
                    .addComponent(btnDeleteStudent)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        mainTabbed.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/student.png")), studentPanel); // NOI18N

        tblOrganization.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblOrganization.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrganizationMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblOrganization);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Info"));

        txtOrgName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtOrgName.setText("Sample");

        txtOrgManager.setText("Sample");

        txtOrgTel.setText("Sample");

        txtOrgMail.setText("Sample");

        txtOrgParent.setText("Sample");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrgName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOrgManager, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOrgTel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOrgMail, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOrgParent, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 142, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtOrgName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtOrgManager, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOrgTel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOrgMail, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOrgParent, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRegister.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout orgPanelLayout = new javax.swing.GroupLayout(orgPanel);
        orgPanel.setLayout(orgPanelLayout);
        orgPanelLayout.setHorizontalGroup(
            orgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orgPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRegister))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        orgPanelLayout.setVerticalGroup(
            orgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnRegister)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        mainTabbed.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/organization.png")), orgPanel); // NOI18N

        tblEvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblEvent);

        tblEventToday.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tblEventToday);

        cbShowallOption.setText("Show all event");
        cbShowallOption.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbShowallOptionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout eventPanelLayout = new javax.swing.GroupLayout(eventPanel);
        eventPanel.setLayout(eventPanelLayout);
        eventPanelLayout.setHorizontalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eventPanelLayout.createSequentialGroup()
                        .addComponent(cbShowallOption)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE))
                .addContainerGap())
        );
        eventPanelLayout.setVerticalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbShowallOption)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        mainTabbed.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/event.png")), eventPanel); // NOI18N

        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAdmin);

        adminPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Admin tools"));
        adminPanel.setName(""); // NOI18N

        btnAddUser.setText("Add user");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        cmbViewControl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "User list", "All log" }));
        cmbViewControl.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbViewControlItemStateChanged(evt);
            }
        });

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbViewControl, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddUser)
                    .addComponent(cmbViewControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data tools"));

        cmbDataViewMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Class", "Department", "Event" }));
        cmbDataViewMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDataViewModeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbDataViewMode, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cmbDataViewMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblAdminDataView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblAdminDataView);

        javax.swing.GroupLayout settingPanelLayout = new javax.swing.GroupLayout(settingPanel);
        settingPanel.setLayout(settingPanelLayout);
        settingPanelLayout.setHorizontalGroup(
            settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(settingPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        settingPanelLayout.setVerticalGroup(
            settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(settingPanelLayout.createSequentialGroup()
                        .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbed.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/services.png")), settingPanel); // NOI18N

        txtStatus.setText("Status:");

        javax.swing.GroupLayout jMainPanelLayout = new javax.swing.GroupLayout(jMainPanel);
        jMainPanel.setLayout(jMainPanelLayout);
        jMainPanelLayout.setHorizontalGroup(
            jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtStatus)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(mainTabbed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jMainPanelLayout.setVerticalGroup(
            jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainTabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStatus)
                .addGap(300, 300, 300))
        );

        mainTabbed.getAccessibleContext().setAccessibleName("tab1");

        miFile.setText("File");

        miOpenFile.setText("Open");
        miFile.add(miOpenFile);

        jMenuItem2.setText("Export");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        miFile.add(jMenuItem2);

        jMenuItem3.setText("Import");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        miFile.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Logout");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        miFile.add(jMenuItem4);

        menuBar.add(miFile);

        miView.setText("View");

        miViewRequest.setText("View request ");
        miViewRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miViewRequestActionPerformed(evt);
            }
        });
        miView.add(miViewRequest);

        menuBar.add(miView);

        miEdit.setText("Edit");

        miAddClassStudent.setText("Add class");
        miAddClassStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddClassStudentActionPerformed(evt);
            }
        });
        miEdit.add(miAddClassStudent);

        miAddDept.setText("Add department");
        miAddDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddDeptActionPerformed(evt);
            }
        });
        miEdit.add(miAddDept);

        miAddEvent.setText("Add Event");
        miAddEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddEventActionPerformed(evt);
            }
        });
        miEdit.add(miAddEvent);

        menuBar.add(miEdit);

        miAbout.setText("About");
        miAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                miAboutMouseClicked(evt);
            }
        });
        menuBar.add(miAbout);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        LoginForm lf = new LoginForm();
        lf.setVisible(true);
        Logging.logout(usrName);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudentActionPerformed
        AddStudent as = new AddStudent(this, true, "Add", null);
        as.setVisible(true);
    }//GEN-LAST:event_btnAddStudentActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        AddUser au = new AddUser(this, false);
        au.setTitle(usrName);
        au.setVisible(true);
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void tblAdminMouseDoubleClicked(java.awt.event.MouseEvent evt) {
        int index = tblAdmin.getSelectedRow();
        UserModel um = (UserModel) tblAdmin.getModel();
        User u = um.getUser(index);

        int result = JOptionPane.showConfirmDialog(this, "Delete user", "Notice !", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            RetrieveData.deleteUser(u.getUserName());
            um.removeUser(index);
        }

    }

    private void tblAdminDataViewMouseDoubleClicked(MouseEvent evt) {
        int index = cmbDataViewMode.getSelectedIndex();
        switch (index) {
            case 0:
                ClassStudentModel csm = (ClassStudentModel) tblAdminDataView.getModel();
                ClassStu c = csm.getClassStu(index);
                if (JOptionPane.showConfirmDialog(this, "Delete " + c.getClaName(), "Confirm delete", JOptionPane.OK_CANCEL_OPTION)
                        == JOptionPane.YES_OPTION) {
                    if (RetrieveData.deleteClassByID(c.getClaID())) {
                        JOptionPane.showMessageDialog(this, "Delete success !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error had occured");
                    }
                }
                break;
            case 1:
                DepartmentModel dm = (DepartmentModel) tblAdminDataView.getModel();
                Department d = dm.getDepartment(index);
                if (JOptionPane.showConfirmDialog(this, "Delete " + d.getName(), "Confirm delete", JOptionPane.OK_CANCEL_OPTION)
                        == JOptionPane.YES_OPTION) {
                    if (RetrieveData.deleteDeptByID(d.getID())) {
                        JOptionPane.showMessageDialog(this, "Delete success !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error had occured");
                    }
                }
                break;
            case 2:
                EventModel em = (EventModel) tblAdminDataView.getModel();
                Event e = em.getEvent(index);
                if (JOptionPane.showConfirmDialog(this, "Delete " + e.getEvtName(), "Confirm delete", JOptionPane.OK_CANCEL_OPTION)
                        == JOptionPane.YES_OPTION) {
                    if (RetrieveData.deleteEvent(e.getEvtID(), null)) {
                        JOptionPane.showMessageDialog(this, "Delete success !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error had occured");
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "No object has chosen !");

        }
    }

    private void tblStudentMouseDoubleClicked(MouseEvent evt) {
        int index = tblStudent.getSelectedRow();
        Student s = stm.getStudent(index, Integer.parseInt(txtStuTblPage.getText()));
        AddStudent as = new AddStudent(this, rootPaneCheckingEnabled, "Update", s);
        as.setVisible(true);
    }

    private void tblEventMouseDoubleClicked(MouseEvent evt) {
        int index = tblEvent.getSelectedRow();
        String st = (String) tblEvent.getValueAt(index, 0).toString();
        System.out.println(st);
        EveryEvent ev = new EveryEvent(this, rootPaneCheckingEnabled, st);
        ev.setVisible(true);
    }

    private void txtSearchFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchFieldCaretUpdate
        String patterm = txtSearchField.getText();
        if (patterm.length() == 0) {
            stm.reloadTable();
        }
        stm.filterTable(patterm);
    }//GEN-LAST:event_txtSearchFieldCaretUpdate

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        stm.reloadTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnStuPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStuPreviousActionPerformed
        stm.previousPage();
    }//GEN-LAST:event_btnStuPreviousActionPerformed

    private void btnStuNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStuNextActionPerformed
        stm.nextPage();
    }//GEN-LAST:event_btnStuNextActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AdvanceSearchDialog asd = new AdvanceSearchDialog(this, false,usrName,role);
        asd.setTitle("Advance search");
        asd.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStudentActionPerformed
        StudentModel sm = (StudentModel) tblStudent.getModel();
        int index = tblStudent.getSelectedRow();
        int currentPage = Integer.parseInt(txtStuTblPage.getText());
        Student s = sm.getStudent(index, currentPage);
        PermanentlyDeleteConfirm pdc = new PermanentlyDeleteConfirm(this, true);
        pdc.setVisible(true);
        Logging.createLog(usrName, "Permantly delete " + s.getStudentID());
        int result = pdc.permanetlyConfirm();
        if (result == 1) {
            if (RetrieveData.deleteStudentByID(s.getStudentID(), false) == -1) {
                JOptionPane.showMessageDialog(this, "An error has occured !");
            } else {
                stm.removeStudent(index, currentPage);
                JOptionPane.showMessageDialog(this, "Deleted !");
            }
        }
        if (result == 2) {
            if (RetrieveData.deleteStudentByID(s.getStudentID(), true) == -1) {
                JOptionPane.showMessageDialog(this, "An error has occured !");
            } else {
                stm.removeStudent(index, currentPage);
                JOptionPane.showMessageDialog(this, "Deleted !");
            }
        }
        pdc.dispose();
    }//GEN-LAST:event_btnDeleteStudentActionPerformed

    private void cmbViewControlItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbViewControlItemStateChanged
        switch (cmbViewControl.getSelectedIndex()) {
            case 0:
                tblAdmin.setModel(um);
                break;
            case 1:
                tblAdmin.setModel(lm);
                break;
            default:
        }
    }//GEN-LAST:event_cmbViewControlItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int mode = cmbViewControl.getSelectedIndex();
        if (mode == 0) {
            User u = um.getUser(tblAdmin.getSelectedRow());
            if (u != null) {
                ResetPasswordForm rpf = new ResetPasswordForm(this, true, u.getUserName());
                rpf.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        stm = new StudentModel(RetrieveData.getAllStudent());
        tblStudent.setModel(stm);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cmbDataViewModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDataViewModeItemStateChanged
        int index = cmbDataViewMode.getSelectedIndex();
        loadAdminDataView(index);
    }//GEN-LAST:event_cmbDataViewModeItemStateChanged

    private void miAddClassStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddClassStudentActionPerformed
        AddClass addClassForm = new AddClass(this, true, "Add", null);
        addClassForm.setVisible(true);
    }//GEN-LAST:event_miAddClassStudentActionPerformed

    private void miAddEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddEventActionPerformed
        new AddEvent(this, false).setVisible(true);
    }//GEN-LAST:event_miAddEventActionPerformed

    private void miAddDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddDeptActionPerformed
        new AddDeptForm(this, true).setVisible(true);
    }//GEN-LAST:event_miAddDeptActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int result;
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File("."));
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        result = jfc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            ArrayList<Student> list = new ArrayList<>();
            if (FileIO.loadFromFile(list, jfc.getSelectedFile().getAbsolutePath())) {
                new LoadStudent(this, false, list).setVisible(true);
            }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void tblOrganizationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrganizationMouseClicked
        Organization org = om.getOrganization(tblOrganization.getSelectedRow());
        txtOrgName.setText(org.getOrgName());
        txtOrgManager.setText(org.getOrgManager());
        txtOrgTel.setText(org.getOrgTel());
        txtOrgMail.setText(org.getOrgEmail());
        txtOrgParent.setText("Trực thuộc " + org.getParentID());
    }//GEN-LAST:event_tblOrganizationMouseClicked

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        if (tblOrganization.getSelectedRow() != - 1) {
            Organization org = om.getOrganization(tblOrganization.getSelectedRow());
            String orgID = org.getOrgID();
            new RegisterForm(this, true, usrName, orgID).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please choose Organization");
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void miViewRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miViewRequestActionPerformed
        new RequestForm(this, false, RetrieveData.getAllParticipation()).setVisible(true);
        Logging.createLog(usrName, "View request");
    }//GEN-LAST:event_miViewRequestActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int result;
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File("."));
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        result = jfc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            FileIO.saveToFile(stuList, jfc.getSelectedFile().getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Export success !");
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void miAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miAboutMouseClicked
        new About(this, false).setVisible(true);
    }//GEN-LAST:event_miAboutMouseClicked

    private void lblNewMessageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewMessageMouseClicked
        if (getoday.getRowCount() != 0 && (role == 1 || role ==2)) {
            mainTabbed.setSelectedIndex(3);
        }
    }//GEN-LAST:event_lblNewMessageMouseClicked

    private void cbShowallOptionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbShowallOptionItemStateChanged
        if(cbShowallOption.isSelected()){
            EventModel em = new EventModel(RetrieveData.getAllEvent());
            tblEvent.setModel(em);            
        }else tblEvent.setModel(ge);
    }//GEN-LAST:event_cbShowallOptionItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adminPanel;
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnDeleteStudent;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnStuNext;
    private javax.swing.JButton btnStuPrevious;
    private javax.swing.JCheckBox cbShowallOption;
    private javax.swing.JComboBox cmbDataViewMode;
    private javax.swing.JComboBox cmbViewControl;
    private javax.swing.JEditorPane epLastestNews;
    private javax.swing.JPanel eventPanel;
    private javax.swing.JPanel homePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jHeader;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jMainPanel;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblNewMessage;
    private javax.swing.JTabbedPane mainTabbed;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu miAbout;
    private javax.swing.JMenuItem miAddClassStudent;
    private javax.swing.JMenuItem miAddDept;
    private javax.swing.JMenuItem miAddEvent;
    private javax.swing.JMenu miEdit;
    private javax.swing.JMenu miFile;
    private javax.swing.JMenuItem miOpenFile;
    private javax.swing.JMenu miView;
    private javax.swing.JMenuItem miViewRequest;
    private javax.swing.JPanel orgPanel;
    private javax.swing.JPanel settingPanel;
    private javax.swing.JPanel studentPanel;
    private javax.swing.JTable tblAdmin;
    private javax.swing.JTable tblAdminDataView;
    private javax.swing.JTable tblEvent;
    private javax.swing.JTable tblEventToday;
    private javax.swing.JTable tblOrganization;
    private javax.swing.JTable tblStudent;
    private javax.swing.JLabel txtOrgMail;
    private javax.swing.JLabel txtOrgManager;
    private javax.swing.JLabel txtOrgName;
    private javax.swing.JLabel txtOrgParent;
    private javax.swing.JLabel txtOrgTel;
    private javax.swing.JTextField txtSearchField;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JTextField txtStuTblPage;
    private javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
