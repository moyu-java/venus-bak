package com.junmoyu.venus.starter.core.util;

import cn.hutool.core.collection.CollectionUtil;
import lombok.experimental.UtilityClass;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class 类工具类
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@UtilityClass
public class ClassUtils extends org.springframework.util.ClassUtils {

    private final ParameterNameDiscoverer PARAMETERNAMEDISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * 获取方法参数信息
     *
     * @param constructor    构造器
     * @param parameterIndex 参数序号
     * @return {MethodParameter}
     */
    public MethodParameter getMethodParameter(Constructor<?> constructor, int parameterIndex) {
        MethodParameter methodParameter = new SynthesizingMethodParameter(constructor, parameterIndex);
        methodParameter.initParameterNameDiscovery(PARAMETERNAMEDISCOVERER);
        return methodParameter;
    }

    /**
     * 获取方法参数信息
     *
     * @param method         方法
     * @param parameterIndex 参数序号
     * @return {MethodParameter}
     */
    public MethodParameter getMethodParameter(Method method, int parameterIndex) {
        MethodParameter methodParameter = new SynthesizingMethodParameter(method, parameterIndex);
        methodParameter.initParameterNameDiscovery(PARAMETERNAMEDISCOVERER);
        return methodParameter;
    }

    /**
     * 获取Annotation
     *
     * @param method         Method
     * @param annotationType 注解类
     * @param <A>            泛型标记
     * @return {Annotation}
     */
    public <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
        Class<?> targetClass = method.getDeclaringClass();
        // The method may be on an interface, but we need attributes from the target
        // class.
        // If the target class is null, the method will be unchanged.
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the original
        // method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        // 先找方法，再找方法上的类
        A annotation = AnnotatedElementUtils.findMergedAnnotation(specificMethod, annotationType);
        if (null != annotation) {
            return annotation;
        }
        // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
        return AnnotatedElementUtils.findMergedAnnotation(specificMethod.getDeclaringClass(), annotationType);
    }

    /**
     * 获取Annotation
     *
     * @param handlerMethod  HandlerMethod
     * @param annotationType 注解类
     * @param <A>            泛型标记
     * @return {Annotation}
     */
    public <A extends Annotation> A getAnnotation(HandlerMethod handlerMethod, Class<A> annotationType) {
        // 先找方法，再找方法上的类
        A annotation = handlerMethod.getMethodAnnotation(annotationType);
        if (null != annotation) {
            return annotation;
        }
        // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
        Class<?> beanType = handlerMethod.getBeanType();
        return AnnotatedElementUtils.findMergedAnnotation(beanType, annotationType);
    }

    /**
     * 将两个有相同属性的类型相加，Integer类型会相加
     *
     * @param t1  对象1
     * @param t2  对象2
     * @param <T> 泛型
     * @return 返回对象
     * @throws Exception 异常
     */
    public static <T> T addProperty(T t1, T t2) throws Exception {
        if (t1 != null && t2 != null) {
            return addProperty(t1, t2, (String[]) null);
        }
        if (t1 == null && t2 != null) {
            return t2;
        }
        if (t1 != null && t2 == null) {
            return t1;
        }
        return null;
    }

    /**
     * 相同对象集合的Integer属性相加
     *
     * @param list 集合
     * @param <T>  对象
     * @return 集成结果
     * @throws Exception 空异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T addProperty(List<T> list) throws Exception {
        List<T> ts = CollectionUtil.removeNull(list);
        if (ts.size() == 0) {
            return null;
        }
        if (CollectionUtil.isNotEmpty(list)) {
            T t3 = (T) list.get(0).getClass().getConstructor().newInstance();
            for (T t : list) {
                t3 = addProperty(t3, t);
            }
            return t3;
        } else {
            throw new Exception("集合不能为空");
        }
    }

    /**
     * 将两个有相同属性的类型相加，Integer类型会相加
     *
     * @param t1  对象1
     * @param t2  对象2
     * @param <T> 泛型
     * @return 返回对象
     * @throws Exception 异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T addProperty(T t1, T t2, String... excludeProperties) throws Exception {
        Field[] t1Fields = t1.getClass().getDeclaredFields();
        Field[] t2Fields = t2.getClass().getDeclaredFields();
        T t3 = (T) t1.getClass().getConstructor().newInstance();
        try {
            List<Field> fields = Arrays.stream(t1Fields).filter(t1Field -> !CollectionUtil.toList(excludeProperties).contains(t1Field.getName())).collect(Collectors.toList());
            fields.forEach(field -> {
                for (Field t2Field : t2Fields) {
                    if (t2Field.equals(field)) {
                        if (Integer.class.isAssignableFrom(t2Field.getType()) && Integer.class.isAssignableFrom(field.getType())) {
                            try {
                                for (Field declaredField : t3.getClass().getDeclaredFields()) {
                                    field.setAccessible(true);
                                    t2Field.setAccessible(true);
                                    declaredField.setAccessible(true);
                                    if (declaredField.equals(t2Field)) {
                                        declaredField.set(t3, (Integer) (field.get(t1) == null ? 0 : field.get(t1)) + ((Integer) (t2Field.get(t2) == null ? 0 : t2Field.get(t2))));
                                        return;
                                    }
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t3;
    }
}
