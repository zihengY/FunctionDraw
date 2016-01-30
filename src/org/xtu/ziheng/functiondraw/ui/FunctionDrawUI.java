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
 * ����ͼ�񹤾ߴ���
 * ���ܣ��������򴰿ڣ���ʾ���û�������Ϣ��
 * @author ziheng
 * @date 2015-12-28
 */

@SuppressWarnings("serial")
public class FunctionDrawUI extends JFrame{
	public static MenuBarListener menuListener;
	public static BufferedImage bufferedImage;
	public static DrawingFuncAera drawingAera;
	public static FunctionCalc  mainCalc;
	public static int sliderValueX = 50;		//������X��ֵ
	public static int sliderValueY = 50;		//������Y��ֵ

	private static FunctionDrawUI mainUI;
	private final Font systemFont = new Font("����", Font.PLAIN, 15);
	private final int W = 1024, H = 768;
	
	public FunctionDrawUI() {
		super();
		mainCalc = new FunctionCalc();
		initGlobalFont(systemFont);
	}
	
	//��������������ķ���
	public void draw(){
		this.setTitle("����ͼ����ʾ����");
		this.setMinimumSize(new Dimension(W, H));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	
	
        // ���ñ�������ͼ��Ϊfunctiondraw.png
		ImageIcon icon = new ImageIcon("image/functiondraw.png");
		this.setIconImage(icon.getImage());
		setLayout(new BorderLayout());
		
		//�������ɲ˵����ķ���������Ӳ˵��� ��
		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		//�������ɹ������ķ�����ӹ�����
		ToolBar toolBar = new ToolBar();
		this.add(toolBar, BorderLayout.WEST);
		
		//����һ��������
		TipsBar tipsBar = new TipsBar();
		this.add(tipsBar, BorderLayout.SOUTH);
		
		DrawBackboard backboard = new DrawBackboard();
		this.add(backboard, BorderLayout.CENTER);
		
		drawingAera = new DrawingFuncAera();
		backboard.add(drawingAera, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	/**
	 * ���ڶ�ÿһ��������������г�ʼ��
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
