package com.mentorship.program.cli;

import org.apache.commons.cli.*;

public class CommandLineParser {

    private final org.apache.commons.cli.CommandLineParser parser;
    private CommandLine cmd;

    public CommandLineParser(final String[] args) {
        this.parser = new DefaultParser();
        parse(args);
    }

    public String getArgument(final String arg) {
        return cmd.getOptionValue(arg);
    }

    private Options createOptions() {
        final Options options = new Options();

        final Option location = new Option(CLIOptions.LOCATION.getOpt(),true,CLIOptions.LOCATION.getDescription());
        final Option value = new Option(CLIOptions.VALUE.getOpt(),true,CLIOptions.VALUE.getDescription());
        final Option searchBy = new Option(CLIOptions.SEARCH_BY.getOpt(),true,CLIOptions.SEARCH_BY.getDescription());
        final Option searchAlgorithms = new Option(CLIOptions.SEARCH_ALGORITHM.getOpt(),true,CLIOptions.SEARCH_ALGORITHM.getDescription());

        options.addOption(location);
        options.addOption(value);
        options.addOption(searchBy);
        options.addOption(searchAlgorithms);

        return options;
    }

    private void parse(final String[] args) {
        try {
            cmd = parser.parse( createOptions(), args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
