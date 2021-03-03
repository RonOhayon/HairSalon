package com.example.hair_salon;

public class UserHairdresser extends User {
    private String hairSalonName;
    private HairSalon hairSalon;

    public UserHairdresser(String Name, String email, String password, String phoneNumber, String hairSalonName, HairSalon hairSalon) {
        super(Name, email, password, phoneNumber);
        this.hairSalonName = hairSalonName;
        this.hairSalon = hairSalon;
    }

    public String getHairSalonName() {
        return hairSalonName;
    }

    public HairSalon getHairSalon() {
        return hairSalon;
    }

    public void setHairSalonName(String hairSalonName) {
        this.hairSalonName = hairSalonName;
    }

    public void setHairSalon(HairSalon hairSalon) {
        this.hairSalon = hairSalon;
    }
}
