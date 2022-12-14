package network_term;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
class LoginPanel extends JPanel implements ActionListener {
    JPanel mainPanel;
    JTextField idTextField;
    JPasswordField passTextField;
    String userMode = "일반";
    ClientGui lp;
    Socket socket;
    DataOutputStream out;
    Font font = new Font("회원가입", Font.BOLD, 40);
    String admin = "admin";

    public LoginPanel(ClientGui lp, Socket socket) {
        this.lp = lp;
        this.socket = socket;

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));

        JPanel centerPanel = new JPanel();
        JLabel loginLabel = new JLabel("Malhatalk");
        loginLabel.setFont(font);
        centerPanel.add(loginLabel);

        JPanel userPanel = new JPanel();

        JPanel gridBagidInfo = new JPanel(new GridBagLayout());
        gridBagidInfo.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        GridBagConstraints c = new GridBagConstraints();

        JLabel idLabel = new JLabel(" 아이디 : ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        gridBagidInfo.add(idLabel, c);

        idTextField = new JTextField(15);
        c.insets = new Insets(0, 5, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        gridBagidInfo.add(idTextField, c);

        JLabel passLabel = new JLabel(" 비밀번호 : ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20, 0, 0, 0);
        gridBagidInfo.add(passLabel, c);

        passTextField = new JPasswordField(15);
        c.insets = new Insets(20, 5, 0, 0);
        c.gridx = 1;
        c.gridy = 1;
        gridBagidInfo.add(passTextField, c);

        JPanel loginPanel = new JPanel();
        JButton loginButton = new JButton("로그인");
        loginPanel.add(loginButton);

        JPanel signupPanel = new JPanel();
        JButton signupButton = new JButton("회원가입");
        loginPanel.add(signupButton);

        mainPanel.add(centerPanel);
        mainPanel.add(userPanel);
        mainPanel.add(gridBagidInfo);
        mainPanel.add(loginPanel);
        mainPanel.add(signupPanel);


        loginButton.addActionListener(this);

        signupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                lp.card.next(lp.cardPanel);
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton jb = (JButton) e.getSource();

        switch (e.getActionCommand()) {

            case "로그인":

                String id = idTextField.getText();
                String pass = passTextField.getText();

                try {
                    String msg = '1'+id+','+pass;
                    out = new DataOutputStream(socket.getOutputStream());
                    out.writeUTF(msg);
                }catch(IOException ec){
                    ec.printStackTrace();
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Login Failed", "로그인 실패", 1);
                    System.out.println("Exception" + ex);
                }
                break;
        }
    }

}