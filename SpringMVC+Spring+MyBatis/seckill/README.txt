Java高并发秒杀API项目（Spring+SpringMVC+MyBatis）：
	秒杀业务场景具有典型的“事务”特性
	秒杀/红包类的需求越来越常见

1.创建项目：maven指令
	指令：mvn archetype:generate -DgroupId=org.seckill -DartifactId=seckill -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeCatalog=internal
	-DgroupId=org.seckill -DartifactId=seckill   ----->  项目坐标
	-DarchetypeArtifactId=maven-archetype-webapp  -----> 使用maven的webapp原型创建项目
	pom.xml为maven标准配置文件

2.修改Servlet版本为3.1
	直接参考E:\apache-tomcat-8.0.33\webapps\examples\WEB-INF下的web.xml
  补全项目结构

3.pom.xml修改junit版本为4.11（使用注解方式运行junit进行测试）
  	补全项目依赖（寻找依赖坐标网址：http://www.mvnrepository.com/）  ----->  即jar包下载,应用与管理：
		日志：java日志（slf4j + logback）
		实现slf4j接口并整合
		数据库相关依赖：
			mysql-connector-java连接驱动只有在真正工作时才会使用，补全maven工作范围：<scope>runtime</scope>
		DAO框架：MyBatis依赖
		MyBatis自身实现的spring整合依赖
		Servlet web相关依赖
		Spring依赖（4.1.7.RELEASE）：
			Spring核心依赖
			Spring DAO依赖（jdbc + tx）
			Spring web相关依赖
			Spring test 相关依赖

4.秒杀业务分析
	秒杀业务的核心：
		库存的处理
	用户针对库存的业务分析：
		减库存 + 记录购买明细  ----->  完整的事务  ----->  准确数据落地
	Mysql实现秒杀业务的难点分析   难点问题-“竞争”：
		竞争（事务 + 行级锁）：      ？
			竞争出现在减库存（update）上
	秒杀功能：
		秒杀接口暴露（避免用户通过浏览器插件在提前知道秒杀接口后填入参数自动提前秒杀）
		执行秒杀
		相关查询：
			列表查询
			详情页查询


DAO（数据访问层）设计编码
5.DAO层设计与开发（接口设计+SQL编写）：
	数据库设计：
		设计Table
	DAO层实体和接口编码：
		设计实体（entity）：
			数据库表和列直接对应java实体中的类和属性：
				Table ----->  Entity（seckill_id -----> seckillId      start_time -----> startTime）
			SuccessKilled实体中包含Seckill实体（复合关系）
		设计实体entity对应的dao接口：
			Seckill -----> SeckillDao    SuccessKilled -----> SuccessKilledDao   即设计各实体操作数据库的业务方法：
				/*根据id查询SuccessKilled并携带秒杀产品对象实体*/
				SuccessKilled queryByIdWithSeckill(@Param("seckillId") int seckillId,@Param("userPhone") String userPhone);
	基于myBatis实现DAO：
		MyBatis：
			参数 + SQL = Entity/List
			通过XML提供SQL,MyBatis内部Mapper自动实现DAO接口,不需要编写dao层具体实现类
			数据库 <----->  映射  <-----> 对象
			把数据库的数据{例：seckill_id}映射到entity对象{seckillId}
			把entity对象里的数据映射到数据库
			一种ORM对象关系映射框架
			*****  不要理所当然地认为普通sql语句查询结果返回的就是对象！要经过JDBC/MyBatis/Hibernate的映射与封装！
		myBatis官方文档：
            http://www.mybatis.org/mybatis-3/zh/index.html
        创建MyBatis全局配置文件并进行全局配置：
			mybatis-config.xml
		创建MyBatis SQL映射文件夹mapper,为dao接口方法提供sql语句配置：
			SeckillDao.xml
			SuccessKilledDao.xml
	myBatis整合Spring：
		spring-framework-reference文档
		spring/spring-dao.xml：
			第一步：配置数据库相关参数
			第二步：配置数据库连接池
			第三步：配置sqlSessionFactory对象
			第四步：配置扫描dao接口包,MyBatis内部Mapper自动动态实现dao接口后会注入到spring容器中
	DAO层单元测试编码和问题排查：
		配置spring与junit的整合,使得junit启动时加载springIOC容器：
			@RunWith(SpringJUnit4ClassRunner.class)
			在此之前MyBatis内部Mapper自动实现的dao实现类已被注入springIOC容器中
		告诉junit spring的配置文件（即MyBatis与Spring框架整合配置文件）：
			@ContextConfiguration({"classpath:spring/spring-dao.xml"})
		注入dao实现类依赖：
			@Resource
            private SeckillDao seckillDao;
            直接从springIOC容器中拿SeckillDao实现类对象进行测试


Service设计编码
6.秒杀Service接口设计：
	DAO拼接等逻辑在Service层完成,DAO层不应夹杂逻辑程序,逻辑应放在Service层完成
	service包：
		存放service接口和实现类
	exception包：
		存放service接口所需要的异常,例：重复秒杀,秒杀已关闭...
	dto包：
		数据传输层,负责web与service间的数据传递,其实是service接口返回数据的封装

7.实现SeckillService接口：
	所有编译期异常转化为运行期异常：
		spring声明式事务只会对运行期的异常进行rollback回滚
	如果SeckillServiceImpl事务组合逻辑没有错误,就返回new SeckillExecution(seckillId, SeckillStateEnums.SUCCESS,successKilled)
	如果SeckillServiceImpl事务组合逻辑抛出异常,用try catch把可能的已设计出的异常抛出,在SeckillController里根据抛出的异常信息进行不同的new SeckillExecution返回
	SeckillController里return new SeckillResult<SeckillExecution>(true,seckillExecution);统一的数据封装模式：
		页面所有ajax请求返回类型，封装json结果，结果为泛型T
	spring事务遇到(运行期)异常就会回滚不会提交
	用枚举封装并表示常量字典：
		新建enums包,类型为enum

8.基于Spring托管Service依赖（即实现）：
	Spring IOC功能理解：
		对象工厂 + 依赖管理  ----->  一致的访问接口（获取任意实例）
	项目业务对象依赖：
		SeckillService 依赖于： SeckillDao + SuccessKilledDao 依赖于：SqlSessionFactory 依赖于：DataSource...
	IOC使用：
		XML配置 -----> package-scan -----> Annotation注解：
			spring-service.xml：
			    <!--扫描service包下所有使用注解的类型 -->
                <context:component-scan base-package="org.seckill.service"></context:component-scan>
			SeckillServiceImpl中：
				class SeckillServiceImpl（@Service）
				private SeckillDao seckillDao;   private SuccessKilledDao successKilledDao;：
					此前MyBatis内部Mapper已经实现dao接口并注入Spring容器中,直接注入Service依赖@Autowired

9.Spring声明式事务配置：
	抛出运行期异常时Spring声明式事务rollback回滚
	配置事务管理器：
		<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	使用注解来管理事务行为：
		<tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
	SeckillServiceImpl下的executeSeckill方法：
		@Transactional
	若是只读操作或只有一条修改操作,则不需要事务管理

10.集成测试Service逻辑：
	注入seckillService对象：
		@Autowired
		private SeckillService seckillService;
	logback.xml：
		Log4J是Apache的一个开放源代码项目(http://logging.apache.org/log4j/docs/)，它是一个日志操作包。
		通过使用Log4J,可以指定日志信息输出的目的地，控制每一条日志的输出格式，定义日志信息的级别。所有这些功能通过一个配置文件灵活进行配置。
	注意看控制台的输出！


Web设计编码
11.前端交互设计 *****

12.Restful接口设计：
	Restful本身是一种优雅的URL表述方式：
		GET   /seckill/list   秒杀列表
		GET   /seckill/{id}/detail   详情页
		POST  /seckill/{id}/{md5}/execution   执行秒杀
		POST  /seckill/{seckillId}/execution
		DELETE  /seckill/{id}/delete
		/user/{uid}/followers   ----->  关注者列表

13.整合配置SpringMVC框架：
	web.xml：
		配置SpringMVC中央控制器Servlet：DispatcherServlet
		配置SpringMVC需要加载的配置文件,实现三大框架的整合：
			spring-dao.xml   spring-service.xml   spring-web.xml
            MyBatis -> Spring  -> SpringMVC
        匹配所有请求   “/”
    spring-web.xml：
    	配置SpringMVC：
			开启SpringMVC注解模式    ----->   自动注册基于注解的URL适配与方法的映射
			允许使用“/”做整体映射
			配置jsp,显示ViewResolver     org.springframework.web.servlet.view.JstlView
			扫描web相关的bean   ----->  SeckillController ：
				<context:component-scan base-package="org.seckill.web"></context:component-scan>

14.使用SpringMVC实现Restful接口：
	Controller就是MVC中的控制层：接受参数,根据一些验证或判断进行页面跳转的控制（json数据 或 ModelAndView -> jsp+model  ）
	SeckillController.java：
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
	dto中SeckillResult：
		所有ajax请求返回类型，封装json结果，结果为泛型T：
			return new SeckillResult<Exposer>(true,exposer);：
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

15.基于bootstrap开发页面结构：
	http://www.runoob.com/bootstrap/bootstrap-environment-setup.html
	<%@include file="common/head.jsp"%>     ----->    提取各jsp页面公用代码
	<%@include file="common/tag.jsp"%>      ----->    引入Jstl：
		JSP 标准标签库（JSP Standard Tag Library，JSTL）是一个实现 Web应用程序中常见的通用功能的定制标记库集，这些功能包括迭代和条件判断、数据管理格式化、XML 操作以及数据库访问。
		<c:forEach var="sk" items="${list}">    ----->   jstl + el

16.交互逻辑编程
	cookie登录交互：
		js插件官网：http://www.bootcdn.cn/
		javascript模块化开发
		detail.jsp使用EL(Expression Language)表达式向javascript传入参数,el表达式其实是一种取值的方式：
        	<script type="text/javascript">
        		$(function(){
        		  //detail.jsp使用EL表达式向javascript传入参数
        		  seckill.detail.init({
        			seckillId:${seckill.seckillId},
        			startTime:${seckill.startTime.time},  //毫秒
        			endTime:${seckill.endTime.time}
        		  });
        		});
        	</script>
        电话写入cookie：
        	$.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});   7天有效期,写入seckill路径下
		在cookie中查找手机号：
        	var killPhone = $.cookie('killPhone');
        在浏览器中输出相应信息便于debug：
        	console.log("inputPhone="+inputPhone);//TODO
        错误信息提示：
        	$("#killPhoneMessage").hide().html('<label class="label label-danger">输入电话错误！</label>').show(300);
		引入js文件时charset="GBK"解决中文乱码问题
	计时交互：
		$.get(seckill.URL.now(),{}, function (result) {
						//URL：seckill.URL.now()
						//参数：{}
						//回调函数：function (result) {}
						//回调结果：result
                    });
		countdown函数使用：
			var killTime = new Date(startTime+1000);
            seckillBox.countdown(killTime,function(event){
            	//时间格式
            	var format = event.strftime('秒杀倒计时; %D天 %H时 %M分 %S秒');
            	seckillBox.html(format);
            	/*时间完成后回调事件*/
            }).on('finish.countdown',function(){
            	//获取秒杀地址，执行秒杀
            	seckill.handleSeckill(seckillId,seckillBox);
            	});
	秒杀交互：
		使用one("click")事件，可以确保只绑定一次事件，防止重复提交以至于服务器重复收到同样的请求而业务繁忙。
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		先隐藏按钮,等函数回调完成,即绑定按钮触发事件后,再把按钮显示(show)出来,保证系统的健壮性。

17.项目总结

18.高并发优化：
	优化策略：
		前端控制：暴露接口,按钮防重复（只绑定一次事件 , 防止用户不停地点击秒杀按钮）
		动静态数据分离：CDN缓存静态资源  ？ ,后端缓存（redis）
		事务竞争优化：减少事务锁时间    ？
	redis缓存（服务器后端缓存）Seckill对象：
		系统暴露秒杀接口只参照开启时间,结束时间等一时不变,静态数据,可以缓存至内存,降低数据库访问压力！
		从数据库中取数据相当于从硬盘取数据,时间 > 从内存取数据的时间！
		Tomcat中sql语句传送至Mysql服务端需要传送时间！
		QPS  ？
		pom.xml：
			redis客户端：Jedis依赖
			protostuff序列化依赖
		spring-dao.xml：
			因为redis不属于myBatis范畴,因此要自己注入已经写好的RedisDao bean
		RedisDaoTest.java：
			@Autowired   //自动装载RedisDao对象
        	private RedisDao redisDao;
		cmd下开启redis服务：
			redis-server.exe redis.conf
			redis-cli.exe -h 127.0.0.1 -p 6379 -a 123456
		FLUSHALL：
			清除整个redis数据（清楚全部KEYS）
		KEYS *：
			查看所有KEY值

