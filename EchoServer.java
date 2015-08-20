import java.io.* ;
import java.net.* ;

class EchoServer{
	public static void main(String[] args){
		try{
			ServerSocket s = new ServerSocket(8189) ;
			Socket incoming = s.accept() ;
			//DataInputStream in = new DataInputStream(incoming.getInputStream()) ;
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in)) ;
			//PrintStream out = new PrintStream(incoming.getOutputStream()) ;
			PrintWriter out = new PrintWriter(incoming.getOutputStream()) ;
			out.println("Hello! Enter BYE to exit.") ;
			
			boolean done = false ;
			while(!done){
				String str = in.readLine() ;
				if(str == null) done = true ;
				else {
					System.out.println("I received : "+str+". Now I'll bounce it back !") ;
					out.println("Echo : "+str) ;
					if(str.trim().equals("BYE"))
						done = true ;
				}
			}
			incoming.close() ;
		}catch (Exception e){
			System.out.println(e) ;
		}
	}
}
