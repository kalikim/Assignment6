package sysengineering.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

import sysengineering.controller.DrawingController;
import sysengineering.model.DrawingModel;



public class OverviewPanel extends Panel {

private static final long serialVersionUID = 1L;
	
	private static OverviewPanel sharedInstance;
	
	
	public static OverviewPanel getSharedInstance(DrawingController drawingController) {
		if(OverviewPanel.sharedInstance != null) {
			return OverviewPanel.sharedInstance;
		} else {
			OverviewPanel.sharedInstance = new OverviewPanel(drawingController);
			return OverviewPanel.sharedInstance;
		}
	}
	

	private OverviewPanel(DrawingController drawingController) {
//		addMouseListener(drawingController);
//		addMouseMotionListener(drawingController);
		setBackground(Color.LIGHT_GRAY);
		System.out.println("Panel created...");

		BorderLayout bl = new BorderLayout();

		bl.setVgap(1);
		this.setLayout(bl);
	}
	
	@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			
			DrawingModel.getSharedInstance().repaint(g, false, 10.0);
		}
}
