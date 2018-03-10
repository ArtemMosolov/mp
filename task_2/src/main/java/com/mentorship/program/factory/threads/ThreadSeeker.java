package com.mentorship.program.factory.threads;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;

public abstract class ThreadSeeker {

    public abstract void findFile(File fileLocation, String searchValue, Searchable searchable, Node<String> searchTree);

    public void search(File fileLocation, String searchValue, Searchable searchable, Node<String> searchTree) {
        for (File fileEntry : fileLocation.listFiles()) {
            if (fileEntry.isDirectory()) {
            	//searchTree.addChild(appender + fileEntry.getName());
                //search(fileEntry, searchValue, searchable, searchTree,  appender + appender);
                findFile(fileEntry, searchValue, searchable, searchTree.addChild(fileEntry.getName()));
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
