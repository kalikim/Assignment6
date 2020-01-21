package sysengineering.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import sysengineering.app.DrawingHelper;





public  class DrawingRectangle extends DrawingShapesPrimitive {

	protected Point2D start;
	protected int width;
	protected int height;
	
	protected LinkedList<DrawingLine> incomingLines;
	protected LinkedList<DrawingLine> outgoingLines;

	public DrawingRectangle(Point2D start) {
		this(start, 70);
	}
	
	public DrawingRectangle(Point2D start, int square) {
		this(start, square, square);
	}
	
	public DrawingRectangle(Point2D start , int width, int height) {
		this.start = start;
		this.width = width;
		this.height = height;
		
		this.setStroke(new BasicStroke(1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 1.0f }, // Dash pattern
				0.0f));
		this.setColor(Color.BLACK);
		
		this.incomingLines = new LinkedList<DrawingLine>();
		this.outgoingLines = new LinkedList<DrawingLine>();
	}

	public Point2D getStart() {
		return start;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

	public Point2D getCenter() {
		return new Point2D.Double(this.start.getX() + this.width / 2, this.start.getY() + this.height / 2);
	}
	
	public void addIncomingLine(DrawingLine line) {
		this.incomingLines.add(line);
	}
	
	public void addOutgoingLine(DrawingLine line) {
		this.outgoingLines.add(line);
	}
	
	public LinkedList<DrawingLine> getIncomingLines() {
		return this.incomingLines;
	}
	
	public LinkedList<DrawingLine> getOutgoingLines() {
		return this.outgoingLines;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(new Point((int) Math.round(this.start.getX()), (int) Math.round(this.start.getY())), new Dimension(this.width, this.height));
	}

	public boolean contains(Point2D point) {
		return this.getBoundingBox().contains(point);
	}

	public boolean intersectsWith(DrawingRectangle rectangle) {
		Point2D p1 = rectangle.start;
		Point2D p2 = new Point2D.Double(rectangle.start.getX() + rectangle.width, rectangle.start.getY());
		Point2D p3 = new Point2D.Double(rectangle.start.getX() + rectangle.width, rectangle.start.getY() + rectangle.height);
		Point2D p4 = new Point2D.Double(rectangle.start.getX(), rectangle.start.getY() + rectangle.height);
		
		Point2D[] points = {p1, p2, p3, p4};
		
		boolean intersects = false;
		
		for(Point2D p: points) {
			if(this.contains(p)) {
				intersects = true;
				break;
			}
		}
		return intersects;
	}
	
	public void translateTo(Point2D _p) {
		Point2D startTemp = this.start;
		this.start.setLocation(_p);
		for(DrawingShapesPrimitive gp: this.getParts()) {
			gp.translateTo(_p);
		}
	}
	
	@Override
	public void draw(Graphics _g, boolean _all, Double _scale) {
		Graphics2D g2 = (Graphics2D) _g;
		g2.setColor(this.getColor());
		g2.setStroke(this.getStroke());
		
		Point2D newStart = DrawingHelper.getPoint(this.getStart(), 1.0 / _scale);
		
		int x = (int) Math.round(newStart.getX());
		int y = (int) Math.round(newStart.getY());
		
		g2.drawRect(x, y, this.width / (int) Math.round(_scale), this.height / (int) Math.round(_scale));
		
		if (_all) {
			for(DrawingShapesPrimitive gp : this.getParts()) {
				gp.draw(_g, _all, _scale);
			}
		}
	}
}
