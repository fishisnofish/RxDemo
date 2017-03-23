package rx.demo.com.rxdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ScrollingActivity extends AppCompatActivity {

    //tag
    private static final String TAG = "Scorll";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        testRxjava();
    }
    /**
     * by yuyou
     * */
    private void testRxjava(){
        Log.d(TAG, "testRxjava....");
        Flowable.just("Hello world").subscribe();
        Flowable.just("Hello world").subscribe(System.out::println);

        //1. lambda
        List<String> list = Arrays.asList("hello", "xiaoming", "xiaohua");
        Collections.sort(list, (a, b) -> a.compareTo(b));
        System.out.println(list);

        //2.FunctionalInterface
        Converter<String, Integer> conn = (from) -> Integer.valueOf(from);
        Integer in = conn.con("1234");
        System.out.println(in);

        //3. construct
        conn = Integer::new;
        System.out.println(conn);

        //4. static method
        conn = Integer::valueOf;
        System.out.println(conn.con("123"));

        //5. factory
        PersonFactory<Person> personFactory = Person::new;

        Person p = personFactory.create("tom", "man");
        System.out.println(p.toString());

        //6. factory String
        StringFactory<String> stringFactory = String::new;
        stringFactory.create();

        List<String> list1 = Arrays.asList("11", "01", "02");
        Collections.sort(list1, (a, b) -> a.compareTo(b));
        Person o1 = new Person();
        Person o2 = new Person();

        StringCompare<String> stringCompare = Person::createPerson;
        stringCompare.create();

        String help = "";
        Comparator<String> com = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };
        List list2 = new ArrayList();
        String[] arr = {"", "", ""};
        Arrays.sort(arr, String::compareTo);

        Person person = personFactory.create("jack", "man");
        Person[] persons = new Person[10];
        List<Person> personList = new ArrayList<Person>();
        Arrays.sort(persons, Person::compareByAge);
        Collections.sort(personList, Person::compareByAge);
        Collections.sort(personList, person::compareByAgeLocal);
        Arrays.sort(persons, (a, b) -> a.compare(b));

        //8 Founction
        Function<Integer, String> function = (x) -> "abc" + x;
        Function<String, String> function2 = (x) -> "abc" + x;
        function.apply(20);
        function.andThen(function2).apply(30);


        //9 supplier
        Supplier<String> supplier = () -> "";
        System.out.println(supplier.get());

        //10 consumer
        Consumer<String> consumer1 = (x) -> System.out.print(x);
        Consumer<String> consumer2 = (x) -> {
            System.out.println(" after consumer 1");
        };
        consumer1.andThen(consumer2).accept("test consumer1");

        //11 predicate
        Predicate<String> predicate = (x) -> x.length() > 0;
        System.out.println(predicate.test("String"));

        //12
        //使用lambda表达式
        Set<Person> personSet = person.compare(personList,()-> new HashSet<Person>());

        //使用方法引用
        //引用的是构造方法
        Set<Person> personSet2 = person.compare(personList, HashSet::new);

        //13 stream
        Stream.generate(Math::random).limit(10).forEach((Double d) -> System.out.println(d));
        Stream.generate(Math::random).limit(10).forEach((d) -> System.out.println(d));
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    interface StringCompare<S extends String>{
        Person create();
    }

    interface StringFactory<S extends String>{
        void create();
    }

    interface PersonFactory<P extends Person>{
        P create(String name, String sex);
    }



    @FunctionalInterface
    interface Converter<F, T>{
        T con(F from);

    }

    /**
     * by yuyou
     * */
    private void createObserver(){
        //create observer
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "observer onSubcribe calling: " + d.toString());
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "observer onNext calling: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "observer onError calling: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "observer onComplete calling: " );
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "observer onSubscribe calling: " + s.toString() );
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "observer onNext calling: " + s);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "observer onComplete calling: " + t.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "observer onComplete calling: " );
            }
        };


    }

}
