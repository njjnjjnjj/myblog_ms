package com.njj.blog.common.util.common.aspect;

import com.njj.blog.common.util.common.string.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/2/19
 */
public class AspectUtils {

    public static String generateMethodNameWithArgs(ProceedingJoinPoint pjp){
        Signature signature = pjp.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        Object[] args = pjp.getArgs();
        List<String> argStrs = new ArrayList<String>();
        for (Object obj : args){
            argStrs.add(obj.toString());
        }
        String arg = StringUtils.join(argStrs,",");
        return StringUtils.format("{}.{}({})", declaringTypeName, methodName, arg);
    }

}
