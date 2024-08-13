package com.suanfa.huaweiPrivate;

import java.util.ArrayList;
import java.util.List;

public class transAndresove {
    //0 1 4 5
    //a 1 2

    List<CmdInfo> methodName(int[][] ports, List<CmdInfo> list){
        return new ArrayList<>();
    }
}

class CmdInfo{
    int portR;
    int portG;
    String method;

    @Override
    public String toString() {
        return "CmdInfo{" +
                "portR=" + portR +
                ", portG=" + portG +
                ", method='" + method + '\'' +
                '}';
    }
}
