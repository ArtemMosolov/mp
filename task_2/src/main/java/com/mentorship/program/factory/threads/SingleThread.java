package com.mentorship.program.factory.threads;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;

public class SingleThread extends ThreadSeeker {

	@Override
    public void findFile(File fileLocation, String searchValue, Searchable searchable, Node<String> searchTree) {
        search(fileLocation, searchValue, searchable, searchTree);
    }
}
