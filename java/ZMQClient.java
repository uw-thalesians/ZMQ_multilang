import org.zeromq.ZMQ;
import java.lang.management.ManagementFactory;

public class ZMQClient
{
    public static void main(String []args)
    {
        String id = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        
        org.zeromq.ZMQ.Context context = org.zeromq.ZMQ.context(1);
        
        org.zeromq.ZMQ.Socket socket = context.socket(ZMQ.REQ);
        System.out.println("Connecting to ZMQServer");
        socket.connect("tcp://localhost:5555");
        
        System.out.println("Sending message...");
        
        try
        {
            while(!Thread.currentThread().isInterrupted()){
                
                String msg = "message from client " + id;
                
                System.out.println("TX: " + msg);
                
                socket.send(msg.getBytes(), 0);
                
                byte [] reply = socket.recv(0);
                
                System.out.println("RX: " + new String(reply));
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception caught...");
            System.out.println(e.toString());
        }
        
        
        System.out.println("Closing socket and terminating context");
        socket.close();
        context.term();
    
    }

}