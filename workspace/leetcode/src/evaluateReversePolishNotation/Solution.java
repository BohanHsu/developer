package evaluateReversePolishNotation;

import java.util.Stack;
public class Solution {
    
    public int evalRPN(String[] tokens) {
        Stack<String> expression = new Stack<String>();
        int len = tokens.length;
        for (int i = 0; i < len; i++){
            String s = tokens[i];
            if (isOperator(s)){
                // pop the stack and form a new expression
                String exp2 = expression.pop();
                String exp1 = expression.pop();
                
                //String expNew = "(" + exp1 + " " + s + " " + exp2 + ")";
                String expNew = null;
                switch(s){
                    case "+":
                        expNew = (Integer.parseInt(exp1) + Integer.parseInt(exp2))+"";
                        break;
                    case "-":
                        expNew = (Integer.parseInt(exp1) - Integer.parseInt(exp2))+"";
                        break;
                    case "*":
                        expNew = (Integer.parseInt(exp1) * Integer.parseInt(exp2))+"";
                        break;
                    case "/":
                        expNew = (Integer.parseInt(exp1) / Integer.parseInt(exp2))+"";
                        break;
                }
                expression.push(expNew);
                
            }else{
                // push to stack
                expression.push(s);
            }
        }
        
        return Integer.parseInt(expression.pop());
    }
    
    private boolean isOperator(String str){
        switch(str){
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;
            default:
                return false;
        }
    }
    
    public static void main(String[] args) {
		Solution s = new Solution();
		int outCome = s.evalRPN(new String[]{"4", "13", "5", "/", "+"});
		System.out.println(outCome);
	}
}