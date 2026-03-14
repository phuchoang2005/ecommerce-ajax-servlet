package org.personal_project.ecommerce.util;

import java.util.List;
import java.util.ArrayList;

public class FilterChainTracerUtil{

    private static final ThreadLocal<List<String>> chain = ThreadLocal.withInitial(ArrayList::new);

    public static void add(String filterName) {
        chain.get().add(filterName);
    }

    public static String getChain() {
        return String.join(" → ", chain.get());
    }

    public static void clear() {
        chain.remove();
    }
}
