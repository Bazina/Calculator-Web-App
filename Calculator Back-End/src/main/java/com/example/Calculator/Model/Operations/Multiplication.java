package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;

public class Multiplication implements EvalOperation {
    private final BigDecimal op1, op2;

    public Multiplication(BigDecimal op1, BigDecimal op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();
            if (op1 != null && op2 != null) {
                result.setResult(op1.multiply(op2));
                result.setMessage("Multiplication is Done");
                result.setError(Boolean.FALSE);
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Multiplication is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return op1 + " * " + op2;
    }
}
