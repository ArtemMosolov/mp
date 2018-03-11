package com.mentorship.program.factory.threads;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;
import com.mentorship.program.util.TreeRender;

import java.io.File;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ThreadSeeker {

	private BlockingQueue<Node<String>> queue;
	private AtomicBoolean flag;

	public ThreadSeeker() {
		this.queue = new LinkedBlockingDeque<>();
		this.flag = new AtomicBoolean(false);
	}

	public BlockingQueue<Node<String>> getQueue() {return queue;}

	public AtomicBoolean getFlag() {return flag;}

	public abstract void findFile(File fileLocation, String searchValue, Searchable searchable, Node<String> searchTree);

	public void search(File fileLocation, String searchValue, Searchable searchable, Node<String> searchTree) {
		if (!getFlag().get()) {
			for (File fileEntry : fileLocation.listFiles()) {
				if (fileEntry.isDirectory()) {
					Node<String> node = searchTree.addChild(fileEntry.getName());
					//queue.add(searchTree);
					findFile(fileEntry, searchValue, searchable, node);
					//queue.add(node);
				} else {
					if (searchable.check(fileEntry, searchValue)) {
						// add & stop all threads
						flag.set(true);
						Node<String> node = searchTree.addChild(fileEntry.getName());
						//queue.add(searchTree);
					} else {
						Node<String> node = searchTree.addChild(fileEntry.getName());
						//queue.add(searchTree);
					}
				}
			}
		}
	}

}
