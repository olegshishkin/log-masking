package io.github.olegshishkin.mask.builder;

import io.github.olegshishkin.mask.annotation.Mask;
import io.github.olegshishkin.mask.builder.masker.MaskerResolver;
import java.lang.reflect.Field;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringSummary;

public class MaskedReflectionToStringBuilder extends ReflectionToStringBuilder {

    public MaskedReflectionToStringBuilder(Object o, ToStringStyle style) {
        super(o, style, null, null, false, false, true);
    }

    @SneakyThrows
    @Override
    protected void appendFieldsIn(Class<?> cls) {
        if (cls.isArray()) {
            super.appendFieldsIn(cls);
            return;
        }

        Field[] fields = Stream.of(cls.getDeclaredFields())
                .filter(this::accept)
                .filter(Field::trySetAccessible)
                .toArray(Field[]::new);
        for (Field field : fields) {
            Object val = this.getValue(field);
            if (!this.isExcludeNullValues() || val != null) {
                if (field.isAnnotationPresent(Mask.class)) {
                    val = getMaskedValue(field, val);
                }
                this.append(field.getName(), val, !field.isAnnotationPresent(ToStringSummary.class));
            }
        }
    }

    private Object getMaskedValue(Field field, Object o) {
        if (o == null) {
            return null;
        }
        String rule = field.getAnnotation(Mask.class).value();
        return MaskerResolver.masker(rule).mask(o);
    }
}
