import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PipeFilterPatternTest {
	@Test
	void testPipeFilterPattern() {
		Data data = new Data("Hello, World!");

		Filter uppercaseFilter = Mockito.mock(UppercaseFilter.class);
		Filter reverseFilter = Mockito.mock(ReverseFilter.class);

		Pipeline pipeline = new Pipeline();
		pipeline.addFilter(uppercaseFilter);
		pipeline.addFilter(reverseFilter);

		pipeline.process(data);

		Mockito.verify(uppercaseFilter).process(data);
		Mockito.verify(reverseFilter).process(data);

		Assertions.assertEquals("!DLROW ,OLLEH", data.getContent());
	}
}