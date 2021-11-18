package com.example.Calculator.Model.Operations;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Result;

import java.math.BigDecimal;
import java.math.MathContext;

public class SquareRoot implements EvalOperation {
    private final BigDecimal operand;

    public SquareRoot(BigDecimal operand) {
        this.operand = operand;
    }

    @Override
    public Result Evaluate() throws Exception {
        try {
            Result result = new Result();
            MathContext mc = new MathContext(16);
            BigDecimal zero = BigDecimal.valueOf(0);

            if (operand != null && operand.compareTo(zero) > 0) {
                result.setError(Boolean.FALSE);
                result.setMessage("Square Rooting is Done");
                result.setResult(operand.sqrt(mc));
            } else if (operand != null && operand.compareTo(zero) < 0) {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_NEGATIVE_VALUE);
                EvalOperation.getLogger().error(Constants.ERROR_NEGATIVE_VALUE);
            } else {
                result.setError(Boolean.TRUE);
                result.setMessage(Constants.ERROR_OPERAND_NOT_VALID);
                EvalOperation.getLogger().error(Constants.ERROR_OPERAND_NOT_VALID);
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Square Root is Not Working");
        }
    }

    @Override
    public String writtenOP() {
        return "sqrt" + "( " + operand + " )";
    }
}
