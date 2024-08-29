package com.medinastr.worldcup.entity;

import jakarta.persistence.*;

@Entity
@Table(name="institution")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="money")
    private int money;

    @Column(name="payment_user")
    private int paymentUser;

    @Column(name="payment_key")
    private int paymentKey;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nation_id")
    private Nation nation;

    public Institution(String email, String password, int money, int paymentUser, int paymentKey) {
        this.email = email;
        this.password = password;
        this.money = money;
        this.paymentUser = paymentUser;
        this.paymentKey = paymentKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPaymentUser() {
        return paymentUser;
    }

    public void setPaymentUser(int paymentUser) {
        this.paymentUser = paymentUser;
    }

    public int getPaymentKey() {
        return paymentKey;
    }

    public void setPaymentKey(int paymentKey) {
        this.paymentKey = paymentKey;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", paymentUser=" + paymentUser +
                ", paymentKey=" + paymentKey +
                ", nation=" + nation +
                '}';
    }
}
