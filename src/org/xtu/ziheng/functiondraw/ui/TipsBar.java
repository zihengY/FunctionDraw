package org.xtu.ziheng.functiondraw.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * ������
 * ��ʾ�ڴ������·���ʵʱ������Ϣ
 * @author ziheng
 *
 */
@SuppressWarnings("serial")
public class TipsBar  extends JLabel{
	
	private static final String DEFAULT_TIPS = "Ҫ��ð���������\"����\"�˵��У�����\"��������\"";

	public static JLabel mousePos = new JLabel("��ǰ�����꣺"); 				//��ʾ���������ı���	
	
	public TipsBar() {
		// TODO Auto-generated constructor stub
		super();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1366, 30));
		resetTips();
	}
	
	public void resetTips(){	
		mousePos.setPreferredSize(new Dimension(230, 30));
		mousePos.setFont(new Font("����", Font.PLAIN, 15));

		setText(DEFAULT_TIPS);
		add(mousePos,BorderLayout.EAST);
	}
	


}
