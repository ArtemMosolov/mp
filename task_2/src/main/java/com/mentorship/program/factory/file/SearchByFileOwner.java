package com.mentorship.program.factory.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;

public class SearchByFileOwner implements Searchable {

    @Override
    public boolean check(File file, String criteria) {
        FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(file.toPath(), FileOwnerAttributeView.class);
        try {
            UserPrincipal owner = ownerAttributeView.getOwner();
            return owner.getName().equalsIgnoreCase(criteria);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
