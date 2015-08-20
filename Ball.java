import java.awt.* ;
import java.util.* ;

public class Ball implements Sprite,Networkable {
	public int xpos,oldxpos,ypos,oldypos,xspeed,yspeed ;
    protected int minSpeed, speedRange;
	protected Dimension screen ;
	public boolean moved = false ;
	
	public Ball(Dimension screen,int xpos,int ypos,int xspeed,int yspeed){
		this.xpos = xpos ;
		this.ypos = ypos ;
		this.xspeed = xspeed ;
		this.yspeed = yspeed ;
		this.screen = screen ;
        minSpeed = 5;
        speedRange = 5;
	}
	
	public void reset(){
		Random rand = new Random(System.currentTimeMillis()) ;
		xpos = screen.width/2 ;
		ypos = screen.height/2 ;
        // Randomize the x direction
        int xDir = 1;
        if (rand.nextInt() % 2 == 0) {
            xDir = -1;
        }

        // Keep trying until the ratio is good.
        float ratio;
        do {
            xspeed = xDir * ((rand.nextInt() % speedRange) + minSpeed) ;
            yspeed = (rand.nextInt() % speedRange) + minSpeed  ;
            ratio = Math.abs(xspeed/(float)yspeed);
            System.out.println("speed ratio is "+ratio);
        } while (ratio < 0.3);
	}
	
	public Point getPos(){
		return new Point(xpos,ypos) ;
	}
	
	public Dimension getDim(){
		return new Dimension(10,10) ;
	}

	public void setPDU(PDU newpdu){
    	xpos = newpdu.xpos ;
		ypos = newpdu.ypos ;
		xspeed = newpdu.xspeed ;
		yspeed = newpdu.yspeed ;
	}

	public PDU getPDU(){
		moved = false ;
		return new PDU(xpos,ypos,xspeed,yspeed,3) ;
	}

	
	public int move(){
		if(xpos < 0) return 1 ;
		if(xpos > screen.width) return 2 ;
		xpos += xspeed ;
		if((ypos > screen.height-10) || (ypos<0)) changeDir('v') ;
		ypos += yspeed ;
		
		return 0 ;
	}
	
	public void changeDir(char axis){
		if(axis == 'h') xspeed *= -1 ;
		else yspeed *= -1 ;
		moved = true ;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.white) ;
		g.fillOval(oldxpos,oldypos,10,10) ;
		g.setColor(Color.black) ;
		g.fillOval(xpos,ypos,10,10) ;
		oldxpos = xpos ;
		oldypos = ypos ;
	}

}
