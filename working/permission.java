package com.dtzhejiang.enforcement.supervision.rest.permission;

import com.dtzhejiang.enforcement.supervision.service.annotation.MethodParamPermissionData;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.reflect.Method;

/**
 * Created by guihao on 2021-03-25 11:14
 * 权限校验处理器
 **/
public abstract class AbstractPermissionCheckProcessor {


    public PermissionCheckResult checkPermission(Method objMethod,MethodParamPermissionData annotation) {
        Object value = findParamValue(objMethod);
        return null;
    }

    /**
     * 实际的校验方法
     *
     * @return
     */
    protected abstract PermissionCheckResult check();

    /**
     * 寻找字段对应的值
     *
     * @param paramName
     * @param methodArgs
     * @param methodParamPermissionData
     * @return
     */
    protected Object findParamValue(String[] paramName, Object[] methodArgs, MethodParamPermissionData methodParamPermissionData) {
        for (int i = 0; i <= paramName.length - 1; i++) {
            String name = paramName[i];
            if (methodParamPermissionData.fieldName().equals(name)) {//优先寻找名称相同的
                return methodArgs[i];
            }
        }
        for (int i = 0; i <= paramName.length - 1; i++) {
            Class<?> argClazz = methodArgs[i].getClass();
            if (argClazz.equals(methodParamPermissionData.clazz())) {//第二优先级是寻找到类相同的
                BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(methodArgs[i]);//按照属性名称获取对象中属性的值
                Object attributeValue = wrapper.getPropertyValue(methodParamPermissionData.fieldName());
                if (attributeValue != null) {
                    return attributeValue;
                }
            }
        }
        //未找到
        return null;
    }

}


package com.dtzhejiang.enforcement.supervision.rest.permission;

import com.dtzhejiang.enforcement.supervision.rest.context.UserDataAccessContext;
import com.dtzhejiang.enforcement.supervision.service.annotation.MethodParamPermissionData;
import com.dtzhejiang.enforcement.supervision.service.domain.bo.UserDataAccessHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by guihao on 2021-03-23 15:54
 * 权限校验类
 **/
@Component
@Slf4j
public class PermissionCheckManager {

//    @Autowired
//    private 查询所有权限的方法

    /**
     * 获取用户的权限
     *
     * @return 可用区域列表
     */
    private Set<String> regions() {
        UserDataAccessHolder holder = UserDataAccessContext.get();
        if (holder != null) {
            if (!CollectionUtils.isEmpty(holder.getRegionCodeList())) {
                return holder.getRegionCodeList().stream().collect(Collectors.toSet());
            }
        }
        //测试用 TODO
        Set<String> regions = Stream.of("330000",
                "330100",
                "330200",
                "330300",
                "330400",
                "330500",
                "330600",
                "330700",
                "330800",
                "330900",
                "331000",
                "331100"
        ).collect(Collectors.toSet());
        return regions;
    }

    /**
     * 校验单个编码
     *
     * @param regionCode 需要校验的地区编码
     * @return 校验结果对象
     */
    private PermissionCheckResult checkPass(String regionCode) {
        Set<String> regions = regions();
        if (regions.contains(regionCode)) {
            return PermissionCheckResult.success();
        }
        return PermissionCheckResult.fail(Stream.of(regionCode).collect(Collectors.toList()));
    }

    /**
     * 校验地区编码数组
     *
     * @param regionCode 需要校验的地区编码
     * @return 校验结果对象
     */
    private PermissionCheckResult checkPass(String[] regionCode) {
        if (regionCode == null || regionCode.length == 0) {
            return checkAll();
        }
        List<String> arrayList = new ArrayList<>(regionCode.length);
        Collections.addAll(arrayList, regionCode);
        return checkPass(arrayList);
    }

    /**
     * 校验地区编码列表
     *
     * @param regionCode 需要校验的地区编码
     * @return 校验结果对象
     */
    private PermissionCheckResult checkPass(List<String> regionCode) {
        if (CollectionUtils.isEmpty(regionCode)) {
            return checkAll();
        }
        Set<String> regionCodeSet = regionCode.stream().collect(Collectors.toSet());
        Set<String> sub = subtraction(regions(), regionCodeSet);
        if (CollectionUtils.isEmpty(sub)) {
            return PermissionCheckResult.success();
        }
        return PermissionCheckResult.success();
    }

    /**
     * 校验用户是否有所有权限
     *
     * @return 校验结果对象
     */
    private PermissionCheckResult checkAll() {
        List<String> all = all();
        return checkPass(all);
    }

    /**
     * 查询系统的所有权限
     *
     * @return
     */
    private List<String> all() {
        List<String> regions = Stream.of("330000",
                "330100",
                "330200",
                "330300",
                "330400",
                "330500",
                "330600",
                "330700",
                "330800",
                "330900",
                "331000",
                "331100"
        ).collect(Collectors.toList());
        return regions;
    }

    /**
     * 两个集合的差集
     *
     * @param userSet  用户拥有的权限集合
     * @param checkSet 需要检查的权限集合
     * @return 需要检查的权限集合 - 用户拥有的权限集合 的差集
     */
    private Set<String> subtraction(Set<String> userSet, Set<String> checkSet) {
        Set<String> check = new HashSet<>(checkSet);
        check.removeAll(userSet);
        return check;
    }

    /**
     * 校验方法的参数
     *
     * @param method                    校验的方法
     * @param methodArgs                方法的参数
     * @param methodParamPermissionData 方法注解
     * @return 校验结果对象
     */
    public PermissionCheckResult check(Method method, Object[] methodArgs, MethodParamPermissionData methodParamPermissionData) {
        ParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();
        String[] paramName = discoverer.getParameterNames(method);
        if (!ArrayUtils.isEmpty(paramName)) {//找到地市字段的值
            Object value = null ;//findParamValue(paramName, methodArgs, methodParamPermissionData);
            if (value != null) {
                if (value instanceof String) {
                    //检验逻辑，单个字符串
                    return checkPass((String) value);
                } else if (value instanceof List) {
                    //检验逻辑，列表逻辑
                    return checkPass((List) value);
                } else if (value instanceof String[]) {
                    //检验逻辑，数组
                    return checkPass((String[]) value);
                } else {
                    //其他类型，则不校验，直接抛出，
                }
            } else {
                //为空，则校验全部权限
                return checkAll();
            }

        }
        return PermissionCheckResult.emptyFail();
    }

}

