package com.cmw.demo;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface FundHandleServer extends Predicate<String>, Supplier<String> {
    Object todo(String ss) throws Exception;
}
