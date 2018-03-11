package com.mentorship.program.factory.file;

import java.io.File;

public class SearchByPartialName implements Searchable  {

    @Override
    public boolean check(File file, String criteria) {
        return file.getName().endsWith(criteria);
    }
}
