package com.zhu.api_comment.util;

import java.util.List;

public interface QueryStrategy<F, R> {

   List<R> doSomething(String column, F fieldKey);
}
