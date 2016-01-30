package org.xtu.ziheng.functiondraw.listener;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.xtu.ziheng.functiondraw.ui.DrawingFuncAera;
import org.xtu.ziheng.functiondraw.ui.FunctionDrawUI;

public class BgColorWindowAndListener implements ActionListener {
	JRadioButton button;
	JDialog dialog;
	Container container;
	JButton jButton;
	JLabel jLabel;
	JPanel jPanel;
	Color color;
	String [] eightColor= {"黑色", "白色", "红色", "黄色", "蓝色", "绿色", "粉色", "灰色" };
	ButtonGroup buttonGroup;
	public BgColorWindowAndListener() {
		// TODO Auto-generated constructor stub
		dialog = new JDialog(FunctionDrawUI.getInstance(), "背景颜色", true);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		container = dialog.getContentPane();
		
		jLabel = new JLabel("背景颜色设置");
		container.add(jLabel);
		
		jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout());
		jPanel.setBackground(Color.GRAY);
		jPanel.setPreferredSize(new Dimension(250, 67));
		
		buttonGroup = new ButtonGroup();
		for(int i = 0; i < eightColor.length; i++){
			button = new JRadioButton(eightColor[i], false);
			button.addActionListener(this);
			jPanel.add(button);
			buttonGroup.add(button);
		}
		
		container.add(jPanel);
		jButton = new JButton("应用选择");
		jButton.addActionListener(this);
		container.add(jButton);
		
		dialog.setSize(300, 200);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "黑色":
			color = Color.BLACK;
			break;
		case "白色":
			color = Color.WHITE;
			break;
		case "红色":
			color = Color.RED;
			break;
		case "黄色":
			color = Color.YELLOW;
			break;
		case "蓝色":
			color = Color.BLUE;
			break;
		case "绿色":
			color = Color.GREEN;
			break;
		case "粉色":
			color = Color.PINK;
			break;
		case "灰色":
			color = Color.GRAY;
			break;
		case "应用选择":
			DrawingFuncAera.background = color;
			JOptionPane.showMessageDialog(button, "应用成功");
			FunctionDrawUI.drawingAera.repaint();
			break;
		}
	}
}
