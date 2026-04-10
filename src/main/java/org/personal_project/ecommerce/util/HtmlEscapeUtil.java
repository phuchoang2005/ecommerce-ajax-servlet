package org.personal_project.ecommerce.util;
public class HtmlEscapeUtil {

    public static String escape(String input) {
        if (input == null) return null;

        StringBuilder out = new StringBuilder(Math.max(16, input.length()));
        for (char c : input.toCharArray()) {
            switch (c) {
                case '&': out.append("&amp;"); break;
                case '<': out.append("&lt;"); break;
                case '>': out.append("&gt;"); break;
                case '"': out.append("&quot;"); break;
                case '\'': out.append("&#x27;"); break;
                case '/': out.append("&#x2F;"); break;
                default: out.append(c);
            }
        }
        return out.toString();
    }
}