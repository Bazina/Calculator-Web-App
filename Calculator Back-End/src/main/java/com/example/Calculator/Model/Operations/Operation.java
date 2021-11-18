package com.example.Calculator.Model.Operations;

import com.example.Calculator.Model.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public class Operation {
    private static final Logger logger = LogManager.getLogger(Operation.class);

    protected BigDecimal op1;
    protected BigDecimal op2;
    protected BigDecimal operand;
    protected String operation;

    public Result RoutingOperation() throws Exception {
        Result result = new Result();
        EvalOperation OP;
        if (Objects.equals(this.operation, "add")) {
            OP = new Addition(op1, op2);
        } else if (Objects.equals(this.operation, "subtract")) {
            OP = new Subtraction(op1, op2);
        } else if (Objects.equals(this.operation, "multiply")) {
            OP = new Multiplication(op1, op2);
        } else if (Objects.equals(this.operation, "divide")) {
            OP = new Division(op1, op2);
        } else if (Objects.equals(this.operation, "square")) {
            OP = new Squaring(operand);
        } else if (Objects.equals(this.operation, "sqrt")) {
            OP = new SquareRoot(operand);
        } else if (Objects.equals(this.operation, "percent")) {
            OP = new Percentage(operand);
        } else if (Objects.equals(this.operation, "tax")) {
            OP = new Tax(op1, op2);
        } else if (Objects.equals(this.operation, "inverse")) {
            OP = new Inverse(operand);
        } else if (Objects.equals(this.operation, "negate")) {
            OP = new Negation(operand);
        } else {
            logger.error("Error during Execution");
            result.setError(Boolean.TRUE);
            result.setMessage("Operation " + this.operation + " isn't working");
            return result;
        }
        logger.info("Input is: " + OP.writtenOP());
        result = OP.Evaluate();
        return result;
    }

    public BigDecimal getOp2() {
        return op2;
    }

    public BigDecimal getOp1() {
        return op1;
    }

    public String getOperation() {
        return operation;
    }

    public BigDecimal getOperand() {
        return operand;
    }

    public void setOperand(BigDecimal operand) {
        this.operand = operand;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setOp1(BigDecimal op1) {
        this.op1 = op1;
    }

    public void setOp2(BigDecimal op2) {
        this.op2 = op2;
    }

    public static Logger getLogger() {
        return logger;
    }
}
