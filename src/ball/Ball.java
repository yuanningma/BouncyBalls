package ball;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	private Color col;
	private int x;
	private int y;
	private double vx;
	private double vy;
	private int rad;
	private int numCollisions = 0;
	
//	public Ball(Color col, int x, int y, double vx, double vy, int rad) {
//		this.col = col;
//		this.x = x;
//		this.y = y;
//		this.vx = vx;
//		this.vy = vy;
//		this.rad = rad;
//		
//	}
//	
	private int returnRandomSign() {
		double sign = Math.random();
		int toret = 1;
		if (sign < 0.5) {
			toret = -1;
		}
		return toret;
	}
//	public Ball(int x, int y) {
//		this.col = new Color(
//                (int) Math.floor((Math.random() * 256)),
//                (int) Math.floor((Math.random() * 256)),
//                (int) Math.floor((Math.random() * 256))
//        );
//		
//		this.x = x;
//		this.y = y;
//		this.vx = returnRandomSign()*Math.floor((Math.random() * 10));
//		this.vy = returnRandomSign()*Math.floor((Math.random() * 10));
//		this.rad = 1;
//	}
//	
	public Ball() {
		this.col = new Color(
                (int) Math.floor((Math.random() * 256)),
                (int) Math.floor((Math.random() * 256)),
                (int) Math.floor((Math.random() * 256))
        );
		this.x = (int)Math.floor((Math.random() * 400));
		this.y = (int)Math.floor((Math.random() * 400));
		this.vx = returnRandomSign()*Math.floor((Math.random() * 5) + 5);
		this.vy = returnRandomSign()*Math.floor((Math.random() * 5) + 5);
		this.rad = (int)Math.floor((Math.random() * 10) + 10);
		System.out.println("Ball: " + this.col.toString() + " color, " + this.x + " x, " + this.y + " y");
	}
//	public Ball(Color col, int x, int y, int rad) {
//		this.col = col;
//		this.x = x;
//		this.y = y;
//		this.vx = returnRandomSign()*Math.floor((Math.random() * 10));
//		this.vy = returnRandomSign()*Math.floor((Math.random() * 10));
//		this.rad = rad;
//	}
//	
//	public Ball(int x, int y, int rad) {
//		this.col = new Color(
//                (int) Math.floor((Math.random() * 256)),
//                (int) Math.floor((Math.random() * 256)),
//                (int) Math.floor((Math.random() * 256))
//        );
//		this.x = x;
//		this.y = y;
//		this.vx = returnRandomSign()*Math.floor((Math.random() * 10));
//		this.vy = returnRandomSign()*Math.floor((Math.random() * 10));
//		this.rad = rad;
//	}
	public boolean recentlyCollided() {
		numCollisions += 1;
		if (numCollisions <= 3) {
			return true;
		} else {
			numCollisions = 0;
			return false;
		}
	}
	public void update() {
		this.x = this.x + (int)this.vx;
		this.y = this.y + (int)this.vy;
	}
	
	public void draw(Graphics g)  {
		g.setColor(this.col);
		g.fillOval(this.x, this.y, this.rad, this.rad);
	}
	
	public boolean isIntersecting(Ball ball2) {
		if (this == ball2) {
			return false;
		}
		int c1x = this.x + this.rad;
		int c1y = this.y + this.rad;
		int c2x = ball2.getX() + ball2.getRad();
		int c2y = ball2.getY() + ball2.getRad();
		int dist = this.rad + ball2.getRad();
		
		int dx = c2x-c1x;
		int dy = c2y-c1y;
		return (dx*dx + dy*dy <= dist);
	}
	
	public void collide(Ball ball2) {
		int rad2 = ball2.getRad();
		int factor11 = (this.rad - rad2)/(this.rad + rad2);
		int factor12 = (2*rad2)/(this.rad + rad2);
		int factor21 = (2*this.rad)/(this.rad+rad2);
		int factor22 = (rad2-this.rad)/(this.rad+rad2);
		
		this.vx = -(factor11*this.vx + factor12*ball2.getVx());
		System.out.println("vx now " + this.vx);
		ball2.setVx(-(factor21*this.vx + factor22*ball2.getVx()));
		
		this.vy = -(factor11*this.vy + factor12*ball2.getVy());
		System.out.println("vy now " + this.vy);
		ball2.setVy(-(factor21*this.vy + factor22*ball2.getVy()));
		this.update();
		ball2.update();
		
	}
	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public int getRad() {
		return rad;
	}
	
	public void setRad(int rad) {
		this.rad = rad;
	}
}
