package org.xtu.ziheng.functiondraw.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.xtu.ziheng.functiondraw.calc.FunctionCalc;
import org.xtu.ziheng.functiondraw.listener.MenuBarListener;

/**
 * 函数图像工具窗口
 * 功能：创建程序窗口，显示与用户交互信息。
 * @author ziheng
 * @date 2015-12-28
 */

@SuppressWarnings("serial")
public class FunctionDrawUI extends JFrame{
	public static MenuBarListener menuListener;
	public static BufferedImage bufferedImage;
	public static DrawingFuncAera drawingAera;
	public static FunctionCalc  mainCalc;
	public static int sliderValueX = 50;		//滑动条X的值
	public static int sliderValueY = 50;		//滑动条Y的值

	private static FunctionDrawUI mainUI;
	private final Font systemFont = new Font("宋体", Font.PLAIN, 15);
	private final int W = 1024, H = 768;
	
	public FunctionDrawUI() {
		super();
		mainCalc = new FunctionCalc();
		initGlobalFont(systemFont);
	}
	
	//用于生成主界面的方法
	public void draw(){
		this.setTitle("函数图像显示工具");
		this.setMinimumSize(new Dimension(W, H));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	
	
        // 设置标题栏的图标为functiondraw.png
		ImageIcon icon = new ImageIcon("image/functiondraw.png");
		this.setIconImage(icon.getImage());
		setLayout(new BorderLayout());
		
		//调用生成菜单栏的方法生成添加菜单栏 。
		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		//调用生成工具栏的方法添加工具栏
		ToolBar toolBar = new ToolBar();
		this.add(toolBar, BorderLayout.WEST);
		
		//生成一个帮助栏
		TipsBar tipsBar = new TipsBar();
		this.add(tipsBar, BorderLayout.SOUTH);
		
		DrawBackboard backboard = new DrawBackboard();
		this.add(backboard, BorderLayout.CENTER);
		
		drawingAera = new DrawingFuncAera();
		backboard.add(drawingAera, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	/**
	 * 用于对每一个部件的字体进行初始化
	 * @param font
	 */
	private static void initGlobalFont(Font font) {
		FontUIResource fontUIResource = new FontUIResource(font);
		for(Enumeration<Object> keys = UIManager.getDefaults().keys();keys.hasMoreElements();){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource){
				UIManager.put(key, fontUIResource);
			}
		}
	}
	
	
	public static FunctionDrawUI getInstance(){
		return mainUI;
	}
	
 	public static void main(String[] args) {
 		mainUI = new FunctionDrawUI();
		mainUI.draw();
		
	}

}
