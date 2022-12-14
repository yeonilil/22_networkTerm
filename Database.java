package network_term;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://localhost:3306/user";
    String user = "root";
    String passwd = "1234";

    Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            stmt = con.createStatement();
            System.out.println("MySQL 서버 연동 성공");
        } catch (Exception e) {
            System.out.println("MySQL 서버 연동 실패 : " + e.toString());
        }
    }

    boolean logincheck(String il, String pl) {
        boolean passlogin = false;
        String id = il;
        String pw = pl;
        try {
            String checkingStr = "SELECT password FROM member WHERE id='" + id + "'";
            ResultSet result = stmt.executeQuery(checkingStr);

            int count = 0;
            while (result.next()) {
                if (pw.equals(result.getString("password"))) {
                    passlogin = true;
                    System.out.println("로그인 성공");
                } else {
                    passlogin = false;
                    System.out.println("로그인 실패");
                }
                count++;
            }
        } catch (Exception e) {
            passlogin = false;
            System.out.println("로그인 실패 : " + e.toString());
        }
        return passlogin;
    }

    boolean joincheck(String ij, String pj, String nm,String dt,String sx,String em) {
        boolean pass = false;
        String id = ij;
        String pw = pj;
        String name = nm;
        String date = dt;
        String sex = sx;
        String email = em;
        try {
            String insertingStr = "insert into member values('" + id + "','" + pw + "','" + name + "','" + date + "','" +sex + "','" + email +"')";
            stmt.executeUpdate(insertingStr);

            pass = true;
            System.out.println("회원가입 성공");
        } catch (Exception e) {
            pass = false;
            System.out.println("회원가입 실패 : " + e.toString());
        }
        return pass;
    }
    public ArrayList<usermodel> useread(String id){
        ArrayList<usermodel> arr = new ArrayList<usermodel>();
        try{
            String name;
            String uid;
            String password;
            String on;
            String intro;

            String readingStr = "select * from member where id = '"+id+"';";
            rs = stmt.executeQuery(readingStr);
            while(rs.next()){
                arr.add(new usermodel(rs.getString(1),rs.getString(2),rs.getString(3),Integer.toString(rs.getInt(6)),rs.getString(7)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return arr;
    }
    public void online(String id){
        try{
            String sql ="update member set on/off = 1 where id= '"+id+"';";
            stmt.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}