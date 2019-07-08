package com.example.aopstudy;

import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 实现防止多次点击
 */
@Aspect
public class SingleClickAop {
    private static final long DEFAULT_TIME_INTERVAL = 5000;

    /**
     * 定义切点，标记切点为所有被@AopClick注解的方法。
     * 注意:这里的com.example.aopstudy.AopClick需要替换成自己
     * 安装路径下面的自定义注解接口
     */
    @Pointcut("execution(@com.example.aopstudy.AopClick * *(..))")
    public void cutPointMothod() {
    }

    /**
     * 定义一个切面方法，包裹切点方法
     */
    @Around("cutPointMothod()")
    public void JoinPointToPointFace(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        for (Object arg : joinPoint.getArgs()) {//判断是否是View  joinPoint.getArgs返回切点方法里面的参数
            if (arg instanceof View) {
                view = (View) arg;
                break;
            }

        }
        if (view == null) {
            return;
        }
        //取出来方法的注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();//拦截的方法名称
        Method method = methodSignature.getMethod();
        if (!method.isAnnotationPresent(AopClick.class)) {//返回true来检测是否方法上面标注这指定类型"AopClick"注解
            return;
        }
        AopClick aopClick = method.getAnnotation(AopClick.class);//方法返回该元素的指定类型的注释
        //判断是否快速点击
        if (!isFastDoubleClick(view,aopClick.value())) {//aopClick.value拿到注解的数值然后进行判断是否
            joinPoint.proceed();
        }

    }

    /**
     * 最近一次点击的时间
     */
    private static long mLastClickTime;
    /**
     * 最近一次点击的控件ID
     */
    private static int mLastClickViewId;

    /**
     * 是否是快速点击
     *
     * @param v              点击的控件
     * @param intervalMillis 时间间期（毫秒）
     * @return true:是，false:不是
     */
    public static boolean isFastDoubleClick(View v, long intervalMillis) {
        int viewId = v.getId();
        long time = System.currentTimeMillis();
        long timeInterval = Math.abs(time - mLastClickTime);
        if (timeInterval < intervalMillis && viewId == mLastClickViewId) {
            return true;
        } else {
            mLastClickTime = time;
            mLastClickViewId = viewId;
            return false;
        }
    }

}
