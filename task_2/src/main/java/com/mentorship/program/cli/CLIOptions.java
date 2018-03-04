package com.mentorship.program.cli;

public enum CLIOptions {

    LOCATION("a1", "entry point for search file"),
    VALUE("a2", "file value"),
    SEARCH_BY("a3", "search criteria"),
    SEARCH_ALGORITHM("a4", "search algorithm");

    private final String opt;
    private final String description;

    CLIOptions(final String opt, final String description) {
        this.opt = opt;
        this.description = description;
    }

    public String getOpt() {
        return opt;
    }

    public String getDescription() {
        return description;
    }
}
