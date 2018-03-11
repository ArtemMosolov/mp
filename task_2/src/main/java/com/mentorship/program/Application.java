package com.mentorship.program;

import java.io.File;

import com.mentorship.program.cli.CLIParser;
import com.mentorship.program.cli.validation.CommandLineValidator;
import com.mentorship.program.data.Node;
import com.mentorship.program.factory.file.Searchable;
import com.mentorship.program.factory.file.factory.SearchFileFactory;
import com.mentorship.program.factory.threads.ThreadSeeker;
import com.mentorship.program.factory.threads.factory.ThreadBuildFactory;
import com.mentorship.program.util.TreeRender;

class Application {

    public void run(final String[] args) {

        CLIParser cli = new CLIParser(args);
        CommandLineValidator.validate(cli);

        // -a1 /projects/MP/task_2/test_dir -a2 test_file.txt -a3 byName -a4 singleThread
        // -a1 /projects/MP/task_2/test_dir -a2 test_file.txt -a3 byName -a4 workStealing

        File fileLocation = new File(cli.getArgument("a1"));
        String searchValue = cli.getArgument("a2");
        Searchable searchable = SearchFileFactory.getSearchCriteria(cli.getArgument("a3"));
        ThreadSeeker threadable = ThreadBuildFactory.getThreadAlgorithm(cli.getArgument("a4"));

        Node<String> tree = new Node<>(fileLocation.getName());
        // new Thread(new RenderTreeConsumer<String>(threadable.getQueue(), threadable.getFlag())).start();
        
        FileSeeker fileSeeker = new FileSeeker(fileLocation, searchValue, searchable, threadable, tree);
        fileSeeker.findFile();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        TreeRender.renderDirectoryTree(tree);
    }
}
