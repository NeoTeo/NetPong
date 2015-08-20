import java.awt.* ;
import java.awt.event.* ;

public class OkDialog extends Dialog implements ActionListener{
	protected Button b ;
	protected Label portlab,ipnolab,playerslab, inportlab ;
	protected Pong main ;
	protected TextField port,ipno, players, inport ;
	
	public OkDialog(Pong pong,boolean modal,String text){
		super(pong,"OK Dialog",modal) ;
		main = pong ;
		setBackground(Color.gray) ;
		setLayout(new BorderLayout(15,15)) ;
		setSize(100,100) ;
		
		// button stuff
		b = new Button("OK") ;
    b.addActionListener(this) ;

		port = new TextField(4) ;		
		ipno = new TextField(20) ;		
		players = new TextField(2) ;
		inport = new TextField(4) ;
		Label portlab = new Label("Opponents port :") ;
		Label ipnolab = new Label("Opponents IP : ") ;
		Label playerslab = new Label("Your player no : ") ;
		Label inportlab = new Label("Your port : ") ;
		
		b.setFont(new Font("courier",Font.BOLD,10)) ;
		b.setSize(15,5) ;
		b.setBackground(Color.lightGray) ;
		
		Panel contents = new Panel() ;
		Panel buttons = new Panel() ;
		Panel fields = new Panel() ;
		
		contents.setLayout(new GridLayout(2,1)) ;
		fields.setLayout(new GridLayout(4,2)) ;
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER,15,15)) ;
		
		fields.add(portlab) ;
		fields.add(port) ;
		
		fields.add(ipnolab) ;
		fields.add(ipno) ;
		
		fields.add(playerslab) ;
		fields.add(players) ;
		
		fields.add(inportlab) ;
		fields.add(inport) ;
		
		buttons.add(b) ;
		contents.add(fields) ;
		contents.add(buttons) ;
		add("South",contents) ;
		pack() ;
		show() ;
	}
	
	public NetData getData(){
		NetData dat = new NetData() ;
		dat.destport = port.getText() ;
        if (dat.destport.isEmpty()) { dat.destport = "5000"; }
		System.out.println("dat.destport is "+dat.destport) ;
		dat.ipno = ipno.getText() ;
        if (dat.ipno.isEmpty()) { dat.ipno = "127.0.0.1"; }
		dat.players = players.getText() ;
        if (dat.players.isEmpty()) { dat.players = "1"; }
		dat.inport = inport.getText() ;
        if (dat.inport.isEmpty()) { dat.inport = "6000"; }
		return dat ;
	}
	
	public boolean mouseDown(Event evt,int x,int y) {
		System.out.println("hello ?") ;
		return true ;
	}

	/*public boolean action(Event evt,Object arg){
		if(evt.target == b){
			
		}
		return true ;
	} */
  public void actionPerformed(ActionEvent e)
  {
    // e.getSource() would give me the object that generated the event
		main.setData(getData()) ;
		main.runner() ;
		setVisible(false) ;
		dispose () ;
  }
}
