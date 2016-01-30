package org.xtu.ziheng.functiondraw.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.xtu.ziheng.functiondraw.listener.MenuBarListener;



@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {		

	//创建主菜单
	public MenuBar() {
		super();
		//创建两个数组用于存储菜单条目
		String[] Menu = { "文 件(F) ", "设置(S) ",  "帮 助(H)" };
		String[][] MenuItem = { { "新建", "保存", "退出" }, { "背景颜色", "线条颜色", "数轴颜色" }, { "帮助主题", "关于工具" } };
		
		//使用循环创建菜单栏
		MenuBarListener menuListener = new MenuBarListener();
		
		for(int i = 0; i < Menu.length; i++) {
			JMenu menu = new JMenu(Menu[i]);
			//将menu添加到menubar上
			this.add(menu);             
			for(int j = 0; j < MenuItem[i].length; j++) {
				JMenuItem menuItem = new JMenuItem(MenuItem[i][j]);
				menuItem.addActionListener(menuListener);
				if(MenuItem[i][j] == "退出")  menu.addSeparator();
				menu.add(menuItem);
			}
		}
		
		FunctionDrawUI.menuListener = menuListener;
	}
}
