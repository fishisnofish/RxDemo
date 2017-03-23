package rx.demo.com.rxdemo;

import java.util.Collection;
import java.util.Date;
import java.util.function.Supplier;

/**
 * Created by Administrator on 2017/3/17.
 */

public class Person{
    public enum Sex{
        MALE,FEMALE
    }

    String name;
    Date birthday;
    Sex gender;
    String emailAddress;

    public Person(){

    }

    public static Person createPerson(){
        return new Person();
    }

    public Person(String name, String emailAddress){
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Sex getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST compare(SOURCE sourceCollection, Supplier<DEST> collectionFactory){
        DEST result = collectionFactory.get();

        result.addAll(sourceCollection);
        return result;
    }

    public int compare(Person b){
        return b.name.length();
    }

    public static int compareByAge(Person a, Person b){
        return a.birthday.compareTo(b.birthday);
    }

    public int compareByAgeLocal(Person a, Person b){
        return a.birthday.compareTo(b.birthday);
    }

    public static int compare(String str1, String str2){
        return 1;
    }

}