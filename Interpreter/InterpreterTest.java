import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class InterpreterTest {
	@Test
	void testInterpreterPattern() {
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

		Assertions.assertEquals(11, result);
	}

	@Test
	void testInterpreterPatternWithUndefinedVariables() {
		Expression expression = new AddExpression(
				new VariableExpression("x"),
				new SubtractExpression(
						new VariableExpression("y"),
						new NumberExpression(2)
				)
		);

		Map<String, Integer> context = new HashMap<>();

		int result = expression.interpret(context);

		Assertions.assertEquals(0, result);
	}
}