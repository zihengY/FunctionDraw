package org.xtu.ziheng.functiondraw.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.xtu.ziheng.functiondraw.listener.MenuBarListener;



@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {		

	//�������˵�
	public MenuBar() {
		super();
		//���������������ڴ洢�˵���Ŀ
		String[] Menu = { "�� ��(F) ", "����(S) ",  "�� ��(H)" };
		String[][] MenuItem = { { "�½�", "����", "�˳�" }, { "������ɫ", "������ɫ", "������ɫ" }, { "��������", "���ڹ���" } };
		
		//ʹ��ѭ�������˵���
		MenuBarListener menuListener = new MenuBarListener();
		
		for(int i = 0; i < Menu.length; i++) {
			JMenu menu = new JMenu(Menu[i]);
			//��menu��ӵ�menubar��
			this.add(menu);             
			for(int j = 0; j < MenuItem[i].length; j++) {
				JMenuItem menuItem = new JMenuItem(MenuItem[i][j]);
				menuItem.addActionListener(menuListener);
				if(MenuItem[i][j] == "�˳�")  menu.addSeparator();
				menu.add(menuItem);
			}
		}
		
		FunctionDrawUI.menuListener = menuListener;
	}
}
