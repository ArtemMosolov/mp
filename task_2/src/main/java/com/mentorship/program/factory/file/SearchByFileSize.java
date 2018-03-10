package com.mentorship.program.factory.file;

import java.io.File;

public class SearchByFileSize implements Searchable {

    @Override
    public boolean search(File file, String criteria) {
        return file.length() == Long.valueOf(criteria);
    }
}
