package reflaction;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassExplorer {
    public static void explorerMetadata(Object object) throws Exception {
        System.out.println("Extracting attributes");
        for (Field field : object.getClass().getDeclaredFields()) {
            System.out.println(field.getName() + ":" + field.getType().getName());
        }

        System.out.println("Extracting methods");
        for (Method method : object.getClass().getDeclaredMethods()) {
            System.out.println(method.getName() + ":" + method.getReturnType().getName());
        }

        System.out.println("Extracting data");
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                System.out.println(field.get(object));
            } finally {
                field.setAccessible(false);
            }
        }

        System.out.println("Extracting data by methods");
        for (Method method : object.getClass().getDeclaredMethods()) {
            if(method.getName().startsWith("get")) {
                System.out.println(method.getName() + "- Value: " + method.invoke(object));
            }
        }
    }
}
