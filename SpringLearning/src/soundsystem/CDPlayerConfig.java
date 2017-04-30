package soundsystem;
import concert.Audience;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = {"soundsystem","video"})通过包名是类型不安全的
//@ComponentScan(basePackageClasses = {CDPlayerConfig.class})
public class CDPlayerConfig {

    @Bean(name = "lonelyHeartsClubBand")
    public CompactDisc sgtPeppers(){
        return new SgtPeppers();
    }

    /*@Bean
    public MediaPlayer cdPlayer(){
        return new CDPlayer(sgtPeppers());
    }*/

    @Bean
    public MediaPlayer cdPlayer(CompactDisc compactDisc){
        return new CDPlayer(compactDisc);
    }

    @Bean
    public Audience audience(){
        return new Audience();
    }
}
