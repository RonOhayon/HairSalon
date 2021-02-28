package com.example.hair_salon;

public class UserHairdresser extends User {
    private String hairSalonName;
    private HairSalon hairSalon;

    public UserHairdresser(String Name, String email, String password, String phoneNumber, String  hairSalonName,HairSalon hairSalon ) {
        super(Name, email, password, phoneNumber);
        this. hairSalonName =  hairSalonName;


    }

    public String getHairSalon() {
        return  hairSalonName;
    }

    public void setHairSalon(String hairSalon) {
        this. hairSalonName = hairSalon;
    }

    public void setHairSalon(HairSalon hairSalon) {
        this.hairSalon = hairSalon;
    }

    
}
