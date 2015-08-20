import java.io.* ;
import java.net.* ;

public class UDPReceive extends Thread{
	protected int port ;
	protected int pkt_size = 7 ;
	protected PongGame game ;
	public UDPReceive(PongGame game,int receiveport){
		this.game = game ;
		System.out.println("server is starting...") ;
		port = receiveport ;
	}
	
	public void run(){
		PDU tpdu ;
		byte[] buffer = new byte[pkt_size] ;
		try{
			DatagramSocket socket = new DatagramSocket(port) ;
			
			for(;;){
				//System.out.println("server is running...") ;
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length) ;
				socket.receive(packet) ;
				//System.out.println("so far...") ;
				buffer = packet.getData() ;
				tpdu = new PDU() ;
		 		tpdu.xpos = ((buffer[1] << 8) | (buffer[0] & 0xff));
				tpdu.ypos = ((buffer[3] <<8) | (buffer[2] & 0xff));
				tpdu.xspeed =  buffer[4] ;
				tpdu.yspeed =  buffer[5] ;
				tpdu.idno = buffer[6] ;
				game.setPDU(tpdu,tpdu.idno) ;
/*
				System.out.println("UDPReceive: received  at port:"+packet.getPort()+":") ;
				System.out.println("xpos : "+xpos+". ypos : "+ypos+". xspeed : "+xspeed+". yspeed : "+yspeed) ;
				System.out.println("id no : "+idno) ;
*/
				yield() ;
			}
		}catch(Exception e){}
	}

}
