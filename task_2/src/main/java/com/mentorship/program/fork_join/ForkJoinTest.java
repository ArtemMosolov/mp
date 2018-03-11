package com.mentorship.program.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;
import com.mentorship.program.factory.file.factory.SearchFileFactory;
import com.mentorship.program.util.TreeRender;

public class ForkJoinTest {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		Node<String> tree = new Node<>("test_dir");
		Searchable checker = SearchFileFactory.getSearchCriteria("byName");
		FolderProcessor system = new FolderProcessor("/projects/MP/task_2/test_dir", "test_file.txt", checker, tree);
		pool.execute(system);

		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!system.isDone());
		pool.shutdown();
		TreeRender.renderDirectoryTree(tree);
	}
}
