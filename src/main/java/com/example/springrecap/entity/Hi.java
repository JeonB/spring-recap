package com.example.springrecap.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hi {

    private Integer age;
    private String name;

    public Hi(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public void setAge(int age) {
        if (age > 100 || age < 0) {
            System.out.println("100살 초과 또는 0살 미만");
            return;
        }
        this.age = age;
    }

    public void addOneAge(){

        this.age++;
        if (this.age >= 100 || this.age < 0) {
            this.age--;
            System.out.println("100살 초과");
        }
        ;
    }

    public static void main(String[] args) {
        Hi hi = new Hi(20, "JeonB");
        System.out.println(hi.getName());
        System.out.println(hi.getAge());
        System.out.println();
        hi.addOneAge();
        System.out.println(hi.getAge());
        hi.setAge(101);
        hi.setAge(-5);
    }

}
