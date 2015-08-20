import java.awt.* ;

public class Pong extends Frame {
	protected PongGame game ;
	protected UDPReceive net_in ;
	protected static int playerno,oppno ;
	protected boolean done = false ;
	protected int destport,inport ;
	protected String ipno ;
	protected Canvas c ;
  protected Insets inset ;
//	static int serverport ;
	
	public Pong(){
		c = new Canvas() ;
		this.add(c) ;
		show() ; // to make the insets correct
    inset = this.getInsets() ;
    System.out.println("left : "+inset.left+", right : "+inset.right) ;
    System.out.println("top : "+inset.top+", bottom : "+inset.bottom) ;
		setSize(640+(inset.left+inset.right),400+(inset.top+inset.bottom)) ;
		setBackground(Color.white) ;
		show() ;
		new OkDialog(this,true,"Please input : ") ;
	}
	
	public void runner(){
		int receiveport ;
		game = new PongGame(c,destport,ipno,playerno) ;
		game.start() ;
		net_in = new UDPReceive(game,inport) ;
		net_in.start() ;
	}

	public void setData(NetData dat){
		System.out.println("so far...") ;
		destport = Integer.parseInt(dat.destport) ; 
		System.out.println("so good.") ;
		ipno = dat.ipno ;
		playerno = Integer.parseInt(dat.players) ;
		inport = Integer.parseInt(dat.inport) ;
	}
	
	public static void main(String[] argv){
		System.out.println("Player 2 must select an IP one higher than player 1") ;
		new Pong() ;

	}
	
	public boolean keyDown(Event e, int key){
		switch(key){
			case 1004  :	//up
			case 56	:
				game.changeLastKey(playerno,1) ;
				break ;
			case 1005	:
			case 53 :
			case 50 :	//down
				game.changeLastKey(playerno,2) ;
				break ;
			case 97 : 	// alternative up
				game.changeLastKey(oppno,1) ;
				break ;
			case 122  :	// alternative down ;
				game.changeLastKey(oppno,2) ;
				break ;
			default :
				//System.out.println(key) ;
		}
		return true ;
	}

	public boolean keyUp(Event e, int key){
		switch(key){
			case 1004  :	//up
			case 56	:
			case 1005	:
			case 53 :
			case 50 :	//down
				game.changeLastKey(playerno,0) ;
				break ;
			case 97 : 	// alternative up
			case 122  :	// alternative down ;
				game.changeLastKey(oppno,0) ;
				break ;
			default :
				//System.out.println(key) ;
		}
		return true ;
	}


}
