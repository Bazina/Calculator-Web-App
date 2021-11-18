package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Division implements EvalOperation {
    private final BigDecimal op1, op2;

    public Division(BigDecimal op1, BigDecimal op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();
            BigDecimal zero = BigDecimal.valueOf(0);
            if (Objects.equals(op2, zero)) {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_DIVIDE_BY_ZERO);
                EvalOperation.getLogger().error(Constants.ERROR_DIVIDE_BY_ZERO);
            } else if (op1 != null && op2 != null) {
                result.setResult(op1.divide(op2, 12, RoundingMode.DOWN));
                result.setError(Boolean.FALSE);
                result.setMessage("Division is Done");
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Division is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return op1 + " / " + op2;
    }
}
