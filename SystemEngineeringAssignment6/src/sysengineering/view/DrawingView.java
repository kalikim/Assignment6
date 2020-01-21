package sysengineering.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import sysengineering.controller.DrawingController;
import sysengineering.model.DrawingDataObserver;
import sysengineering.model.DrawingShapesPrimitive;



public class DrawingView implements DrawingDataObserver{

	private DrawingController mDrawingController;
	private Frame mMainFrame;
	
	private ComponentsPanel mPanelNorth;
	private DrawingPanel mPanelDrawing;
	private OverviewPanel mPanelOverview;
	
	public DrawingView(DrawingController _drawingController) {
		mDrawingController = _drawingController;
		this.drawWindow();
	}
	
	/**
	 * This method draws the main-frame to screen
	 */
	private void drawWindow() {
		mMainFrame = new Frame("Editor Application Assignment 6 ");
		mMainFrame.setSize(800, 400);
		mMainFrame.setMinimumSize(new Dimension(300, 200));
		mMainFrame.setMaximumSize(new Dimension(1920, 1920));
		//mMainFrame.addComponentListener(mGisController);
		
		mMainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mMainFrame.setVisible(false);
				System.exit(0);
			}
		});
		
		this.mPanelNorth = ComponentsPanel.getSharedInstance(mDrawingController);
		mMainFrame.add(this.mPanelNorth, BorderLayout.NORTH);
		
		this.mPanelDrawing = DrawingPanel.getSharedInstance(mDrawingController);
		mMainFrame.add(this.mPanelDrawing, BorderLayout.CENTER);
		
		this.mPanelOverview = OverviewPanel.getSharedInstance(mDrawingController);
		this.mPanelOverview.setPreferredSize(new Dimension(600 / 3, 400));
//		this.mPanelOverview.setPreferredSize(new Dimension(100, 300));
		mMainFrame.add(mPanelOverview, BorderLayout.EAST);
		
		mMainFrame.setVisible(true);
	}

	@Override
	public void update(LinkedList<DrawingShapesPrimitive> _data) {
		// TODO Auto-generated method stub
		this.mPanelDrawing.repaint();
		this.mPanelOverview.repaint();
	}

	
}
