package kr.co.test.cli.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ResourceExample {
    @Autowired private ResourceLoader resourceLoader;

    public void print() {
        System.out.println(resourceLoader);
    }
}
