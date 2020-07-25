package SliderButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;



public class Switcher extends JComponent{

 boolean band;
 private ChangeSwitchListener listener =null; 
 public Switcher(boolean band) {
	 this.band=band;
	 setupEvents();
}
	
	private void setupEvents() {
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isBand()) {
					setBand(false);	
					
				}else {
					setBand(true);
				}
				if (listener!=null) {
					listener.SwitchChanged();
				}
			}
		});
		
}

	public void paint( Graphics g){
	 int width=getWidth();
	 int height	= getHeight();
	 
	 g.setColor(Color.BLACK);
	 g.drawRect((width/2)-25, height/3, height*44/height, (height/3));
		
		if (band) {
			g.setColor(Color.GREEN);
			g.fillRect((width/2)-2,(height/3)+2,height*20/height,(height/3)-3);
		}else {
			g.setColor(Color.RED);
			g.fillRect((width/2)-23,(height/3)+2, height*20/height,(height/3)-3);
		}
	}

	public boolean isBand() {
		return band;
	}

	public void setBand(boolean band) {
		this.band = band;
		repaint();
	}
	
	public void addChangeSwitchListener(ChangeSwitchListener l){
		listener=l;
	}
}
