package org.example.add;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Loader;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class assist {

    public static void main(String[] args) throws ClassNotFoundException {
//        String clazz = NormalPerson.class.toString();
        //https://www.javassist.org/tutorial/tutorial.html
        String clazz = "org.example.add.NormalPerson";
        Class classNormal = Class.forName(clazz);
        NormalPerson person = new NormalPerson();
        person.setName("normal");
        System.out.println("before " + person);

        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get(clazz);
            CtMethod method = CtNewMethod.make("public void fly(){System.out.println(\"我是超人我会飞\");}", ctClass);
            ctClass.addMethod(method);

            Loader cl = new Loader(pool);
            Class cs = cl.loadClass(clazz);
            System.out.println("class print");
            System.out.println(Integer.toHexString(classNormal.hashCode()));
            System.out.println(Integer.toHexString(cs.hashCode()));

            MyClassLoader loader1 = new MyClassLoader();
            Class class1 = loader1.findClassByBytes(clazz, ctClass.toBytecode());
            System.out.println(Integer.toHexString(class1.hashCode()));

            Class class12 = loader1.findClassByBytes(clazz, ctClass.toBytecode());
            System.out.println(Integer.toHexString(class12.hashCode()));

            MyClassLoader loader2 = new MyClassLoader();
            Class class2 = loader2.findClassByBytes(clazz, ctClass.toBytecode());
            System.out.println(Integer.toHexString(class2.hashCode()));

            Object obj = cs.getDeclaredConstructor().newInstance();
            cs.getDeclaredMethod("fly").invoke(obj);
        } catch (NotFoundException | CannotCompileException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.example.add;

public class NormalPerson {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


package org.example.add;

public class MyClassLoader extends ClassLoader {

    /**
     * 通过classBytes加载类
     */
    public Class<?> findClassByBytes(String className, byte[] classBytes) {
        return defineClass(className, classBytes, 0, classBytes.length);
    }
}


