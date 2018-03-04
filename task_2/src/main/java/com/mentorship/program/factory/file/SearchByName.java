package com.mentorship.program.factory.file;

import java.io.File;

public class SearchByName implements Searchable {

    @Override
    public boolean search(File file, String criteria) {
        return file.getName().equalsIgnoreCase(criteria);
    }

}
