package network_term;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

public class Search extends JFrame implements ActionListener,ListSelectionListener{
	private JTextField searchwindow;
	private JButton search_button;
	private Font result;
	private JTextField input;
	private JList resultList;
	private JButton friend_add,setting_button;
	private DefaultListModel modelresult;
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/user";
	String id = "root";
	String pwd = "1234";
	Connection con = null;
	
	String[] name=new String[100]; 
	String [] userid=new String[100];
	String [] password=new String[100];
	String [] introduce=new String[100];
	
	int [] onOff= new int[100];
	int [] user_id=new int[100];
	int count=0;
	int resultcount=0;
	
	Search(){
		
		result=new Font ("나눔스퀘어",Font.PLAIN,15);
		
		
		//데베 연결
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

		//사용자들 정보 불러오기
		String sql = "SELECT * FROM member";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		int i=0;
		try {

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count++;
				
				name[i] = rs.getString("name");
				userid[i] = rs.getString("id");
				password[i] = rs.getString("password");
				onOff[i] = rs.getInt("on/off");
				introduce[i] = rs.getString("introduce");
				user_id[i]= rs.getInt("user_id");
				
				System.out.println("피드 데이터 성공");
				System.out.println("> 유저이름: " + name[i]);
				System.out.println("> 한줄소개: " + introduce[i]);
				System.out.println("> 온라인/오프라인 여부: " + onOff[i]);
				
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
		
		setSize(450, 450);
		setTitle("Search");
		//getContentPane().setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		
		//검색창 panel
		
		JPanel window = new JPanel();
		window.setBackground(Color.WHITE);
		window.setPreferredSize(new Dimension(450, 450));
		window.setLayout(null);
		
		ImageIcon searchbt= new ImageIcon("C:\\Users\\82102\\eclipse-workspace\\network_term\\src\\network_img\\search22.png");
		Image searchbt_png = searchbt.getImage();  //ImageIcon을 Image로 변환.
		Image searchbt_png2 = searchbt_png.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		ImageIcon sc = new ImageIcon(searchbt_png2); //Image로 ImageIcon 생성
		
		search_button=new JButton(sc);
		//search_button.setPreferredSize(new Dimension(55, 55));
		search_button.setBorderPainted(false);
		search_button.setContentAreaFilled(false);
		search_button.setFocusPainted(false);
		search_button.addActionListener(this);
		search_button.setBounds(40, 10, 50, 50); 
		
		input = new JTextField(); //검색창
		input.setFont(result);
		input.addActionListener(this);
		window.add(input);
		input.setBounds(110, 20, 270, 40); 
		
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search a3 = new Search();
				a3.setVisible(true);
			}
		});
		
		window.add(search_button);
		searchwindow=new JTextField();
		
		// 검색결과---------------------------------------------------
		//리스트 생성
		
		modelresult = new DefaultListModel();
		resultList = new JList();
		JScrollPane scrollOn = new JScrollPane(resultList);
		resultList.setModel(modelresult);
		resultList.setBounds(50,80,200,170);
		resultList.setFont(result);
		resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultList.addListSelectionListener(this);
		
		window.add(resultList);
		window.add(scrollOn);
		
		
		//검색 나오면 친구등록 창 (addfriend) or 상세정보 (setting) 보는 부분-----------------------------------
		//-------------------------------------------------------------------
		
		ImageIcon friendbt= new ImageIcon("C:\\Users\\82102\\eclipse-workspace\\network_term\\src\\network_img\\addFriend2.png");
		Image friendbt_png = friendbt.getImage();  //ImageIcon을 Image로 변환.
		Image friendbt_png2 = friendbt_png.getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon fb = new ImageIcon(friendbt_png2); //Image로 ImageIcon 생성
		
		friend_add=new JButton(fb);
		friend_add.setPreferredSize(new Dimension(55, 55));
		friend_add.setBorderPainted(false);
		friend_add.setContentAreaFilled(false);
		friend_add.setFocusPainted(false);
		friend_add.addActionListener(this);
		friend_add.setBounds(110, 270, 90, 60); 
		
		friend_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFriend a1 = new AddFriend();
				a1.setVisible(true);
			}
		});
		
		ImageIcon settingbt= new ImageIcon("C:\\Users\\82102\\eclipse-workspace\\network_term\\src\\network_img\\info.png");
		Image settingbt_png = settingbt.getImage();  //ImageIcon을 Image로 변환.
		Image settingbt_png2 = settingbt_png.getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon st = new ImageIcon(settingbt_png2); //Image로 ImageIcon 생성
		
		setting_button=new JButton(st);
		setting_button.setPreferredSize(new Dimension(55, 55));
		setting_button.setBorderPainted(false);
		setting_button.setContentAreaFilled(false);
		setting_button.setFocusPainted(false);
		setting_button.addActionListener(this);
		setting_button.setBounds(220, 270, 100, 60); 
		
		setting_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Setting a2 = new Setting();
				a2.setVisible(true);
			}
		});
		
		window.add(friend_add);
		window.add(setting_button);
		add(window);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//부분 검색 && 검색 결과 출력------------------------------------------------------
		if (e.getSource() == input) {
			String inputStr = input.getText();
		//	String [] resultStr = {};
			//name으로 search
			for(int i=0;i<count;i++) {
				if(name[i].contains(inputStr)) {
					System.out.println("검색 결과 : "+name[i]);
					modelresult.insertElementAt(name[i]+"  "+userid[i],resultcount);
					resultcount++;
				}
				else {
					continue;
				}
			}
			//id로 search
			for(int j=0;j<count;j++) {
				if(userid[j].contains(inputStr)) {
					System.out.println("검색 결과 : "+name[j]);
					modelresult.insertElementAt(name[j]+"  "+userid[j],resultcount);
					resultcount++;
				}
				else {
					continue;
				}
				
			}
			
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			Search JFrameSearch = new Search();
			JFrameSearch.setVisible(true);
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
