package domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public class User {

    private String firstName, lastName;

    private String userid;

    private String password, email, phoneNumber;

    private Role role;
    private LocalDate dateOfBirth;

    private ArrayList<User> friends;

    private Status status;


    public User(String userid, String firstName, String lastName, String password, String email, String phoneNumber, LocalDate dateOfBirth){
        setUserid(userid);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        friends = new ArrayList<>();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setPasswordHashed(String password) {
        if(password == null || password.trim().isEmpty()) throw new DomainException();
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            //create MessageDigest
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            //reset
            crypt.reset();
            //update
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            //digest
            byte[] digest = crypt.digest();
            //convert to String
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            //return hashed password
            return digestAsBigInteger.toString(16);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new DomainException(e.getMessage());
        }
    }

    public boolean isCorrectPassword(String password) {
        if(password == null || password.trim().isEmpty()) throw new DomainException();
        return this.password.equals(hashPassword(password));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
