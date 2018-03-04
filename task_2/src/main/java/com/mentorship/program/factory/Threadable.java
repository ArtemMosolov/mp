package com.mentorship.program.factory;

import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;

public interface Threadable {

    void search(final File folder, final String searchValue, final Searchable searchable, final Node<String> searchTree, final String appender);

}
