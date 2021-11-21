package com.bin.lg.test;

import lombok.Data;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * PropertyDescriptor 使用
 *
 * @author bin72
 * @date 2021/11/21 16:02
 */
public class PropertyDescriptorTest {
    /**
     * 构造方法，测试
     */
    @Test
    public void test1() throws NoSuchMethodException, IntrospectionException {

        PropertyDescriptor propertyDescriptor1 = new PropertyDescriptor("name", Student.class);
        PropertyDescriptor propertyDescriptor2 = new PropertyDescriptor("name", Student.class, "getName", "setName");

        Class<?> classType = Student.class;
        Method readMethodName = classType.getMethod("getName");
        Method writeMethodName = classType.getMethod("setName", String.class);
        PropertyDescriptor propertyDescriptor3 = new PropertyDescriptor("name", readMethodName, writeMethodName);
    }

    /**
     * 常用方法
     */
    @Test
    public void test2() throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", Student.class);
        // 获得属性的 Class 对象
        System.out.println(propertyDescriptor.getPropertyType());
        // 获得应该用于读取属性值的方法
        System.out.println(propertyDescriptor.getReadMethod());
        // 获得应该用于写入属性值的方法
        System.out.println(propertyDescriptor.getWriteMethod());
    }

    @Test
    public void test3() throws Exception {
        Class<?> aClass = Class.forName("com.bin.lg.test.Student");
        Object student = aClass.newInstance();

        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", aClass);
        Method writeMethod = propertyDescriptor.getWriteMethod();
        writeMethod.invoke(student, "zhang");
        System.out.println(student);
    }

    public static void setProperty(Object obj, String propertyName, Object value) throws Exception {
        Class<?> clazz = obj.getClass();
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, clazz);
        Method setMethod = pd.getWriteMethod();
        setMethod.invoke(obj, value);
    }

    public static Object getProperty(Object obj, String propertyName) throws Exception {
        Class<?> clazz = obj.getClass();
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, clazz);
        Method getMethod = pd.getReadMethod();
        // 调用方法获取方法的返回值
        return getMethod.invoke(clazz);
    }
}

@Data
class Student {
    private String name;
}
