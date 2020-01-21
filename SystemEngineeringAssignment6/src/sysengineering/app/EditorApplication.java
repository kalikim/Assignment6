package sysengineering.app;


import sysengineering.controller.DrawingController;
import sysengineering.model.DrawingModel;
import sysengineering.view.DrawingView;

public class EditorApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DrawingModel model = DrawingModel.getSharedInstance();
		//DrawingController controller = new DrawingController(model);
		//DrawingView view = new DrawingView(controller);
		//model.addObserver(view);
		
		DrawingModel model = DrawingModel.getSharedInstance();
		DrawingController controller = new DrawingController(model);
		DrawingView view = new DrawingView(controller);
		model.addObserver(view);

	}

}
