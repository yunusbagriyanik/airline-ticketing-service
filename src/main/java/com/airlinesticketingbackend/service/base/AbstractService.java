package com.airlinesticketingbackend.service.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractService {
    protected final Logger logger = LogManager.getLogger(this.getClass());
}
