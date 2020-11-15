import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.awt.Color;
public class Lms 
{
    public BookManagement bookManagement[];
    public BookManagement changeBookManagement[];
    Lms()
    {
        bookManagement = new BookManagement[20];
        for(int i = 0;i < 20;i++)
        {
            bookManagement[i] = new BookManagement();
        
        }       
        changeBookManagement = new BookManagement[20];
        for(int i = 0;i < 20;i++)
        {
            changeBookManagement[i] = new BookManagement();
        } 
    }
   
    public static Lms libray = new Lms();
    
    //AddLibrarian
    public String lib_name,lib_password,lib_email,lib_address,lib_city,lib_contact;
    public int rowLength;
    public String dataofLibrarian[] = new String[7];
    public String rowsOfLibrarian[][] = new String[20][7];  
    public int count = 0;
    public int id_no_of_librarian = 0;

    //DeleterLibrarian
    public int enteredIdOfLibrarian;
    public String afterDeleteData[][] = new String[10][7];  

    //LibrarianLogin
    public  boolean isLibrarianUserPasswordCorrect;
    public  String luser_name,luser_password;
    public  int loginLibrarianId;

    //AddBook
    public String abName,abCellNo,abAuthor,abPublisher,abQuantity,abDate;
    public String dataOfBooks[] = new String[8];

    //Issue Book
    public String ibBookCellNo,ibStudentId,ibStudentName,ibStudentContact,ibDate;
    public String dataOfIssueBooks[] = new String[6];
    public int issueBooksIdNo = 0;
    public String issueBooksCellName;
    public boolean isBookCellNoIsCorrect;
    public boolean isBookQuantityIsCorrect;
    public boolean isStudentIdIsValid;


    //Return Book
    public boolean isStudentIdIsCorrect;
    public boolean isValidCellAndIdForReturnBook;
    public int returnBookStudentId =  0;
    public String afterDeleteIssueBooks[][] = new String[20][6];
    public static void main(String[] args) {  
        login();
        
    }

    public void getValueofAddLibrarian()
    {   
        libray.dataofLibrarian[0] = String.valueOf(libray.id_no_of_librarian).toString();
        libray.dataofLibrarian[1] = libray.lib_name;
        libray.dataofLibrarian[2] = libray.lib_password;
        libray.dataofLibrarian[3] = libray.lib_email;
        libray.dataofLibrarian[4] = libray.lib_address;
        libray.dataofLibrarian[5] = libray.lib_city;
        libray.dataofLibrarian[6] = libray.lib_contact;

        System.out.println(
            "id ="+libray.id_no_of_librarian+
            "\nName = "+libray.lib_name+
            "\nPassword = "+libray.lib_password+
            "\nEmail = "+libray.lib_email+
            "\nAddress = "+libray.lib_address+
            "\nCity = "+libray.lib_city+
            "\nContact No = "+libray.lib_contact
        );
        
        for(int i = 0 ; i < 7; i++)
        {
           libray.rowsOfLibrarian[libray.id_no_of_librarian][i] =libray.dataofLibrarian[i];
        }
       libray.id_no_of_librarian++;
    }

    public void deleteValueofAddLibrarian()
    {
        System.out.println("Deleted Id: " + libray.enteredIdOfLibrarian);  
        showListofViewLibrarian();
        int user_input = libray.enteredIdOfLibrarian;
        for(int i = 0; i < libray.id_no_of_librarian;i++)
        {
            for(int j = 0 ;j < 7;j++)
            {
                if(user_input != Integer.parseInt(libray.rowsOfLibrarian[i][0]))
                {
                    libray.afterDeleteData[libray.count][0] = String.valueOf(libray.count);
                    libray.afterDeleteData[libray.count][j] = libray.rowsOfLibrarian[i][j];
                }  
            }
            
            if(user_input != Integer.parseInt(libray.rowsOfLibrarian[i][0]))
            {
                libray.changeBookManagement[libray.count] = libray.bookManagement[i]; 
                libray.count++;
            }

        }
        for(int i = 0; i < libray.count;i++)
        {
            for(int j = 0 ;j < 7;j++)
            {
                libray.rowsOfLibrarian[i][j] = libray.afterDeleteData[i][j];
                libray.bookManagement[i] = libray.changeBookManagement[i];
            }
        }        
        for(int i = libray.count; i < 20;i++)
        {
            libray.bookManagement[i] = new BookManagement();
        } 
        libray.count = 0;
        libray.id_no_of_librarian--;
        
    }
       

    public static void showListofViewLibrarian()
    {
        String column[]={"id","name","password","email","address","city","contact"};
        for(int i = 0; i < libray.id_no_of_librarian;i++)
        {
            System.out.print(i);
            for(int j = 0 ;j < 7;j++)
            {

                System.out.print(column[j]+" : "+ libray.rowsOfLibrarian[i][j]+"\t");
            }
            System.out.print("\n");
        }
        
    } 

    public static void checkUsernameAndPasswordofLibrarian(String username,String password)
    {
        boolean isUserName = false,isPassword = false;
        //check this username or password are valid in Librarian list
        for(int i = 0; i < libray.id_no_of_librarian ;i++)
        {
            for(int j = 0 ;j < 7;j++)
            {
                if(j == 1)
                {

                    if(username.equals(libray.rowsOfLibrarian[i][1]))
                    {
                        isUserName = true; 
                    }else{isUserName = false;}

                }
                if(j == 2)
                {
                    if(password.equals(libray.rowsOfLibrarian[i][2]))
                    {
                        isPassword = true;
                    }
                    else{isPassword = false;}
                }     

            }
            if(isUserName && isPassword){
                libray.loginLibrarianId = i;

                break;
            }
        }
        if(isUserName && isPassword)
        {

            System.out.println("Username : "+ username);
            System.out.println("Password : "+ password);
            System.out.println("Login Id : "+ (libray.loginLibrarianId+1));

            libray.luser_name = username;
            libray.luser_password = password;
            libray.isLibrarianUserPasswordCorrect = true;
            isUserName = false;
            isPassword = false;
        }
        else
        {
            libray.isLibrarianUserPasswordCorrect = false;
        }

        
    }
    
    public void getValuesOfAddBook()
    {
        libray.dataOfBooks[0] = String.valueOf(libray.bookManagement[libray.loginLibrarianId].ab_id_no).toString();
        libray.dataOfBooks[1] = libray.abCellNo;
        libray.dataOfBooks[2] = libray.abName;
        libray.dataOfBooks[3] = libray.abAuthor;
        libray.dataOfBooks[4] = libray.abPublisher;
        libray.dataOfBooks[5] = libray.abQuantity;
        libray.dataOfBooks[6] = "0";
        libray.dataOfBooks[7] = libray.abDate;

        System.out.println(
            "id ="+dataOfBooks[0]+
            "\nCellNo = "+dataOfBooks[1]+
            "\nName = "+dataOfBooks[2]+
            "\nAuthor = "+dataOfBooks[3]+
            "\nPublisher = "+dataOfBooks[4]+
            "\nQuantity = "+dataOfBooks[5]+
            "\nIssued = "+dataOfBooks[6]+
            "\nData/Time = "+dataOfBooks[7]
        );
        for(int i =0 ;i < 8;i++)
        {
            libray.bookManagement[libray.loginLibrarianId].books[libray.bookManagement[libray.loginLibrarianId].ab_id_no][i]= libray.dataOfBooks[i];
            System.out.print(dataOfBooks[i]+"\n");
        }

        libray.bookManagement[libray.loginLibrarianId].ab_id_no++;
    }

    public void getValuesOfIssueBook()
    {
        libray.dataOfIssueBooks[0] = String.valueOf(libray.bookManagement[libray.loginLibrarianId].ib_id_no).toString();
        libray.dataOfIssueBooks[1] = libray.ibBookCellNo;
        libray.dataOfIssueBooks[2] = libray.ibStudentId;
        libray.dataOfIssueBooks[3] = libray.ibStudentName;
        libray.dataOfIssueBooks[4] = libray.ibStudentContact;
        libray.dataOfIssueBooks[5] = libray.ibDate;

        System.out.println(
            "id ="+dataOfIssueBooks[0]+
            "\nCellNo = "+dataOfIssueBooks[1]+
            "\nStudent ID = "+dataOfIssueBooks[2]+
            "\nStudent Name = "+dataOfIssueBooks[3]+
            "\nStudent Contact = "+dataOfIssueBooks[4]+
            "\nData/Time = "+dataOfBooks[5]
        );

        for(int i =0 ;i < 6;i++)
        {
            libray.bookManagement[libray.loginLibrarianId].issueBooks[libray.bookManagement[libray.loginLibrarianId].ib_id_no][i]= libray.dataOfIssueBooks[i];
            System.out.print(dataOfBooks[i]+"\n");
        }
        
        libray.bookManagement[libray.loginLibrarianId].ib_id_no++;

    }


    public void checkBookCellNoOfLibrarian(String bookCellNo)
    {
        boolean isBookCellNo = false;
        //check this username or password are valid in Librarian list
        for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ab_id_no;i++)
        {
            for(int j = 0 ;j < 8;j++)
            {
                if(j == 1)
                {

                    if(bookCellNo.equals(libray.bookManagement[libray.loginLibrarianId].books[i][1]))
                    {
                        libray.issueBooksCellName = libray.bookManagement[libray.loginLibrarianId].books[i][1];
                        isBookCellNo = true; 
                    }else{isBookCellNo = false;}

                }     

            }
            if(isBookCellNo){
               
                libray.issueBooksIdNo = i;
                break;
            }
        }
        if(isBookCellNo)
        {
            libray.isBookCellNoIsCorrect = true;
            isBookCellNo = false;
        }
        else
        {
            libray.isBookCellNoIsCorrect = false;
        }

        
    }
  
    public void checkBookQuantityOfLibrarian(String bookCellNo)
    {
        boolean isQuantityNotFull = false;
        //check this username or password are valid in Librarian list
        for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ab_id_no;i++)
        {
            for(int j = 0 ;j < 8;j++)
            {
                if(j == 1)
                {

                    if(bookCellNo.equals(libray.bookManagement[libray.loginLibrarianId].books[i][1]))
                    {
                        if(Integer.parseInt(libray.bookManagement[libray.loginLibrarianId].books[i][5])>0)   
                        {
                            isQuantityNotFull = true; 
                        }
                        else{isQuantityNotFull = false;}
                    }
                    

                }     

            }
            if(isQuantityNotFull){
               
                break;
            }
        }
        if(isQuantityNotFull)
        {
            libray.isBookQuantityIsCorrect = true;
            isQuantityNotFull = false;
        }
        else
        {
            libray.isBookQuantityIsCorrect = false;
        }

        
    }
  
    public void changeBookQuantityAndIssuedBlockForIssueBook(String bookCellNo)
    {
        int quantityAmount = 0;
        int issuedAmount = 0;
        for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ab_id_no;i++)
        {
            for(int j = 0 ;j < 8;j++)
            {
                if(j == 1)
                {
                    if(bookCellNo.equals(libray.bookManagement[libray.loginLibrarianId].books[i][1]))
                    {
                        if(Integer.parseInt(libray.bookManagement[libray.loginLibrarianId].books[i][5])>0)   
                        {  
                           quantityAmount = (Integer.parseInt(libray.bookManagement[libray.loginLibrarianId].books[i][5]) - 1);
                           libray.bookManagement[libray.loginLibrarianId].books[i][5] = String.valueOf(quantityAmount);

                           issuedAmount = (Integer.parseInt(libray.bookManagement[libray.loginLibrarianId].books[i][6]) + 1);
                           libray.bookManagement[libray.loginLibrarianId].books[i][6] = String.valueOf(issuedAmount);
                        }
                    }
                }     

            }
        }
    }
    public void changeBookQuantityAndIssuedBlockForReturnBook(String bookCellNo)
    {
        int quantityAmount = 0;
        int issuedAmount = 0;
        for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ab_id_no;i++)
        {
            for(int j = 0 ;j < 8;j++)
            {
                if(j == 1)
                {
                    if(bookCellNo.equals(libray.bookManagement[libray.loginLibrarianId].books[i][1]))
                    {
                        if(Integer.parseInt(libray.bookManagement[libray.loginLibrarianId].books[i][6])>0)   
                        {  
                           quantityAmount = (Integer.parseInt(libray.bookManagement[libray.loginLibrarianId].books[i][5]) + 1);
                           libray.bookManagement[libray.loginLibrarianId].books[i][5] = String.valueOf(quantityAmount);

                           issuedAmount = (Integer.parseInt(libray.bookManagement[libray.loginLibrarianId].books[i][6]) - 1);
                           libray.bookManagement[libray.loginLibrarianId].books[i][6] = String.valueOf(issuedAmount);
                        }
                    }
                }     

            }
        }
    }
    public void checkStudentIdForReturnBook(String studentId)
    {
        boolean isStudentId = false;
        //check this username or password are valid in Librarian list
        for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ib_id_no;i++)
        {
            for(int j = 0 ;j < 6;j++)
            {
                if(j == 1)
                {

                    if(studentId.equals(libray.bookManagement[libray.loginLibrarianId].issueBooks[i][2]))
                    {
                        isStudentId = true; 
                    }else{isStudentId = false;}

                }     

            }
            if(isStudentId){
               
                break;
            }
        }
        if(isStudentId)
        {
            libray.isStudentIdIsCorrect = true;
            isStudentId = false;
        }
        else
        {
            libray.isStudentIdIsCorrect = false;
        }

        
    }
    public void checkStudentIdAndCellNoInIssueBookForReturnBook(String cellNo,String studentId)
    {
        boolean isValidId = false;
        //check this username or password are valid in Librarian list
        for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ib_id_no;i++)
        {
            for(int j = 0 ;j < 6;j++)
            {
                if(j == 1)
                {
                    if(cellNo.equals(libray.bookManagement[libray.loginLibrarianId].issueBooks[i][1]))
                    {

                        if(studentId.equals(libray.bookManagement[libray.loginLibrarianId].issueBooks[i][2]))
                        {
                            isValidId = true; 
                        }
                    }else{isValidId = false;}

                }     

            }
            if(isValidId){
                break;
            }
        }
        if(isValidId)
        {
            libray.isValidCellAndIdForReturnBook = true;
            isValidId = false;
        }
        else
        {
            libray.isValidCellAndIdForReturnBook = false;
        }

        
    }
   
   public void checkStudentIdIsValidId(String studentId)
   {
        boolean isStudentValid = false;
        for(int i = 0 ; i < libray.bookManagement[libray.loginLibrarianId].ib_id_no;i++)
        {

                if(studentId.equals(libray.bookManagement[libray.loginLibrarianId].issueBooks[i][2]))
                {
                    isStudentValid = true;
                }
        }
        if(isStudentValid)
        {
            libray.isStudentIdIsValid = true;
        }
        else
        {
            libray.isStudentIdIsValid = false;
        }
   }

   public void deleteValueofIssueBook(String studentId)
   {
       System.out.println("Student Id: " + studentId);  
       for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ib_id_no;i++)
       {
           for(int j = 0 ;j < 6;j++)
           {
               if(!studentId.equals(libray.bookManagement[libray.loginLibrarianId].issueBooks[i][2]))
               {
                   libray.afterDeleteIssueBooks[libray.count][0] = String.valueOf(libray.count);
                   libray.afterDeleteIssueBooks[libray.count][j] = libray.bookManagement[libray.loginLibrarianId].issueBooks[i][j];
               }  
           }
           
           if(!studentId.equals(libray.bookManagement[libray.loginLibrarianId].issueBooks[i][2]))
           {
               libray.count++;
           }

       }
       for(int i = 0; i < libray.count;i++)
       {
           for(int j = 0 ;j < 6;j++)
           {
                libray.bookManagement[libray.loginLibrarianId].issueBooks[i][j] = libray.afterDeleteIssueBooks[i][j];
           }
       }        

       libray.count = 0;
       libray.bookManagement[libray.loginLibrarianId].ib_id_no--;
       
   }
      

    //Frames
    public static void login()
    {
        JFrame  loginFrame;
        JButton btnAdminLogin;
        JButton btnLibrarianLogin;

        loginFrame = new JFrame("Log In");
        JLabel lbHeading = new JLabel("Library Management");
        lbHeading.setBounds(100,50,150,30);
        loginFrame.add(lbHeading);

        btnAdminLogin = new JButton("Admin Login");
        btnAdminLogin.setBounds(105,100,110,40);
        btnAdminLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                loginFrame.dispose();
                adminLogin();
            }
        });        
        loginFrame.add(btnAdminLogin);

        btnLibrarianLogin = new JButton("Librarian Login");
        btnLibrarianLogin.setBounds(105,160,110,40);
        btnLibrarianLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(libray.id_no_of_librarian > 0)
                {
                    loginFrame.dispose();
                    librarianLogin();
                }
            }
        });
        loginFrame.add(btnLibrarianLogin); 

        loginFrame.setSize(350,350);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(3);
    }

    public static void adminLogin()
    {
        
        JFrame adminLoginFrame;
        JTextField tfEnterName;
        JPasswordField tfEnterPassword;
        JButton btnAdminLogin,btnBack;

        adminLoginFrame = new JFrame();
        JLabel lbHeading = new JLabel("Admin Login Form");
        lbHeading.setBounds(100,50,150,30);
        adminLoginFrame.add(lbHeading);

        JLabel lbEnterName = new JLabel("Enter Name: ");
        lbEnterName.setBounds(30,100,100,30);
        adminLoginFrame.add(lbEnterName);

        tfEnterName = new JTextField();
        tfEnterName.setBounds(140,100,180,25);
        adminLoginFrame.add(tfEnterName);
        
        JLabel lbEnterPassword = new JLabel("Enter Password: ");
        lbEnterPassword.setBounds(30,150,100,30);
        adminLoginFrame.add(lbEnterPassword);

        tfEnterPassword = new JPasswordField();
        tfEnterPassword.setBounds(140,150,180,25);
        adminLoginFrame.add(tfEnterPassword);

        btnAdminLogin = new JButton("Login");
        btnAdminLogin.setBounds(100,200,110,30);
        btnAdminLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String user_name,user_password;
                user_name = tfEnterName.getText();
                user_password = tfEnterPassword.getText();
                if(e.getSource() == btnAdminLogin)
                {  
                    if(user_name.equals("admin") && user_password.equals("admin123"))
                    {
                        adminLoginFrame.dispose();
                        adminSection();
                    }
                }
            }
        });
        adminLoginFrame.add(btnAdminLogin);  
        
        btnBack = new JButton("Back");
        btnBack.setBounds(105,250,100,20);
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                adminLoginFrame.dispose();
                login();
            }
        });
        adminLoginFrame.add(btnBack);  

        adminLoginFrame.setSize(350,350);
        adminLoginFrame.setLayout(null);
        adminLoginFrame.setVisible(true);
        adminLoginFrame.setLocationRelativeTo(null);                 
        adminLoginFrame.setDefaultCloseOperation(3);
    }

    public static void adminSection()
    {
        JFrame adminSectionFrame;
        JButton btnAddLibrarian;
        JButton btnViewLibrarian;
        JButton btnDeleteLibrarian;
        JButton btnLogout;

        adminSectionFrame = new JFrame();
        JLabel lbHeading = new JLabel("Admin Section");
        lbHeading.setBounds(110,50,150,30);
        adminSectionFrame.add(lbHeading);

        btnAddLibrarian = new JButton("Add Librarian");
        btnAddLibrarian.setBounds(90,100,130,30);
        btnAddLibrarian.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                adminSectionFrame.dispose();
                addLibrarian();
            }
        });
        adminSectionFrame.add(btnAddLibrarian);    

        btnViewLibrarian = new JButton("View Librarian");
        btnViewLibrarian.setBounds(90,150,130,30);
        btnViewLibrarian.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                adminSectionFrame.dispose();
                viewLibrarian();
            }
        });
        adminSectionFrame.add(btnViewLibrarian);

        btnDeleteLibrarian = new JButton("Delete Librarian");
        btnDeleteLibrarian.setBounds(90,200,130,30);
        btnDeleteLibrarian.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                adminSectionFrame.dispose();
                deleteLibrarian();
            }
        });
        adminSectionFrame.add(btnDeleteLibrarian);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(90,250,130,30);
        btnLogout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                    adminSectionFrame.dispose();
                    login();
            }
        });
        adminSectionFrame.add(btnLogout);

        adminSectionFrame.setSize(350,400);
        adminSectionFrame.setLayout(null);
        adminSectionFrame.setVisible(true);
        adminSectionFrame.setLocationRelativeTo(null);
        adminSectionFrame.setDefaultCloseOperation(3);
    }

    public static void addLibrarian(){

        JFrame addLibrarianFrame;
        JTextField tfName;
        JPasswordField tfPassword;
        JTextField tfEmail;
        JTextField tfAddress;
        JTextField tfCity;
        JTextField tfContact;
        JButton btnAddLibrarian;
        JButton btnBack;
 
        addLibrarianFrame = new JFrame();
        JLabel lbHeading = new JLabel("Add Librarian");
        lbHeading.setBounds(110,50,150,30);
        addLibrarianFrame.add(lbHeading);

        JLabel lbName = new JLabel("Name: ");
        lbName.setBounds(30,100,100,30);
        addLibrarianFrame.add(lbName);

        tfName = new JTextField();
        tfName.setBounds(140,100,180,25);
        addLibrarianFrame.add(tfName);
        
        JLabel lbPassword = new JLabel("Password: ");
        lbPassword.setBounds(30,150,100,30);
        addLibrarianFrame.add(lbPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(140,150,180,25);
        addLibrarianFrame.add(tfPassword);

        JLabel lbEmail = new JLabel("Email: ");
        lbEmail.setBounds(30,200,100,30);
        addLibrarianFrame.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(140,200,180,25);
        addLibrarianFrame.add(tfEmail);
        
        JLabel lbAddress = new JLabel("Address: ");
        lbAddress.setBounds(30,250,100,30);
        addLibrarianFrame.add(lbAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(140,250,180,25);
        addLibrarianFrame.add(tfAddress);

        JLabel lbCity = new JLabel("City: ");
        lbCity.setBounds(30,300,100,30);
        addLibrarianFrame.add(lbCity);

        tfCity = new JTextField();
        tfCity.setBounds(140,300,180,25);
        addLibrarianFrame.add(tfCity);
        
        JLabel lbContact = new JLabel("Contact: ");
        lbContact.setBounds(30,350,100,30);
        addLibrarianFrame.add(lbContact);

        tfContact = new JTextField();
        tfContact.setBounds(140,350,180,25);
        addLibrarianFrame.add(tfContact);

        btnAddLibrarian = new JButton("Add Librarian");
        btnAddLibrarian.setBounds(100,400,110,30);
        btnAddLibrarian.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String name,password,email,address,city,contactNo;
                if(e.getSource() == btnAddLibrarian)
                {  
                    long  number;
                    boolean isNumber;
                    try{
                        number = Long.parseUnsignedLong(tfContact.getText().toString());
                        isNumber = true; 
                    }   
                    catch(NumberFormatException nfe)
                    {
                        isNumber = false;
                    }
            
                    if(!tfName.getText().isEmpty() && !tfPassword.getText().isEmpty() && !tfEmail.getText().isEmpty() && !tfAddress.getText().isEmpty() && !tfCity.getText().isEmpty() && !tfContact.getText().isEmpty())
                    {
                        name = tfName.getText();
                        password = tfPassword.getText();
                        email = tfEmail.getText();
                        address = tfAddress.getText();
                        city = tfCity.getText();
                        if(isNumber){
                            contactNo = tfContact.getText();
                            libray.lib_name = name;
                            libray.lib_password = password;
                            libray.lib_email = email;
                            libray.lib_address = address;
                            libray.lib_city = city;
                            libray.lib_contact = contactNo;
                            JOptionPane.showMessageDialog(addLibrarianFrame,"Librarian Added Succesfully ");
                            addLibrarianFrame.dispose();
                            libray.getValueofAddLibrarian();
                            adminSection();
                        }    
                        else{
                            JOptionPane.showMessageDialog(addLibrarianFrame, "Enter Contact Number");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(addLibrarianFrame, "Fill All Fields!");
                    }      
                }
            }
        });
        addLibrarianFrame.add(btnAddLibrarian);   

        btnBack = new JButton("Back");
        btnBack.setBounds(110,450,80,30);
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addLibrarianFrame.dispose();
                adminSection();
            }
        });
        addLibrarianFrame.add(btnBack);   


        addLibrarianFrame.setSize(360,540);
        addLibrarianFrame.setLayout(null);
        addLibrarianFrame.setVisible(true);
        addLibrarianFrame.setLocationRelativeTo(null);
        addLibrarianFrame.setDefaultCloseOperation(3);

    }

    public static void viewLibrarian()
    {
        JFrame viewLibrarianFrame;
        DefaultTableModel librarianModel = new DefaultTableModel(); 
        String column[]={"id","name","password","email","address","city","contact"};
        JTable tLibrarian;
        JButton btnBack; 
        String data[] = new String[7];

        viewLibrarianFrame = new JFrame();
        //setValueInLibrarianModel
        for(int i = 0 ;i < column.length;i++)
        {
            librarianModel.addColumn(column[i]);
        }
        int rowNum = 0;
        for(int i = 0; i<   libray.id_no_of_librarian;i++)
        {

            for(int j = 0 ;j <= 6 ;j++)
            {
                data[j] = libray.rowsOfLibrarian[i][j];           
            }             
            librarianModel.insertRow(rowNum,new Object[] {data[0],data[1],data[2],data[3],data[4],data[5],data[6]});
            rowNum++;
        }
    
        //setValueInLibrarianModel End

        tLibrarian=new JTable(librarianModel);    
        tLibrarian.setBounds(30,40,200,300);   

        JScrollPane sp=new JScrollPane(tLibrarian); 

        btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                viewLibrarianFrame.dispose();
                adminSection();
            }
        });
        btnBack.setBounds(330,500,80,20);

        viewLibrarianFrame.add(btnBack);  
        viewLibrarianFrame.add(sp);               
        viewLibrarianFrame.setSize(700,600);
        viewLibrarianFrame.setVisible(true);
        viewLibrarianFrame.setLocationRelativeTo(null);
        viewLibrarianFrame.setDefaultCloseOperation(3);
        
    }

    public static void deleteLibrarian()
    {
        JFrame deleteLibrarianFrame;
        JButton btnDelete;
        JButton btnBack;
        JTextField tfEnterId;
        

        deleteLibrarianFrame = new JFrame();
        JLabel lbHeading = new JLabel("Delete Librarian");
        lbHeading.setBounds(100,50,150,30);
        deleteLibrarianFrame.add(lbHeading);

        JLabel lbEnterId = new JLabel("Enter Id:");
        lbEnterId.setBounds(20,100,50,20);
        deleteLibrarianFrame.add(lbEnterId);

        tfEnterId = new JTextField();
        tfEnterId.setBounds(90,100,180,20);
        deleteLibrarianFrame.add(tfEnterId);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(110,150,70,30);
        btnDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                boolean isNumber;
                if(e.getSource() == btnDelete)
                {
                    if(!tfEnterId.getText().isEmpty())
                    {
                        int number = 0;
                        try
                        {
                            number = Integer.parseInt(tfEnterId.getText().toString());
                            isNumber = true;
                        }    
                        catch(NumberFormatException n){
                            isNumber = false;
                        }
                        if(isNumber)
                        {
                            if(number < libray.id_no_of_librarian)
                            {
                                libray.enteredIdOfLibrarian = Integer.parseInt(tfEnterId.getText().toString());
                                libray.deleteValueofAddLibrarian();
                                JOptionPane.showMessageDialog(deleteLibrarianFrame,"Record Delete Successfully!");
                                deleteLibrarianFrame.dispose();
                                adminSection();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(deleteLibrarianFrame,"Enter Valid ID!");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(deleteLibrarianFrame,"Please Enter ID Number!");
                        }
                        
                    }
                }
                
            }    
        });
        deleteLibrarianFrame.add(btnDelete);

        btnBack = new JButton("Back");
        btnBack.setBounds(110,200,70,20);
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                deleteLibrarianFrame.dispose();
                adminSection();
            }
        });
        deleteLibrarianFrame.add(btnBack); 

        deleteLibrarianFrame.setSize(310,310);
        deleteLibrarianFrame.setLayout(null);
        deleteLibrarianFrame.setVisible(true);
        deleteLibrarianFrame.setLocationRelativeTo(null);
        deleteLibrarianFrame.setDefaultCloseOperation(3);

    }

    public static void librarianLogin()
    {
            JFrame librarianLoginFrame;
            JTextField tfEnterName;
            JPasswordField tfEnterPassword;
            JButton btnLibraeianLogin,btnBack;

            librarianLoginFrame = new JFrame();
            System.out.println("Total id : "+ (libray.id_no_of_librarian+1));
            JLabel lbHeading = new JLabel("Librarian Login Form");
            lbHeading.setBounds(100,50,150,30);
            librarianLoginFrame.add(lbHeading);
    
            JLabel lbEnterName = new JLabel("Enter Name: ");
            lbEnterName.setBounds(30,100,100,30);
            librarianLoginFrame.add(lbEnterName);
    
            tfEnterName = new JTextField();
            tfEnterName.setBounds(140,100,180,25);
            librarianLoginFrame.add(tfEnterName);
            
            JLabel lbEnterPassword = new JLabel("Enter Password: ");
            lbEnterPassword.setBounds(30,150,100,30);
            librarianLoginFrame.add(lbEnterPassword);
    
            tfEnterPassword = new JPasswordField();
            tfEnterPassword.setBounds(140,150,180,25);
            librarianLoginFrame.add(tfEnterPassword);
    
            btnLibraeianLogin = new JButton("Login");
            btnLibraeianLogin.setBounds(100,200,110,30);
            btnLibraeianLogin.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    if(e.getSource() == btnLibraeianLogin)
                    {
                        if(!tfEnterName.getText().isEmpty() && !tfEnterPassword.getText().isEmpty())
                        {
                            libray.checkUsernameAndPasswordofLibrarian(tfEnterName.getText(),tfEnterPassword.getText());
                            if(libray.isLibrarianUserPasswordCorrect)
                            {
                                librarianLoginFrame.dispose();
                                librarianSection();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(librarianLoginFrame,"Enter Valid Username or Password!");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(librarianLoginFrame,"Enter Username or Password!");
                        }

                    }
                }
            });
            librarianLoginFrame.add(btnLibraeianLogin);  
    
            btnBack = new JButton("Back");
            btnBack.setBounds(105,250,100,20);
            btnBack.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    librarianLoginFrame.dispose();
                    login();
                }
            });

            librarianLoginFrame.add(btnBack);  
    
            librarianLoginFrame.setSize(350,350);
            librarianLoginFrame.setLayout(null);
            librarianLoginFrame.setVisible(true);
            librarianLoginFrame.setLocationRelativeTo(null);
            librarianLoginFrame.setDefaultCloseOperation(3);
    }

    public static void librarianSection()
    {
            JFrame librarianSectionFrame;
            JLabel lbUserName;
            JButton btnAddBook;
            JButton btnViewBook;
            JButton btnIssueBook;
            JButton btnLogout;
            JButton btnViewIssuedBook;
            JButton btnReturnBook;

            librarianSectionFrame = new JFrame();
            JLabel lbHeading = new JLabel("Librarian Section");
            lbHeading.setBounds(110,50,150,30);
            librarianSectionFrame.add(lbHeading);
    
            lbUserName = new JLabel("User : "+ libray.luser_name);
            lbUserName.setBounds(110,100,150,30);
            librarianSectionFrame.add(lbUserName);
            
            btnAddBook = new JButton("Add Book");
            btnAddBook.setBounds(90,150,150,30);
            btnAddBook.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    librarianSectionFrame.dispose();
                    addBook();
                }
            });
            librarianSectionFrame.add(btnAddBook);    
    
            btnViewBook = new JButton("View Book");
            btnViewBook.setBounds(90,200,150,30);
            btnViewBook.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    librarianSectionFrame.dispose();
                    viewBook();
                }
            });
            librarianSectionFrame.add(btnViewBook);
    
            btnIssueBook = new JButton("Issue Book");
            btnIssueBook.setBounds(90,250,150,30);
            btnIssueBook.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    librarianSectionFrame.dispose();
                    issueBook();
                }
            });
            librarianSectionFrame.add(btnIssueBook);
    
            btnViewIssuedBook = new JButton("View Issued Book");
            btnViewIssuedBook.setBounds(90,300,150,30);
            btnViewIssuedBook.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                        librarianSectionFrame.dispose();
                        viewIssuedBooks();
                }
            });
            librarianSectionFrame.add(btnViewIssuedBook);    
    
            btnReturnBook = new JButton("Return Book");
            btnReturnBook.setBounds(90,350,150,30);
            btnReturnBook.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    librarianSectionFrame.dispose();
                    returnBook();
                }
            });
            librarianSectionFrame.add(btnReturnBook);
    
            btnLogout = new JButton("Logout");
            btnLogout.setBounds(90,400,150,30);
            btnLogout.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    librarianSectionFrame.dispose();
                    login();
                }
            });
            librarianSectionFrame.add(btnLogout);
         
            librarianSectionFrame.setSize(350,500);
            librarianSectionFrame.setLayout(null);
            librarianSectionFrame.setVisible(true);
            librarianSectionFrame.setLocationRelativeTo(null);
            librarianSectionFrame.setDefaultCloseOperation(3);
    }

    public static void addBook()
    {

        JFrame addBookFrame;
        JTextField tfCellNo;
        JTextField tfName;
        JTextField tfAuthor;
        JTextField tfPublisher;
        JTextField tfQuantity;
        JButton btnAddBook;
        
        JButton btnBack;

        addBookFrame = new JFrame();
        JLabel lbHeading = new JLabel("Add Book");
        lbHeading.setBounds(150,50,150,30);
        addBookFrame.add(lbHeading);

        JLabel lbCallNo = new JLabel("Cell No.: ");
        lbCallNo.setBounds(30,100,100,30);
        addBookFrame.add(lbCallNo);

        tfCellNo = new JTextField();
        tfCellNo.setBounds(140,100,180,25);
        addBookFrame.add(tfCellNo);

        JLabel lbName = new JLabel("Name: ");
        lbName.setBounds(30,150,100,30);
        addBookFrame.add(lbName);

        tfName = new JTextField();
        tfName.setBounds(140,150,180,25);
        addBookFrame.add(tfName);

        JLabel lbAuthor = new JLabel("Author: ");
        lbAuthor.setBounds(30,200,100,30);
        addBookFrame.add(lbAuthor);

        tfAuthor = new JTextField();
        tfAuthor.setBounds(140,200,180,25);
        addBookFrame.add(tfAuthor);

        JLabel lbPublisher = new JLabel("Publisher: ");
        lbPublisher.setBounds(30,250,100,30);
        addBookFrame.add(lbPublisher);

        tfPublisher = new JTextField();
        tfPublisher.setBounds(140,250,180,25);
        addBookFrame.add(tfPublisher);

        JLabel lbQuantity = new JLabel("Quantity: ");
        lbQuantity.setBounds(30,300,100,30);
        addBookFrame.add(lbQuantity);

        tfQuantity = new JTextField();
        tfQuantity.setBounds(140,300,180,25);
        addBookFrame.add(tfQuantity);


        btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(120,350,110,30);
        btnAddBook.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == btnAddBook)
                {

                    if(!tfCellNo.getText().isEmpty()&&!tfName.getText().isEmpty()&&!tfAuthor.getText().isEmpty()&&!tfPublisher.getText().isEmpty()&&!tfQuantity.getText().isEmpty())
                    {
                        boolean isNumber;
                        int qantityNumber = 0;
                        try
                        {
                            qantityNumber = Integer.parseInt(tfQuantity.getText().toString());
                            isNumber = true;
                        }
                        catch(NumberFormatException n)
                        {
                            isNumber = false;
                        }
                        if(isNumber)
                        {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime ldtNow = LocalDateTime.now();
                            libray.abDate = dtf.format(ldtNow);
                            libray.abCellNo = tfCellNo.getText().toString();
                            libray.abName = tfName.getText().toString();
                            libray.abAuthor = tfAuthor.getText().toString();
                            libray.abPublisher = tfPublisher.getText().toString();
                            libray.abQuantity = tfQuantity.getText().toString();
                            libray.getValuesOfAddBook();
                            addBookFrame.dispose();
                            librarianSection();
                        }
                        else
                        {

                            JOptionPane.showMessageDialog(addBookFrame,"Enter Correct Cell or Quantity Number!");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(addBookFrame,"Fill All Fields!");
                    }
                }
            }
        });
        addBookFrame.add(btnAddBook);

        btnBack = new JButton("Back");
        btnBack.setBounds(130,400,80,30);
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addBookFrame.dispose();
                librarianSection();
            }
        });
        addBookFrame.add(btnBack);


        addBookFrame.setSize(360,500);
        addBookFrame.setLayout(null);
        addBookFrame.setVisible(true);
        addBookFrame.setLocationRelativeTo(null);
        addBookFrame.setDefaultCloseOperation(3);
    
    }

    public static void viewBook()
    {
        JFrame viewBookFrame;
        JTable tBook;
        DefaultTableModel addBookModel = new DefaultTableModel(); 
        JButton btnBack;
        String column[]={"id","callNo","name","author","publisher","quantity","issued","added_date"};         
        String data[] = new String[8];
 
        viewBookFrame = new JFrame();

        //setValueInAddBookModel
        for(int i = 0 ;i < column.length;i++)
        {
            addBookModel.addColumn(column[i]);
        }
        int rowNum = 0;
        for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ab_id_no;i++)
        {

            for(int j = 0 ;j < 8 ;j++)
            {
                data[j] = libray.bookManagement[libray.loginLibrarianId].books[i][j];                
            }             
            addBookModel.insertRow(rowNum,new Object[] {data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]});
            rowNum++;
        }
        // End setValueInAddBookModel

        tBook=new JTable(addBookModel);    
        tBook.setBounds(30,40,200,300);   

        JScrollPane sp=new JScrollPane(tBook); 

        btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                viewBookFrame.dispose();
                librarianSection();
            }
        });
        btnBack.setBounds(330,500,80,20);

        viewBookFrame.add(btnBack);  
        viewBookFrame.add(sp);               
        viewBookFrame.setSize(700,600);
        viewBookFrame.setVisible(true);
        viewBookFrame.setLocationRelativeTo(null);
        viewBookFrame.setDefaultCloseOperation(3);
    }

    public static void issueBook()
    {   
        JFrame issueBookFrame;
        JTextField tfBookCallNo;
        JTextField tfStudentId;
        JTextField tfStudentName;
        JTextField tfStudentContact;
        JButton btnAddBook;
        JButton btnBack;
    
        issueBookFrame = new JFrame();
        JLabel lbHeading = new JLabel("Issue Book");
        lbHeading.setBounds(150,50,150,30);
        issueBookFrame.add(lbHeading);

        JLabel lbBookCallNo = new JLabel("Book Call No.: ");
        lbBookCallNo.setBounds(30,100,100,30);
        issueBookFrame.add(lbBookCallNo);

        tfBookCallNo = new JTextField();
        tfBookCallNo.setBounds(140,100,180,25);
        issueBookFrame.add(tfBookCallNo);
        
        JLabel lbStudentId = new JLabel("Student Id: ");
        lbStudentId.setBounds(30,150,100,30);
        issueBookFrame.add(lbStudentId);

        tfStudentId = new JTextField();
        tfStudentId.setBounds(140,150,180,25);
        issueBookFrame.add(tfStudentId);

        JLabel lbStudentName = new JLabel("Student Name: ");
        lbStudentName.setBounds(30,200,100,30);
        issueBookFrame.add(lbStudentName);

        tfStudentName = new JTextField();
        tfStudentName.setBounds(140,200,180,25);
        issueBookFrame.add(tfStudentName);
        
        JLabel lbStudentContact = new JLabel("Student Contact: ");
        lbStudentContact.setBounds(30,250,100,30);
        issueBookFrame.add(lbStudentContact);

        tfStudentContact = new JTextField();
        tfStudentContact.setBounds(140,250,180,25);
        issueBookFrame.add(tfStudentContact);

    

        btnAddBook = new JButton("Issue Book");
        btnAddBook.setBounds(120,300,110,30);
        btnAddBook.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == btnAddBook)
                {

                    if(!tfBookCallNo.getText().isEmpty()&&!tfStudentId.getText().isEmpty()&&!tfStudentName.getText().isEmpty()&&!tfStudentContact.getText().isEmpty())
                    {
                        boolean isNumber;
                        long studentId = 0,studentContact = 0;
                        try
                        {
                            studentId = Long.parseUnsignedLong(tfStudentId.getText().toString());
                            studentContact = Long.parseUnsignedLong(tfStudentContact.getText().toString());
                            isNumber = true;
                        }
                        catch(NumberFormatException n)
                        {
                            isNumber = false;
                        }
                        libray.checkBookCellNoOfLibrarian(tfBookCallNo.getText().toString());
                        libray.checkBookQuantityOfLibrarian(tfBookCallNo.getText().toString());

                        if(isNumber && libray.isBookCellNoIsCorrect)
                        {

                            libray.checkStudentIdIsValidId(tfStudentId.getText().toString());

                            if(!libray.isStudentIdIsValid)
                            {
                                if(libray.isBookQuantityIsCorrect)
                                {
                                        
                                    libray.changeBookQuantityAndIssuedBlockForIssueBook(tfBookCallNo.getText().toString());
                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    LocalDateTime ldtNow = LocalDateTime.now();
                                    libray.ibDate = dtf.format(ldtNow);
                                    libray.ibBookCellNo = tfBookCallNo.getText().toString();
                                    libray.ibStudentId = tfStudentId.getText().toString();
                                    libray.ibStudentName = tfStudentName.getText().toString();
                                    libray.ibStudentContact =tfStudentContact.getText().toString();
                                    libray.getValuesOfIssueBook();
                                    libray.isStudentIdIsValid = false;
                                    JOptionPane.showMessageDialog(issueBookFrame,"Book Issue Sucessfully!");
                                    issueBookFrame.dispose();
                                    librarianSection();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(issueBookFrame,"All Books Issued");
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(issueBookFrame,"Please Enter a different Student ID!");
                            }
                        }
                        else
                        {

                            JOptionPane.showMessageDialog(issueBookFrame,"Enter Correct Cell Number!");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(issueBookFrame,"Fill All Fields!");
                    }
                }
            }
        });
        issueBookFrame.add(btnAddBook);   

        btnBack = new JButton("Back");
        btnBack.setBounds(130,350,80,30);
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == btnBack)
                {
                    issueBookFrame.dispose();
                    librarianSection();
                }
            }
        });
        issueBookFrame.add(btnBack);   

        JLabel lbNote= new JLabel("Note: Please Check Student Id CareFully before Issuing book!");
        lbNote.setBounds(10,400,350,20);
        lbNote.setForeground(Color.red);
        issueBookFrame.add(lbNote);

        issueBookFrame.setSize(375,500);
        issueBookFrame.setLayout(null);
        issueBookFrame.setVisible(true);
        issueBookFrame.setLocationRelativeTo(null);
        issueBookFrame.setDefaultCloseOperation(3);
    }

    public static void viewIssuedBooks()
    {
        JFrame viewIssuedBooksFrame;
        JTable tIssueBook;
        DefaultTableModel addIssueBookModel = new DefaultTableModel(); 
        JButton btnBack;
        String column[]={"id","BookcallNo","StudentId","StudentName","StudentContact","IssuedDate"};         
        String data[] = new String[8];
 
        viewIssuedBooksFrame = new JFrame();

        //setValueInAddBookModel
        for(int i = 0 ;i < column.length;i++)
        {
            addIssueBookModel.addColumn(column[i]);
        }
        int rowNum = 0;
        for(int i = 0; i < libray.bookManagement[libray.loginLibrarianId].ib_id_no;i++)
        {

            for(int j = 0 ;j < 6 ;j++)
            {
                data[j] = libray.bookManagement[libray.loginLibrarianId].issueBooks[i][j];                
            }             
            addIssueBookModel.insertRow(rowNum,new Object[] {data[0],data[1],data[2],data[3],data[4],data[5]});
            rowNum++;
        }
        // End setValueInAddBookModel

        tIssueBook=new JTable(addIssueBookModel);    
        tIssueBook.setBounds(30,40,200,300);   

        JScrollPane sp=new JScrollPane(tIssueBook); 

        btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                viewIssuedBooksFrame.dispose();
                librarianSection();
            }
        });
        btnBack.setBounds(330,500,80,20);

        viewIssuedBooksFrame.add(btnBack);  
        viewIssuedBooksFrame.add(sp);               
        viewIssuedBooksFrame.setSize(700,600);
        viewIssuedBooksFrame.setVisible(true);
        viewIssuedBooksFrame.setLocationRelativeTo(null);
        viewIssuedBooksFrame.setDefaultCloseOperation(3);
    }
    
    public static void returnBook()
    {
        JFrame returnBookFrame;
        JTextField tfEnterBookCallNo;
        JTextField tfStudentId;
        JButton btnReturnBook;
        JButton btnBack;
        
        returnBookFrame = new JFrame();
        JLabel lbHeading = new JLabel("Return Book");
        lbHeading.setBounds(150,50,150,30);
        returnBookFrame.add(lbHeading);

        JLabel lbEnterName = new JLabel("Book CallNo: ");
        lbEnterName.setBounds(30,100,100,30);
        returnBookFrame.add(lbEnterName);

        tfEnterBookCallNo = new JTextField();
        tfEnterBookCallNo.setBounds(140,100,180,25);
        returnBookFrame.add(tfEnterBookCallNo);
        
        JLabel lbEnterStudentId = new JLabel("Student Id: ");
        lbEnterStudentId.setBounds(30,150,100,30);
        returnBookFrame.add(lbEnterStudentId);

        tfStudentId = new JTextField();
        tfStudentId.setBounds(140,150,180,25);
        returnBookFrame.add(tfStudentId);

        btnReturnBook = new JButton("Retrun Book");
        btnReturnBook.setBounds(120,200,110,30);
        btnReturnBook.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!tfEnterBookCallNo.getText().isEmpty()&&!tfStudentId.getText().isEmpty())
                {
                    int number;
                    boolean isNumber;
                    try
                    {
                        number = Integer.parseInt(tfStudentId.getText().toString());
                        isNumber = true;
                    }
                    catch(NumberFormatException n)
                    {
                        isNumber = false;
                    }
                    if(isNumber)
                    {
                        libray.checkBookCellNoOfLibrarian(tfEnterBookCallNo.getText().toString());
                        if(libray.isBookCellNoIsCorrect)
                        {
                            libray.checkStudentIdForReturnBook(tfStudentId.getText().toString());
                            if(libray.isStudentIdIsCorrect)
                            {
                                libray.checkStudentIdAndCellNoInIssueBookForReturnBook(tfEnterBookCallNo.getText().toString(),tfStudentId.getText().toString());
                                if(libray.isValidCellAndIdForReturnBook)
                                {
                                    libray.changeBookQuantityAndIssuedBlockForReturnBook(tfEnterBookCallNo.getText().toString());
                                    libray.deleteValueofIssueBook(tfStudentId.getText().toString());
                                    System.out.println("CellNo : "+tfEnterBookCallNo.getText().toString()+" and Student Id : "+tfStudentId.getText().toString());
                                    System.out.print("book returned");
                                    libray.isBookCellNoIsCorrect = false;
                                    libray.isStudentIdIsCorrect = false;
                                    libray.isValidCellAndIdForReturnBook = false;
                                    returnBookFrame.dispose();
                                    librarianSection();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(returnBookFrame,"Entered Student Id and Cell Number are not Matched!");
                                }
                            }
                            else
                            {
                            JOptionPane.showMessageDialog(returnBookFrame,"Enter Student Id");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(returnBookFrame,"Enter Correct CellNo");
                        }
                       // libray.checkBookCallNoAndStudentId(tfEnterBookCallNo.getText().toString(),tfStudentId.getText().toString());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(returnBookFrame,"Enter Number in Student Id!");
                    }
                }
            }
        });
        returnBookFrame.add(btnReturnBook);    

        btnBack = new JButton("Back");
        btnBack.setBounds(130,250,80,30);
        btnBack.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                returnBookFrame.dispose();
                librarianSection();
            }
        });
        returnBookFrame.add(btnBack);   

        JLabel lbNote= new JLabel("Note: Check Book Properly!");
        lbNote.setBounds(100,300,150,20);
        lbNote.setForeground(Color.red);
        returnBookFrame.add(lbNote);

        returnBookFrame.setSize(375,365);
        returnBookFrame.setLayout(null);
        returnBookFrame.setVisible(true);
        returnBookFrame.setLocationRelativeTo(null);
        returnBookFrame.setDefaultCloseOperation(3);
    }

}
class BookManagement
{
    public String books[][] = new String[20][8];
    public String issueBooks[][] = new String[20][6];
    public int bookIssued[] = new int[20];
    public int ab_id_no;
    public int ib_id_no;
}