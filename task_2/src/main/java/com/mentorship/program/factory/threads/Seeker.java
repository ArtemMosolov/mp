package com.mentorship.program.factory.threads;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;

public interface Seeker {

    default void search(File fileLocation, String searchValue, Searchable searchable, Node<String> searchTree) {
        for (File fileEntry : fileLocation.listFiles()) {
            if (fileEntry.isDirectory()) {
                //searchTree.addChild(new Node<>(appender + fileEntry.getName()));
            	searchTree.addChild(fileEntry.getName());
                search(fileEntry, searchValue, searchable, searchTree);
            } else {
                if(searchable.search(fileEntry, searchValue)) {
                    // add & stop all threads
                    searchTree.addChild(fileEntry.getName());
                } else {
                    searchTree.addChild(fileEntry.getName());
                }
            }
        }
    }
}
