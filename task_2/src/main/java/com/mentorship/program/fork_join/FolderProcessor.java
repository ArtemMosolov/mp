package com.mentorship.program.fork_join;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicBoolean;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;

public class FolderProcessor extends RecursiveTask<List<String>> {
	private final String path;
	private final String searchValue;
	private final Node<String> tree;
	private final Searchable searchable;
	private final AtomicBoolean flag = new AtomicBoolean(false);

	public FolderProcessor(String path, String searchValue, Searchable searchable, Node<String> tree) {
		this.path = path;
		this.searchValue = searchValue;
		this.searchable = searchable;
		this.tree = tree;
	}

	@Override
	protected List<String> compute() {
		if (!flag.get()) {
			//List<String> list = new ArrayList<String>();
			List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();
			File file = new File(path);
			for (File f : file.listFiles()) {
				if (f.isDirectory()) {
					FolderProcessor task = new FolderProcessor(f.getAbsolutePath(), searchValue, searchable,
							tree.addChild(f.getName()));
					task.fork();
					tasks.add(task);
				} else {
					if (searchable.check(f, searchValue)) {
						//list.add(f.getAbsolutePath());
						flag.set(true);
						tree.addChild(f.getName());
					} else {
						tree.addChild(f.getName());
					}
				}
			}
		}
		return null;
	}
}