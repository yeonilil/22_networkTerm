package network_term;
import java.awt.*;
import javax.swing.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.DataOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.net.*;
public class user extends JFrame implements ActionListener, MouseListener,ListSelectionListener {

	private JButton friend_add,setting_button,close_button,profile_button,search_button;
	private JButton onLinebutton,offLinebutton;
	private JLabel on,off,userName,userIntroduction;
	private Font on1,off1,userName1,userIntroduction1,list,publicdt;
	private JList onlineList,offlineList;
	private JPopupMenu menu;
	JMenuItem pfPop,sendPop,chatPop,filePop;

	//공공데이터````````````````````````````````````````````````````````````````````````````````
	private Image 맑음 = new ImageIcon(Board.class.getResource("../network_img/맑음.png")).getImage();//배경이미지
	private Image 비 = new ImageIcon(Board.class.getResource("../network_img/비.png")).getImage();//배경이미지
	private Image 흐리고비 = new ImageIcon(Board.class.getResource("../network_img/흐리고비.png")).getImage();//배경이미지
	private Image 구름많음 = new ImageIcon(Board.class.getResource("../network_img/구름많음.png")).getImage();//배경이미지
	private Image 눈비 = new ImageIcon(Board.class.getResource("../network_img/눈비.png")).getImage();//배경이미지
	//private Image snow = new ImageIcon(Board.class.getResource("../workshop.img/snow.png")).getImage();//배경이미지

	/*생성자*/


	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/user";
	String id = "root";
	String pwd = "1234";
	Connection con = null;
	// tag값의 정보를 가져오는 메소드(공공데이터)
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if(nValue == null) 
			return null;
		return nValue.getNodeValue();
	}
	//------
	DataOutputStream out;
	Socket socket;
	String Info;
	String infoname;
	String infocom;
	String infoid;
	String[] name=new String[50]; 
	String [] userid=new String[50];
	String [] password=new String[50];
	String [] introduce=new String[50];

	int [] onOff= new int[50];
	int [] user_id=new int[50];

	String user_name,user_introduction;
	public user(Socket socket,String s){
		this.socket = socket;
		Info= s;
		String arr[] =Info.split(",");
		infoname = arr[0];
		infoid = arr[1];
		infocom = arr[4];
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pwd);
		}catch(Exception e){
			e.printStackTrace();
			try{
				con.close();
			}catch(SQLException e1){

			}
		}

		setSize(500, 780);
		setTitle("user main");
		//getContentPane().setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(user.EXIT_ON_CLOSE);

		//메뉴바

		JPanel menubar = new JPanel();
		menubar.setBackground(Color.WHITE);
		menubar.setPreferredSize(new Dimension(450, 70));
		menubar.setLayout(null);

		ImageIcon friendbt= new ImageIcon("C:\\Users\\pc\\IdeaProjects\\multichat\\src\\network_img\\add_friend.png");
		Image friendbt_png = friendbt.getImage();  //ImageIcon을 Image로 변환.
		Image friendbt_png2 = friendbt_png.getScaledInstance(38, 38, java.awt.Image.SCALE_SMOOTH);
		ImageIcon fb = new ImageIcon(friendbt_png2); //Image로 ImageIcon 생성

		friend_add=new JButton(fb);
		friend_add.setPreferredSize(new Dimension(55, 55));
		friend_add.setBorderPainted(false);
		friend_add.setContentAreaFilled(false);
		friend_add.setFocusPainted(false);
		friend_add.addActionListener(this);
		friend_add.setBounds(10, 12, 55, 55); 

		friend_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFriend a1 = new AddFriend();
				a1.setVisible(true);
			}
		});

		ImageIcon settingbt= new ImageIcon("C:\\Users\\pc\\IdeaProjects\\multichat\\src\\network_img\\setting.png");
		Image settingbt_png = settingbt.getImage();  //ImageIcon을 Image로 변환.
		Image settingbt_png2 = settingbt_png.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		ImageIcon st = new ImageIcon(settingbt_png2); //Image로 ImageIcon 생성

		setting_button=new JButton(st);
		setting_button.setPreferredSize(new Dimension(55, 55));
		setting_button.setBorderPainted(false);
		setting_button.setContentAreaFilled(false);
		setting_button.setFocusPainted(false);
		setting_button.addActionListener(this);
		setting_button.setBounds(53, 10, 55, 55); 

		setting_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Setting a2 = new Setting();
				a2.setVisible(true);
			}
		});

		ImageIcon searchbt= new ImageIcon("C:\\Users\\pc\\IdeaProjects\\multichat\\src\\network_img\\search_button.png");
		Image searchbt_png = searchbt.getImage();  //ImageIcon을 Image로 변환.
		Image searchbt_png2 = searchbt_png.getScaledInstance(225, 45, java.awt.Image.SCALE_SMOOTH);
		ImageIcon sc = new ImageIcon(searchbt_png2); //Image로 ImageIcon 생성

		search_button=new JButton(sc);
		//search_button.setPreferredSize(new Dimension(55, 55));
		search_button.setBorderPainted(false);
		search_button.setContentAreaFilled(false);
		search_button.setFocusPainted(false);
		search_button.addActionListener(this);
		search_button.setBounds(80, 18, 320, 40); 

		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search a3 = new Search();
				a3.setVisible(true);
			}
		});

		ImageIcon closebt= new ImageIcon("C:\\Users\\pc\\IdeaProjects\\multichat\\src\\network_img\\close.png");
		Image closebt_png = closebt.getImage();  //ImageIcon을 Image로 변환.
		Image closebt_png2 = closebt_png.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		ImageIcon cb = new ImageIcon(closebt_png2); //Image로 ImageIcon 생성

		close_button=new JButton(cb);
		close_button.setPreferredSize(new Dimension(55, 55));
		close_button.setBorderPainted(false);
		close_button.setContentAreaFilled(false);
		close_button.setFocusPainted(false);
		close_button.addActionListener(this);
		close_button.setBounds(390, 10, 55, 55); 

		close_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
				if(answer==JOptionPane.CLOSED_OPTION) {
				}
				else if (answer==JOptionPane.YES_OPTION) {
					dispose();
				}
				else {

				}
			}
		});
		//유저 프로필/ 한줄소개/ 유저 아이디 / 유저 이름
		JPanel userpanel = new JPanel();
		userpanel.setBackground(Color.WHITE);
		userpanel.setPreferredSize(new Dimension(450, 120));
		userpanel.setLayout(null);

		ImageIcon profile= new ImageIcon("C:\\Users\\pc\\IdeaProjects\\multichat\\src\\network_img\\user_default.png");
		Image profile_png = profile.getImage();  //ImageIcon을 Image로 변환.
		Image profile_png2 = profile_png.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon pf = new ImageIcon(profile_png2); //Image로 ImageIcon 생성

		profile_button=new JButton(pf);
		profile_button.setPreferredSize(new Dimension(55, 55));
		profile_button.setBorderPainted(false);
		profile_button.setContentAreaFilled(false);
		profile_button.setFocusPainted(false);
		profile_button.addActionListener(this);
		profile_button.setBounds(0, 0, 120, 120); 

		profile_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile a4 = new Profile();
				//a3.setVisible(true);
			}
		});

		userName1=new Font ("나눔스퀘어",Font.BOLD,15);
		userIntroduction1=new Font ("나눔스퀘어",Font.PLAIN,12);
		//////로그인 접속 후 이름 받아와야 함!!!!!!!!!!!!!!!!!!!!!!
		user_name= infoname;
		userName=new JLabel(user_name);
		//////로그인 접속 후 한줄소개 받아와야 함!!!!!!!!!!!!!!!!!!!!!!
		user_introduction= infocom;
		userIntroduction=new JLabel(user_introduction);

		userName.setFont(userName1);
		userName.setBounds(130, -10, 120, 120); 
		userIntroduction.setFont(userIntroduction1);
		userIntroduction.setBounds(130, 12, 200, 120); 

		//친구 목록 Panel
		JPanel friendPanel = new JPanel();
		friendPanel.setBackground(Color.WHITE);
		friendPanel.setPreferredSize(new Dimension(450, 450));
		friendPanel.setLayout(null);

		ImageIcon onlinebt= new ImageIcon("C:\\Users\\pc\\IdeaProjects\\multichat\\src\\network_img\\online.png");
		Image onlinebt_png = onlinebt.getImage();  //ImageIcon을 Image로 변환.
		Image onlinebt_png2 = onlinebt_png.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon ol = new ImageIcon(onlinebt_png2); //Image로 ImageIcon 생성

		onLinebutton=new JButton(ol);
		//search_button.setPreferredSize(new Dimension(55, 55));
		onLinebutton.setBorderPainted(false);
		onLinebutton.setContentAreaFilled(false);
		onLinebutton.setFocusPainted(false);
		onLinebutton.addActionListener(this);
		onLinebutton.setBounds(0, 15, 30, 30); 

		on1=new Font ("나눔스퀘어",Font.PLAIN,18);
		off1=new Font ("나눔스퀘어",Font.PLAIN,18);
		list=new Font ("나눔스퀘어",Font.PLAIN,14);
		on=new JLabel("  Online");
		off=new JLabel("  Offline");

		ImageIcon offlinebt= new ImageIcon("C:\\Users\\pc\\IdeaProjects\\multichat\\src\\network_img\\offline.png");
		Image offlinebt_png = offlinebt.getImage();  //ImageIcon을 Image로 변환.
		Image offlinebt_png2 = offlinebt_png.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon ofl = new ImageIcon(offlinebt_png2); //Image로 ImageIcon 생성

		offLinebutton=new JButton(ofl);
		//search_button.setPreferredSize(new Dimension(55, 55));
		offLinebutton.setBorderPainted(false);
		offLinebutton.setContentAreaFilled(false);
		offLinebutton.setFocusPainted(false);
		offLinebutton.addActionListener(this);
		offLinebutton.setBounds(0, 245, 30, 30); 

		friendPanel.add(onLinebutton);
		friendPanel.add(offLinebutton);

		addMouseListener(this);
		on.setFont(on1);
		on.setBounds(25, -30, 120, 120); 
		off.setFont(off1);
		off.setBounds(25, 200, 120, 120); 

		JTextArea onta = new JTextArea();
		onta.setFont(list);

		//scrollOn.setViewportView(onta);
		//팝업메뉴 생성
		menu= new JPopupMenu();
		JMenuItem pfPop = new JMenuItem("친구 정보 (프로필) 보기");
		pfPop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("프로필보기");

				//실제 동작 추가
			}
		});
		sendPop = new JMenuItem("쪽지 보내기");
		sendPop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("쪽지보내기");

				//실제 동작 추가
			}
		});
		chatPop = new JMenuItem("1:1 채팅하기");
		chatPop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					out = new DataOutputStream(socket.getOutputStream());
					String msg = "4,tres,입장하셨습니다";
					out.writeUTF(msg);
				}catch(Exception ek){
					ek.printStackTrace();
				}
				System.out.println("1:1 채팅");

				//실제 동작 추가
			}
		});
		filePop = new JMenuItem("파일 보내기");
		filePop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("파일 보내기");

				//실제 동작 추가
			}
		});
		//리스트 생성-------------------------------------------------------------------
		DefaultListModel model = new DefaultListModel();
		onlineList = new JList();
		JScrollPane scrollOn = new JScrollPane(onlineList);
		onlineList.setModel(model);
		onlineList.setBounds(20,50,400,170);
		onlineList.setFont(list);
		onlineList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		onlineList.addListSelectionListener(this);

		friendPanel.add(onlineList);

		DefaultListModel model2 = new DefaultListModel();
		offlineList = new JList();
		JScrollPane scrollOff = new JScrollPane(offlineList);
		offlineList.setModel(model2);
		offlineList.setBounds(20,280,400,150);
		offlineList.setFont(list);
		offlineList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		offlineList.addListSelectionListener(this);


		friendPanel.add(scrollOn);
		friendPanel.add(scrollOff);

		String sql = "SELECT * FROM member";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		onta.append("\n");
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

				System.out.println("피드 데이터 성공");
				System.out.println("> 유저이름: " + name[i]);
				System.out.println("> 한줄소개: " + introduce[i]);
				System.out.println("> 온라인/오프라인 여부: " + onOff[i]);

				if(onOff[i]==0) { //오프라인
					//	offta.append("  "+name[i]+"  -  "+introduce[i]+"\n");
					model2.insertElementAt(name[i]+"  -     "+introduce[i], i);
				}
				else { //온라인

					model.insertElementAt(name[i]+"  -     "+introduce[i], i);
				}
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


		offlineList.setBounds(20,280,400,150);

		offlineList.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent me) {
				// if right mouse button clicked (or me.isPopupTrigger())
				if (SwingUtilities.isRightMouseButton(me)
						&& !offlineList.isSelectionEmpty()
						&& offlineList.locationToIndex(me.getPoint())
						== offlineList.getSelectedIndex()) {
					menu.show(offlineList, me.getX(), me.getY());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		}
				);

		menu.add(pfPop);
		menu.add(sendPop);
		menu.add(chatPop);
		menu.add(filePop);


		//공공데이터 panel------------------------------------------------------


		JPanel publicPanel = new JPanel();
		publicPanel.setBackground(Color.WHITE);
		publicPanel.setPreferredSize(new Dimension(450, 40));
		publicPanel.setLayout(new FlowLayout());

		try{

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("HHmm");

			String strtime = formatter.format(calendar.getTime());

			Date date = new Date();
			String today = date.getDate();

			int num = Integer.parseInt(strtime)-100;
			int day = Integer.parseInt(today);

			if(num < 700) {
				day = day -1;
				num = 2400;

				System.out.println("업데이트 전 날짜 : " + day);
				System.out.println("업데이트 전 시간 : " + num);   
			}
			else
			{
				System.out.println("오늘 날짜 : " + day);
				System.out.println("지금 시간 : " + num);            
			}
			System.out.println("오늘 날짜 : " + day);
			System.out.println("지금 시간 : " + num);


			String url ="http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=OloI0noYmGlwBZno0d7oflHECyAWh97O4aGT%2F%2Fsq1GnJY6wSKkTolA8g4C714hO96IHdS62LWIszL%2FkkK4fWPw%3D%3D&pageNo=1&numOfRows=1000&dataType=XML&base_date="+day+"&base_time=" + num + "&nx=58&ny=127";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			// root tag 
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			System.out.println("===========================================");

			NodeList nList2 = doc.getElementsByTagName("item");
			System.out.println("파싱할 리스트 수 : "+ nList2.getLength());


			String 습도 = null;
			String 기온 = null;
			String 풍속 = null;
			String 풍향 = null;
			String 강수량 = null;

			JLabel Label = new JLabel();


			for(int temp = 0; temp < nList2.getLength(); temp++){
				Node nNode = nList2.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){

					Element eElement = (Element) nNode;


					if(temp == 0){
						//						System.out.println("강수형태 : " + getTagValue("obsrValue", eElement) + "");
						if(Integer.parseInt(getTagValue("obsrValue", eElement)) == 0) {


							// 아이콘 생성
							ImageIcon icon1 = new ImageIcon(
									Board.class.getResource("../network_img/맑음.png")
									);

							// 라벨에 아이콘(이미지) 설정
							Label.setIcon(icon1);
							// 라벨 설정(크기, 정렬...)
							Label.setBounds(12, 498, 62, 55);
							Label.setHorizontalAlignment(JLabel.CENTER);


							//프레임에 컴포넌트 추가
							publicPanel.add(Label);

							// 프레임 보이기 지정
							setVisible(true);
						}
						if(Integer.parseInt(getTagValue("obsrValue", eElement)) == 1 || Integer.parseInt(getTagValue("obsrValue", eElement)) == 4) {


							// 아이콘 생성
							ImageIcon icon1 = new ImageIcon(
									Board.class.getResource("../network_img/흐리고비.png")
									);

							// 라벨에 아이콘(이미지) 설정
							Label.setIcon(icon1);
							// 라벨 설정(크기, 정렬...)
							Label.setBounds(12, 498, 62, 55);
							Label.setHorizontalAlignment(JLabel.CENTER);


							//프레임에 컴포넌트 추가
							getContentPane().add(Label);

							// 프레임 보이기 지정
							setVisible(true);
						}
						if(Integer.parseInt(getTagValue("obsrValue", eElement)) == 5 || Integer.parseInt(getTagValue("obsrValue", eElement)) == 6) {


							// 아이콘 생성
							ImageIcon icon1 = new ImageIcon(
									Board.class.getResource("../network_img/흐리고비.png")
									);

							// 라벨에 아이콘(이미지) 설정
							Label.setIcon(icon1);
							// 라벨 설정(크기, 정렬...)
							Label.setBounds(12, 498, 62, 55);
							Label.setHorizontalAlignment(JLabel.CENTER);


							//프레임에 컴포넌트 추가
							getContentPane().add(Label);

							// 프레임 보이기 지정
							setVisible(true);
						}
						if(Integer.parseInt(getTagValue("obsrValue", eElement)) == 2 ||Integer.parseInt(getTagValue("obsrValue", eElement)) == 3) {


							// 아이콘 생성
							ImageIcon icon1 = new ImageIcon(
									Board.class.getResource("../network_img/눈비.png")
									);

							// 라벨에 아이콘(이미지) 설정
							Label.setIcon(icon1);
							// 라벨 설정(크기, 정렬...)
							Label.setBounds(12, 498, 62, 55);
							Label.setHorizontalAlignment(JLabel.CENTER);


							//프레임에 컴포넌트 추가
							getContentPane().add(Label);

							// 프레임 보이기 지정
							setVisible(true);

						}
					}	

					if(temp == 1){
						습도 = "습도 : " + getTagValue("obsrValue", eElement) + "%    ";

					}

					if(temp == 2){
						
						 강수량 = "강수량 : "+getTagValue("obsrValue", eElement) + "mm  ";
						
						//System.out.println("1시간 강수량 범주 : " + getTagValue("obsrValue", eElement) + "(1 mm)");
						if(Integer.parseInt(getTagValue("obsrValue", eElement)) >= 15) {

							// 아이콘 생성
							ImageIcon icon1 = new ImageIcon(
									Board.class.getResource("../network_img/흐리고비.png")
									);

							// 라벨에 아이콘(이미지) 설정
							Label.setIcon(icon1);
							// 라벨 설정(크기, 정렬...)
							Label.setBounds(12, 498, 62, 55);
							Label.setHorizontalAlignment(JLabel.CENTER);


							//프레임에 컴포넌트 추가
							publicPanel.add(Label);

							// 프레임 보이기 지정
							setVisible(true);
						}
					}

					if(temp == 3){
						기온 = "기온 : " + getTagValue("obsrValue", eElement) + "℃    ";
						
		                  if(Float.parseFloat(getTagValue("obsrValue", eElement)) > 12) {


								// 아이콘 생성
								ImageIcon icon1 = new ImageIcon(
										Board.class.getResource("../network_img/맑음.png")
										);

								// 라벨에 아이콘(이미지) 설정
								Label.setIcon(icon1);
								// 라벨 설정(크기, 정렬...)
								Label.setBounds(12, 498, 62, 55);
								Label.setHorizontalAlignment(JLabel.CENTER);


								//프레임에 컴포넌트 추가
								publicPanel.add(Label);

								// 프레임 보이기 지정
								setVisible(true);
		                   }

						
						
		                  if(Float.parseFloat(getTagValue("obsrValue", eElement)) <= 12.50) {


								// 아이콘 생성
								ImageIcon icon1 = new ImageIcon(
										Board.class.getResource("../network_img/눈비.png")
										);

								// 라벨에 아이콘(이미지) 설정
								Label.setIcon(icon1);
								// 라벨 설정(크기, 정렬...)
								Label.setBounds(12, 498, 62, 55);
								Label.setHorizontalAlignment(JLabel.CENTER);


								//프레임에 컴포넌트 추가
								publicPanel.add(Label);

								// 프레임 보이기 지정
								setVisible(true);
		                   }
		                  
		            

						
					}

					if(temp == 5){풍향 = "풍향 : "+getTagValue("obsrValue", eElement) + "deg    ";
					}

					if(temp == 7){풍속 = "풍속 : "+getTagValue("obsrValue", eElement) + "m/s    ";
					}
				}
			}	


			System.out.println(기온 + 습도 + 풍속 + 강수량);

			Label.setText(" "+기온 + 습도 + 풍속 + 강수량);
			JTextArea jf = new JTextArea(기온 + 습도 + 풍속 + 강수량);
			Label.setBounds(12, 498, 362, 55);
			jf.append(" "+기온 + 습도 + 풍속 + 강수량);
			//폰트 설정
			publicdt=new Font ("나눔스퀘어",Font.PLAIN,14);
			Label.setFont(publicdt);
			publicPanel.add(jf);
			Label.add(jf);


			setVisible(true);



		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end

		add(menubar);
		menubar.add(friend_add);
		menubar.add(setting_button);
		menubar.add(search_button);
		menubar.add(close_button);

		add(userpanel);
		userpanel.add(profile_button);
		userpanel.add(userName);
		userpanel.add(userIntroduction);

		add(friendPanel);
		friendPanel.add(on);
		friendPanel.add(off);
		friendPanel.add(offlineList);

		add(publicPanel);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	//	public void mouseClicked(MouseEvent e) {
	//		checkPopup(e);
	//	}
	//	public void mousePressed(MouseEvent e) {
	//		checkPopup(e);
	//	}
	//	public void mouseReleased(MouseEvent e) {
	//		checkPopup(e);
	//	}
	//
	//	private void checkPopup(MouseEvent e) {
	//		if (e.isPopupTrigger()) {
	//			menu.show(user.this, e.getX(), e.getY());
	//		}
	//	}
	//	public void mouseEntered(MouseEvent e) {
	//	}
	//	public void mouseExited(MouseEvent e) {
	//	}
	//	@Override
	//	public void valueChanged(ListSelectionEvent e) {
	//		
	//	for(int i=0;i<50;i++) {
	//		if(offlineList.getSelectedIndex() == i) {
	//			  
	//		}
	//	}
	//
	//	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
