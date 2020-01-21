package sysengineering.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Point2D;



public class DrawingText extends DrawingShapesPrimitive{

//	private String mName;
	private Point2D mStart;
	
	public DrawingText(Point2D _start, String _name, Stroke _stroke) {
		this.mStart = _start;
//		this.mName = _name;
		this.setName(_name);
		this.setStroke(_stroke);
	}
	
//	public void setmName(String mName) {
//		this.mName = mName;
//	}

	@Override
	public void draw(Graphics _g, boolean _all, Double _scale) {
		Graphics2D g2 = (Graphics2D) _g;
		g2.setColor(getColor());
		Stroke s = this.getStroke();
		if (s != null) {
			g2.setStroke(s);
		}

		int x = (int) this.mStart.getX();
		int y = (int) this.mStart.getY();
		
//		g2.drawString(this.mName, x, y);
		g2.drawString(this.getName(), x, y);
		
		if (_all) {
			for(DrawingShapesPrimitive gp : this.getParts()) {
				gp.draw(_g, _all, _scale);
			}
		}
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translateTo(Point2D _p) {
		this.mStart = _p;
	}

}
