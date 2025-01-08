import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
public class Receiver extends Frame implements Runnable, ActionListener {
    TextField msg;
    TextArea paragraph;
    Button send;

    
    Socket  sockett;


    DataInputStream inputStream;
    DataOutputStream outputStream;
     
    Thread chat;

    Receiver()
    {
        msg = new TextField();
        paragraph = new TextArea();
        send = new Button("Send");

        send.addActionListener(this);
        try {
            sockett = new Socket("localhost",12000);
     
            
            inputStream = new DataInputStream(sockett.getInputStream());
            outputStream = new DataOutputStream(sockett.getOutputStream());

        } catch (Exception e) {
            

        }
        

      

        add(msg);
        add(paragraph);
        add(send);
        msg.setPreferredSize(new Dimension(400, 30));

        chat =new Thread(this);
        chat .start();
       

        setSize(500,500);
        setTitle("Deepika S");
        setLayout(new FlowLayout());
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e)
    {
        String message =msg.getText();
        paragraph.append("Receiver : "+message+"\n");
        msg.setText("");

        try {
            outputStream.writeUTF(message);
            outputStream.flush(); // It sends the message Suddenlyy
        } 
        catch (Exception es) {
        }

    }
    public static void main(String[] args) {
        Receiver obj  = new Receiver();
        
    }
    public void run()
    {
        while (true) { 
            try {
                String currentMessage = inputStream.readUTF();
                paragraph.append("Sender :"+currentMessage+"\n");
                
            } catch (Exception e) {
            }
        }
    }
}

    

