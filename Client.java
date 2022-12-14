package network_term;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Client {
    DataInputStream in;
    DataOutputStream out;
    public static void main(String args[]){
        try{
            ClientGui cg;
            String serverIp = "127.0.0.1";
            Socket socket = new Socket(serverIp,8888);
            System.out.println("서버에 연결되었습니다.");

            cg = new ClientGui(socket);
            cg.setFrame(cg);
            //login gui 실행하여 id pwd 입력후 login btn 누를시 actionListener 실행후 server에게 보내는 것까지
            Thread sr = new Thread(new ClientSender(socket,cg));
            sr.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static class ClientSender extends Thread{
        String id="";
        Socket socket;
        DataOutputStream out;
        DataInputStream in;
        ClientGui cg;
        ClientSender(Socket socket, ClientGui cg){
            this.socket = socket;
            this.cg = cg;
            try{
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        public void run(){
            String msg;
            String line;
            String UserInfo;
            try{
                line = in.readUTF();
                String op = line.substring(0,1);
                msg = line.substring(1);
                if(op.equalsIgnoreCase("a")){
                    String krr[] = msg.split("-");
                    id = krr[0];
                    UserInfo = krr[1];
                    System.out.println(UserInfo);
                    user JFrame = new user(socket,UserInfo);
                }
                else if(op.equalsIgnoreCase("d")){
                    //login gui 다시 실행
                    cg = new ClientGui(socket);
                    cg.setFrame(cg);
                }
                else if(op.equalsIgnoreCase("사용자 op")){

                }
                else if(op.equalsIgnoreCase("s")){
                    //get window - 대화창을 수락하시겠습니까? 1.btn yes 2.btn no
                    String buttons[] = {"예(Y)","아니요(N)"};
                    int num = JOptionPane.showOptionDialog(null,"계속 할 것입니까?","Confirm",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "예(Y)");
                    if(num==0){
                        new ChatingGui(socket,id,msg);
                    }
                    else{

                    }
                    //1번 case - 대화창 gui 를 띄우고 받은 msg(보낸사람 id+내용)를 add
                    //2번 case - 대화 거부
                }
                else if(op.equalsIgnoreCase("g")){
                    String arr[] = line.split(",");
                    String sender_id = arr[0];
                    String sender_content = arr[1];
                    //gui 에 내용 add
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
