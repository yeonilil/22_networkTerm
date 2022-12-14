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

public class Profile extends JFrame implements ActionListener{
	
	private JButton profile_button,edit_button1,edit_button2;
	private JTextField input1,input2;
	
	Profile(){
		setSize(410, 350);
		setTitle("Setting");
		//getContentPane().setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		
		//이름 변경 panel
		
		JPanel setPanel = new JPanel();
		setPanel.setBackground(Color.WHITE);
		setPanel.setPreferredSize(new Dimension(450, 400));
		setPanel.setLayout(null);
	
	
		//프로필 사진띄우고
		ImageIcon profile= new ImageIcon("C:\\Users\\82102\\eclipse-workspace\\network_term\\src\\network_img\\user_default.png");
		Image profile_png = profile.getImage();  //ImageIcon을 Image로 변환.
		Image profile_png2 = profile_png.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon pf = new ImageIcon(profile_png2); //Image로 ImageIcon 생성
		
		profile_button=new JButton(pf);
		profile_button.setBorderPainted(false);
		profile_button.setContentAreaFilled(false);
		profile_button.setFocusPainted(false);
		profile_button.addActionListener(this);
		profile_button.setBounds(145, 30, 150, 150); 
		setPanel.add(profile_button);
		
		
		// textfield 띄우고 (default 값을 현재 이름으로)
		// DBusername  기본값으로 !!!! 가져와야함!!!!!
		input1 = new JTextField("곽서연");
		input1.addActionListener(this);
		setPanel.add(input1);
		input1.setBounds(60, 190, 300, 35); 
		
		
		// 이름 수정 버튼
		
		ImageIcon edit_bt= new ImageIcon("C:\\Users\\82102\\eclipse-workspace\\network_term\\src\\network_img\\edit button.png");
		Image edit_bt_png = edit_bt.getImage();  //ImageIcon을 Image로 변환.
		Image edit_bt_png2 = edit_bt_png.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon ed = new ImageIcon(edit_bt_png2); //Image로 ImageIcon 생성
		
		edit_button1=new JButton(ed);
		//search_button.setPreferredSize(new Dimension(55, 55));
		edit_button1.setBorderPainted(false);
		edit_button1.setContentAreaFilled(false);
		edit_button1.setFocusPainted(false);
		edit_button1.addActionListener(this);
		edit_button1.setBounds(375, 190, 30, 30); 
		setPanel.add(edit_button1);
		
		edit_button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "변경하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
				if(answer==JOptionPane.CLOSED_OPTION) {
					
				}
				else if (answer==JOptionPane.YES_OPTION) { //yes 누를시
					//DB변경
					String newName = input1.getText();
					System.out.println(newName);
					
				}
				else {
					
				}
			}
		});

	
		// textfield 띄우고
		////DBuserintroduction  기본값으로 변경 필요!!!!!!!!!!!!!!!!!!!!!!!!!! 
		input2 = new JTextField("곽서연입니다");
		input2.addActionListener(this);
		setPanel.add(input2);
		input2.setBounds(60, 240, 300, 35); 
		
				
	    // 한줄소개 수정 버튼
		edit_button2=new JButton(ed);
		//search_button.setPreferredSize(new Dimension(55, 55));
		edit_button2.setBorderPainted(false);
		edit_button2.setContentAreaFilled(false);
		edit_button2.setFocusPainted(false);
		edit_button2.addActionListener(this);
		edit_button2.setBounds(375, 240, 30, 30); 
		setPanel.add(edit_button2);
		
		edit_button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "변경하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
				if(answer==JOptionPane.CLOSED_OPTION) {
					
				}
				else if (answer==JOptionPane.YES_OPTION) { //yes 누를시
					//DB변경
					String newIntroduction = input2.getText();
					System.out.println(newIntroduction);
					
				}
				else {
					
				}
				
			}
		});
		
				
	    //팝업창 (정말 변경하시겠습니까?)
				
	     //Y누를시 DB변경
				
		add(setPanel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			Profile JFramepf = new Profile();
			JFramepf.setVisible(true);
	}
}
