package com.mentorship.program;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.Threadable;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;

public class FileSeekerFacade {

    private final File folder;
    private final String searchValue;
    private final Searchable searchable;
    private final Threadable threadable;
    private final Node<String> searchTree;

    public FileSeekerFacade(final File folder, final String searchValue, final Searchable searchable, final Threadable threadable, final Node<String> searchTree) {
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
