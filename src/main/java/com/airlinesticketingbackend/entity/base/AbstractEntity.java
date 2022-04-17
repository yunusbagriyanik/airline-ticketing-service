package com.airlinesticketingbackend.entity.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Transient;
import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    protected final Logger logger = LogManager.getLogger(this.getClass());
}
