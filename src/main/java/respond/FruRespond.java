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
import java.util.Collections;
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

    public <T> List<T> getFrus(Class clazz){
        if(frus == null){
            return Collections.emptyList();
        }
        List<T> rs = new ArrayList<T>(frus.size());
        for(Fru fru : frus){
            if(fru.getClass().equals(clazz)){
                rs.add((T)fru);
            }
        }
        return rs;
    }
}
