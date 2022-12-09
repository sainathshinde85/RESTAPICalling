package com.actimize.ir.main;

import com.actimize.ir.model.Data;
import com.google.gson.Gson;

public class ConvertJsonToObject {
    public static void main(String[] args) {
        String json =
                "{"+ "'title': 'Computing and Information systems',"
                        + "'id' : 1,"
                        + "'children' : 'true',"
                        + "'groups' : [{"
                        + "'title' : 'Level one CIS',"
                        + "'id' : 2,"
                        + "'children' : 'true',"
                        + "'groups' : [{"
                        + "'title' : 'Intro To Computing and Internet',"
                        + "'id' : 3,"
                        + "'children': 'false',"
                        + "'groups':[]"
                        + "}]"
                        + "}]"
                        + "}";

        // Now do the magic.
        Data data = new Gson().fromJson(json, Data.class);

        System.out.println("************");
        System.out.println(data);
    }

}
