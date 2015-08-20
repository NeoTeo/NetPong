import java.awt.* ;

public class Paddle implements Sprite,Networkable {
	protected int xpos,ypos,xoffset,scr_hei,scr_wid,oldxpos,oldypos,points,id_no,oppno  ;
	public boolean moved = false ;
	public Paddle(int player_no){
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
		return new Dimension(10,70) ;
	}
	
	public void move(int dir){
		moved = true ;
		if((dir == 1) && (ypos > 0)) ypos -= 6 ;
		else if(ypos < (scr_hei-70)) ypos += 6 ;	
	}
	
	public void paint(Graphics g){
		g.setColor(Color.white) ;
		g.clearRect(oldxpos,oldypos,10,70) ;
		g.setColor(Color.black) ;
		oldxpos = xpos+xoffset ;
		g.fillRect(oldxpos,ypos,10,70) ;
		oldypos = ypos ;
	}
}
