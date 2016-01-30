package org.xtu.ziheng.functiondraw.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * 帮助栏
 * 显示在窗口最下方的实时帮助信息
 * @author ziheng
 *
 */
@SuppressWarnings("serial")
public class TipsBar  extends JLabel{
	
	private static final String DEFAULT_TIPS = "要获得帮助，请在\"帮助\"菜单中，单击\"帮助主题\"";

	public static JLabel mousePos = new JLabel("当前的坐标："); 				//显示鼠标坐标的文本框	
	
	public TipsBar() {
		// TODO Auto-generated constructor stub
		super();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1366, 30));
		resetTips();
	}
	
	public void resetTips(){	
		mousePos.setPreferredSize(new Dimension(230, 30));
		mousePos.setFont(new Font("楷体", Font.PLAIN, 15));

		setText(DEFAULT_TIPS);
		add(mousePos,BorderLayout.EAST);
	}
	


}
