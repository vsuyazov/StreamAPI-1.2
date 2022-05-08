import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Kelly", "Gale", "Mel", "Bobbie", "Sam", "Mike");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> person = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            person.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(names.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }

        int underageCount = (int) person.stream()
                .filter(Person -> Person.getAge() < 18)
                .count();
        System.out.println(underageCount);


        List<String> conscript = person.stream()
                .filter(Person -> Person.getSex() == Sex.MAN)
                .filter(Person -> Person.getAge() > 18 && Person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscript);

        List<Person> workers = person.stream()
                .filter(Person -> Person.getAge() > 18)
                .filter(Person -> (Person.getSex() == Sex.WOMAN && Person.getAge() < 60) || (Person.getSex() == Sex.MAN && Person.getAge() < 65))
                .filter(Person -> Person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workers);
    }
}