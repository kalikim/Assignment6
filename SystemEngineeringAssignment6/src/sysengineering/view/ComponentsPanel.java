package sysengineering.view;

import java.awt.Color;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;


import sysengineering.controller.DrawingController;
import sysengineering.controller.DrawingKeyAdapter;
import sysengineering.controller.DrawingTextFieldChangedListener;



public class ComponentsPanel extends Panel {
	private static final long serialVersionUID = 1L;

	private static ComponentsPanel sharedInstance;
	
	// observer
		private LinkedList<DrawingTextFieldChangedListener> mObservers;
	
	private JRadioButton jrbRectangle;
	private JRadioButton jrbLine;
	private JRadioButton jrbComment;
	private TextField tfName;
	private JRadioButton jrbMove;
	private JRadioButton jrbRemove;
	private JRadioButton jrbRename;
	
	
	public static ComponentsPanel getSharedInstance(DrawingController _eaController) {
		if(ComponentsPanel.sharedInstance != null) {
			return ComponentsPanel.sharedInstance;
		} else {
			ComponentsPanel.sharedInstance = new ComponentsPanel(_eaController);
			return ComponentsPanel.sharedInstance;
		}
	}
	
	
	private ComponentsPanel(DrawingController _eaController) {
		this.mObservers = new LinkedList<DrawingTextFieldChangedListener>();
		this.addObserver(_eaController);
		
		this.setBackground(Color.WHITE);
		
		jrbRectangle = new JRadioButton("Rectangle");
		jrbRectangle.setSelected(true);
		jrbRectangle.addActionListener(_eaController);
		
		jrbLine = new JRadioButton("Line");
		jrbLine.addActionListener(_eaController);
		
		jrbComment = new JRadioButton("Comment");
		jrbComment.addActionListener(_eaController);
		
		jrbMove = new JRadioButton("Move");
		jrbMove.addActionListener(_eaController);
		
		jrbRemove = new JRadioButton("Remove");
		jrbRemove.addActionListener(_eaController);
		
		jrbRename = new JRadioButton("Rename");
		jrbRename.addActionListener(_eaController);
		
		ButtonGroup radioButtonsGroup = new ButtonGroup();
		radioButtonsGroup.add(jrbRectangle);
		radioButtonsGroup.add(jrbLine);
		radioButtonsGroup.add(jrbComment);
		radioButtonsGroup.add(jrbMove);
		radioButtonsGroup.add(jrbRemove);
		radioButtonsGroup.add(jrbRename);
		
		this.tfName = new TextField("name", 25);
		this.tfName.addActionListener(_eaController);

		KeyAdapter ka = new DrawingKeyAdapter(this) {
			public void keyReleased(KeyEvent ke) {
	            if(!(ke.getKeyChar()==27||ke.getKeyChar()==65535))//this section will execute only when user is editing the JTextField
	            {
	                System.out.println("User is editing something in TextField");
	                if (this.additionalInfo instanceof ComponentsPanel) {
	                		ComponentsPanel pn = (ComponentsPanel) this.additionalInfo;
	                		pn.notifyObservers(pn.tfName);
	                }
	            }
	        }
		};
		
		this.tfName.addKeyListener(ka);
		
		this.add(this.jrbRectangle);
		this.add(this.jrbLine);
		this.add(this.jrbComment);
		this.add(this.tfName);
		this.add(this.jrbRename);
		this.add(this.jrbMove);
		this.add(this.jrbRemove);
	}
	
	/**
	 * This method adds an observer to the observer list observer gets called if
	 * textField gets changed
	 * 
	 * @param _observer
	 *            data observer
	 */
	public void addObserver(DrawingTextFieldChangedListener _observer) {
		this.mObservers.add(_observer);
	}
	
	public void notifyObservers(TextField tf) {
		for (int i = 0; i < this.mObservers.size(); i++) {
			this.mObservers.get(i).textFieldChanged(tf);
		}
	}
	
	public boolean isJRBRectangle(JRadioButton _radioButton) {
		return _radioButton == this.jrbRectangle;
	}
	public boolean isJRBLine(JRadioButton _radioButton) {
		return _radioButton == this.jrbLine;
	}
}
