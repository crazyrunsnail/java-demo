package person.davino.basic.jvm.classload.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    static {
        System.out.println("开始加载ClassLoaderTest....");
    }


    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    System.out.println("Loading class name of [" + name + "]");
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        System.out.println("Is is null, using prarent classLoader!");
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);

                } catch (IOException ioe) {
                    throw new ClassNotFoundException(name);
                }

            }
        };

        System.out.println("calling myLoader.loadClass()");
        Class<?> aClass = myLoader.loadClass("person.davino.basic.jvm.classload.classloader.ClassLoaderTest");
        System.out.println("calling end!");
        System.out.println("newInstanceing....");
        Object object = aClass.newInstance();
        System.out.println("Instanceing end....");
        System.out.println(object.getClass());
        System.out.println(object instanceof ClassLoaderTest);
        ClassLoader parent = myLoader.getParent();
        if (parent != null) {
            System.out.println(parent);
        }
    }
}
