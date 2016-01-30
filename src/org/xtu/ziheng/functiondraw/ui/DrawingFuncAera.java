package org.xtu.ziheng.functiondraw.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawingFuncAera extends JPanel{
	
	Cursor crossCursor, handCursor; 	//��������
	public static Color background = Color.WHITE;
	public static Color lineAr = Color.BLACK;
	public static Color lineFV = Color.RED;
	int W = 575,H = 497;
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//FunctionDrawUI.g = g;
		int W = this.getWidth();
		int H = this.getHeight();
		System.out.println(W + "  " + H);
		//setSize(new Dimension(W, H));
		//setPreferredSize(new Dimension(W, H));
		// ��䱳����ɫ
		g.setColor(background);
		g.fillRect(0, 0, W, H);
		
		// ����������
		g.setColor(lineAr);
		g.drawLine(0, H / 2 , W, H / 2); // x��
		g.drawLine(W / 2, 0, W / 2, H); // y��

		// ���Ƽ�ͷ
		g.drawLine((int)(W / 2), 0, (int)(W / 2) + 10, 10);
		g.drawLine((int)(W / 2), 0, (int)(W / 2) - 10, 10);
		g.drawLine((int)W, (int)(H / 2), W - 10, (int)(H / 2) + 10);
		g.drawLine((int)W, (int)(H / 2), W - 10, (int)(H / 2) - 10);
		
		// ��ӿ̶ȱ�ǩ
		g.setFont(new Font("Times New Roman", Font.BOLD, 15));
		g.drawString("X", (int)W - 20, (int)(H/2) + 20);
		g.drawString("Y", (int)W/2 - 20, 15);
		g.drawString("0", (int)W/2 - 20, (int)H/2 + 20);
		DecimalFormat oneDigit = new DecimalFormat("0.0");
		g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		//X��������
		for(int i = 50; i < (W / 2)-20; i += 50){	
			g.drawLine( W/2+i,  H/2,  W/2+i,  H/2 - 10);
			g.drawString(String.valueOf(oneDigit.format((double)i/FunctionDrawUI.sliderValueX)), W/2+i-10, H/2 + 20);
		}
		//x�ܸ�����
		for(int i = 50; i < (W / 2)-10; i += 50){
			g.drawLine( W/2-i, H/2, W/2-i, H/2 - 10);
			g.drawString(String.valueOf(oneDigit.format(-(double)i/FunctionDrawUI.sliderValueX)), W/2-i-15, H/2 + 20);
		}
		//Y��������
		for(int i = 50; i < (H / 2)-10; i += 50){
			g.drawLine( W/2, H/2-i, W/2 + 10, H/2-i  );
			g.drawString(String.valueOf(oneDigit.format((double)i/FunctionDrawUI.sliderValueY)), W/2 - 30, H/2 - i+5);
		}
		//Y�ܸ�����
		for(int i = 50; i < (H / 2)-10; i += 50){
			g.drawLine( W/2, H/2+i, W/2 + 10, H/2+i );
			g.drawString(String.valueOf(oneDigit.format(-(double)i/FunctionDrawUI.sliderValueY)), W/2 - 35, H/2 + i+5);
		}
		
		// ���ƺ���ͼ��
		g.setColor(lineFV);
		int y2 = 0;
		try{
			for(int i = -W / 2-1; i <= W / 2+1; ++i){
				double x = (double) i / FunctionDrawUI.sliderValueX; // �Ա���ȡֵ
				FunctionDrawUI.mainCalc.setValue(x);
				double res = FunctionDrawUI.mainCalc.getValue();
				if(Double.isNaN(res)) continue;		//���Ƿ��������
				int y1 = (int)(res * FunctionDrawUI.sliderValueY); // ����������ֵ
				if(y1 < -(int)(H / 2)-1 || y1 > (int)(H/2)+1 ) continue;//��������Ļ��Χ�ڵĵ����
				//System.out.println("x = " + x + " y1 = " + y1);
				g.fillRect((int)(W / 2) + i,(int)(H / 2) - y1, 1, 1); // ����
						
				//������������Զ�Ҳ����������ֱ������
				if(i > -(int)(W / 2) + 1 && Math.abs(y2 - y1) > 1 && Math.abs(y2 - y1) < 100)
					g.drawLine((int)(W / 2) + i - 1, (int)(H / 2) - y2, (int)(W / 2) + i, (int)(H / 2) - y1);
					y2=y1;
			}			
		}catch(Exception e){
					
		}		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
	
				double X, Y; // �������
				double sx, sy; // applet��ʵ������
				sx = e.getX();
				sy = e.getY();
	
				// ����任
				X = (double)(sx - 0.5 * W) / FunctionDrawUI.sliderValueX; 
				Y = (double)-(sy - 0.5 * H) / FunctionDrawUI.sliderValueY; 
				// ���������ʽ
				DecimalFormat twoDigit = new DecimalFormat("0.00");
				TipsBar.mousePos.setText("��ǰ�����꣺" + "x=" + twoDigit.format(X) + "  y=" + twoDigit.format(Y));
				TipsBar.mousePos.setFont(new Font("����", Font.PLAIN, 15));
	
				// ���ù��ͼ��
				crossCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
				handCursor = new Cursor(Cursor.HAND_CURSOR);
				if(sx > 0 && sx < W && sy > 0 && sy < H)
					setCursor(crossCursor);
				else
					setCursor(handCursor);
			}
		});
	}
}
