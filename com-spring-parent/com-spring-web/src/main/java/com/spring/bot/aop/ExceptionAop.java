package com.spring.bot.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Date;

@Aspect
@Component
public class ExceptionAop {
    private Logger logger = LoggerFactory.getLogger(ExceptionAop.class);
    /****
     * 此切点主要针对controller入参和返回参数监控
     */
    public static final String EDP = "execution(* com.spring.bot.controller.*.*(..))";

    @Around(EDP)   //spring中Around通知
    public Object logAround(ProceedingJoinPoint joinPoint) {
        System.out.println("执行切面操作");
        logger.debug("logAround开始:[{}] 参数为:{}",joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName(),joinPoint.getArgs()); //方法执行前的代理处理
        Object[] args = joinPoint.getArgs();
        Object obj = null;
        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.debug("logAround结束:现在时间是:{} 返回为:{}",new Date(),obj);  //方法执行后的代理处理
        return obj;
     /*   Object[] args = joinPoint.getArgs();
        Object obj = null;
        try {
            obj = joinPoint.proceed(args);
        } catch (BusinessExcepiton e) {
            logger.error("业务异常:{},{}",e.getErrorCode(),e.getErrorMsg());
            obj = new ResponseEntity(Constants.System.FAIL,e.getErrorCode(),e.getMessage(),null);
        }catch (BaseException e){
            logger.error("第三方数据返回异常",e);
            obj = new ResponseEntity(Constants.System.FAIL,e.getErrorCode(),e.getMessage(),null);
        }catch (Throwable e){
            logger.error("系统异常", e);
            if (e instanceof SQLException) {
                obj = new ResponseEntity(Constants.System.FAIL, cn.upenny.common.exception.ERROR.E00000001.name(), cn.upenny.common.exception.ERROR.E00000001.toString(), "数据库执行异常");
            }
            obj = new ResponseEntity(Constants.System.FAIL, cn.upenny.common.exception.ERROR.E00000001.name(), cn.upenny.common.exception.ERROR.E00000001.toString(),e.getMessage());
        }
        logger.debug("logAround结束:现在时间是:{} 返回为:{}",new Date(),obj);  //方法执行后的代理处理
        return obj;*/
    }
}
