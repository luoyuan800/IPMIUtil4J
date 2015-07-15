/*
 * PrivilegeLevel.java
 * Date: 7/15/2015
 * Time: 9:02 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/
package param;

public enum PrivilegeLevel {
    CallBack(1), User(2), Operator(3), Administrator(4), OEM(5);
    private int level;

    private PrivilegeLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
