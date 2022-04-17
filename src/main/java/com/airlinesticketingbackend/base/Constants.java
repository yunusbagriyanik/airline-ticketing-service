package com.airlinesticketingbackend.base;

public final class Constants {
    public final static Integer TRUE_FLAG = 1;
    public final static Integer FALSE_FLAG = 0;
    public final static Long LONG_FALSE_FLAG = 0L;
    public final static Long LONG_TRUE_FLAG = 1L;
    public final static String[] requiredSwaggerPaths = new String[]{"/v2/api-docs", "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger.json",
            "/swagger-ui/index.html"};
}
