package io.github.olegshishkin.mask.example.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import io.github.olegshishkin.mask.builder.MaskedToStringBuilder;

public class MaskConsoleAppender extends ConsoleAppender<ILoggingEvent> {

    @Override
    public void doAppend(ILoggingEvent event) {
        if (event.getArgumentArray() != null) {
            replaceArgs(event);
        }
        super.doAppend(event);
    }

    private static void replaceArgs(ILoggingEvent event) {
        for (int i = 0; i < event.getArgumentArray().length; i++) {
            event.getArgumentArray()[i] = MaskedToStringBuilder.masked(event.getArgumentArray()[i]);
        }
    }
}
