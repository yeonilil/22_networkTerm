package network_term;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class ChatingGui extends JFrame {
    private String panelName;
    private JTextArea textArea;
    private JButton sendButton;
    private JButton imgFileButton;
    private JTextPane jtp;
    private StyledDocument document;

    Color talkBackgroundColor = new Color(233, 242, 255);
    Color messageSendButtonColor = new Color(209, 206, 206);
    Socket socket;
    DataOutputStream out;
    String msg;
    String userID;
    String senderID;
    public ChatingGui(Socket socket,String userID,String msg) {
        this.socket = socket;
        this.userID = userID;
        this.msg = msg;
        setSize(400,630);
        setLocation(240,40);

        setBackground(talkBackgroundColor);
        getContentPane().setLayout(null);

        imgFileButton = showImgFileButton();
        getContentPane().add(imgFileButton);

        sendButton = showSendButton();
        getContentPane().add(sendButton);

        textArea = new JTextArea(20, 20);

        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBounds(0, 500, 321, 65);
        getContentPane().add(scroller);
        String arr[] = msg.split(",");
        senderID = arr[0];

        String sendmsg = arr[1];
        StyleContext context = new StyleContext();
        document = new DefaultStyledDocument(context);
        try{
            document.insertString(document.getLength(),"["+senderID+"]"+ sendmsg +"\n",null );
        }catch(Exception e){
            e.printStackTrace();
        }
        jtp = new JTextPane(document);
        jtp.setBackground(talkBackgroundColor);

        JScrollPane scroller2 = new JScrollPane(jtp);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller2.setBounds(0, 10, 389, 450);
        getContentPane().add(scroller2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public JButton showImgFileButton() {
        JButton imgFileButton = new JButton(new ImageIcon("../image/user.png"));
        imgFileButton.setText("File");
        imgFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        imgFileButton.setBackground(talkBackgroundColor);
        Border emptyBorder2 = BorderFactory.createEmptyBorder();
        imgFileButton.setBorder(emptyBorder2);
        imgFileButton.setFocusPainted(false);
        imgFileButton.setBounds(0, 460, 60, 40);
        return imgFileButton;
    }
    public JButton showSendButton() {
        JButton sendButton = new JButton(">");
        sendButton.setBackground(messageSendButtonColor);
        sendButton.setFont(new Font("맑은 고딕", Font.BOLD, 19));
        sendButton.setFocusPainted(false);
        sendButton.setBounds(320, 500, 56, 63);
        sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    out = new DataOutputStream(socket.getOutputStream());
                    String msg = "5"+senderID+","+textArea.getText();
                    out.writeUTF(msg);
                }catch(Exception ek){
                    ek.printStackTrace();
                }
                try{
                    document.insertString(document.getLength(),"["+userID+"]"+ textArea.getText() +"\n",null );
                }catch(Exception et){
                    et.printStackTrace();
                }
            }
        });
        return sendButton;
    }
}