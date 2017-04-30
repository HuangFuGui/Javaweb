package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangfugui on 2017/4/2.
 */
@Configuration
@EnableAspectJAutoProxy
public class TrackCounterConfig {

    @Bean
    public CompactDisc sgtPeppers(){
        SgtPeppers sgtPeppers = new SgtPeppers();
        List<String> tracks = new ArrayList<String>();
        tracks.add("HaHaHaHaHaHaHaHa");
        tracks.add("HaHaHaHaHaHaHa");
        tracks.add("HaHaHaHaHaHa");
        tracks.add("HaHaHaHaHa");
        tracks.add("HaHaHaHa");
        tracks.add("HaHaHa");
        tracks.add("HaHa");
        tracks.add("Ha");
        sgtPeppers.setTracks(tracks);
        return sgtPeppers;
    }

    @Bean
    public TrackCounter trackCounter(){
        return new TrackCounter();
    }
}
