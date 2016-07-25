package com.taskadapter.redmineapi.bean;

import java.util.Date;

/**
 * An empty value to allow unset a field. 
 * @author thomas
 *
 */
public class EmptyValue {

    /** An empty ID. */
    public static final Integer EMPTY_ID = -999;
    
    /** An empty Date. */
    public static final Date EMPTY_DATE = new Date(0);
    
    /** An empty Float. */
    public static final Float EMPTY_FLOAT = Float.MIN_VALUE;
    
    /** An empty version. */
    public static final Version EMPTY_VERSION = new Version(EMPTY_ID);
    
    /** An empty category. */
    public static final IssueCategory EMPTY_CATEGORY = new IssueCategory(EMPTY_ID);
}
