import java.awt.* ;

public class PongGame extends Thread {
	protected int lastplKey,lastoppKey,oldplkey,playerno,oppno ;
	protected Graphics onscreen,offscreen ;
	protected Image img ;
	protected boolean drawn = false ;
	protected Paddle player,opponent ;
	protected Ball ball ;
	protected Dimension screen ;
	protected UDPSend net ;
	protected int counter = 0 ;

	// player one is no 2 ... don't ask!	
	public PongGame(Canvas f,int destport,String ipno,int playerno){
		this.playerno = playerno ;
		oppno = (playerno%2)+1 ;
		screen = new Dimension(640,400) ;
		player = new Paddle(playerno) ;
		opponent = new Paddle((playerno%2)+1) ;
		ball = new Ball(screen,320,200,3,3) ;
		// the opponent will receive on serverport
		net = new UDPSend(destport,ipno) ;
		img = f.createImage(640,400) ;
		onscreen = f.getGraphics() ;	
		offscreen = img.getGraphics() ;
	}
	
	public void run(){
		long oldtime ;
		while(true){
			oldtime = System.currentTimeMillis() ;
			counter++ ;
			movement() ;
			player.paint(offscreen) ;
			opponent.paint(offscreen) ;
			switch(ball.move()){
				case 1:
					opponent.addPoints(1) ;
					ball.reset() ;
					System.out.println("Player 2 wins") ;
					break ;
				case 2 :
					player.addPoints(1) ;
					ball.reset() ;
					System.out.println("Player 1 wins") ;
					break ;
				default :
					;
			}
			ball.paint(offscreen) ;
			if(check(player,ball)||check(opponent,ball)) 
			{
				ball.changeDir('h') ;
				ball.xspeed *= 1.2 ;
			}
			if(ball.moved) {net.send(ball.getPDU()) ;counter = 0 ;}
			if(counter>5) {net.send(ball.getPDU()) ;counter = 0;}
			
			try{
				sleep(25-(System.currentTimeMillis()-oldtime)) ;
			}catch(Exception e){}
			onscreen.drawImage(img,0,0,null) ;
		}
	}
	
	void setPDU(PDU newpdu,int id){
		if(id == oppno)opponent.setPDU(newpdu) ;
		else if(id == playerno)player.setPDU(newpdu) ;
		else ball.setPDU(newpdu) ;
	}

	void movement(){
		switch(lastplKey){
			case 1 :
				player.move(1) ;
				break ;
			case 2 :
				player.move(2) ;
				break ;
		}
		switch(lastoppKey){
			case 1 :
				opponent.move(1) ;
				break ;
			case 2 :
				opponent.move(2) ;
				break ;
			default :
				;
		}
	}
	
	public void changeLastKey(int play,int key){
		if(play==playerno){
			lastplKey = key ;
			if(oldplkey!=lastplKey)
				net.send(player.getPDU()) ;
			
			oldplkey = key ;
		}
		else lastoppKey = key ;
	}
	
	boolean check(Sprite a,Sprite b){
		Rectangle one = new Rectangle(a.getPos().x,a.getPos().y,a.getDim().width,a.getDim().height) ;
		Rectangle two = new Rectangle(b.getPos().x,b.getPos().y,b.getDim().width,b.getDim().height) ;
		return(one.intersects(two)) ;
	}
	
}
