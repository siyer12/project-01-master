import Core.Data;
import Core.Scale;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Calendar;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**This runs the GUI simulator for Eye Tracking Device
 *  The simulator shows movement of eyes and validation scroller value from 0 - 4
 * @author Bharat Goel
 */
public class GuiEye extends JPanel  {
	    private Scale mousePosition = new Scale(0, 0);
	    private int scroll;
	    Data data;
	    //private Scroll window = new Scroll();
	    private Eye leftEye = new Eye(350 - 120, 200, 100, 20);
	    private Eye rightEye = new Eye(350 + 120, 200, 100, 20);
	    
	    private Font font = new Font("Arial", Font.PLAIN, 50);
	    private Font font2 = new Font("Arial", Font.PLAIN, 150);
	    static JSlider slider_1 = new JSlider();
	    public GuiEye() {
	        setPreferredSize(new Dimension(800, 600));
	        addMouseMotionListener(new MouseHandler());

	        slider_1.setMaximum(4);
    		slider_1.setMajorTickSpacing(1);
    		slider_1.setPaintTicks(true);
    		slider_1.setValue(0);
    		slider_1.setFont(new Font("Calibri", Font.BOLD, 16));
    		slider_1.setToolTipText("Move me");
    		slider_1.setForeground(Color.WHITE);
    		slider_1.setBackground(Color.PINK);
    		slider_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    		slider_1.setPaintLabels(true);
			slider_1.addChangeListener(new ChangeListener(){ public void stateChanged(ChangeEvent e) {
				scroll = slider_1.getValue(); }});
	    }


	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        leftEye.draw(g, mousePosition);
	        rightEye.draw(g, mousePosition);
	        
	        /*g.setFont(font);
	        g.drawString("Eye Tracking Simulator", 100, 180);
	        /*g.setFont(font2);
	        g.drawString("+1", 180, 350);
	        g.setFont(font);
	        g.drawString("subscribers :) !!!", 220, 460);*/
	    }


	    private class MouseHandler extends MouseAdapter {
	        @Override
	        public void mouseMoved(MouseEvent e) {
	            mousePosition.setX(e.getX());
	            mousePosition.setY(e.getY());
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				long initialTime = calendar.getTimeInMillis();
				double timeStamp = 0;
				timeStamp = (System.currentTimeMillis() - initialTime) * .001;
				data=new Data(timeStamp,e.getX(),e.getY(),scroll);
	            repaint();
	        }
	    }

	    public Data getData(){
	    	return data;

		}
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                GuiEye guiEye = new GuiEye();
	                JFrame frame = new JFrame();	              
	                frame.setTitle("Eye Tracking Simulator");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.getContentPane().add(guiEye);
	                frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	                frame.setResizable(false);
	                frame.setBounds(50, 50, 700, 500);
	                frame.setBackground(Color.YELLOW);	           
	        		frame.getContentPane().add(slider_1, BorderLayout.SOUTH);

	                guiEye.requestFocus();
	            }

	        });
	    }
}
