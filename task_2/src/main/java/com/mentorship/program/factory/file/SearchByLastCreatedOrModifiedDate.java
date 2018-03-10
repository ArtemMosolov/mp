package com.mentorship.program.factory.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchByLastCreatedOrModifiedDate implements Searchable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public boolean search(File file, String criteria) {
        try {
            LocalDateTime datetime = LocalDateTime.parse(criteria);
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime lastModified = attr.lastModifiedTime();
            FileTime createdTime = attr.creationTime();
            if(datetime.getSecond() == lastModified.toMillis() || datetime.getSecond() == createdTime.toMillis()) {
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
