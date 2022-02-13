package com.database.database;
import java.sql.*;
public class JavaMysqlCode {
   static public Connection connection;

    public static void getConnection()  {
        String dataBaseName ="oreganodatabase";
        String dataBaseUser ="root";
        String dataBasePassword ="asd123==";
        String url ="jdbc:mysql://localhost/"+dataBaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url,dataBaseUser,dataBasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        }
        catch(SQLException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");

        }


    }

    public static void deleteRow(Employee row) {
        try {
            System.out.println("delete from  employees where e_id="+row.getE_id() + ";");
            getConnection();
            ExecuteStatement("delete from  employees where e_id="+row.getE_id() + ";");
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void deleteRow(Role row) {
        try {
            System.out.println("delete from  roles where role_id='"+row.getRole_id() + "';");
            getConnection();
            ExecuteStatement("delete from  roles where role_id='"+row.getRole_id() + "';");
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void updateEmployeeTable(int e_id, String e_name, String target) {

        try {
            getConnection();
            ExecuteStatement("update  employees set "+target+" = '"+e_name + "' where e_id = "+e_id+";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRoleTable(String role_id, String e_name,String target) {

        try {
            getConnection();
            ExecuteStatement("update  roles set "+target+" = '"+e_name + "' where role_id = '"+role_id+"';");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(Employee rc) {

        try {
            getConnection();
            ExecuteStatement("Insert into employees (e_id, e_name, birthdate, phone, r_id,overtime_hours) values("
                    + rc.getE_id()+",'"
                    + rc.getE_name()+"','"
                    + rc.getBirthdate() +"',"
                    + rc.getPhone()+", '"
                    + rc.getR_id()+"',"
                    + rc.getOvertime_hours()+")");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(Role rc) {

        try {
            getConnection();
            ExecuteStatement("Insert into roles (role_id, role_name, base_salary, overtime_hours_price) values('"
                    + rc.getRole_id()+"','"
                    + rc.getRole_name()+"',"
                    + rc.getBase_salary() +","
                    + rc.getOvertime_hours_price()+")");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }











}
