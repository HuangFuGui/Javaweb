package concert;

import org.springframework.context.annotation.*;

/**
 * Created by huangfugui on 2017/4/2.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
@ImportResource({"classpath:spring.xml"})
public class ConcertConfig {
    @Bean
    public Audience audience(){
        return new Audience();
    }

    @Bean
    public EncoreableIntroducer encoreableIntroducer(){
        return new EncoreableIntroducer();
    }
}
