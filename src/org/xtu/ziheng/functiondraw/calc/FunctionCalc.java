package org.xtu.ziheng.functiondraw.calc;

import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class FunctionCalc {
	private double x;
	private String str; // 标准串
	private String buf; // 存放后缀表达
	private String delim = "+-*/^sctlex|";
	
	final private int INF = 0x7fffffff;
	
	// 将输入的字符串转化为标准串
	public void getInput(String input) {
		str = "";
		for(int i = 0; i < input.length(); ++i) {
			if(input.charAt(i) == ' ') continue; // 过滤空格
			else if(input.charAt(i) == '(' || input.charAt(i) == ')')
				str += input.charAt(i); // 对括号的判断
			else if(i + 2 < input.length() && input.substring(i, i + 3).equals("sin")) {
				str += "s"; // 对sinx的判断
				i += 2;
			}
			else if(i + 2 < input.length() && input.substring(i, i + 3).equals("cos")) {
				str += "c"; // 对cosx的判断
				i += 2;
			}
			else if(i + 2 < input.length() && input.substring(i,  i + 3).equals("tan")) {
				str += "t"; // 对tanx的判断
				i += 2;
			}
			else if(i + 2 < input.length() && input.substring(i, i + 2).equals("ln")) {
				str += "l"; // 对lnx的判断
				i += 1;
			}
			else
				str += input.charAt(i); // 其他运算符和x变量直接读入
		}
		// System.out.println(str);
	}

	public void setValue(double num){
		x = num;
	}
	
	private boolean isOperator(char c){
		// 判断是否是操作符或x或e
		return (c == '+' || c == '-' || c == '*' || c == '/' ||
				c == '^' || c == 's' || c == 'c' || c == 't' ||
				c == '(' || c == ')' || c == '#' || c == 'l' ||
				c == 'o' || c == 'x' || c == 'e' || c == '|' );
	}
	
	private boolean isPrecede(char c1, char c2){
		// 比较c1和c2的优先级，
		// c1比c2优先级高，返回true，否则返回false
		// +,- < *,/ < ^ < sin,cos,tan
		// +,-,*,/优先级相同从左往右计算，sin,cos,tan优先级相同从右往左计算
		if((c1 == '+' || c1 == '-') && c2 == '(') return true;
		if((c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-' || c2 == '(')) return true;
		if(c1 == '^' && (c2 == '+' || c2 == '-' || c2 == '*' || c2 == '/' || c2 == '(')) return true;
		if(c1 == 's' || c1 == 'c' || c1 == 't' || c1 == 'l') return true;
		return false;
	}

	private double Operate(double x, char c){
		// 单目运算符计算
		if(c == 's') return Math.sin(x);
		else if(c == 'c') return Math.cos(x);
		else if(c == 't') return Math.tan(x);
		else if(c == 'l'){
			if(x < 0) return INF; // 过滤不在定义域的点
			else return Math.log(x);
		}
		else return INF;
	}

	private double Operate(double x, char c, double y){
		// 双目运算符计算
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
		// 中缀表达式转后缀表达式，并存在buf中
		buf = ""; 
		int cnt = 0;
		Stack<Character> st1 = new Stack<Character>();
		st1.clear();
		
		if(str == null){
			return false; // 避免空指针运行时异常
		} 
		char c; // 临时变量
		for(int i = 0; i < str.length(); ++i) {
			if(str.charAt(i) == 'x' || str.charAt(i) == 'e' || (str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '.') {
				// 如果是x或者e或者数字直接输出
				buf += str.charAt(i);
			}
			else if(str.charAt(i) == '('){
				// 遇到左括号直接入栈
				cnt ++;
				st1.push(str.charAt(i));
			}
			else if(str.charAt(i) == ')'){
				cnt --;
				// 遇到右括号将包含在括号内的元素出栈
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
				//防止前后操作数均为数字导致计算出错，故每个运算符变为后缀之前，先插入一个间隔符。
				st1.push('|'); 	
				// 如果是运算符则需要判断
				if(st1.empty()) {
					// 如果栈为空则直接入栈
					st1.push(str.charAt(i));
				}
				else {
					// 如果栈非空则需要比较该元素与栈顶元素的优先级
					// 若比栈顶元素优先级高则入栈，否则将栈顶元素出栈，直到找到或者栈空
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
			JOptionPane.showMessageDialog(null, "输入函数非法，请查证");
			return false;
		}
		// 将剩余元素出栈
		while(!st1.empty()){
			c = (Character) st1.pop();
			buf += c;
		}	
		
		buf += '#'; // 添加结尾标识符 
		// 输出后缀表达式
		System.out.println(buf);
		return true;
	}
	
	public double getValue() {		
		if(infixToPostfix() == false) return Double.NaN;
		// 由后缀表达式计算函数值
		// 如果是sin, cos, tan, ln单目运算符则取栈顶元素
		// 如果是+-*/^双目运算符则取栈顶前两个元素
		
		Stack<Double> st3 = new Stack<Double>();
		double res = 0, tmp, tmp1, tmp2;
		char c; // 临时变量
		
		// 以delim中的字符为分隔符
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
