package domain.model;

import java.util.ArrayList;

public class Grocery {
    private String familyMember,item;
    private int amount;

    private Grocery(String familyMember,String item,int amount){
        setFamilyMember(familyMember);
        setItem(item);
        setAmount(amount);
    }

    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
