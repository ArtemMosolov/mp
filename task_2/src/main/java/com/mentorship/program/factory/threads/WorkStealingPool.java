package com.mentorship.program.factory.threads;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkStealingPool extends ThreadSeeker {

    private final ExecutorService es;

    public WorkStealingPool() {
        es = Executors.newWorkStealingPool();
//        try {
//            es.shutdownNow();
//            es.awaitTermination(1, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void search(File fileLocation, String searchValue, Searchable searchable, Node<String> searchTree) {
        es.submit(() -> {
            super.search(fileLocation, searchValue, searchable, searchTree);
        });
    }

    public void findFile(File fileLocation, String searchValue, Searchable searchable, Node<String> searchTree) {
        search(fileLocation, searchValue, searchable, searchTree);
    }
}
