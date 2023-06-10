import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackboardTest {
    private Blackboard blackboard;

    @BeforeEach
    void setup() {
        blackboard = new Blackboard();
    }

    @Test
    void testAddData() {
        blackboard.addData("Data 1");
        blackboard.addData("Data 2");

        String data = blackboard.getData();

        Assertions.assertEquals("Data 1, Data 2", data);
    }
}

class KnowledgeSource1Test {
    private Blackboard blackboard;
    private KnowledgeSource1 knowledgeSource1;

    @BeforeEach
    void setup() {
        blackboard = new Blackboard();
        knowledgeSource1 = new KnowledgeSource1();
    }

    @Test
    void testProcessKnowledge() {
        blackboard.addData("Initial Data");

        knowledgeSource1.processKnowledge(blackboard);

        String data = blackboard.getData();

        Assertions.assertEquals("Initial Data, Processed by Knowledge Source 1", data);
    }
}

class KnowledgeSource2Test {
    private Blackboard blackboard;
    private KnowledgeSource2 knowledgeSource2;

    @BeforeEach
    void setup() {
        blackboard = new Blackboard();
        knowledgeSource2 = new KnowledgeSource2();
    }

    @Test
    void testProcessKnowledge() {
        blackboard.addData("Initial Data");

        knowledgeSource2.processKnowledge(blackboard);

        String data = blackboard.getData();

        Assertions.assertEquals("Initial Data, Processed by Knowledge Source 2", data);
    }
}