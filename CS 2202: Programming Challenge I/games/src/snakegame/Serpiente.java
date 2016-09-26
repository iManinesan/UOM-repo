/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Graphics2D;
import java.awt.Color;
public class Serpiente{
	Parte cabeza;
	Color color;
	int dir;
	String nombre;
	Serpiente enemigo;
	Sneics sneics;
	boolean muerto,pc;
	final static int w=10;
	Serpiente(int x,int y,int d,Color c,Sneics s){
		cabeza=new Parte(x,y,null,null);
		dir=d;
		color=c;
		sneics=s;
		muerto=false;
	}
	public void setPc(boolean b){
		pc=b;
	}
	public void setOtro(Serpiente enem){
		enemigo=enem;
	}
	public void actualizar(){
		if(pc)mover();
		Parte nueva=null;
		if(dir==1)nueva=new Parte(cabeza.x+w,cabeza.y,null,cabeza);
		else if(dir==2)nueva=new Parte(cabeza.x,cabeza.y+w,null,cabeza);
		else if(dir==3)nueva=new Parte(cabeza.x-w,cabeza.y,null,cabeza);
		else if(dir==4)nueva=new Parte(cabeza.x,cabeza.y-w,null,cabeza);
		cabeza.anterior=nueva;
		cabeza=nueva;
		if(colision(cabeza)){
			cabeza=cabeza.siguiente;
			sneics.terminado();
			muerto=true;
		}
	}
	private void mover(){
		int tdir,num;
		Parte nueva=null;
		if((int)(Math.random()*100)>90)if((tdir=getDir())!=-1)dir=tdir;
		else{
			num=0;
			for(int i=cabeza.y-w;i<=cabeza.y+w;i+=w){
				for(int j=cabeza.x-w;j<=cabeza.x+w;j+=w){
					nueva=new Parte(j,i,null,null);
					if(!colision(nueva))num++;
				}
			}
			if(num<=1)if((tdir=getDir())!=-1)dir=tdir;
		}
		if(dir==1)nueva=new Parte(cabeza.x+w,cabeza.y,null,cabeza);
		else if(dir==2)nueva=new Parte(cabeza.x,cabeza.y+w,null,cabeza);
		else if(dir==3)nueva=new Parte(cabeza.x-w,cabeza.y,null,cabeza);
		else if(dir==4)nueva=new Parte(cabeza.x,cabeza.y-w,null,cabeza);
		if(colision(nueva)){
			tdir=getDir();
			if(tdir!=-1)dir=tdir;
		}
	}
	private int getDir(){
		int temp=-1,area;
		Parte nueva=null;
		Parte parts[][];
		int direcciones[]=new int[4],cont=0;
		for(int i=1;i<=4;i++){
			if(i==1)nueva=new Parte(cabeza.x+w,cabeza.y,null,cabeza);
			else if(i==2)nueva=new Parte(cabeza.x,cabeza.y+w,null,cabeza);
			else if(i==3)nueva=new Parte(cabeza.x-w,cabeza.y,null,cabeza);
			else if(i==4)nueva=new Parte(cabeza.x,cabeza.y-w,null,cabeza);
			if(!colision(nueva)){
				temp=i;
				direcciones[cont]=temp;
				cont++;
			}
		}
		if(temp!=-1){
			int mayor=0,dist,indice=0,n,vx=0,vy=0;
			Parte punto=null,punto2=null;
			for(int i=0;i<cont;i++){
				dist=0;
				if(direcciones[i]==1){
					vx=w;
					vy=0;
				}
				else if(direcciones[i]==2){
					vx=0;
					vy=w;
				}
				else if(direcciones[i]==3){
					vx=-w;
					vy=0;
				}
				else if(direcciones[i]==4){
					vx=0;
					vy=-w;
				}
				punto=new Parte(cabeza.x,cabeza.y,null,null);
				while(true){
					punto=new Parte(punto.x+vx,punto.y+vy,null,null);
					if(colision(punto))break;
					dist++;
					punto2=punto;
				}
				n=0;
				punto=punto2;
				for(int m=punto.y-w;m<=punto.y+w;m+=w){
					for(int mn=punto.x-w;mn<=punto.x+w;mn+=w){
						punto2=new Parte(mn,m,null,null);
						if(!colision(punto2))n++;
					}
				}
				if(n<3)dist=0;
				else dist+=n*2;
				/*
				punto=new Parte(cabeza.x+vx,cabeza.y+vy,null,null);
				parts=new Parte[600/w][600/w];
				area=area(punto,parts,0);
				dist+=area;
				System.out.println("Direccion ["+i+"] = "+direcciones[i]+" de "+cont+"  "+dist+" > "+mayor+" Valor = "+dist+" ( "+punto.x+","+punto.y+" )");
				*/
				if(dist>mayor){
					mayor=dist;
					indice=i;
				}
				else if(dist==mayor){
					if((int)(Math.random()*100)>50)indice=i;
				}
			}
			temp=direcciones[indice];
		}
		return temp;
	}
	private int area(Parte p,Parte parts[][],int tope){
		parts[p.x/w][p.y/w]=p;
		tope++;
		Parte p2;
		p2=new Parte(p.x+w,p.y,null,null);
		if(!colision(p2)&&parts[p2.x/w][p2.y/w]==null)tope=area(p2,parts,tope);
		p2=new Parte(p.x,p.y+w,null,null);
		if(!colision(p2)&&parts[p2.x/w][p2.y/w]==null)tope=area(p2,parts,tope);
		p2=new Parte(p.x-w,p.y,null,null);
		if(!colision(p2)&&parts[p2.x/w][p2.y/w]==null)tope=area(p2,parts,tope);
		p2=new Parte(p.x,p.y-w,null,null);
		if(!colision(p2)&&parts[p2.x/w][p2.y/w]==null)tope=area(p2,parts,tope);
		return tope;
	}
	private boolean colision(Parte p){
		if(p.x<0)return true;
		else if(p.x>=600)return true;
		else if(p.y<0)return true;
		else if(p.y>=600)return true;
		else{
			if(enemigo.colisionSerpiente(p))return true;
			else if(cabeza.siguiente!=null){
				return cabeza.siguiente.colision(p);
			}
			else return false;
		}
	}
	public boolean colisionSerpiente(Parte c){
		if(cabeza.colision(c))return true;
		else return false;
	}
	public void pintar(Graphics2D g){
		Parte partes[]=aArray();
		for(int i=0;i<partes.length;i++){
			if(i!=0)g.setColor(color);
			else g.setColor(color.GREEN);
			g.fillRect(partes[i].x,partes[i].y,w,w);
			g.setColor(Color.BLACK);
			g.drawRect(partes[i].x,partes[i].y,w,w);
		}
	}
	private int length(){
		return cabeza.length();
	}
	private Parte[] aArray(){
		Parte partes[]=new Parte[length()];
		cabeza.aArray(partes,0);
		return partes;
	}
	public class Parte{
		Parte anterior,siguiente;
		int x,y;
		Parte(int px,int py,Parte ant,Parte sig){
			x=px;
			y=py;
			anterior=ant;
			siguiente=sig;
		}
		public void aArray(Parte partes[],int n){
			partes[n]=this;
			if(siguiente!=null)siguiente.aArray(partes,n+1);
		}
		public int length(){
			if(siguiente==null)return 1;
			else return 1+siguiente.length();
		}
		public boolean colision(Parte p){
			if(p.x==x&&p.y==y)return true;
			else{
				if(siguiente==null)return false;
				else return siguiente.colision(p);
			}
		}
		public boolean equals(Parte p){
			return p.x==x&&p.y==y;
		}
	}
}

