package bit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by huangfugui on 2016/11/7.
 */

/**
 * hello没有继承Servlet类，因此不是一个servlet.
 * 是一个普通java类，javabean.
 */
@Controller
public class hello {
    //private final Log logger = LogFactory.getLog(hello.class);

    //处理HEAD类型的”/”请求
    @RequestMapping(value={"/"},method= {RequestMethod.HEAD})
    public String head() {
        return "go.jsp";
    }

    //处理GET类型的"/index"和”/”请求
    @RequestMapping(value={"/index","/"},method= {RequestMethod.GET})
    public String index(Model model) throws Exception {
        //logger.info("======processed by index=======");
        //返回msg参数
        model.addAttribute("msg", "Hello SpringMVC World!");
        return "go.jsp";
    }
}
