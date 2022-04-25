package com.zhu.api_course.util;

import java.util.List;

public interface QueryStrategy<F, R> {

   List<R> doSomething(String column, F fieldKey);
}
