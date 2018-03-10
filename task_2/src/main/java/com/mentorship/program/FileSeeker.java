package com.mentorship.program;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;
import com.mentorship.program.factory.threads.ThreadSeeker;

import java.io.File;

class FileSeeker {

    private final File folder;
    private final String searchValue;
    private final Searchable searchable;
    private final ThreadSeeker threadable;
    private final Node<String> tree;

    public FileSeeker(File folder, String searchValue, Searchable searchable, ThreadSeeker threadable, Node<String> tree) {
        this.folder = folder;
        this.searchValue = searchValue;
        this.searchable = searchable;
        this.threadable = threadable;
        this.tree = tree;
    }

    public void findFile() {
        threadable.findFile(folder, searchValue, searchable, tree);
    }

}
