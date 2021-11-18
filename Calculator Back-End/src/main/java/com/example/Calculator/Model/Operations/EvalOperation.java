package com.example.Calculator.Model.Operations;

import com.example.Calculator.Model.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface EvalOperation {
    Logger logger = LogManager.getLogger(Operation.class);
    Result Evaluate() throws Exception;
    String writtenOP();

    static Logger getLogger() {
        return logger;
    }
}
