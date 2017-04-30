package concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Created by huangfugui on 2017/4/2.
 */
@Aspect
public class EncoreableIntroducer {
    @DeclareParents(value = "concert.Peformance+",defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}
