package org.personal_project.ecommerce.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterDebugUtil {

    private static final ThreadLocal<Integer> depth = ThreadLocal.withInitial(() -> 0);
    private static final Logger logger = LoggerFactory.getLogger(FilterDebugUtil.class);

    public static void enter(String name) {
        int d = depth.get();
        log(d, ">>> " + name);
        depth.set(d + 1);
    }

    public static void exit(String name) {
        int d = depth.get() - 1;
        depth.set(d);
        log(d, "<<< " + name);
    }

    private static void log(int depth, String msg) {
        String indent = " ".repeat(depth * 4);
        logger.debug(indent + msg);
    }
}