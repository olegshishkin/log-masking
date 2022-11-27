package io.github.olegshishkin.mask.builder;

import static io.github.olegshishkin.mask.builder.util.MaskedToStringUtils.isJdkClass;

import lombok.SneakyThrows;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

public class MaskedMultilineRecursiveToStringStyle extends MultilineRecursiveToStringStyle {

    private static final String INDENT_FIELD = "INDENT";
    private static final String RESET_INDENT_METHOD = "resetIndent";
    private static final String SPACES_FIELD = "spaces";

    public MaskedMultilineRecursiveToStringStyle() {
        this.setUseClassName(false);
        this.setUseIdentityHashCode(false);
        this.setFieldNameValueSeparator(":");
    }

    @Override
    public void appendDetail(StringBuffer buf, String fieldName, Object val) {
        if (!ClassUtils.isPrimitiveWrapper(val.getClass()) &&
                !String.class.equals(val.getClass()) &&
                this.accept(val.getClass())) {

            this.setSpaces(this.getSpaces() + this.getIndent());
            this.invokeResetIndent();
            buf.append(new MaskedReflectionToStringBuilder(val, this));
            this.setSpaces(this.getSpaces() - this.getIndent());
            this.invokeResetIndent();
        } else {
            super.appendDetail(buf, fieldName, val);
        }
    }

    @Override
    protected boolean accept(Class<?> clazz) {
        return !isJdkClass(clazz) && super.accept(clazz);
    }

    @SneakyThrows
    private Integer getSpaces() {
        return (Integer) FieldUtils.readField(this, SPACES_FIELD, true);
    }

    @SneakyThrows
    private void setSpaces(int value) {
        FieldUtils.writeField(this, SPACES_FIELD, value, true);
    }

    @SneakyThrows
    private Integer getIndent() {
        return (Integer) FieldUtils.readStaticField(super.getClass(), INDENT_FIELD, true);
    }

    @SneakyThrows
    private void invokeResetIndent() {
        MethodUtils.invokeMethod(this, true, RESET_INDENT_METHOD);
    }
}
