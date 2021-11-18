package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Inverse implements EvalOperation {
    private final BigDecimal operand;

    public Inverse(BigDecimal operand) {
        this.operand = operand;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();
            BigDecimal one = BigDecimal.valueOf(1);
            BigDecimal zero = BigDecimal.valueOf(0);

            if (Objects.equals(operand, zero)) {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_DIVIDE_BY_ZERO);
                EvalOperation.getLogger().error(Constants.ERROR_DIVIDE_BY_ZERO);
            } else if (operand != null) {
                result.setError(Boolean.FALSE);
                result.setMessage("Inverse is Done");
                result.setResult(one.divide(operand, 12, RoundingMode.DOWN));
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Inverse is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return 1 + "/" + operand;
    }
}
