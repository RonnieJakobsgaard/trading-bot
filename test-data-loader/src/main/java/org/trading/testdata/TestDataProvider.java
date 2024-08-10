package org.trading.testdata;

import org.springframework.beans.factory.InitializingBean;

public interface TestDataProvider extends InitializingBean {
    void load() throws Exception;
}
