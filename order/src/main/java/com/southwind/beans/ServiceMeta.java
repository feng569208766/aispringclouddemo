package com.southwind.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("serviceMeta")
public class ServiceMeta {

    private String beanId;
    private String method;
    private String httpRequestMethod;

}
