package org.wildhamsters.loginservice;

record Log(String level, String timeStamp, String microService, String className, String logMsg) {
    enum Level {
        ERROR,
        DEBUG,
        INFO
    }
}
