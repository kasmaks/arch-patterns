import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MasterSlavePattern {
	public static void main(String[] args) {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task("Task 1", 5));
		tasks.add(new Task("Task 2", 3));
		tasks.add(new Task("Task 3", 7));
		tasks.add(new Task("Task 4", 2));

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		List<Future<Result>> futures = new ArrayList<>();
		for (Task task : tasks) {
			Future<Result> future = executorService.submit(task);
			futures.add(future);
		}

		List<Result> results = new ArrayList<>();
		for (Future<Result> future : futures) {
			try {
				Result result = future.get();
				results.add(result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		for (Result result : results) {
			System.out.println(result);
		}

		executorService.shutdown();
	}
}

class Task implements Callable<Result> {
	private String name;
	private int processingTime;

	public Task(String name, int processingTime) {
		this.name = name;
		this.processingTime = processingTime;
	}

	@Override
	public Result call() throws Exception {
		System.out.println("Executing task: " + name);
		Thread.sleep(processingTime * 1000);
		return new Result(name, "Task completed in " + processingTime + " seconds");
	}
}

class Result {
	private String taskName;
	private String message;

	public Result(String taskName, String message) {
		this.taskName = taskName;
		this.message = message;
	}

	@Override
	public String toString() {
		return taskName + ": " + message;
	}
}