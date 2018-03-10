package com.mentorship.program.factory.file.factory;

import com.mentorship.program.exception.MissedSearchCriteria;
import com.mentorship.program.factory.file.*;

public class SearchFileFactory {

    public static Searchable getSearchCriteria(String criteria) {

        if (criteria.equalsIgnoreCase("byName")) {
            return new SearchByName();
        } else if (criteria.equalsIgnoreCase("bySize")) {
            return new SearchByFileSize();
        } else if (criteria.equalsIgnoreCase("byOwner")) {
            return new SearchByFileOwner();
        } else if (criteria.equalsIgnoreCase("byCreateOfModifyDate")) {
            return new SearchByLastCreatedOrModifiedDate();
        } else if (criteria.equalsIgnoreCase("byPartialName")) {
            return new SearchByPartialName();
        }

        throw new MissedSearchCriteria(criteria + " does not exists");
    }

}
