package network_term;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.net.*;
public class ClientGui {
    Socket socket;
    JPanel cardPanel;
    CardLayout card;
    String id = null;
    public ClientGui(Socket socket){
        this.socket = socket;
    }

    public void setFrame(ClientGui lpro) {

        JFrame jf = new JFrame();
        LoginPanel lp = new LoginPanel(lpro,socket);
        signupPanel sp = new signupPanel(lpro,socket);

        card = new CardLayout();

        cardPanel = new JPanel(card);
        cardPanel.add(lp.mainPanel, "Login");
        cardPanel.add(sp.mainPanel, "Register");

        jf.add(cardPanel);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 700);
        jf.setVisible(true);
    }
    public void setid(String id) {
        this.id = id;
    }

}
