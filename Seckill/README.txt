1.本项目通过maven管理,秒杀系统实现框架为Spring+SpringMVC+MyBatis,因此可以说是Maven+Spring+SpringMVC+MyBatis的整合
    参考链接：
          https://github.com/HuangFuGui/Javaweb/tree/master/SpringMVC%2BSpring%2BMyBatis/seckill

2.高内聚低耦合：

    我们都知道评判一个系统好不好,高内聚低耦合是一个很重要的标准.高内聚,即一个模块恰好做一件事情,描述的是模块内的功能联
    系(例如dao模块只负责数据库的访问与操作,enums模块只负责枚举,redis模块只负责数据缓存);低耦合,即指模块与模块之间的联系
    要尽量少而简单.或只是简单的调用关系,如果两个模块的关系比较复杂,那就要考虑进一步划分.总而言之,高内聚低耦合有利于系统
    修改与组合.

    因此在开发过程中,应该重视项目的分块开发,传统的Javaweb开发模式（即分包）其实能很好的体现高内聚低耦合.

3.谈谈maven：

    Maven提供了一个项目模块化管理的舞台！不但把高内聚低耦合的特性发挥到极致.而且比传统的开发模式更先进,利于团队集体开发,
    项目的整合可以说是无缝对接,通过Maven可以先创建一个父模块(parent),在该模块下创建各子模块(entity,dao,service,web等),这
    样当一个系统由若干个人来分工的时候就显得非常简单,A负责entity模块,B负责dao模块,C负责service模块...他们之间的开发互不干
    犹,因为他们只需要创建或import/open自己的模块就行,这在传统的Javaweb开发模式(即分包来体现分模块的思想)中是办不到的,传统
    的开发模式每个人都要创建整个项目,项目进行到一定阶段准备整合时又要把各自负责的包不断的进行复制粘贴成为一个新的项目,而
    maven就不需要,因为他们各自负责的模块本来就是在一个父模块下的.

    Maven最重要的好处就是方便重用,一般公司或企业会将一些自己开发的重要的类与依赖发布到仓库中,这样以后要用到就不需要再编写
    ,直接引用依赖坐标就可以直接调用,为的不就是这样吗？

    Maven还有很多好处,例如通过dependency自动下载并引用jar包,根本不需要手动导入,还能管理各种插件等等...

4.项目结构：

    parent
        dao
            pom.xml(jar)
        dto
            pom.xml(jar)
        entity
            pom.xml(jar)
        enums
            pom.xml(jar)
        exception
            pom.xml(jar)
        redis
            pom.xml(jar)
        service
            pom.xml(jar)
        web
            pom.xml(war)
        pom.xml(pom)

5.注意事项：

    模块依赖关系一定不能出现循环！java注解模式不支持循环！
    凡是要用到SpringIOC容器内的依赖都要加上@Autowired注解（不用新建对象,new）
    在web.xml中特别注意各配置文件的引用顺序！

    在service模块的test中：
        @RunWith(SpringJUnit4ClassRunner.class)
        @ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-Redisdao.xml",
        "classpath:spring/spring-service.xml"})
    目的：
        引用dao配置文件,连接池,mybatis实现类整合到spring容器中,redisdao注入到spring容器中,service相应的实现类中要调用这些
        实现类.实现类中的@Service注解意思是将写好的SeckillService注入到SpringIOC容器中,这样就可以在test中直接@Autowired
        private SeckillService seckillService;拿到统一的接口SeckillService.

    在web模块的web.xml中：
        <param-value>classpath:spring/spring-dao.xml,classpath:spring/spring-Redisdao.xml,classpath:spring/spring-service
        .xml,classpath:spring/spring-web.xml</param-value>
    目的：
        因为web.xml会在tomcat启动后加载,所以这就实现了最开始将所有配置文件加载到Servlet中,各实现类,统一的实现接口,已经
        在spring容器中准备就绪.随时调用！

    spring-dao.xml要在dao模块中定义,spring-service.xml就在service模块中定义,各模块的配置文件分开.

    一般项目依赖链：
        web依赖于service,service依赖于dao,dao依赖于entity,sqlSessionFactory....