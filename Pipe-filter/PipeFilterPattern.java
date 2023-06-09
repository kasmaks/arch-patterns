import java.util.ArrayList;
import java.util.List;

interface Filter {
	void process(Data data);
}

class Data {
	private String content;

	public Data(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

class UppercaseFilter implements Filter {
	@Override
	public void process(Data data) {
		String content = data.getContent();
		String uppercaseContent = content.toUpperCase();
		data.setContent(uppercaseContent);
	}
}

class ReverseFilter implements Filter {
	@Override
	public void process(Data data) {
		String content = data.getContent();
		StringBuilder reversedContent = new StringBuilder(content).reverse();
		data.setContent(reversedContent.toString());
	}
}

class Pipeline {
	private List<Filter> filters;

	public Pipeline() {
		filters = new ArrayList<>();
	}

	public void addFilter(Filter filter) {
		filters.add(filter);
	}

	public void process(Data data) {
		for (Filter filter : filters) {
			filter.process(data);
		}
	}
}

class PipeFilterPattern {
	public static void main(String[] args) {
		Data data = new Data("Hello, World!");

		Filter uppercaseFilter = new UppercaseFilter();
		Filter reverseFilter = new ReverseFilter();

		Pipeline pipeline = new Pipeline();
		pipeline.addFilter(uppercaseFilter);
		pipeline.addFilter(reverseFilter);

		pipeline.process(data);

		System.out.println(data.getContent());
	}
}