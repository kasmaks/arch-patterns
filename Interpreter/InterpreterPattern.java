import java.util.HashMap;
import java.util.Map;

interface Expression {
	int interpret(Map<String, Integer> context);
}

class NumberExpression implements Expression {
	private int value;

	public NumberExpression(int value) {
		this.value = value;
	}

	public int interpret(Map<String, Integer> context) {
		return value;
	}
}

class VariableExpression implements Expression {
	private String variableName;

	public VariableExpression(String variableName) {
		this.variableName = variableName;
	}

	public int interpret(Map<String, Integer> context) {
		if (context.containsKey(variableName)) {
			return context.get(variableName);
		}
		return 0;
	}
}

// Nonterminal Expression
class AddExpression implements Expression {
	private Expression leftOperand;
	private Expression rightOperand;

	public AddExpression(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public int interpret(Map<String, Integer> context) {
		return leftOperand.interpret(context) + rightOperand.interpret(context);
	}
}

// Nonterminal Expression
class SubtractExpression implements Expression {
	private Expression leftOperand;
	private Expression rightOperand;

	public SubtractExpression(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public int interpret(Map<String, Integer> context) {
		return leftOperand.interpret(context) - rightOperand.interpret(context);
	}
}

public class InterpreterPattern {
	public static void main(String[] args) {
		Expression expression = new AddExpression(
			new VariableExpression("x"),
			new SubtractExpression(
				new VariableExpression("y"),
				new NumberExpression(2)
			)
		);

		Map<String, Integer> context = new HashMap<>();
		context.put("x", 5);
		context.put("y", 8);

		int result = expression.interpret(context);
		System.out.println("Result: " + result);
	}
}