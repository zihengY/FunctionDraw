package org.xtu.ziheng.functiondraw.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import org.xtu.ziheng.functiondraw.listener.ToolBarListener;

/**
 * �๤����
 * ���ܣ� ���뺯��������X,Y������ű���
 * @author ziheng
 *
 */
@SuppressWarnings("serial")
public class ToolBar extends JPanel{
	
	String tipTags[] = {"�����뺯�����ʽ��", "����X�ᵥλ���ȣ�", "����Y�ᵥλ���ȣ�"};

	JLabel tipTag;										//��ǩ	
	public static JSlider sliderX, sliderY;				//�ֱ����XY�����ű����Ļ�����													
	public static JTextField function;					//�������뺯�����ı���
	public static JButton cleanButton, InputButton; 	//���뺯�����ı�����ư�ť
	public static JButton resetButtonX, resetButtonY;	//���鸴λ
	public ToolBar() {
		super();
		unitTools();
	}
	
	public void unitTools(){
		
		//���ò���
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
		//���öԻ����С
		this.setPreferredSize(new Dimension(220, 1080));
		//����������
		ToolBarListener toolListener = new ToolBarListener();	
		
		//��Ӷ�����ӭͼƬ
		Image imageIcon = new ImageIcon("image/welcome.png").getImage();
		imageIcon = imageIcon.getScaledInstance(200, 220, 0);
		tipTag = new JLabel();
		tipTag.setIcon(new ImageIcon(imageIcon));
		tipTag.setPreferredSize(new Dimension(200, 270));
		this.add(tipTag);
		
		//��Ӻ��������ǩ
		tipTag = new JLabel(tipTags[0]);
		this.add(tipTag);
		
		//��Ӻ��������ı���
		function = new JTextField("�磺sinx",25);
		function.addActionListener(toolListener);			//��Ӽ�����
		this.add(function);
		
		//�����հ�ť
		cleanButton = new JButton("���");
		cleanButton.addActionListener(toolListener);
		cleanButton.setPreferredSize(new Dimension(80, 20));
		this.add(cleanButton);
		
		//���ȷ�ϰ�ť
		InputButton = new JButton("ȷ��");
		InputButton.addActionListener(toolListener);
		InputButton.setPreferredSize(new Dimension(80, 20));
		this.add(InputButton);
		
		tipTag = new JLabel("");
		tipTag.setPreferredSize(new Dimension(1920, 20));
		this.add(tipTag);
		//���X����ƻ����ǩ
		tipTag = new JLabel(tipTags[1]);
		this.add(tipTag);
		
		//��ӿ���X��Ļ���
		sliderX = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		sliderX.setPaintLabels(true);						//��ʾ�̶ȱ�ǩ
		sliderX.setPaintTicks(true);						//��ʾ�̶ȱ��
		sliderX.setMajorTickSpacing(20);					//���̶ȼ��
		sliderX.setMinorTickSpacing(5);						//С�̶ȼ��
		sliderX.setPreferredSize(new Dimension(180, 40));	//�����С
		sliderX.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sliderX.addChangeListener(toolListener);       		//��Ӽ�����
		resetButtonX = new JButton("��λ");
		resetButtonX.addActionListener(toolListener);
		this.add(resetButtonX);
		this.add(sliderX);
		
		//���Y����ƻ����ǩ
		tipTag = new JLabel(tipTags[2]);
		this.add(tipTag);
		
		//��ӿ���Y��Ļ���
		sliderY = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		sliderY.setPaintLabels(true);						//��ʾ�̶ȱ�ǩ	
		sliderY.setPaintTicks(true);						//��ʾ�̶ȱ��
		sliderY.setMajorTickSpacing(20);					//���̶ȼ��
		sliderY.setMinorTickSpacing(5);						//С�̶ȼ��
		sliderY.setPreferredSize(new Dimension(180, 40));	//�����С
		sliderY.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sliderY.addChangeListener(toolListener);  			//��Ӽ�����
		resetButtonY = new JButton("��λ");
		resetButtonY.addActionListener(toolListener);
		this.add(resetButtonY);
		this.add(sliderY);							

	}
}
