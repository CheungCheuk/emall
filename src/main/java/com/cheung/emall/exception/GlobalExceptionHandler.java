package com.cheung.emall.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
//  @ControllerAdvice = @ExceptionHandler + @InitBinder + @ModelAttribute
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e)throws Exception{
        e.printStackTrace();
        //  Class.forName：加载异常类 ConstraintViolationException 到jvm，并初始化
//        jvm在装载类时会执行类的静态代码段，静态代码是和class绑定的，
//        class装载成功就表示执行了静态方法的代码了，而且以后不会再执行这段静态代码
        Class<?> constrainViolationException
                = Class.forName("org.hibernate.exception.ConstraintViolationException");

        //  如果存在 Throwable，且 Throwable 类型为 ConstraintViolationException，打印信息
        if (e.getCause() != null && constrainViolationException == e.getCause().getClass()){
            return "违反了参照完整性约束";
        }
        return e.getMessage();
    }
}
