import java.awt.* ;

public class Paddle implements Sprite,Networkable {
	protected int xpos,ypos,xoffset,scr_hei,scr_wid,oldxpos,oldypos,points,id_no,oppno  ;
    protected int paddleHeight, paddleWidth;
    protected int paddleMovement;

	public boolean moved = false ;
	public Paddle(int player_no){

        paddleHeight = 70;
        paddleWidth = 10;

        paddleMovement = 5;

		scr_hei = 400 ;
		scr_wid = 640 ;
		points = 0 ;
		if(player_no == 2) xoffset = 0 ;
		else xoffset = scr_wid-10 ;
		System.out.println("Your offset is "+xoffset+" player "+player_no) ;
		xpos = 0 ;
		ypos = scr_hei/2 ;
		id_no = player_no ;
	}
	
	public void setPDU(PDU newpdu){
    	xpos = newpdu.xpos ;
		ypos = newpdu.ypos ;
	}

	public PDU getPDU(){
		moved = false ;
		return new PDU(xpos,ypos,0,0,id_no) ;
	}
	
	public void addPoints(int p){
		points += p ;
	}
	
	public Point getPos(){
		return new Point(xpos+xoffset,ypos) ;
	}
	
	public Dimension getDim(){
		return new Dimension(paddleWidth, paddleHeight) ;
	}
	
	public void move(int dir){
		moved = true ;
        
		if (dir == 1) {
            if (ypos-paddleMovement >= 0) 
                ypos -= paddleMovement ;
        } else {
            if(ypos < (scr_hei-paddleHeight)) 
                ypos += paddleMovement ;	
        }

	}
	
	public void paint(Graphics g){
		g.setColor(Color.white) ;
		g.clearRect(oldxpos,oldypos,paddleWidth, paddleHeight) ;
		g.setColor(Color.black) ;
		oldxpos = xpos+xoffset ;
		g.fillRect(oldxpos,ypos,paddleWidth, paddleHeight) ;
		oldypos = ypos ;
	}
}
