package sysengineering.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.LinkedList;



public abstract class DrawingShapesPrimitive {
	private String mName;
	private Stroke mStroke;
	private Color mColor;
	private LinkedList<DrawingShapesPrimitive> mParts;
	
	public DrawingShapesPrimitive() {
		this.mParts = new LinkedList<DrawingShapesPrimitive>();
	}

	public String getName() {
		return mName;
	}

	public void setName(String _name) {
		this.mName = _name;
	}

	public Stroke getStroke() {
		return mStroke;
	}

	public void setStroke(Stroke _stroke) {
		this.mStroke = _stroke;
	}

	public Color getColor() {
		return mColor;
	}

	public void setColor(Color _color) {
		this.mColor = _color;
	}
	
	public void addPart(DrawingShapesPrimitive _part) {
		this.mParts.add(_part);
	}
	
	public void removePart(DrawingShapesPrimitive _part) {
		this.mParts.remove(_part);
	}
	
	public LinkedList<DrawingShapesPrimitive> getParts() {
		return this.mParts;
	}


	abstract public java.awt.Rectangle getBoundingBox();

	abstract public void draw(Graphics _g, boolean _all, Double _scale);
	
	abstract public void translateTo(Point2D _p);
}
