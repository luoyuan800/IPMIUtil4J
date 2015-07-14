/*
 * FruRespond.java
 * Date: 7/10/2015
 * Time: 10:42 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package respond;

import model.Fru;

import java.util.ArrayList;
import java.util.List;

public class FruRespond implements IPMIRespond{
    private List<Fru> frus;
    private boolean isSuccess;
    public void addFru(Fru fru){
        if(frus == null){
            frus = new ArrayList<Fru>();
        }
        frus.add(fru);
    }
    @Override
    public boolean hasResponsed() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
