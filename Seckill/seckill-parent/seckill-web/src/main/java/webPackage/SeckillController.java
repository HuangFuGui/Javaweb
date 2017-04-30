package webPackage;

import dtoPackage.Exposer;
import dtoPackage.SeckillExecution;
import dtoPackage.SeckillResult;
import entityPackage.Seckill;
import enumsPackage.SeckillStateEnums;
import exceptionPackage.RepeatKillException;
import exceptionPackage.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import servicePackage.SeckillService;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
@Controller
@RequestMapping("/seckill")  // url:  /模块/资源/{id}细分
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        //获取列表页
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list",list);

        //list.jsp + model = ModelAndView
        return  "list";  /*   /WEB-INF/jsp/"list".jsp  */
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") int seckillId,Model model){
        if(String.valueOf(seckillId)==null){   // int --> String
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill==null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    //ajax json
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody  //SpringMVC会将SeckillResult<Exposer>封装为json
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") int seckillId){

        SeckillResult<Exposer> result;

        try {
            Exposer exposer =  seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }

    //dto是web层跟service层的数据传递
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") int seckillId,
                                                   @CookieValue(value = "killPhone",required = false)String phone,
                                                   @PathVariable("md5") String md5){
        if(phone==null){
            return  new SeckillResult<SeckillExecution>(false,"未注册");
        }

        SeckillResult<SeckillExecution> result;

        try{
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }
        /*
        由于executeSeckillProcedure已经将重复秒杀，秒杀结束（无库存）合并到返回的SeckillExecution中，所以不用再捕获这两个异常
        catch (RepeatKillException e){
            //捕获executeSeckill函数抛出的重复秒杀异常，并返回异常数据类型到前端
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnums.REPEAT_KILL);
            return  new SeckillResult<SeckillExecution>(true,seckillExecution);
        }
        catch (SeckillCloseException e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnums.END);
            return  new SeckillResult<SeckillExecution>(true,seckillExecution);
        }*/
        catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnums.INNER_ERROR);
            return  new SeckillResult<SeckillExecution>(true,seckillExecution);
        }
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult(true,now.getTime());
    }
}
