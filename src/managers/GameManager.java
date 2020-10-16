package managers;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import ball.Ball;

public class GameManager{


	private List<Ball> balls;
	private ControlPanel controlPanel;
	private JFrame frame;
	private DrawPanel drawPanel;
	private int width;
	private int height;
	private int numBalls;
	private boolean paused;
	
	public GameManager() {
		this.balls = new ArrayList<Ball>();
		this.frame = new JFrame();
		this.width = 400;
		this.height = 400;
		for (int i = 0; i < 10; i++) {
			Ball ball = new Ball();
			this.balls.add(ball);
			this.numBalls ++;
		}
//		this.drawPanel = new DrawPanel();
//		
//		 this.frame.getContentPane().add(drawPanel);
//		 this.frame.setTitle("Bouncing Balls");
//		 this.frame.setSize(this.width, this.height);
//		 this.frame.setVisible(true);
//		 this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		 
		 
		 
		 
		 

         
	}
	public void run() {
		this.drawPanel = new DrawPanel();
		this.controlPanel = new ControlPanel();
		this.frame.setLayout(new BorderLayout());
		this.frame.getContentPane().add(drawPanel, BorderLayout.CENTER);
		this.frame.getContentPane().add(this.controlPanel, BorderLayout.SOUTH);
		
		this.frame.setTitle("Bouncing Balls");
		this.frame.setSize(this.width, this.height);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);
		
		
		while (true) {
			if (!paused) {
			 for (Ball b: balls) {
	                b.update();
	                for (Ball b2: balls) {
//	                	if (b.isIntersecting(b2)) {
////	                		if (!b.recentlyCollided() && !b2.recentlyCollided()) {
////	                			b.collide(b2);
////	                		}
////	                		System.out.println("X and y vels: " + b.getVx() + " , " + b.getVy() + " , " + b2.getVx() + " , " + b2.getVy());
//	                		b.collide(b2);
////	                		System.out.println("New X and y vels: " + b.getVx() + " , " + b.getVy()+ " , " + b2.getVx() + " , " + b2.getVy());
//	                		
////	                		System.out.println("collision detected");
//	                		
//	                	}
	                }
	                int x = b.getX();
	                int y = b.getY();
	                if (x > this.width || x < 0) {
	                	b.setVx(b.getVx()*(-1));
	                }
	                if (y > this.height || y < 0) {
	                	b.setVy(b.getVy()*(-1));
	                }
	                if (x < 0) {
	                	b.setX(0);
	                }
	                if (y < 0) {
	                	b.setY(0);
	                }
	                if (x > this.width) {
	                	b.setX(this.width);
	                }
	                if (y > this.height) {
	                	b.setY(this.height);
	                }
	          }
			 this.frame.repaint();
			 }
			 try {
	             Thread.sleep(10);
	         } catch (InterruptedException e) {
	             e.printStackTrace();
	         }
			 
		
		}
	}
	
	private class DrawPanel extends JPanel {
		public DrawPanel() {
			super();
			setPreferredSize(new Dimension(width, height));
		}
		@Override
		public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            for (Ball b: balls) {
                b.draw(graphics);
            }

        }
		
	}
	
	private class ControlPanel extends JPanel {

		public ControlPanel() {
			super();
			setPreferredSize(new Dimension(width, height/4));
			JCheckBox pauseControl = new JCheckBox();
	        this.add(new JLabel("Pause"));
	        this.add(pauseControl);
	        pauseControl.addItemListener(new ItemListener() {
	           @Override
	           public void itemStateChanged(ItemEvent e) {
	              paused = !paused;  // Toggle pause/resume flag
	              System.out.println("Now paused is " + paused);
	              transferFocusUpCycle();  // To handle key events
	           }
	        });
	        
	        final JButton addBall = new JButton("Add New Ball");
	        this.add(addBall);
	        addBall.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               Ball newBall = new Ball();
	               
	               balls.add(newBall);
	               numBalls ++;
	               System.out.println("Now there are " + numBalls + " balls");
	               frame.repaint();
	               transferFocusUpCycle();  // To handle key events
	            }
	         });
	        
	        final JButton removeBall = new JButton("Remove a Ball");
	        this.add(removeBall);
	        removeBall.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if (balls.size() > 0) {
	            		balls.remove(0);
	 	               numBalls--;
	 	               frame.repaint();
	 	               transferFocusUpCycle();  // To handle key events
	            	}
	               
	            }
	         });
	        
	        final JButton removeAll = new JButton("Clear Screen");
	        this.add(removeAll);
	        removeAll.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	balls = new ArrayList<Ball>();
	            	numBalls = 0;
	            	frame.repaint();
	            	transferFocusUpCycle();
	               
	            }
	         });
		}
		
	}
	

}
