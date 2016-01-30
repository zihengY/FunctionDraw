package org.xtu.ziheng.functiondraw.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawBackboard extends JPanel{
	JLabel jLabel;
	public DrawBackboard(){
		unitdrabbackb();
		
	}
	
	public void unitdrabbackb(){
		this.setLayout(new BorderLayout(5,5));
		this.setBackground(Color.GRAY);	
		
		jLabel = new JLabel();
		add(jLabel, BorderLayout.WEST);
		
		jLabel = new JLabel();
		add(jLabel, BorderLayout.EAST);
		
		jLabel = new JLabel();
		add(jLabel, BorderLayout.SOUTH);
		
		jLabel = new JLabel();
		add(jLabel, BorderLayout.NORTH);
	}
	
}
