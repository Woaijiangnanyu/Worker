
package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

public class LambdaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        initData();
    }

    private void initData() {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };


        //fist way
//        forEachWay (javaProgrammers,phpProgrammers);
        //second way
//        forEachWay2(javaProgrammers,phpProgrammers);
        //third way
//        filterWay(phpProgrammers);
        // fourth
//        sortWay(javaProgrammers);
        // fivth way
        mapWay(javaProgrammers,phpProgrammers);
    }

    /**
     * 现在我们使用forEach方法来迭代输出上述列表:
     */
    private void forEachWay(List<Person> javaProgrammers,List<Person> phpProgrammers) {
        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p)->System.out.printf("%s %s; ",p.getFirstName(),p.getLastName()));
        phpProgrammers.forEach((p)->System.out.printf("%s %s; ",p.getFirstName(),p.getLastName()));
    }

    /**
     * 使用forEach方法,增加程序员的工资5%:
     */
    private void forEachWay2(List<Person> javaProgrammers,List<Person> phpProgrammers){
        System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e-> e.setSalary(e.getSalary() / 100 * 5  + e.getSalary());
        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);
    }

    /**
     * 过滤器filter() ,让我们显示月薪超过1400美元的PHP程序员
     */
    private void filterWay(List<Person> phpProgrammers){
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream()
                .filter((p) -> (p.getSalary() > 1400))
                .forEach((p) -> Log.i("=== ", p.getFirstName() +  p.getLastName()));
    }

    /**
     * 根据 salary 排序 Java programmers
     */
    private void sortWay(List<Person> javaProgrammers){
        System.out.printf("根据 salary 排序 Java programmers");
        List<Person> sortedJavaProgrammers = javaProgrammers.stream()
                .sorted((p,p2) -> (p.getSalary()-p2.getSalary()))
                .collect(Collectors.toList());
        sortedJavaProgrammers.forEach((p)->Log.i("===",p.getFirstName() + p.getLastName()));

    }

    /**
     * map用法
     * @param javaProgrammers
     * @param phpProgrammers
     */

    private void mapWay(List<Person> javaProgrammers,List<Person> phpProgrammers){
        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String phpDevelopers = phpProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(joining(" ; ")); // 在进一步的操作中可以作为标记(token)

        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> javaDevFirstName = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(toSet());

        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Person::getLastName)
                .collect(toCollection(TreeSet::new));
    }
}
