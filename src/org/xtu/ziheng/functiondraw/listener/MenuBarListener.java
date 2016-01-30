package org.xtu.ziheng.functiondraw.listener;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.xtu.ziheng.functiondraw.ui.FunctionDrawUI;

public class MenuBarListener implements ActionListener{

	
	//重写监听方法
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "新建":	 
			newWindow(); 	
			break;
		case "保存":	 	 
			save();	
			break;
		case "退出":		 
			exit();	 	
			break;
		case "背景颜色":
			new BgColorWindowAndListener();
			break;
		case "线条颜色":
			new LineColorWindowAndListener();
			break;
		case "数轴颜色":
			new ArColorWindowAndListener();
			break;
		case "帮助主题":
			help();
			break;
		case "关于工具":
			aboutME();
			break;
		}
	}
	
	private void newWindow() {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FunctionDrawUI drawUI = new FunctionDrawUI();
				drawUI.draw();
			}
		});
	}

	/**
		 * 将画布中的函数保存为图片
		 */
	private void save() {
		String filePath = null, fileName = null;
		Point top_left = FunctionDrawUI.drawingAera.getLocationOnScreen();
		Rectangle rect = new Rectangle((int) top_left.getX(), (int) top_left.getY(), FunctionDrawUI.drawingAera.getWidth(),
				FunctionDrawUI.drawingAera.getHeight());
		try {
			FunctionDrawUI.bufferedImage = new Robot().createScreenCapture(rect);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (fileName == null) {
			FileDialog fileDialog = new FileDialog(FunctionDrawUI.getInstance(), "存储到", FileDialog.SAVE);
			fileDialog.setVisible(true);
			fileName = fileDialog.getFile();
			filePath = fileDialog.getDirectory();
		}
		System.out.println(filePath + fileName);
		if(fileName == null || filePath == null){
			return ;
		}
		if (!fileName.endsWith(".jpg")) {
			fileName += ".jpg";
		}
		File file = new File(filePath + fileName);
		if (file != null) {
			try {
				if (ImageIO.write(FunctionDrawUI.bufferedImage, "jpg", file)) {
					JOptionPane.showMessageDialog(null, "保存成功");
					FunctionDrawUI.getInstance().setTitle(fileName + " - 函数图像工具");
				} else {
					JOptionPane.showMessageDialog(null, "保存失败", "错误", JOptionPane.ERROR_MESSAGE);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "保存失败", "错误", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	private void exit() {
		System.exit(0);
	}
	
	private void help(){
		JDialog dialog = new JDialog(FunctionDrawUI.getInstance(), "帮助主题", false);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
		dialog.setSize(650, 700);
		dialog.setLocationRelativeTo(null);
		Container container = dialog.getContentPane();

		Image imageIcon = new ImageIcon("image/help.png").getImage();
		imageIcon = imageIcon.getScaledInstance(640, 600, 0);
		JLabel jLabel = new JLabel();
		jLabel.setIcon(new ImageIcon(imageIcon));
		container.add(jLabel);
		
		JButton jButton = new JButton("确认");
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		container.add(jButton);
		dialog.setVisible(true);
		
	}
	
	private void aboutME(){
		JDialog dialog = new JDialog(FunctionDrawUI.getInstance(), "关于工具", true);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
		dialog.setSize(632, 350);
		dialog.setLocationRelativeTo(null);
		Container container = dialog.getContentPane();

		Image imageIcon = new ImageIcon("image/about.png").getImage();
		imageIcon = imageIcon.getScaledInstance(632, 240, 0);
		JLabel jLabel = new JLabel();
		jLabel.setIcon(new ImageIcon(imageIcon));
		container.add(jLabel);
		
		JButton jButton = new JButton("确认");
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		container.add(jButton);
		dialog.setVisible(true);
	}
}
