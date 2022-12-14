package network_term;
import java.awt.*;
import javax.swing.*;


import java.sql.*;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Scanner;
public class Setting extends JFrame implements ActionListener{
	private JTextField input1,input2,input3,input4;
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/user";
	String id = "root";
	String pwd = "1234";
	Connection con = null;
	
	String[] name=new String[50]; 
	String [] userid=new String[50];
	String [] password=new String[50];
	String [] introduce=new String[50];
	String [] email=new String[50];
	String [] phonenum=new String[50];
	
	int [] onOff= new int[50];
	int [] user_id=new int[50];
	int [] friend = new int [50];
	
	Setting(){
		
		//데베 연결 -->DTO/DAO로 변경할 예정
		try {
			System.out.println("networkDB 연결중");
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("드라이버 로딩 성공");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 ");
			try {
				con.close();
			} catch (SQLException e1) {
			}
		}
		
		//데베 데이터 가져오기
		//유저 정보만 !!!!!!! 배열도 필요 XXXXXXXXXXXXX
		String sql = "SELECT * FROM member";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		int i=0;
		try {

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				name[i] = rs.getString("name");
				userid[i] = rs.getString("id");
				password[i] = rs.getString("password");
				onOff[i] = rs.getInt("on/off");
				introduce[i] = rs.getString("introduce");
				user_id[i]= rs.getInt("user_id");
				email[i]= rs.getString("email");
				phonenum[i]= rs.getString("phonenum");
				friend[i]= rs.getInt("friend");
				
				i++;
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		
		setSize(500, 460);
		setTitle("Setting");
		setLocationRelativeTo(null);
		//getContentPane().setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		JPanel setPanel = new JPanel();
		setPanel.setBackground(Color.WHITE);
		setPanel.setPreferredSize(new Dimension(500, 460));
		setPanel.setLayout(null);
		
		//프로필
		ImageIcon profile= new ImageIcon("C:\\Users\\82102\\eclipse-workspace\\network_term\\src\\network_img\\user_default.png");
		Image profile_png = profile.getImage();  //ImageIcon을 Image로 변환.
		Image profile_png2 = profile_png.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon pf = new ImageIcon(profile_png2); //Image로 ImageIcon 생성
		
		JButton profile_button=new JButton(pf);
		profile_button.setBorderPainted(false);
		profile_button.setContentAreaFilled(false);
		profile_button.setFocusPainted(false);
		profile_button.addActionListener(this);
		profile_button.setBounds(18,35,100, 100); 
		setPanel.add(profile_button);
		
		//폰트
		Font realfont=new Font ("나눔스퀘어",Font.PLAIN,13);
		
		//유저 이름
		JLabel label1=new JLabel("MY"); 
		setPanel.add(label1);
		label1.setFont(realfont);
		label1.setBounds(130, 10, 300, 35); 
		
		input1 = new JTextField(name[0]);////유저 이름 받아와서
		input1.addActionListener(this);
		setPanel.add(input1);
		input1.setFont(realfont);
		input1.setBounds(130, 40, 300, 35); 
		
		//유저 아이디
		JLabel label2=new JLabel("MY ID");
		setPanel.add(label2);
		label2.setFont(realfont);
		label2.setBounds(130, 80, 300, 35); 
		
		input2 = new JTextField(userid[0]); //유저 아이디 받아와서
		input2.addActionListener(this); 
		setPanel.add(input2);
		input2.setFont(realfont);
		input2.setBounds(130, 110, 300, 35); 
		
		// 유저 이메일
		JLabel label3=new JLabel("MY EMAIL");
		setPanel.add(label3);
		label3.setFont(realfont);
		label3.setBounds(130, 150, 300, 35); 
		
		input3 = new JTextField(email[0]); //이메일 받아와서
		input3.addActionListener(this);
		setPanel.add(input3);
		input3.setFont(realfont);
		input3.setBounds(130, 180, 300, 35); 
		
		//유저 전화번호
		JLabel label4=new JLabel("MY PHONE");
		setPanel.add(label4);
		label4.setFont(realfont);
		label4.setBounds(130, 220, 300, 35); 
		
		input4 = new JTextField(phonenum[0]); //폰넘버 받아와서
		input4.addActionListener(this);
		setPanel.add(input4);
		input4.setFont(realfont);
		input4.setBounds(130, 250, 300, 35); 
				
				
		
		add(setPanel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			Setting JFramest = new Setting();
			JFramest.setVisible(true);
	}
}
