package org.xtu.ziheng.functiondraw.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import org.xtu.ziheng.functiondraw.listener.ToolBarListener;

/**
 * 侧工具栏
 * 功能： 输入函数，调整X,Y轴的缩放比例
 * @author ziheng
 *
 */
@SuppressWarnings("serial")
public class ToolBar extends JPanel{
	
	String tipTags[] = {"请输入函数表达式：", "调整X轴单位长度：", "调整Y轴单位长度："};

	JLabel tipTag;										//标签	
	public static JSlider sliderX, sliderY;				//分别控制XY轴缩放比例的滑动条													
	public static JTextField function;					//用于输入函数的文本框
	public static JButton cleanButton, InputButton; 	//输入函数的文本框控制按钮
	public static JButton resetButtonX, resetButtonY;	//滑块复位
	public ToolBar() {
		super();
		unitTools();
	}
	
	public void unitTools(){
		
		//设置布局
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
		//设置对话框大小
		this.setPreferredSize(new Dimension(220, 1080));
		//监听器声明
		ToolBarListener toolListener = new ToolBarListener();	
		
		//添加顶部欢迎图片
		Image imageIcon = new ImageIcon("image/welcome.png").getImage();
		imageIcon = imageIcon.getScaledInstance(200, 220, 0);
		tipTag = new JLabel();
		tipTag.setIcon(new ImageIcon(imageIcon));
		tipTag.setPreferredSize(new Dimension(200, 270));
		this.add(tipTag);
		
		//添加函数输入标签
		tipTag = new JLabel(tipTags[0]);
		this.add(tipTag);
		
		//添加函数输入文本框
		function = new JTextField("如：sinx",25);
		function.addActionListener(toolListener);			//添加监听器
		this.add(function);
		
		//添加清空按钮
		cleanButton = new JButton("清空");
		cleanButton.addActionListener(toolListener);
		cleanButton.setPreferredSize(new Dimension(80, 20));
		this.add(cleanButton);
		
		//添加确认按钮
		InputButton = new JButton("确认");
		InputButton.addActionListener(toolListener);
		InputButton.setPreferredSize(new Dimension(80, 20));
		this.add(InputButton);
		
		tipTag = new JLabel("");
		tipTag.setPreferredSize(new Dimension(1920, 20));
		this.add(tipTag);
		//添加X轴控制滑块标签
		tipTag = new JLabel(tipTags[1]);
		this.add(tipTag);
		
		//添加控制X轴的滑块
		sliderX = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		sliderX.setPaintLabels(true);						//显示刻度标签
		sliderX.setPaintTicks(true);						//显示刻度标记
		sliderX.setMajorTickSpacing(20);					//主刻度间隔
		sliderX.setMinorTickSpacing(5);						//小刻度间隔
		sliderX.setPreferredSize(new Dimension(180, 40));	//滑块大小
		sliderX.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sliderX.addChangeListener(toolListener);       		//添加监听器
		resetButtonX = new JButton("复位");
		resetButtonX.addActionListener(toolListener);
		this.add(resetButtonX);
		this.add(sliderX);
		
		//添加Y轴控制滑块标签
		tipTag = new JLabel(tipTags[2]);
		this.add(tipTag);
		
		//添加控制Y轴的滑块
		sliderY = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		sliderY.setPaintLabels(true);						//显示刻度标签	
		sliderY.setPaintTicks(true);						//显示刻度标记
		sliderY.setMajorTickSpacing(20);					//主刻度间隔
		sliderY.setMinorTickSpacing(5);						//小刻度间隔
		sliderY.setPreferredSize(new Dimension(180, 40));	//滑块大小
		sliderY.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sliderY.addChangeListener(toolListener);  			//添加监听器
		resetButtonY = new JButton("复位");
		resetButtonY.addActionListener(toolListener);
		this.add(resetButtonY);
		this.add(sliderY);							

	}
}
