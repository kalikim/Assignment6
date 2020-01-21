package sysengineering.controller;

import java.awt.event.KeyAdapter;

public class DrawingKeyAdapter extends KeyAdapter {

public Object additionalInfo;
	
	public DrawingKeyAdapter(Object _additionalInfo) {
		super();
		this.additionalInfo = _additionalInfo;
	}
}
