package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;

public class Tax implements EvalOperation {
    private final BigDecimal op1, op2;

    public Tax(BigDecimal op1, BigDecimal op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();
            BigDecimal percent = BigDecimal.valueOf(0.01);

            if (op1 != null) {
                result.setError(Boolean.FALSE);
                result.setMessage("Taxing is Done");
                result.setResult(op1.multiply(op2.multiply(percent)));
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Tax is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return op1 + " Got Taxed";
    }
}
