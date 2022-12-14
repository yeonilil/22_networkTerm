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

public class AddFriend extends JFrame implements ActionListener{
	private JTextField input1,input2;
	
	AddFriend(){
		setSize(500, 300);
		setTitle("AddFriend");
		//getContentPane().setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		JPanel setPanel = new JPanel();
		setPanel.setBackground(Color.WHITE);
		setPanel.setPreferredSize(new Dimension(500, 460));
		setPanel.setLayout(null);
		
		
		//폰트
		Font realfont=new Font ("나눔스퀘어",Font.PLAIN,13);
		Font bigfont=new Font ("나눔스퀘어",Font.BOLD,16);
		
		
		JLabel label0=new JLabel("[ ADD FRIEND ]"); 
		setPanel.add(label0);
		label0.setFont(bigfont);
		label0.setBounds(50, 13, 270, 35); 
		
		//연락처로 추가
		JLabel label1=new JLabel("연락처로 추가"); 
		setPanel.add(label1);
		label1.setFont(realfont);
		label1.setBounds(50, 50, 270, 35); 
		
		input1 = new JTextField();////유저 이름 받아와서
		input1.addActionListener(this);
		setPanel.add(input1);
		input1.setFont(realfont);
		input1.setBounds(50, 80, 270, 35); 
		
		//추가 버튼
		JButton addbt1=new JButton("추가");
		setPanel.add(addbt1);
		addbt1.setContentAreaFilled(false);
		addbt1.setFont(realfont);
		addbt1.setBounds(330, 80, 80, 35); 
		addbt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//친구 추가 기능 !!!!!!!!!!!!!!!!!!!!!!!!11
				String phoneinput = input1.getText();
			}
		});
		
		//아이디로 추가
		JLabel label2=new JLabel("아이디로 추가");
		setPanel.add(label2);
		label2.setFont(realfont);
		label2.setBounds(50, 120, 270, 35); 
		
		input2 = new JTextField(); //유저 아이디 받아와서
		input2.addActionListener(this); 
		setPanel.add(input2);
		input2.setFont(realfont);
		input2.setBounds(50, 150, 270, 35); 
		
		//추가 버튼
		JButton addbt2=new JButton("추가");
		setPanel.add(addbt2);
		addbt2.setContentAreaFilled(false);
		addbt2.setFont(realfont);
		addbt2.setBounds(330, 150, 80, 35); 
		addbt2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//친구 추가 기능 !!!!!!!!!!!!!!!!!!!!!!!!11
				String idinput = input2.getText();
				}
			});
		
		
		add(setPanel);

		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			AddFriend JFrameaf = new AddFriend();
			JFrameaf.setVisible(true);
	}
}
