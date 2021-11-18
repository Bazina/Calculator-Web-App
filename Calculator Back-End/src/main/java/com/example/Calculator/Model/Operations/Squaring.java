package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;

public class Squaring implements EvalOperation {
    private final BigDecimal operand;

    public Squaring(BigDecimal operand) {
        this.operand = operand;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();

            if (operand != null) {
                result.setError(Boolean.FALSE);
                result.setMessage("Squaring is Done");
                result.setResult(operand.multiply(operand));
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Squaring is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return operand + "^2";
    }
}
