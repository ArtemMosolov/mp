package com.mentorship.program.factory.threads.factory;

import com.mentorship.program.exception.IncorrectThreadAlgorithm;
import com.mentorship.program.factory.threads.SingleThread;
import com.mentorship.program.factory.threads.ThreadSeeker;
import com.mentorship.program.factory.threads.WorkStealingPool;

public class ThreadBuildFactory {

    public static ThreadSeeker getThreadAlgorithm(String type) {

        if (type.equalsIgnoreCase("singleThread")) {
            return new SingleThread();
        } else if (type.equalsIgnoreCase("workStealing")) {
            return new WorkStealingPool();
        } else if (type.equalsIgnoreCase("workStealing")) {
            return new WorkStealingPool();
        } else if (type.equalsIgnoreCase("workStealing")) {
            return new WorkStealingPool();
        }

        throw new IncorrectThreadAlgorithm(type + " algorithm does not exist");
    }

}
