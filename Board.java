package network_term;


//import UserImformation.Date;

import java.net.URL;
import java.net.URLEncoder;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import UserImformation.Date;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class Board extends JFrame{
	
	/*여기있는 이미지를 프레임*/
	private Image 맑음 = new ImageIcon(Board.class.getResource("../image/맑음.png")).getImage();//배경이미지
	private Image 비 = new ImageIcon(Board.class.getResource("../image/비.png")).getImage();//배경이미지
	private Image 흐리고비 = new ImageIcon(Board.class.getResource("../image/흐리고비.png")).getImage();//배경이미지
	private Image 구름많음 = new ImageIcon(Board.class.getResource("../image/구름많음.png")).getImage();//배경이미지
	private Image 눈비 = new ImageIcon(Board.class.getResource("../image/눈비.png")).getImage();//배경이미지
	private JTextField txtHihihiihi;
	/*생성자*/
	
	
	// tag값의 정보를 가져오는 메소드(공공데이터)
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if(nValue == null) 
			return null;
		return nValue.getNodeValue();
	}
	
	public Board() {
		
		setTitle("1");//타이틀
		setSize(400,600);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		getContentPane().setLayout(null);//레이아웃을 내맘대로 설정가능
		
		
		//(공공데이터)
		try{
			Date date = new Date();
			String today = date.getDate();
			System.out.println("오늘 날짜 : " + today);
			String url ="http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=OloI0noYmGlwBZno0d7oflHECyAWh97O4aGT%2F%2Fsq1GnJY6wSKkTolA8g4C714hO96IHdS62LWIszL%2FkkK4fWPw%3D%3D&pageNo=1&numOfRows=1000&dataType=XML&base_date="+today+"&base_time=0600&nx=58&ny=127";

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
					    	getContentPane().add(Label);

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
					    	getContentPane().add(Label);

					        // 프레임 보이기 지정
					        setVisible(true);
						}
						}
					
					if(temp == 3){
						기온 = "기온 : " + getTagValue("obsrValue", eElement) + "℃    ";
					}
		
					if(temp == 5){
						풍향 = "풍향 : "+getTagValue("obsrValue", eElement) + "deg    ";
					}

					if(temp == 7){
						풍속 = "풍속 : "+getTagValue("obsrValue", eElement) + "m/s    ";
					}
					
			        
				}// for end 1
			}	// for end 2
			
			// if end
			
			
			System.out.println(습도 + 기온 + 풍속 + 풍향);
			
			Label.setText(습도 + 기온 + 풍속 + 풍향);
			JTextArea jf = new JTextArea(습도 + 기온 + 풍속 + 풍향);
			Label.setBounds(12, 498, 362, 55);
			jf.append(습도 + 기온 + 풍속 + 풍향);
			getContentPane().add(jf);
			Label.add(jf);
			
			
			setVisible(true);
			
			

		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
	}
	

	

	public static void main(String[] args) {
		
		new Board();
		
	}	
}

	

