package top.jglo.hotel.controller;


import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.model.result.ServerResult;

/**
 * @author gkirito
 */

@Api(tags = "事例")
@CrossOrigin
@Controller
@RequestMapping(value = {"example"})
public class ExController {



    @PostMapping(value = {"test1"})
    @ApiOperation(value = "测试1", notes = "采用RequestParam的形式", produces = "application/json,application/xml")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id号", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @ResponseBody
    public ServerResult findUser(@RequestParam int id, @RequestParam String pwd) {
        ServerResult result=new ServerResult();
        result.setMessage(id+","+pwd);
        return result;
    }

    @PostMapping(value = {"test2"})
    @ApiOperation(value = "测试2", notes = "采用RequestBody的形式", produces = "application/json,application/xml")
    @ResponseBody
    public ServerResult addWorker(@ApiParam(value = "user类") @RequestBody ServerResult result) {
        return result;
    }
}
