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
	String [] eightColor= {"��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ" };
	ButtonGroup buttonGroup;
	public BgColorWindowAndListener() {
		// TODO Auto-generated constructor stub
		dialog = new JDialog(FunctionDrawUI.getInstance(), "������ɫ", true);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		container = dialog.getContentPane();
		
		jLabel = new JLabel("������ɫ����");
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
		jButton = new JButton("Ӧ��ѡ��");
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
		case "��ɫ":
			color = Color.BLACK;
			break;
		case "��ɫ":
			color = Color.WHITE;
			break;
		case "��ɫ":
			color = Color.RED;
			break;
		case "��ɫ":
			color = Color.YELLOW;
			break;
		case "��ɫ":
			color = Color.BLUE;
			break;
		case "��ɫ":
			color = Color.GREEN;
			break;
		case "��ɫ":
			color = Color.PINK;
			break;
		case "��ɫ":
			color = Color.GRAY;
			break;
		case "Ӧ��ѡ��":
			DrawingFuncAera.background = color;
			JOptionPane.showMessageDialog(button, "Ӧ�óɹ�");
			FunctionDrawUI.drawingAera.repaint();
			break;
		}
	}
}
