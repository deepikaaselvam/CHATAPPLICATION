import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Sender extends Frame implements Runnable, ActionListener {
    TextField msg;
    TextArea paragraph;
    Button send;

    ServerSocket serversockettt;
    Socket  sockett;


    DataInputStream inputStream;
    DataOutputStream outputStream;
     
    Thread chat;

    Sender()
    {
        msg = new TextField();
        paragraph = new TextArea();
        send = new Button("Send");

        send.addActionListener(this);
        try {
            serversockettt = new ServerSocket(12000);
            sockett = serversockettt.accept();
            
            inputStream = new DataInputStream(sockett.getInputStream());
            outputStream = new DataOutputStream(sockett.getOutputStream());

        } catch (Exception e) {

        }
        

      

        add(msg);
        add(paragraph);
        add(send);

        msg.setPreferredSize(new Dimension(400, 30));

        chat =new Thread(this);
        chat.setDaemon(true); ///It takes the highest priority to run 
        chat .start();
       

        setSize(500,500);
        setTitle("Deepika S");
        setLayout(new FlowLayout());
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e)
    {
        String message =msg.getText();
        paragraph.append("Sender: "+message+"\n");
        msg.setText("");

        try {
            outputStream.writeUTF(message);
            outputStream.flush(); // It sends the message Suddenlyy
        } 
        catch (Exception es) {
        }

    }
    public static void main(String[] args) {
       Sender obj = new Sender();
        
    }
    public void run()
    {
        while (true) { 
            try {
                String currentMessage = inputStream.readUTF();
                paragraph.append("Receiver :"+currentMessage+"\n");
                
            } catch (Exception e) {
            }
        }
    }
}

    

