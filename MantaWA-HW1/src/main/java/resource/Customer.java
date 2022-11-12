package resource;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;


public class Customer {
    private final UUID customerId;
    private final String name;
    private final String surname;
    private Date birthDate;
    private String documentNumber;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    private Date registrationDate;
    private String hotelUser;       //used in case of having a customer registered by a user


    public Customer(UUID customerId, String name, String surname){
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
    }

    public Customer(UUID customerId, String name, String surname, String email){
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.registrationDate = Date.valueOf(java.time.LocalDate.now());
    }

    public Customer(UUID customerId, String name, String surname, Date birthDate, String email, String documentNumber){
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.registrationDate = Date.valueOf(java.time.LocalDate.now());
        this.documentNumber = documentNumber;
        this.registrationDate = java.sql.Date.valueOf(LocalDate.now());
    }

    public Customer(UUID customerId, String name, String surname, Date birthDate, String phoneNumber, String email, String password){
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.registrationDate = Date.valueOf(java.time.LocalDate.now());
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.registrationDate = java.sql.Date.valueOf(LocalDate.now());
    }

    public Customer(UUID customerId, String name, String surname, Date birthDate, String documentNumber, String phoneNumber, String email, String password, String address, String hotelUser){
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.registrationDate = Date.valueOf(java.time.LocalDate.now());
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.documentNumber = documentNumber;
        this.address = address;
        this.hotelUser = hotelUser;
        this.password = password;
    }
    public Customer(UUID customerId, String name, String surname, Date birthDate, String documentNumber, String phoneNumber, String email, String password, String address, Date registrationDate, String hotelUser){
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.documentNumber = documentNumber;
        this.address = address;
        this.hotelUser = hotelUser;
        this.password = password;
    }


    public UUID getCustomerId(){ return customerId;}
    public String getEmail(){ return email;}
    public String getName(){return name;}
    public String getSurname(){return surname;}
    public Date getRegistrationDate(){return registrationDate;}
    public Date getBirthDate(){return birthDate;}
    public String getDocumentNumber(){return documentNumber;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getPassword(){return password;}
    public String getAddress(){return address;}
    public String getHotelUser(){return hotelUser;}
}
