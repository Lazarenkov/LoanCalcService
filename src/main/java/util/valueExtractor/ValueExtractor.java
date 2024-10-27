package util.valueExtractor;

import exeptions.CalcErrorException;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

public class ValueExtractor {

    private static final HashMap<String, Object> valueMap = new HashMap<>();

    public static Object getValue(ValueTypes valueType) {
        try {
            return valueMap.get(valueType.toString());
        } catch (NullPointerException e) {
            LogManager.getLogger(ValueExtractor.class.getName()).error("Экстрактор значений не был проинициализирован" + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    public static void init(Object object) {
        valueMap.clear();
        extract(object);
    }

    private static void extract(Object object) {
        Class<?> cls = object.getClass();

        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(ExtractNested.class)) {
                    if (field.get(object) != null) {
                        extract(field.get(object));
                    }
                } else if (field.isAnnotationPresent(ExtractValue.class)) {
                    valueMap.put(field.getName(), field.get(object));
                }
            } catch (IllegalAccessException e) {
                LogManager.getLogger(ValueExtractor.class.getName()).error("Нет доступа к объекту " + field.getName());
                throw new CalcErrorException();
            }
        }
    }
}
