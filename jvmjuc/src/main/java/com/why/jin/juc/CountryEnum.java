package com.why.jin.juc;

import lombok.Getter;

public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THERR(3,"燕"),FOUR(4,"楚"),FIVE(5,"魏"),SIX(6,"韩");

    @Getter private Integer retCode;
    @Getter private String retMessage;

    CountryEnum(Integer retCode,String retMessage){
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
      CountryEnum[]  myArray = CountryEnum.values();
        for (CountryEnum element:myArray) {
            if (index == element.retCode){
                return element;
            }
        }
        return null;
    }
}

/*mysql dbName = CountryEnum

table
one
ID userName age birth userEmail
1     齐     100*/
