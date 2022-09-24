/*
Don't be harsh
 */
package hrsystem;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Hamo
 */
public class HRSystem extends Application {
    boolean access = false, nameT = false, titleT = false, ageT = false, salaryT = false, borrowT = false, dayT = false, balanceT = false;
    Statement stat;
    int counter = 0;
    int i = 0;
    int j = 1;
    int min = 1;
    int max = 10000;
    int idRandom = (int) Math.floor(Math.random() * (max - min + 1) + min);
    Scene loginScene;
    Scene registerScene;
    Scene databaseScene;
    TextField txtUsername = new TextField();
    TextField txtPassword = new TextField();
    TextField txtFullName = new TextField();
    TextField txtUsernameNew = new TextField();
    TextField txtPasswordNew = new TextField();
    TextField txtAge = new TextField();
    TextField txtId = new TextField();
    TextField txtFullNameData = new TextField();
    TextField txtTitle = new TextField();
    TextField txtAgeData = new TextField();
    TextField txtSalary = new TextField();
    TextField txtDayOff = new TextField();
    TextField txtBorrow = new TextField();
    TextField txtBalance = new TextField();
    TextField txtwhID = new TextField();
    Label rand1,rand2,rand3,rand4;
    Label lFullNameData;
    Label FullName1,FullName2,FullName3,FullName4;
    Label lTitle;
    Label Title1,Title2,Title3,Title4;
    Label lAgedata;
    Label Agedata1,Agedata2,Agedata3,Agedata4;
    Label lSalary;
    Label Salary1,Salary2,Salary3,Salary4;
    Label lDayOff;
    Label DayOff1,DayOff2,DayOff3,DayOff4;
    Label lBorrow;
    Label Borrow1,Borrow2,Borrow3,Borrow4;
    Label lBalance;
    Label Balance1,Balance2,Balance3,Balance4;
    Label where;
    GridPane dataPane;
    String updatedName,updatedTitle,updatedAge,updatedSalary,updatedBorrow,updatedDayOff,updatedBalance,userIdTest;
    
    

    @Override
    public void start(Stage primaryStage) {
        //---------------------------------------------------------------- Login Page
        GridPane loginPane = new GridPane();
        Label lUsername = new Label("username");
        Label lPassword = new Label("password");
        Button btnLogin = new Button("Login");
        Button btnRegister = new Button("Register");
        
        
        loginPane.setHgap(100);
        loginPane.setVgap(50);
        loginPane.add(lUsername, 0, 3);
        loginPane.add(txtUsername, 1, 3);
        loginPane.add(lPassword, 0, 4);
        loginPane.add(txtPassword, 1, 4);
        loginPane.add(btnLogin, 0, 5);
        loginPane.add(btnRegister, 1, 5);
        loginScene = new Scene(loginPane, 1400, 600);
        btnLogin.setOnAction(e -> {
            insertDatabaseValidate();
            if (insertDatabaseValidate()){
                insertDatabase();
                primaryStage.setScene(databaseScene);
            }
        });
        btnRegister.setOnAction(e -> {
            primaryStage.setScene(registerScene);
            
        });
        //---------------------------------------------------------------- Login Page

        //---------------------------------------------------------------- Register Page
        GridPane registerPane = new GridPane();
        Label lFullName = new Label("Full Name");
        Label lUsernameNew = new Label("Username");
        Label lPasswordNew = new Label("Password");
        Label lAge = new Label("Age");
        
        
        Button btnNextRegister = new Button("Next");
        Button btnBackRegister = new Button("Back");
        
        
        registerPane.setHgap(100);
        registerPane.setVgap(50);
        registerPane.add(lFullName, 0, 0);
        registerPane.add(txtFullName, 1, 0);
        registerPane.add(lUsernameNew, 0, 2);
        registerPane.add(txtUsernameNew, 1, 2);
        registerPane.add(lPasswordNew, 0, 3);
        registerPane.add(txtPasswordNew, 1, 3);
        registerPane.add(lAge, 0, 4);
        registerPane.add(txtAge, 1, 4);
        registerPane.add(btnNextRegister, 0, 5);
        registerPane.add(btnBackRegister, 1, 5);

        registerScene = new Scene(registerPane, 1400, 600);
        btnBackRegister.setOnAction(e -> {
            primaryStage.setScene(loginScene);
        });
        btnNextRegister.setOnAction(e -> {
            primaryStage.setScene(databaseScene);
        });
        //---------------------------------------------------------------- Register Page

        //---------------------------------------------------------------- Database Page
        dataPane = new GridPane();
        Label lChoose = new Label("Choose Function");
        Label lId = new Label("ID");
        
        lFullNameData = new Label("Name");
        lTitle = new Label("Title");
        lAgedata = new Label("Age");
        lSalary = new Label("Salary");
        lDayOff = new Label("Day off");
        lBorrow = new Label("Borrow");
        lBalance = new Label("Balance");
        where = new Label("Where ID is :");
        //Emp1
        rand1 = new Label("");
        FullName1 = new Label("");
        Title1 = new Label("");
        Agedata1 = new Label("");
        Salary1 = new Label("");
        DayOff1 = new Label("");
        Borrow1 = new Label("");
        Balance1 = new Label("");
        //Emp2
        rand2 = new Label("");
        FullName2 = new Label("");
        Title2 = new Label("");
        Agedata2 = new Label("");
        Salary2 = new Label("");
        DayOff2 = new Label("");
        Borrow2 = new Label("");
        Balance2 = new Label("");
        //Emp3
        rand3 = new Label("");
        FullName3 = new Label("");
        Title3 = new Label("");
        Agedata3 = new Label("");
        Salary3 = new Label("");
        DayOff3 = new Label("");
        Borrow3 = new Label("");
        Balance3 = new Label("");
        //Emp4
        rand4 = new Label("");
        FullName4 = new Label("");
        Title4 = new Label("");
        Agedata4 = new Label("");
        Salary4 = new Label("");
        DayOff4 = new Label("");
        Borrow4 = new Label("");
        Balance4 = new Label("");
        
        Button btnFullName = new Button("Change FullName");
        Button btnTitle = new Button("Change Title");
        Button btnAge = new Button("Change Age");
        Button btnSalary = new Button("Change Salary");
        Button btnDayOff = new Button("Change Day Off");
        Button btnBorrow = new Button("Change Borrow");
        Button btnBalance = new Button("Change Balance");
        
        Button btnConfirm = new Button("Confirm");
        
        
        //String IdR = Integer.toString(idRandom);
        dataPane.setHgap(30);
        dataPane.setVgap(30);
        dataPane.add(lChoose, 0, 0);
        dataPane.add(btnFullName, 0, 1);
        dataPane.add(btnTitle, 1, 1);
        dataPane.add(btnAge, 2, 1);
        dataPane.add(btnSalary, 3, 1);
        dataPane.add(btnDayOff, 4, 1);
        dataPane.add(btnBorrow, 5, 1);
        dataPane.add(btnBalance, 6, 1);
        
        dataPane.add(btnConfirm, 0, 3);
        dataPane.add(where, 1, 3);
        dataPane.add(txtwhID, 2, 3);
        //dataPane.add(d, 0, 3);
        dataPane.add(lId, 0, 4);
        dataPane.add(lFullNameData, 1, 4);
        dataPane.add(lTitle, 2, 4);
        dataPane.add(lAgedata, 3, 4);
        dataPane.add(lSalary, 4, 4);
        dataPane.add(lDayOff, 5, 4);
        dataPane.add(lBorrow, 6, 4);
        dataPane.add(lBalance, 7, 4);
        
        dataPane.add(rand1, 0, 5);dataPane.add(rand2, 0, 6);dataPane.add(rand3, 0, 7);dataPane.add(rand4, 0, 8);
        dataPane.add(FullName1, 1, 5);dataPane.add(FullName2, 1, 6);dataPane.add(FullName3, 1, 7);dataPane.add(FullName4, 1, 8);
        dataPane.add(Title1, 2, 5);dataPane.add(Title2, 2, 6);dataPane.add(Title3, 2, 7);dataPane.add(Title4, 2, 8);
        dataPane.add(Agedata1, 3, 5);dataPane.add(Agedata2, 3, 6);dataPane.add(Agedata3, 3, 7);dataPane.add(Agedata4, 3, 8);
        dataPane.add(Salary1, 4, 5);dataPane.add(Salary2, 4, 6);dataPane.add(Salary3, 4, 7);dataPane.add(Salary4, 4, 8);
        dataPane.add(DayOff1, 5, 5);dataPane.add(DayOff2, 5, 6);dataPane.add(DayOff3, 5, 7);dataPane.add(DayOff4, 5, 8);
        dataPane.add(Borrow1, 6, 5);dataPane.add(Borrow2, 6, 6);dataPane.add(Borrow3, 6, 7);dataPane.add(Borrow4, 6, 8);
        dataPane.add(Balance1, 7, 5);dataPane.add(Balance2, 7, 6);dataPane.add(Balance3, 7, 7);dataPane.add(Balance4, 7, 8);
        
        databaseScene = new Scene(dataPane, 1400, 800);
        dataPane.add(txtFullName, 0, 2);
        dataPane.add(txtTitle, 1, 2);
        dataPane.add(txtAgeData, 2, 2);
        dataPane.add(txtSalary, 3, 2);
        dataPane.add(txtDayOff, 4, 2);
        dataPane.add(txtBorrow, 5, 2);
        dataPane.add(txtBalance, 6, 2);
        btnFullName.setOnAction(e -> {
            updatedName = txtFullName.getText();
            nameT = true;
        });
        btnTitle.setOnAction(e -> {
            updatedTitle = txtTitle.getText();
            titleT = true;
        });
        btnAge.setOnAction(e -> {
            updatedAge = txtAgeData.getText();
            ageT = true;
        });
        btnSalary.setOnAction(e -> {
            updatedSalary = txtSalary.getText();
            salaryT = true;
        });
        btnDayOff.setOnAction(e -> {
           updatedDayOff = txtDayOff.getText();
           dayT = true;
        });
        btnBorrow.setOnAction(e -> {
            updatedBorrow = txtBorrow.getText();
            borrowT = true;
        });
        btnBalance.setOnAction(e -> {
            updatedBalance = txtBalance.getText();
            balanceT = true;
        });
        btnConfirm.setOnAction(e -> {
            userIdTest = txtwhID.getText();
            insertDatabaseUpdateName();
            insertDatabase();
            primaryStage.setScene(databaseScene);
        });
        
        //---------------------------------------------------------------- Database Page
        
        primaryStage.setTitle("System");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public boolean insertDatabaseValidate() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrsystem", "root", "Hello123#");
            stat = con.createStatement();
            ResultSet result = stat.executeQuery("select * from hrsystem.user");
            while (result.next()) {
                if ("456123".equals(txtUsername.getText()) && "123456".equals(txtPassword.getText()) ) {
                    System.out.println("it's in the system");
                    counter++;
                    access = true;
                    
                }
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(HRSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return access;

    }
    public void insertDatabase() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrsystem", "root", "Hello123#");
            stat = con.createStatement();
            ResultSet result = stat.executeQuery("select * from hrsystem.user");
            
            while (result.next()) {
                    if(j == 1){
                        rand1.setText(result.getString("user_id"));
                        FullName1.setText(result.getString("name"));
                        Title1.setText(result.getString("title"));
                        Agedata1.setText(result.getString("age"));
                        Salary1.setText(result.getString("salary"));
                        DayOff1.setText(result.getString("day_off"));
                        Borrow1.setText(result.getString("borrow"));
                        Balance1.setText(result.getString("balance"));
                    }
                    if(j == 2){
                        rand2.setText(result.getString("user_id"));
                        FullName2.setText(result.getString("name"));
                        Title2.setText(result.getString("title"));
                        Agedata2.setText(result.getString("age"));
                        Salary2.setText(result.getString("salary"));
                        DayOff2.setText(result.getString("day_off"));
                        Borrow2.setText(result.getString("borrow"));
                        Balance2.setText(result.getString("balance"));
                    }
                    if(j == 3){
                        rand3.setText(result.getString("user_id"));
                        FullName3.setText(result.getString("name"));
                        Title3.setText(result.getString("title"));
                        Agedata3.setText(result.getString("age"));
                        Salary3.setText(result.getString("salary"));
                        DayOff3.setText(result.getString("day_off"));
                        Borrow3.setText(result.getString("borrow"));
                        Balance3.setText(result.getString("balance"));
                    }
                    if(j == 4){
                        rand4.setText(result.getString("user_id"));
                        FullName4.setText(result.getString("name"));
                        Title4.setText(result.getString("title"));
                        Agedata4.setText(result.getString("age"));
                        Salary4.setText(result.getString("salary"));
                        DayOff4.setText(result.getString("day_off"));
                        Borrow4.setText(result.getString("borrow"));
                        Balance4.setText(result.getString("balance"));
                    }
                    j++;
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(HRSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void insertDatabaseUpdateName() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrsystem", "root", "Hello123#");
            stat = con.createStatement();
            if(nameT == true){
                String sql1 = "update hrsystem.user set name ='" + updatedName + "' where user_id = '" + userIdTest + "'";
                stat.executeUpdate(sql1);
            }
            if(titleT == true){
                String sql2 = "update hrsystem.user set title ='" + updatedTitle + "' where user_id = '" + userIdTest + "'";
                stat.executeUpdate(sql2);
            }
            if(ageT == true){
                String sql3 = "update hrsystem.user set age ='" + updatedAge + "' where user_id = '" + userIdTest + "'";
                stat.executeUpdate(sql3);
            }
            if(salaryT == true){
                String sql4 = "update hrsystem.user set salary ='" + updatedSalary + "' where user_id = '" + userIdTest + "'";
                stat.executeUpdate(sql4);
            }
            if(borrowT == true){
                String sql5 = "update hrsystem.user set borrow ='" + updatedBorrow + "' where user_id = '" + userIdTest + "'";
                stat.executeUpdate(sql5);
            }
            if(balanceT == true){
                String sql6 = "update hrsystem.user set balance ='" + updatedBalance + "' where user_id = '" + userIdTest + "'";
                stat.executeUpdate(sql6);
            }
            if(dayT == true){
                String sql7 = "update hrsystem.user set day_off ='" + updatedDayOff + "' where user_id = '" + userIdTest + "'";
                stat.executeUpdate(sql7);
            }
            
            
            System.out.println("insert completed");

        } catch (SQLException ex) {
            Logger.getLogger(HRSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
