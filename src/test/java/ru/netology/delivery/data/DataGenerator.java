package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {

    }

    public static String generateDate(int dayAgo) {
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        LocalDate currentDate = LocalDate.now();
        LocalDate days = currentDate.plusDays(dayAgo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = days.format(formatter);
        return date;
    }

    public static String generateCity() {
        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        List<String> russianCities = Arrays.asList(
                "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород", "Казань"
        );

        Random random = new Random();
        int index = random.nextInt(russianCities.size());
        return russianCities.get(index);
    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        Faker faker = new Faker(new Locale(locale));
        String phone =  "+79" + faker.number().digits(9);
        return phone;
    }


    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            String city = generateCity();
            String name = generateName("ru");
            String phone = generatePhone("ru");

            return new UserInfo (city, name, phone);
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}

