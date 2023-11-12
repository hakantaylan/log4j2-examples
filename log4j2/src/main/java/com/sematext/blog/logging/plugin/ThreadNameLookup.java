package com.sematext.blog.logging.plugin;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

/**
 * NOT: log4j2.xml'de lookup yaparken plugin adını olduğu gibi değil, hep küçük harf istiyor.
 * $${threadlookup:keyname} şeklinde $${threadLookup:keyname} değil !!!!!!!!!!!!!!!!!!!!
 * don't use camelCase in Plugin names!
 */
@Plugin(name="threadLookup", category=StrLookup.CATEGORY)
public class ThreadNameLookup implements StrLookup {

    @Override
    public String lookup(String key) {
        return Thread.currentThread().getName();
    }

    @Override
    public  String lookup(LogEvent event, String key) {
        // Check event first:
        if (event.getThreadName() != null) {
            return event.getThreadName();
        }
        // Fallback to key if event doesn't define a threadName:
        return this.lookup(key);
    }

}