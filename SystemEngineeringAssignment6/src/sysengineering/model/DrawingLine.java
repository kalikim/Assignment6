package sysengineering.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;





public abstract class DrawingLine extends DrawingShapesPrimitive {

	protected Point2D start;
	protected Point2D end;
	/**
	 * creates a new Line object
	 * @param mMousePressedPoint
	 *            : start-point of line
	 * @param point2d
	 *            : end-point of line
	 */
	public DrawingLine(Point2D _start, Point2D _end) {
		this.start = _start;
		this.end = _end;
	}

	@Override
	public Rectangle getBoundingBox() {
		int startX = (int) this.start.getX();
		int startY = (int) this.start.getY();
		int width = (int) (this.end.getX() - this.start.getX());
		int height = (int) (this.end.getY() - this.end.getY());

		java.awt.Rectangle rec = new java.awt.Rectangle(startX, startY, width,
				height);
		return rec;
	}

	public boolean equals(DrawingLine line) {
		boolean straight = this.start.equals(line.start) && this.end.equals(line.end);
		//boolean reversed = this.start.equals(line.end) && this.end.equals(line.start);
		return straight;// || reversed;
	}
	
	public Point2D getUnitVector() {
		Point2D vector = getVector();
		Double length = getLength();
		
		Double x = vector.getX() / length;
		Double y = vector.getY() / length;
		
		return new Point2D.Double(x, y);
	}
	
	public Double getLength() {
		Point2D vector = getVector();
		return Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
	}
	
	public Point2D getVector() {
		return new Point2D.Double(end.getX() - start.getX(), end.getY() - start.getY());
	}
	
	public Point2D getCenter() {
		Point2D vector = getVector();
		return new Point2D.Double(this.start.getX() + vector.getX() * 0.5, this.start.getY() + vector.getY() * 0.5);
	}
	
	public Point2D getArrowPointOnLine(Point2D p, Double length) {
//		Double length = 15.0;
		Point2D unitVector = this.getUnitVector();
//		return new Point2D.Double(this.end.getX() - length * unitVector.getX(), this.end.getY() - length * unitVector.getY());
		return new Point2D.Double(p.getX() - length * unitVector.getX(), p.getY() - length * unitVector.getY());
	}
	
	/**
	 * Calculates the distance from a point to a given line
	 * @param A startPoint of line
	 * @param B endPoint of line
	 * @param P the point to measure the distance
	 * @return the distance from a line (A--B) to a point (P)
	 */
	private double pointToLineDistance(Point2D A, Point2D B, Point2D P) {
	    double normalLength = Math.sqrt((B.getX()-A.getX())*(B.getX()-A.getX())+(B.getY()-A.getY())*(B.getY()-A.getY()));
	    return Math.abs((P.getX()-A.getX())*(B.getY()-A.getY())-(P.getY()-A.getY())*(B.getX()-A.getX()))/normalLength;
	  }
	
	public boolean contains(Point2D _p) {
		double minX = Math.min(this.start.getX(), this.end.getX());
		double maxX = Math.max(this.start.getX(), this.end.getX());
		double minY = Math.min(this.start.getY(), this.end.getY());
		double maxY = Math.max(this.start.getY(), this.end.getY());
		double width = maxX - minX;
		double height = maxY - minY;
		Rectangle rec = new Rectangle((int) Math.round(minX), (int) Math.round(minY), (int) Math.round(width), (int) Math.round(height));
		if (rec.contains(_p)) {
			return this.pointToLineDistance(this.start, this.end, _p) <= 4;
		}
		return false;
	}
	
	public Point2D rotateAround(Point2D center, Point2D point, double angle) {
		double tempx = center.getX() + (Math.cos(Math.toRadians(angle)) * (point.getX() - center.getX()) - Math.sin(Math.toRadians(angle)) * (point.getY() - center.getY()));
		double tempy = center.getY() + (Math.sin(Math.toRadians(angle)) * (point.getX() - center.getX()) + Math.cos(Math.toRadians(angle)) * (point.getY() - center.getY()));
		
		return new Point2D.Double(tempx, tempy);
	}
	
	protected void drawArrow(Point2D p, Graphics2D g2) {
		Point2D onLine = this.getArrowPointOnLine(p, 15.0);
		Point2D arrow1 = this.rotateAround(p, onLine, 15);
		Point2D arrow2 = this.rotateAround(p, onLine, -15);
		
		g2.drawLine((int) p.getX(), (int) p.getY(), (int) arrow1.getX(), (int) arrow1.getY());
		g2.drawLine((int) p.getX(), (int) p.getY(), (int) arrow2.getX(), (int) arrow2.getY());
		g2.drawLine((int) arrow1.getX(), (int) arrow1.getY(), (int) arrow2.getX(), (int) arrow2.getY());
	}
	
	protected void drawFullArrow(Point2D p, Graphics2D g2) {
		Point2D onLine = this.getArrowPointOnLine(p, 15.0);
		Point2D arrow1 = this.rotateAround(p, onLine, 15);
		Point2D arrow2 = this.rotateAround(p, onLine, -15);
		
		int[] xArr = {(int) p.getX(), (int) arrow1.getX(), (int) arrow2.getX()};
		int[] yArr = {(int) p.getY(), (int) arrow1.getY(), (int) arrow2.getY()};
		
		g2.fillPolygon(xArr, yArr, 3);
	}
}
