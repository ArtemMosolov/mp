package com.mentorship.program;

import com.mentorship.program.cli.CommandLineParser;
import com.mentorship.program.cli.validation.CommandLineValidator;
import com.mentorship.program.data.Node;
import com.mentorship.program.factory.SingleThread;
import com.mentorship.program.factory.Threadable;
import com.mentorship.program.factory.file.SearchFileFactory;
import com.mentorship.program.factory.file.Searchable;

import java.io.File;

public class Application {


    public void run(final String[] args) {

        final CommandLineParser cli = new CommandLineParser(args);
        CommandLineValidator.validate(cli);

        final File folder = new File(cli.getArgument("a1"));
        final String searchValue = cli.getArgument("a2");
        final Searchable searchable = SearchFileFactory.getSearchCriteria(cli.getArgument("a3"));
        final Threadable threadable = new SingleThread();
        final Node<String> folderTree = new Node<>("root");

        final FileSeekerFacade fileSeekerFacade = new FileSeekerFacade(folder, searchValue, searchable, threadable, folderTree);
        fileSeekerFacade.findFile();

        printTree(folderTree, " ");

    }

    private static <T> void printTree(final Node<T> node, final String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }

}
