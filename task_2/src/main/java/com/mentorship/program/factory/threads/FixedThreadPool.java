package com.mentorship.program.factory.threads;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;

public class FixedThreadPool extends ThreadSeeker {

    private final ExecutorService es;

    public FixedThreadPool() {
        es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
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
