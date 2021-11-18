package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;

public class Negation implements EvalOperation {
    private final BigDecimal operand;

    public Negation(BigDecimal operand) {
        this.operand = operand;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();

            if (operand != null) {
                result.setError(Boolean.FALSE);
                result.setMessage("Negation is Done");
                result.setResult(operand.negate());
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Negation is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return "Negation: " + operand;
    }
}
