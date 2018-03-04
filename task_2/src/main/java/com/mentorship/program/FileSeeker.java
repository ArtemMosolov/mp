package com.mentorship.program;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.threads.ThreadStrategy;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;

class FileSeeker {

    private final File folder;
    private final String searchValue;
    private final Searchable searchable;
    private final ThreadStrategy threadable;
    private final Node<String> searchTree;

    public FileSeeker(final File folder, final String searchValue, final Searchable searchable, final ThreadStrategy threadable, final Node<String> searchTree) {
        this.folder = folder;
        this.searchValue = searchValue;
        this.searchable = searchable;
        this.threadable = threadable;
        this.searchTree = searchTree;
    }


    public void findFile() {
        threadable.search(this.folder, this.searchValue, this.searchable, this.searchTree, " ");
    }

    public Node<String> getSearchTree() {
        return searchTree;
    }
}
