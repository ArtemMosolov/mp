package com.mentorship.program;

import com.mentorship.program.cli.CommandLineParser;
import com.mentorship.program.cli.validation.CommandLineValidator;
import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.SearchFileFactory;
import com.mentorship.program.factory.file.Searchable;
import com.mentorship.program.factory.threads.ThreadBuildFactory;
import com.mentorship.program.factory.threads.ThreadStrategy;

import java.io.File;

class Application {


    public void run(final String[] args) {

        CommandLineParser cli = new CommandLineParser(args);
        CommandLineValidator.validate(cli);

        File folder = new File(cli.getArgument("a1"));
        String searchValue = cli.getArgument("a2");
        Searchable searchable = SearchFileFactory.getSearchCriteria(cli.getArgument("a3"));

        ThreadStrategy threadable = ThreadBuildFactory.getThreadAlgorithm(cli.getArgument("a4"));

        Node<String> folderTree = new Node<>("root");

        FileSeeker fileSeeker = new FileSeeker(folder, searchValue, searchable, threadable, folderTree);
        fileSeeker.findFile();

//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        printTree(folderTree, " ");

    }

    private static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }

}
