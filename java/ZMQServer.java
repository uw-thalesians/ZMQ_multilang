import org.zeromq.ZMQ;
import java.lang.management.ManagementFactory;

public class ZMQServer 
{
    public static void main(String []args)
    {
        String id = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        
        org.zeromq.ZMQ.Context context = org.zeromq.ZMQ.context(1);
        
        org.zeromq.ZMQ.Socket socket = context.socket(ZMQ.REP);
        
        socket.bind("tcp://*:5555");
        
        System.out.println("ZMQ socket bound");
        
        try
        {
            while(!Thread.currentThread().isInterrupted()){
                
                byte [] reply = socket.recv(0);
                
                System.out.println(new String(reply));
                
                socket.send((id + " ACK").getBytes(),0);
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception caught...");
            System.out.println(e.toString());
        }
        
        socket.close();
        context.term();
    }
}