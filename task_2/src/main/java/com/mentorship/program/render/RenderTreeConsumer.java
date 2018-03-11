package com.mentorship.program.render;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.mentorship.program.data.Node;
import com.mentorship.program.util.TreeRender;

public class RenderTreeConsumer<T> implements Runnable {

	private BlockingQueue<Node<T>> queue;
	private AtomicBoolean flag;

	public RenderTreeConsumer(BlockingQueue<Node<T>> queue, AtomicBoolean flag) {
		this.queue = queue;
		this.flag = flag;
	}

	@Override
	public void run() {
		System.out.println("render start");
		Node<T> node;
		try {
			while (((node = queue.take()) != null) || !flag.get()) {
				System.out.println("============================================");
				TreeRender.renderDirectoryTree(node);
				System.out.println("============================================");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("render stop");
		Runtime.getRuntime().exit(0);
	}

}
