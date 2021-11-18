package com.example.Calculator.Controller;

import com.example.Calculator.Constants;
import com.example.Calculator.Model.Operations.*;
import com.example.Calculator.Model.Result;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping(Constants.CALC_PATH)
public class CalculatorController {
    @PostMapping("/Result")
    public Result res(@RequestBody Operation OP) throws Exception {
        Result result;
        result = OP.RoutingOperation();
        result.written();
        return result;
    }
}
