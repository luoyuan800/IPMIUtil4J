/*
 * AuthenticationType.java
 * Date: 7/15/2015
 * Time: 8:58 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package param;

public enum AuthenticationType {
    None(0), MD2(1), MD5(2), StraightPassword(4), OEM(5);

    private int index;

    private AuthenticationType(int index) {
        this.index = index;
    }
    public int getIndex(){
        return index;
    }
}
