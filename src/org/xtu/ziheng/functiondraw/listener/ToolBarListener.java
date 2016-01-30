package org.xtu.ziheng.functiondraw.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.xtu.ziheng.functiondraw.ui.FunctionDrawUI;
import org.xtu.ziheng.functiondraw.ui.ToolBar;

public class ToolBarListener implements ActionListener, ChangeListener{

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ToolBar.function) {
			FunctionDrawUI.mainCalc.getInput(e.getActionCommand());
			FunctionDrawUI.drawingAera.repaint();
		} else if(e.getSource() == ToolBar.cleanButton){
			ToolBar.function.setText("");
			FunctionDrawUI.mainCalc.getInput("");
			FunctionDrawUI.drawingAera.repaint();
		} else if(e.getSource() == ToolBar.InputButton){
			FunctionDrawUI.mainCalc.getInput(ToolBar.function.getText());
			FunctionDrawUI.drawingAera.repaint();
		} else if(e.getSource() == ToolBar.resetButtonX){
			ToolBar.sliderX.setValue(50);
		} else if(e.getSource() == ToolBar.resetButtonY){
			ToolBar.sliderY.setValue(50);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ToolBar.sliderX) {
			FunctionDrawUI.sliderValueX = ((JSlider)e.getSource()).getValue();
			FunctionDrawUI.drawingAera.repaint();
		}else if(e.getSource() == ToolBar.sliderY){
			FunctionDrawUI.sliderValueY = ((JSlider)e.getSource()).getValue();
			FunctionDrawUI.drawingAera.repaint();
		}
	}



}
