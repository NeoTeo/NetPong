import java.io.* ;
import java.net.* ;

public class UDPSend {
	protected int destport ;
	protected InetAddress address ;
	protected int pkt_size  = 7;
	public UDPSend(int port,String host){
		destport = port ;
		try{
			// create an InetAddress from a string such as "123.123.123.123"
			address = InetAddress.getByName(host) ;
		}catch(Exception e){System.out.println("UDPSend :"+e);}
		
	}
	
	public void send(PDU pdu){
		byte[] message = new byte[pkt_size] ;
		message[0] = (byte)(pdu.xpos & 0xff);
		message[1] = (byte)(pdu.xpos >>> 8) ;
		message[2] = (byte)(pdu.ypos & 0xff) ;
		message[3] = (byte)(pdu.ypos >>> 8) ;
		message[4] = (byte)pdu.xspeed ;
		message[5] = (byte)pdu.yspeed ;
		message[6] = (byte) pdu.idno ;

		try{
			DatagramSocket socket = new DatagramSocket() ;
			DatagramPacket packet = new DatagramPacket(message,pkt_size,address,destport) ;
			//System.out.println("sending packet") ;
			socket.send(packet) ;
		}catch(Exception e){System.out.println("UDPSend : error ") ;}
	}
	
}
