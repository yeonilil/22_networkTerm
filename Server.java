package network_term;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;

public class Server {
    Database db;
    HashMap Clients;
    Server(){
        Clients = new HashMap();
        Collections.synchronizedMap(Clients);
    }
    public void start(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(8888);
            System.out.println("서버 실행");
            ExecutorService pool = Executors.newFixedThreadPool(10);
            while(true){
                socket = serverSocket.accept();
                pool.execute(new ServerReceiver(socket));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Server().start();
    }
    public class ServerReceiver implements Runnable{
        Socket socket;
        DataInputStream in;
        DataOutputStream out;
        String current_id;
        ServerReceiver(Socket socket){
            this.socket = socket;
            try{
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public void run(){
            try{
                db = new Database();
                String msg;
                String line;
                while(true){
                    line = in.readUTF();
                    System.out.println(line);
                    int op = Integer.parseInt(line.substring(0,1));
                    msg = line.substring(1);
                    switch(op){
                        case 1://login op
                            String[] arr = msg.split(",");
                            String id = arr[0];
                            String pwd = arr[1];
                            if(db.logincheck(id,pwd)){
                                Clients.put(id,out);
                                current_id= id;
                                ArrayList<usermodel> prr = new ArrayList<usermodel>();
                                prr=db.useread(id);
                                db.online(id);
                                String userinfo = prr.get(0).getName()+","+prr.get(0).getUid()+","+prr.get(0).getPassword()+","+prr.get(0).getOn()+","+prr.get(0).getIntro();
                                out.writeUTF("a" + arr[0] + "-" + userinfo);//accept
                            }
                            else{
                                out.writeUTF("d");//deny
                            }
                            break;
                        case 2://login sign
                            String[] arr2 = msg.split(",");
                            String id2 = arr2[0];
                            String pd2 = arr2[1];
                            String nm = arr2[2];
                            String date = arr2[3];
                            String sex = arr2[4];
                            String email = arr2[5];
                            if(db.joincheck(id2,pd2,nm,date,sex,email)){
                                out.writeUTF("t");
                            }
                            else{
                                out.writeUTF("f");
                            }
                        case 3://user control op
                            //사용자 관리 관련 protocol 정의 필요
                            break;
                        case 4://chatting start op
                            String[] arr4 = msg.split(",");
                            String id4 = arr4[0];
                            String Send = arr4[1];
                            DataOutputStream out3 = (DataOutputStream) Clients.get(id4);
                            out3.writeUTF('s'+ current_id +','+ Send);
                            break;
                        case 5://general chat
                            String[] arr5 = msg.split(",");
                            String id5 = arr5[0];
                            String Send2 = arr5[1];
                            DataOutputStream out4 = (DataOutputStream) Clients.get(id5);
                            out4.writeUTF('g'+current_id +','+ Send2);
                            break;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}