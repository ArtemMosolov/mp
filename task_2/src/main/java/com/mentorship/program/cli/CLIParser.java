package com.mentorship.program.cli;

import com.mentorship.program.exception.CommandLineParseException;
import org.apache.commons.cli.*;

public class CLIParser {

    private org.apache.commons.cli.CommandLineParser parser;
    private CommandLine cmd;

    public CLIParser(String[] args) {
        this.parser = new DefaultParser();
        parse(args);
    }

    public String getArgument(String arg) {
        return cmd.getOptionValue(arg);
    }

    private Options createOptions() {
        Options options = new Options();

        Option location = new Option(CLIOptions.LOCATION.getOpt(),true,CLIOptions.LOCATION.getDescription());
        Option value = new Option(CLIOptions.VALUE.getOpt(),true,CLIOptions.VALUE.getDescription());
        Option searchBy = new Option(CLIOptions.SEARCH_BY.getOpt(),true,CLIOptions.SEARCH_BY.getDescription());
        Option searchAlgorithms = new Option(CLIOptions.SEARCH_ALGORITHM.getOpt(),true,CLIOptions.SEARCH_ALGORITHM.getDescription());

        options.addOption(location);
        options.addOption(value);
        options.addOption(searchBy);
        options.addOption(searchAlgorithms);

        return options;
    }

    private void parse(String[] args) {
        try {
            cmd = parser.parse(createOptions(), args);
        } catch (ParseException e) {
            throw new CommandLineParseException(e);
        }
    }
}
