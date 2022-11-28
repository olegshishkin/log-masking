package io.github.olegshishkin.mask.builder;

import static io.github.olegshishkin.mask.builder.util.MaskedToStringUtils.isJdkClass;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;

public class MaskedRecursiveToStringStyle extends RecursiveToStringStyle {

    public MaskedRecursiveToStringStyle() {
        this.setUseClassName(false);
        this.setUseIdentityHashCode(false);
        this.setFieldNameValueSeparator(": ");
        this.setFieldSeparator(", ");
    }

    @Override
    public void appendDetail(StringBuffer buf, String fieldName, Object val) {
        if (!ClassUtils.isPrimitiveWrapper(val.getClass()) &&
                !String.class.equals(val.getClass()) &&
                this.accept(val.getClass())) {
            buf.append(new MaskedReflectionToStringBuilder(val, this));
        } else {
            super.appendDetail(buf, fieldName, val);
        }
    }

    @Override
    protected boolean accept(Class<?> clazz) {
        return !isJdkClass(clazz) && super.accept(clazz);
    }
}
