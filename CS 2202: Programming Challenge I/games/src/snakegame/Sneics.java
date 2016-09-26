/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
public class Sneics extends JFrame implements Runnable,KeyListener,ActionListener{
	Graphics2D bufer,fondo,gr;
	Image ibufer,head,pasto,ifondo;
	boolean izq,der,arr,aba,esc,unjugador,demo,enter,iniciado;
	Button iniciar,red,salir,acerca;
	int status,contador,cont=0,ex,ey;
	String nombre;
	SerpienteX serpientes[]=new SerpienteX[2],tu;
	TextField texto;
	Sneics(){
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setIgnoreRepaint(true);
		setTitle("Sneics");
		setUndecorated(true);
		setLayout(null);
		setSize(600,600);
		setVisible(true);
		//device.setFullScreenWindow(this);
		//DisplayMode display=device.getDisplayMode();
		//if(device.isDisplayChangeSupported())device.setDisplayMode(new DisplayMode(800,600,display.getBitDepth() ,DisplayMode.REFRESH_RATE_UNKNOWN));
		ex=getWidth()/2-300;
		ey=getHeight()/2-300;
		status=0;
		contador=0;
		ibufer=createImage(600,600);
		bufer=(Graphics2D)ibufer.getGraphics();
		ifondo=createImage(getWidth(),getHeight());
		fondo=(Graphics2D)ifondo.getGraphics();
		gr=(Graphics2D)getGraphics();
		head=obtImagen("head.png");
		pasto=obtImagen("pasto.png");
		iniciar=new Button("Un jugador");
		red=new Button("Juego en red");
		salir=new Button("Salir");
		acerca=new Button("Acerca");
		texto=new TextField("Nombre");
		texto.setBounds(getWidth()/2-60,ey+245,120,30);
		texto.setBackground(Color.RED);
		texto.setForeground(Color.WHITE);
		iniciar.setBounds(texto.getX(),texto.getY()+40,120,30);
		red.setBounds(iniciar.getX(),iniciar.getY()+40,120,30);
		red.setEnabled(false);
		acerca.setBounds(iniciar.getX(),red.getY()+40,120,30);
		salir.setBounds(iniciar.getX(),acerca.getY()+40,120,30);
		iniciar.addActionListener(this);
		red.addActionListener(this);
		acerca.addActionListener(this);
		salir.addActionListener(this);
		agregar();
		addKeyListener(this);
		new Thread(this).start();
		repintar();
	}
	private void repintar(){
		iniciar.repaint();
		red.repaint();
		acerca.repaint();
		salir.repaint();
		texto.repaint();
		iniciar.requestFocus();
		red.requestFocus();
		acerca.requestFocus();
		salir.requestFocus();
		texto.requestFocus();
	}
	private Image obtImagen(String img){
		Toolkit tk=Toolkit.getDefaultToolkit();
		return tk.getImage(getClass().getResource(img));
	}
	public void terminado(){
		iniciado=false;
		status=3;
	}
	private void demo(){
		iniciar();
		demo=true;
		iniciado=true;
		serpientes[0].setPc(true);
		serpientes[1].setPc(true);
		quitar();
		requestFocus();
		status=2;
	}
	private void iniciar(){
		serpientes[0]=new SerpienteX(50,300,0,Color.BLUE,this,1);
		serpientes[1]=new SerpienteX(550,300,2,Color.RED,this,2);
		serpientes[0].setOtro(serpientes[1]);
		serpientes[1].setOtro(serpientes[0]);
		demo=false;
	}
	private void agregar(){
		add(iniciar);
		add(red);
		add(acerca);
		add(salir);
		add(texto);
		contador=0;
	}
	private void quitar(){
		remove(iniciar);
		remove(red);
		remove(acerca);
		remove(salir);
		remove(texto);
	}
	public void pintar(){
		for(int i=0;i<12;i++){
			for(int j=0;j<12;j++){
				bufer.drawImage(pasto,i*50,j*50,this);
			}
		}
		if(status==0){
			//MENU PRINCIPAL
			dibujarInicio();
		}
		else if(status==1){
			//ACERCA
			dibujarInicio();
			bufer.setColor(Color.BLACK);
			bufer.setFont(new Font("",Font.PLAIN,12));
			bufer.drawString("Diseno y programacion:",180,270);
			bufer.drawString("Arturo Dominguez Garcia",180,290);
			bufer.drawString("ArturoSoft todos los derechos reservados",180,340);
			bufer.drawString("(Izquierdos tambien)",180,360);
		}
		else if(status==2){
			//JUGANDO
			serpientes[0].pintar(bufer);
			serpientes[1].pintar(bufer);
		}
		else if(status==3){
			//TERMINADO
			bufer.setFont(new Font("",Font.BOLD,20));
			serpientes[0].pintar(bufer);
			serpientes[1].pintar(bufer);
			bufer.setColor(new Color(0x80,0x80,0x80,180));
			bufer.fillRect(130,250,360,170);
			if(tu.muerto){
				bufer.setColor(tu.enemigo.color);
				bufer.drawString(tu.enemigo.nombre+" gana!",150,300);
			}
			else{
				bufer.setColor(tu.color);
				bufer.drawString(tu.nombre+" gana!",150,300);
			}
			bufer.drawString("Presiona ESC para salir...",150,350);
			bufer.drawString("Presiona ENTER para reiniciar...",150,400);
		}
		fondo.setColor(Color.BLACK);
		fondo.fillRect(0,0,getWidth(),getHeight());
		fondo.drawImage(ibufer,ex,ey,this);
		gr.drawImage(ifondo,0,0,this);
		if(cont<10){
			cont++;
			repintar();
		}
	}
	private void dibujarInicio(){
		bufer.setColor(new Color(0x80,0x80,0x80,120));
		bufer.fillRect(150,150,300,300);
		bufer.drawImage(head,150,150,this);
	}
	public void actualizar(){
		if(status==0){
			contador++;
			if(contador==500){
				contador=0;
				demo();
			}
		}
		else if(status==2){
			if(!demo){
				if(izq){
					if(tu.dir!=0)tu.dir=2;
				}
				else if(der){
					if(tu.dir!=2)tu.dir=0;
				}
				else if(arr){
					if(tu.dir!=1)tu.dir=3;
				}
				else if(aba){
					if(tu.dir!=3)tu.dir=1;
				}
			}
			if(esc){
				agregar();
				status=0;
			}
			if(iniciado){
				serpientes[0].actualizar();
				serpientes[1].actualizar();
			}
			if(serpientes[0].muerto||serpientes[1].muerto)terminado();
		}
		if(status==3){
			if(esc||demo){
				agregar();
				status=0;
			}
			if(enter)
				unjugador();
		}
	}
	public void run(){
		long tiempo,temp,max=40;
		while(true){
			tiempo=System.currentTimeMillis();
			actualizar();
			pintar();
			temp=System.currentTimeMillis()-tiempo;
			tiempo=max-temp;
			try{
				//System.out.println(tiempo);
				if(tiempo>0)Thread.sleep(tiempo);
			}catch(Exception e){}
		}
	}
	public void keyReleased(KeyEvent evt){
		int key=evt.getKeyCode();
		if(key==KeyEvent.VK_LEFT)izq=false;
		if(key==KeyEvent.VK_RIGHT)der=false;
		if(key==KeyEvent.VK_UP)arr=false;
		if(key==KeyEvent.VK_DOWN)aba=false;
		if(key==KeyEvent.VK_ESCAPE)esc=false;
		if(key==KeyEvent.VK_ENTER)enter=false;
	}
	public void keyPressed(KeyEvent evt){
		if(status==2)iniciado=true;
		int key=evt.getKeyCode();
		if(key==KeyEvent.VK_LEFT)izq=true;
		if(key==KeyEvent.VK_RIGHT)der=true;
		if(key==KeyEvent.VK_UP)arr=true;
		if(key==KeyEvent.VK_DOWN)aba=true;
		if(key==KeyEvent.VK_ESCAPE)esc=true;
		if(key==KeyEvent.VK_ENTER)enter=true;
	}
	public void keyTyped(KeyEvent evt){}
	public void unjugador(){
		nombre=texto.getText();
			if(nombre.length()==0)nombre="Desconocido";
			unjugador=true;
			iniciar();
			tu=serpientes[0];
			tu.setPc(false);
			tu.enemigo.setPc(true);
			tu.nombre=nombre;
			tu.enemigo.nombre="PC";
			quitar();
			requestFocus();
			status=2;
	}
	public void actionPerformed(ActionEvent evt){
		Object src=evt.getSource();
		if(src==iniciar){
			unjugador();
		}
		else if(src==red){
			unjugador=false;
		}
		else if(src==acerca){
			quitar();
			add(salir);
			status=1;
		}
		else if(src==salir){
			if(status==0){
				System.exit(0);
			}
			else if(status==1){
				remove(salir);
				agregar();
				status=0;
			}
		}
	}
	public static void main(String arg[]){
		new Sneics();
	}
}
