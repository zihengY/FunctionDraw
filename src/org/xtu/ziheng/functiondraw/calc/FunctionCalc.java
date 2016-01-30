package org.xtu.ziheng.functiondraw.calc;

import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class FunctionCalc {
	private double x;
	private String str; // ��׼��
	private String buf; // ��ź�׺���
	private String delim = "+-*/^sctlex|";
	
	final private int INF = 0x7fffffff;
	
	// ��������ַ���ת��Ϊ��׼��
	public void getInput(String input) {
		str = "";
		for(int i = 0; i < input.length(); ++i) {
			if(input.charAt(i) == ' ') continue; // ���˿ո�
			else if(input.charAt(i) == '(' || input.charAt(i) == ')')
				str += input.charAt(i); // �����ŵ��ж�
			else if(i + 2 < input.length() && input.substring(i, i + 3).equals("sin")) {
				str += "s"; // ��sinx���ж�
				i += 2;
			}
			else if(i + 2 < input.length() && input.substring(i, i + 3).equals("cos")) {
				str += "c"; // ��cosx���ж�
				i += 2;
			}
			else if(i + 2 < input.length() && input.substring(i,  i + 3).equals("tan")) {
				str += "t"; // ��tanx���ж�
				i += 2;
			}
			else if(i + 2 < input.length() && input.substring(i, i + 2).equals("ln")) {
				str += "l"; // ��lnx���ж�
				i += 1;
			}
			else
				str += input.charAt(i); // �����������x����ֱ�Ӷ���
		}
		// System.out.println(str);
	}

	public void setValue(double num){
		x = num;
	}
	
	private boolean isOperator(char c){
		// �ж��Ƿ��ǲ�������x��e
		return (c == '+' || c == '-' || c == '*' || c == '/' ||
				c == '^' || c == 's' || c == 'c' || c == 't' ||
				c == '(' || c == ')' || c == '#' || c == 'l' ||
				c == 'o' || c == 'x' || c == 'e' || c == '|' );
	}
	
	private boolean isPrecede(char c1, char c2){
		// �Ƚ�c1��c2�����ȼ���
		// c1��c2���ȼ��ߣ�����true�����򷵻�false
		// +,- < *,/ < ^ < sin,cos,tan
		// +,-,*,/���ȼ���ͬ�������Ҽ��㣬sin,cos,tan���ȼ���ͬ�����������
		if((c1 == '+' || c1 == '-') && c2 == '(') return true;
		if((c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-' || c2 == '(')) return true;
		if(c1 == '^' && (c2 == '+' || c2 == '-' || c2 == '*' || c2 == '/' || c2 == '(')) return true;
		if(c1 == 's' || c1 == 'c' || c1 == 't' || c1 == 'l') return true;
		return false;
	}

	private double Operate(double x, char c){
		// ��Ŀ���������
		if(c == 's') return Math.sin(x);
		else if(c == 'c') return Math.cos(x);
		else if(c == 't') return Math.tan(x);
		else if(c == 'l'){
			if(x < 0) return INF; // ���˲��ڶ�����ĵ�
			else return Math.log(x);
		}
		else return INF;
	}

	private double Operate(double x, char c, double y){
		// ˫Ŀ���������
		if(c == '+') return x + y;
		else if(c == '-') return x - y;
		else if(c == '*') return x * y;
		else if(c == '/'){
			if(Math.abs(y) < 0.00001) return INF;
			return x / y;
		}
		else if(c == '^') return Math.pow(x, y);
		else return INF;
	}

	public boolean infixToPostfix(){
		// ��׺���ʽת��׺���ʽ��������buf��
		buf = ""; 
		int cnt = 0;
		Stack<Character> st1 = new Stack<Character>();
		st1.clear();
		
		if(str == null){
			return false; // �����ָ������ʱ�쳣
		} 
		char c; // ��ʱ����
		for(int i = 0; i < str.length(); ++i) {
			if(str.charAt(i) == 'x' || str.charAt(i) == 'e' || (str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '.') {
				// �����x����e��������ֱ�����
				buf += str.charAt(i);
			}
			else if(str.charAt(i) == '('){
				// ����������ֱ����ջ
				cnt ++;
				st1.push(str.charAt(i));
			}
			else if(str.charAt(i) == ')'){
				cnt --;
				// ���������Ž������������ڵ�Ԫ�س�ջ
				while(!st1.empty()){
					c = (Character) st1.pop();
					if(c == '(')
						break;
					buf += c;
				}
			}
			else{
				//System.out.println(str.charAt(i));
				if((i == 0||isOperator(str.charAt(i-1)))&&str.charAt(i) == '-') buf += '0';
				//��ֹǰ���������Ϊ���ֵ��¼��������ÿ���������Ϊ��׺֮ǰ���Ȳ���һ���������
				st1.push('|'); 	
				// ��������������Ҫ�ж�
				if(st1.empty()) {
					// ���ջΪ����ֱ����ջ
					st1.push(str.charAt(i));
				}
				else {
					// ���ջ�ǿ�����Ҫ�Ƚϸ�Ԫ����ջ��Ԫ�ص����ȼ�
					// ����ջ��Ԫ�����ȼ�������ջ������ջ��Ԫ�س�ջ��ֱ���ҵ�����ջ��
					while(!st1.empty()){
						c = (Character) st1.pop();
						if(isPrecede(str.charAt(i), c)){
							break;
						}
						buf += c;
					}
					st1.push(str.charAt(i));
				}
			}
		}
		if(cnt != 0) {
			JOptionPane.showMessageDialog(null, "���뺯���Ƿ������֤");
			return false;
		}
		// ��ʣ��Ԫ�س�ջ
		while(!st1.empty()){
			c = (Character) st1.pop();
			buf += c;
		}	
		
		buf += '#'; // ��ӽ�β��ʶ�� 
		// �����׺���ʽ
		System.out.println(buf);
		return true;
	}
	
	public double getValue() {		
		if(infixToPostfix() == false) return Double.NaN;
		// �ɺ�׺���ʽ���㺯��ֵ
		// �����sin, cos, tan, ln��Ŀ�������ȡջ��Ԫ��
		// �����+-*/^˫Ŀ�������ȡջ��ǰ����Ԫ��
		
		Stack<Double> st3 = new Stack<Double>();
		double res = 0, tmp, tmp1, tmp2;
		char c; // ��ʱ����
		
		// ��delim�е��ַ�Ϊ�ָ���
		StringTokenizer parser = new StringTokenizer(buf, delim, true);
		String token = parser.nextToken();
		
		while(token.charAt(0) != '#'){
			
			try{
				if(token.length() == 1 && isOperator(token.charAt(0))){
					c = token.charAt(0);
					//System.out.println(c);
					if(c == 'x'){
						st3.push(x);
					}
					else if(c == 'e'){
						st3.push(Math.E);
					}
					else if(c == 's' || c == 'c' || c == 't' || c == 'l'){
						tmp = st3.pop();
						res = Operate(tmp, c);
						st3.push(res);
					}
					else if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^'){
						tmp1 = st3.pop();
						//System.out.println(c);
						//System.out.println(st3.size());
						tmp2 = st3.pop();
						res = Operate(tmp2, c, tmp1);
						System.out.println(tmp2 +" "+ c + " " + tmp1 + "=" + res);
						st3.push(res);
					}
					token = parser.nextToken();
				}
				else{
					st3.push(Double.parseDouble(token));
				}
				
			} catch (Exception e) {
			
			}
		}
		
		res = st3.pop();
		System.out.println("x = " + x + " res = " + res);
		return res;
	}
}
