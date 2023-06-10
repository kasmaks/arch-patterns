import java.util.ArrayList;
import java.util.List;

class KnowledgeSource1 {
	public void processKnowledge(Blackboard blackboard) {
		String data = blackboard.getData();
		blackboard.addData(data + " Processed by Knowledge Source 1");
	}
}

class KnowledgeSource2 {
	public void processKnowledge(Blackboard blackboard) {
		String data = blackboard.getData();
		blackboard.addData(data + " Processed by Knowledge Source 2");
	}
}

class Blackboard {
	private List<String> data;

	public Blackboard() {
		this.data = new ArrayList<>();
	}

	public void addData(String newData) {
		data.add(newData);
	}

	public String getData() {
		return String.join(", ", data);
	}
}

public class BlackboardPattern {
	public static void main(String[] args) {
		Blackboard blackboard = new Blackboard();

		KnowledgeSource1 ks1 = new KnowledgeSource1();
		KnowledgeSource2 ks2 = new KnowledgeSource2();

		ks1.processKnowledge(blackboard);
		ks2.processKnowledge(blackboard);

		String finalResult = blackboard.getData();
		System.out.println("Final Result: " + finalResult);
	}
}