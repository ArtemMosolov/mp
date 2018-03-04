package com.mentorship.program.factory.threads;

import com.mentorship.program.exception.IncorrectThreadAlgorithm;

public class ThreadBuildFactory {

    public static ThreadStrategy getThreadAlgorithm(String type) {

        if (type.equalsIgnoreCase("singleThread")) {
            return new SingleThread();
        }

        throw new IncorrectThreadAlgorithm(type + " algorithm does not exist");
    }

}
