package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;

public class Addition implements EvalOperation {
    private final BigDecimal op1, op2;

    public Addition(BigDecimal op1, BigDecimal op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();

            if (op1 != null && op2 != null) {
                result.setResult(op1.add(op2));
                result.setError(Boolean.FALSE);
                result.setMessage("Addition is Done");
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Addition is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return op1 + " + " + op2;
    }
}
