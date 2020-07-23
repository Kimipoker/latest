package com.kimi.domain;


public class Hero implements Comparable{

    public String name;
    public int age;

    public Hero( String name, int age) {

        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Hero)) {
            return false;
        }
        Hero hero=(Hero)obj;

        return hero.name==this.name && hero.age==this.age;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj == null) {
            return 1;
        }
        Hero hero = (Hero) obj;
        if (this.age>hero.age){
            return 1;
        }else if(this.age==hero.age){
            return 0;
        }else{
            return -1;
        }
    }
}
