package com.mentorship.program.factory.file;

import java.io.File;

public class SearchByPartialName implements Searchable  {

    @Override
    public boolean search(File file, String criteria) {
        return file.getName().endsWith(criteria);
    }
}
