package com.mentorship.program.factory;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;

public class SingleThread implements Threadable {

    @Override
    public void search(final File folder, final String searchValue, final Searchable searchable, final Node<String> searchTree, final String appender) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                searchTree.addChild(new Node<>(fileEntry.getName()));
                search(fileEntry, searchValue, searchable, searchTree,  appender + appender);
            } else {
                if(searchable.search(fileEntry, searchValue)) {
                    searchTree.addChild(new Node<>(appender + fileEntry.getName()));
                } else {
                    searchTree.addChild(new Node<>(appender + fileEntry.getName()));
                }
            }
        }

    }
}
