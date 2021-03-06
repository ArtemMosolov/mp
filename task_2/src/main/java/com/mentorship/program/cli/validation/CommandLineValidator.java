package com.mentorship.program.cli.validation;

import com.mentorship.program.cli.CLIOptions;
import com.mentorship.program.cli.CLIParser;
import com.mentorship.program.exception.MissedArgumentExeption;

import java.util.Objects;

public class CommandLineValidator {

    public static void validate(CLIParser clas) {

        for (CLIOptions arg : CLIOptions.values()) {
            if(Objects.isNull(clas.getArgument(arg.getOpt()))) {
                throw new MissedArgumentExeption("Missed required CLI argument : '-" + arg.getOpt() + "' " + arg.getDescription());
            }
        }
    }

}
