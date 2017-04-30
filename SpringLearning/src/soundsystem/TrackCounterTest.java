package soundsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huangfugui on 2017/4/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrackCounterConfig.class)
public class TrackCounterTest {
    @Autowired
    private CompactDisc compactDisc;

    @Autowired
    private TrackCounter counter;

    @Test
    public void testTrackCounter(){

        compactDisc.playTrack(1);
        compactDisc.playTrack(2);
        compactDisc.playTrack(3);
        compactDisc.playTrack(3);
        compactDisc.playTrack(3);
        compactDisc.playTrack(3);
        compactDisc.playTrack(7);
        compactDisc.playTrack(7);

        System.out.println(counter.getPlayCount(1));
        System.out.println(counter.getPlayCount(2));
        System.out.println(counter.getPlayCount(3));
        System.out.println(counter.getPlayCount(4));
        System.out.println(counter.getPlayCount(5));
        System.out.println(counter.getPlayCount(6));
        System.out.println(counter.getPlayCount(7));

    }
}
