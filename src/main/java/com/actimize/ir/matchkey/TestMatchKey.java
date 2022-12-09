package com.actimize.ir.matchkey;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestMatchKey {
    public static void main(String[] args) {
        List<Party> partyList = getPartyList1();
        System.out.println(partyList);
        String irMatchKey = getCalculatedIRMatchKey(partyList);
        System.out.println("IRMatchKey :: "+irMatchKey);

        //printNameAndLength(employeeList);
    }

    private static String getCalculatedIRMatchKey(List<Party> partyList) {
        String irMatchKey=null;
        System.out.println("*******");
        Comparator<Party> firstNameComparator = Comparator.comparing(e->e.getFirstName().length());
        Comparator<Party> lastNameComparator = Comparator.comparing(e->e.getLastName().length());
        //employeeList.stream().filter(e->e.getFirstName()!=null).forEach(System.out::println);
        //employeeList.stream().filter(e->e.getFirstName()!=null && e.getLastName()!=null).forEach(System.out::println);
        Party e1 = partyList.stream().filter(e->e.getFirstName()!=null).max(firstNameComparator).get();
        System.out.println("Maximum character in FirstName employee is :" +e1.getFirstName());
        Party e2 = partyList.stream().filter(e->e.getLastName()!=null).max(lastNameComparator).get();
        System.out.println("Maximum character in lastName employee is :" +e2.getLastName());
        irMatchKey = e1.getFirstName().substring(0,4) + e2.getLastName().substring(0,4) + "E00108";
        return irMatchKey;
    }

    private static void printNameAndLength(List<Party> partyList){
     Map<String,Integer> map= partyList.stream().filter(e->e.getFirstName()!=null).collect(Collectors.toMap(Party::getFirstName, e->e.getFirstName().length()));
        System.out.println(map);
    }

    private static List<Party> getEmployees(){
        List<Party> partyList = new ArrayList<Party>();
        partyList.add(new Party(1,"Sainath","Shinde",38,5000));
        partyList.add(new Party(2,"Vijay","Pawar",38,5000));
        partyList.add(new Party(6,"Bhagyashree",null,38,5000));
        partyList.add(new Party(5,null,"Pande",38,5000));
        partyList.add(new Party(3,"amit","Kulkarni",38,5000));
        partyList.add(new Party(4,"J","Smith",38,5000));
        partyList.add(new Party(8,"Piu","",38,5000));
        partyList.add(new Party(7,null,"sunderkumarswami",38,5000));


        return partyList;
    }
    private static List<Party> getPartyList1(){
        List<Party> partyList = new ArrayList<Party>();
        partyList.add(new Party(1,"John","Peterson",38,5000));
        partyList.add(new Party(2,"Jonathan","Peters",38,5000));
        partyList.add(new Party(6,"John","Pet",38,5000));
        partyList.add(new Party(5,"J","Peterson",38,5000));
        partyList.add(new Party(3,"J","Pet",38,5000));
        partyList.add(new Party(8,null,null,38,5000));
        partyList.add(new Party(4,"J","Peterson",38,5000));
        partyList.add(new Party(8,null,"Peter",38,5000));
        partyList.add(new Party(7,"Joh",null,38,5000));

        return partyList;
    }
}
