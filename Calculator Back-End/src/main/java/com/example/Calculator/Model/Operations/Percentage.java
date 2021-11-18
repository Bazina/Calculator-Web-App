package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;

public class Percentage implements EvalOperation {
    private final BigDecimal operand;

    public Percentage(BigDecimal operand) {
        this.operand = operand;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();
            BigDecimal percent = BigDecimal.valueOf(0.01);

            if (operand != null) {
                result.setError(Boolean.FALSE);
                result.setMessage("Percentile is Done");
                result.setResult(operand.multiply(percent));
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Percentage is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return operand + " Divided by 100";
    }
}
