package com.fluex404.creational;

import javax.management.remote.rmi.RMIConnectionImpl;
import java.io.Serializable;
import java.sql.*;

public class SingletonExample {
    public static void main(String[] args) {
        B b = B.getB();
        System.out.println(b);
        B b2 = B.getB();
        System.out.println(b2);

        A a = A.getA();
        System.out.println(a);
        A a2 = A.getA();
        System.out.println(a2);
    }

}

class A implements Serializable {
    private static A obj = new A(); // Early, instance will be created at load time
    private A(){}
    public static A getA(){
        return obj;
    }
    public void doSomething(){
        // write your code
    }
}

class B{
    private static B obj;
    private B(){}
    public static B getB(){
        if(obj == null) {
            obj = new B(); // instance will be created at request time
        }
        return obj;
    }
    public void doSomething(){
        // write your code
    }
}

class JDBCSingle{
    // Step 1
    // CREATE A JDBCSingleton class.
    // static member holds only one instance of the JDBCSingle class.

    private static JDBCSingle jdbc;

    // JDBCSingle prevents the instantion from any other class.
    private JDBCSingle(){}

    // Now we are providing global point of access.
    public static JDBCSingle getInstance(){
        if(jdbc == null){
            jdbc = new JDBCSingle();
        }
        return jdbc;
    }
    // to get the connetion from methods like insert, view, etc.
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
//        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        return con;
    }
    // to insert the record into the database
    public int insert(String name, String pass) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        int recordCounter = 0;

        try{
            c = getConnection();
            ps = c.prepareStatement("insert into userdata(uname, upassword) values(?, ?)");
            ps.setString(1, name);
            ps.setString(2, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(ps!= null){
                ps.close();
            }
            if(c!= null){
                c.close();
            }
        }
        return recordCounter;
    }
    // to view the data from the database
    public void view(String name) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con=getConnection();
            ps = con.prepareStatement("select * from userdata where uname=?");
            ps.setString(1, name);
            rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println("Name="+rs.getString(2)+"\tPassword="+rs.getString(3));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ps!= null){
                ps.close();
            }
            if(con!= null){
                con.close();
            }
        }
    }
    // to update the password for the given username
    public int update(String name, String password) throws SQLException  {
        Connection c=null;
        PreparedStatement ps=null;

        int recordCounter=0;
        try {
            c=this.getConnection();
            ps=c.prepareStatement(" update userdata set upassword=? where uname='"+name+"' ");
            ps.setString(1, password);
            recordCounter=ps.executeUpdate();
        } catch (Exception e) {  e.printStackTrace(); } finally{

            if (ps!=null){
                ps.close();
            }if(c!=null){
                c.close();
            }
        }
        return recordCounter;
    }

    // to delete the data from the database
    public int delete(int userid) throws SQLException{
        Connection c=null;
        PreparedStatement ps=null;
        int recordCounter=0;
        try {
            c=this.getConnection();
            ps=c.prepareStatement(" delete from userdata where uid='"+userid+"' ");
            recordCounter=ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        finally{
            if (ps!=null){
                ps.close();
            }if(c!=null){
                c.close();
            }
        }
        return recordCounter;
    }
}