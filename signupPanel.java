package network_term;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.*;
class signupPanel extends JPanel {

    JTextField idTf;
    JPasswordField passTf;
    JPasswordField passReTf;
    JTextField nameTf;
    JTextField yearTf;
    JTextField emailTf;
    JPanel mainPanel;
    JPanel subPanel;
    JComboBox<String> monthComboBox;
    JComboBox<String> dayComboBox;
    JRadioButton menButton;
    JRadioButton girlButton;
    JButton registerButton;
    Font font = new Font("회원가입", Font.BOLD, 40);

    String year = "", month = "", day = "";
    String id = "", pass = "", passRe = "", name = "", sex = "", email = "";
    ClientGui lp;
    Socket socket;
    DataOutputStream out;
    public signupPanel(ClientGui lp, Socket socket) {

        this.lp = lp;
        this.socket = socket;
        subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        subPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel idLabel = new JLabel("아이디 : ");
        JLabel passLabel = new JLabel("비밀번호 : ");
        JLabel passReLabel = new JLabel("비밀번호 재확인 : ");
        JLabel nameLabel = new JLabel("이름 : ");
        JLabel birthLabel = new JLabel("생년월일 : ");
        JLabel sexLabel = new JLabel("성별 : ");
        JLabel emailLabel = new JLabel("이메일주소 : ");

        idTf = new JTextField(15);
        passTf = new JPasswordField(15);
        passReTf = new JPasswordField(15);
        nameTf = new JTextField(15);
        yearTf = new JTextField(4);
        emailTf = new JTextField(11);

        monthComboBox = new JComboBox<String>(
                new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" });
        dayComboBox = new JComboBox<String>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
                "28", "29", "30", "31" });

        menButton = new JRadioButton("남자");
        girlButton = new JRadioButton("여자");
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(menButton);
        sexGroup.add(girlButton);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 5, 0, 0);

        c.gridx = 0;
        c.gridy = 0;
        subPanel.add(idLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        subPanel.add(idTf, c); // 아이디

        c.gridx = 0;
        c.gridy = 1;
        subPanel.add(passLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        subPanel.add(passTf, c); // pass

        c.gridx = 2;
        c.gridy = 1;
        subPanel.add(new JLabel("특수문자 + 8자"),c); //보안설정

        c.gridx = 0;
        c.gridy = 2;
        subPanel.add(passReLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        subPanel.add(passReTf, c); // password 재확인

        c.gridx = 0;
        c.gridy = 3;
        subPanel.add(nameLabel, c);

        c.gridx = 1;
        c.gridy = 3;
        subPanel.add(nameTf, c); // 이름

        c.gridx = 0;
        c.gridy = 4;
        subPanel.add(birthLabel, c);

        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.6;
        subPanel.add(yearTf, c);

        c.gridx = 2;
        c.gridy = 4;
        c.weightx = 0.2;
        subPanel.add(monthComboBox, c);

        c.gridx = 3;
        c.gridy = 4;
        c.weightx = 0.2;
        subPanel.add(dayComboBox, c);

        c.gridx = 0;
        c.gridy = 5;
        subPanel.add(sexLabel, c);

        c.gridx = 1;
        c.gridy = 5;
        subPanel.add(menButton, c);

        c.gridx = 2;
        c.gridy = 5;
        subPanel.add(girlButton, c);

        c.gridx = 0;
        c.gridy = 6;
        subPanel.add(emailLabel, c);

        c.gridx = 1;
        c.gridy = 6;
        subPanel.add(emailTf, c);

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel signupLabel = new JLabel("회원가입 화면 ");
        signupLabel.setFont(font);
        signupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerButton = new JButton("회원가입");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(signupLabel);
        mainPanel.add(subPanel);
        mainPanel.add(registerButton);

        monthComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == monthComboBox) {
                    JComboBox monthBox = (JComboBox) e.getSource();
                    month = (String) monthBox.getSelectedItem();
                    System.out.println(month);
                }

            }
        });
        dayComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getSource() == dayComboBox) {
                    JComboBox dayBox = (JComboBox) e.getSource();
                    day = (String) dayBox.getSelectedItem();
                    System.out.println(month);
                }
            }
        });

        menButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                sex = e.getActionCommand();
            }
        });

        girlButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                sex = e.getActionCommand();
            }
        });
        registerButton.addActionListener(new ActionListener() {      //회원가입버튼

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                id = idTf.getText();
                month =
                        pass = new String(passTf.getPassword());
                passRe = new String(passReTf.getPassword());
                name = nameTf.getText();
                year = yearTf.getText();
                email = emailTf.getText();

                Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"); //8자 영문+특문+숫자
                Matcher passMatcher = passPattern1.matcher(pass);

                if (!passMatcher.find()) {
                    JOptionPane.showMessageDialog(null, "비밀번호는 영문+특수문자+숫자 8자로 구성되어야 합니다", "비밀번호 오류", 1);
                } else if (!pass.equals(passRe)) {
                    JOptionPane.showMessageDialog(null, "비밀번호가 서로 맞지 않습니다", "비밀번호 오류", 1);

                } else {
                    try {
                        out = new DataOutputStream(socket.getOutputStream());
                        String date = yearTf.getText() + "-" + month + "-" + day;
                        String msg = '2'+id+','+pass+','+name+','+date+','+sex+','+email;
                        out.writeUTF(msg);
                        JOptionPane.showMessageDialog(null, "회원 가입 완료!", "회원가입", 1);
                        lp.card.previous(lp.cardPanel); // 다 완료되면 로그인 화면으로
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }
}
