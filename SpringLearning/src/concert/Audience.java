package concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by huangfugui on 2017/4/2.
 */
@Aspect
public class Audience {

    @Pointcut("execution(* concert.Peformance.perform(..))")
    public void performance(){}

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("Silence cell phone");
            System.out.println("Taking seats");
            joinPoint.proceed();
            System.out.println("CLAP CLAP CLAP!!!");
        }
        catch (Throwable throwable){
            System.out.println("Demanding a refund");
        }
    }
    /*@Before("performance()")
    public void silenceCellPhone(){
        System.out.println("Silence cell phone");
    }

    @Before("performance()")
    public void takeSeats(){
        System.out.println("Taking seats");
    }

    @AfterReturning("performance()")
    public void applause(){
        System.out.println("CLAP CLAP CLAP!!!");
    }

    @AfterThrowing("performance()0")
    public void demandRefund(){
        System.out.println("Demanding a refund");
    }*/

    /*@Before("execution(* concert.Peformance.perform(..))")
    public void silenceCellPhone(){
        System.out.println("Silence cell phone");
    }

    @Before("execution(* concert.Peformance.perform(..))")
    public void takeSeats(){
        System.out.println("Taking seats");
    }

    @AfterReturning("execution(* concert.Peformance.perform(..))")
    public void applause(){
        System.out.println("CLAP CLAP CLAP!!!");
    }

    @AfterThrowing("execution(* concert.Peformance.perform(..))")
    public void demandRefund(){
        System.out.println("Demanding a refund");
    }*/
}
