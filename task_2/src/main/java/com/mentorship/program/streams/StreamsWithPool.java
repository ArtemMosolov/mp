package com.mentorship.program.streams;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;
import com.mentorship.program.factory.file.factory.SearchFileFactory;
import com.mentorship.program.util.TreeRender;

public class StreamsWithPool {

	private static final AtomicBoolean flag = new AtomicBoolean(false);

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		File location = new File("/projects/MP/task_2/test_dir");
		Node<String> tree = new Node<>("test_dir");
		Searchable checker = SearchFileFactory.getSearchCriteria("byName");
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(2);
		forkJoinPool.submit(() -> search(location, "new_file.txt", checker, tree)).get();
		
		TreeRender.renderDirectoryTree(tree);
	}

	public static void search(File location, String searchValue, Searchable checker, Node<String> tree) {
		if (!flag.get()) {
			Stream.of(location.listFiles()).parallel().forEach(action -> {
				if (action.isDirectory()) {
					search(action, searchValue, checker, tree.addChild(action.getName()));
				} else {
					if (checker.check(action, searchValue)) {
						flag.set(true);
						tree.addChild(action.getName());
					} else {
						tree.addChild(action.getName());
					}
				}
			});
		}
	}
}
