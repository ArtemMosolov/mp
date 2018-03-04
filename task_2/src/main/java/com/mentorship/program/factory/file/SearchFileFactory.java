package com.mentorship.program.factory.file;

import com.mentorship.program.exception.MissedSearchCriteria;

public class SearchFileFactory {

    public static Searchable getSearchCriteria(String criteria) {

        if (criteria.equalsIgnoreCase("byName")) {
            return new SearchByName();
        }
        throw new MissedSearchCriteria(criteria + " does not exists");
    }

}
