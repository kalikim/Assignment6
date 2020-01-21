package sysengineering.model;
import java.util.LinkedList;


public interface DrawingDataObserver {
	public void update(LinkedList<DrawingShapesPrimitive> _data);
}
