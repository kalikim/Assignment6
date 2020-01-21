package sysengineering.model;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.LinkedList;





public class DrawingModel {

	// observer
		private LinkedList<DrawingDataObserver> mObservers;

		// data-model
		private LinkedList<DrawingShapesPrimitive> mShapesPrimitives;

		// singleton
		private static DrawingModel sharedInstance;

//		private String mCurrentName;

		public static DrawingModel getSharedInstance() {
			if (DrawingModel.sharedInstance != null) {
				return DrawingModel.sharedInstance;
			} else {
				DrawingModel.sharedInstance = new DrawingModel();
				return DrawingModel.sharedInstance;
			}
		}

		private DrawingModel() {
			this.mObservers = new LinkedList<DrawingDataObserver>();
			this.mShapesPrimitives = new LinkedList<DrawingShapesPrimitive>();
//			this.mCurrentName = "name";
		}

		/**
		 * This method adds an observer to the observer list observer gets called if
		 * data-model gets updated
		 * 
		 * @param _observer
		 *            data observer
		 */
		public void addObserver(DrawingDataObserver _observer) {
			this.mObservers.add(_observer);
		}

		/**
		 * This method adds a new rectangle to the data-model
		 * 
		 * @param _p
		 */
		public void addGraphics(DrawingShapesPrimitive _p) {
			this.mShapesPrimitives.add(_p);
			this.updateObserver();
		}
		
		public void removeGraphics(DrawingShapesPrimitive _p) {
			this.mShapesPrimitives.remove(_p);
			this.updateObserver();
		}
		
		public void renameGraphics(Point2D _p, String _name) {
			DrawingShapesPrimitive gp = this.getDrawingShapesPrimitivesForPoint(_p);
			gp.setName(_name);
			for (DrawingShapesPrimitive gp2 : gp.getParts()) {
				gp2.setName(_name);
				if (gp2 instanceof DrawingText) {
					DrawingText text = (DrawingText) gp2;
					text.setName(_name);
				}
			}
			this.updateObserver();
		}

		public boolean isPointInRectangle(Point2D point) {
			boolean isInRectangle = false;
			for (DrawingShapesPrimitive gp : this.mShapesPrimitives) {
				if (gp instanceof DrawingRectangle) {
					DrawingRectangle rec = (DrawingRectangle) gp;
					if (rec.contains(point)) {
						isInRectangle = true;
						break;
					}
				}
			}
			return isInRectangle;
		}

		public DrawingRectangle getDrawingRectangleForPoint(Point2D point) {
			for (DrawingShapesPrimitive gp : this.mShapesPrimitives) {
				if (gp instanceof DrawingRectangle) {
					DrawingRectangle rec = (DrawingRectangle) gp;
					if (rec.contains(point)) {
						return rec;
					}
				}
			}
			return null;
		}
		
		public DrawingShapesPrimitive getDrawingShapesPrimitivesForPoint(Point2D point) {
			for (DrawingShapesPrimitive gp : this.mShapesPrimitives) {
				if (gp instanceof DrawingRectangle) {
					DrawingRectangle rec = (DrawingRectangle) gp;
					if (rec.contains(point)) {
						return rec;
					}
				} else if (gp instanceof DrawingLine)  {
					DrawingLine line = (DrawingLine) gp;
					if (line.contains(point)) {
						return line;
					}
				}
			}
			return null;
		}

		public void repaint(Graphics _g, boolean _all, Double _scale) {
			for(DrawingShapesPrimitive gp : mShapesPrimitives) {
				gp.draw(_g, _all, _scale);
			}
		}
		
		public void updateLinesWithPointToPoint(Point2D pOrig, Point2D pNew) {
			for (DrawingShapesPrimitive gp : this.mShapesPrimitives) {
				if (gp instanceof DrawingLine) {
					DrawingLine line = (DrawingLine) gp;
					if (line.start.equals(pOrig)) {
						line.start = pNew;
					} else if (line.end.equals(pOrig)) {
						line.end = pNew;
					}
				}
				
				if (gp instanceof DrawingLineSolid) {
					DrawingLineSolid line = (DrawingLineSolid) gp;
					line.updateTextPosition(line.getCenter());
				}
			}
		}

		public boolean intersects(DrawingRectangle rectangle) {
			boolean intersects = false;
			for (DrawingShapesPrimitive gp : this.mShapesPrimitives) {
				if (gp instanceof DrawingRectangle) {
					DrawingRectangle rec = (DrawingRectangle) gp;
					if (rec.intersectsWith(rectangle)) {
						intersects = true;
						break;
					}
				}
			}
			return intersects;
		}

		public boolean contains(DrawingLine line) {
			boolean contains = false;
			for (DrawingShapesPrimitive gp : this.mShapesPrimitives) {
				if (gp instanceof DrawingLine) {
					DrawingLine l = (DrawingLine) gp;
					if (l.equals(line)) {
						contains = true;
						break;
					}
				}
			}
			return contains;
		}

		public int getNumberOfDrawingRectangles() {
			int count = 0;
			for (DrawingShapesPrimitive gp : this.mShapesPrimitives) {
				if (gp instanceof DrawingRectangle) {
					count++;
				}
			}
			return count;
		}

		public int getNumberOfDrawingLines() {
			int count = 0;
			for (DrawingShapesPrimitive gp : this.mShapesPrimitives) {
				if (gp instanceof DrawingLine) {
					count++;
				}
			}
			return count;
		}
		
		public void updateObserver() {
			for (int i = 0; i < this.mObservers.size(); i++) {
				this.mObservers.get(i).update(mShapesPrimitives);
			}
		}


}
