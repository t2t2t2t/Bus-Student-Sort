package ValidationUtils;

import java.util.regex.Pattern;

public class ValidationUtils {
    //Число не отрицательное: номер автобуса, пробег авт, номер группы студ, номер зачетки,
    public static class NumberValidator implements Validate<Integer> {
        @Override
        public boolean isValid(Integer number) {
            return number != null && number > 0;
        }
    }

    // модель автобуса соответствует списку моделей
    public static class BusModelValidator implements Validate<String> {
        @Override
        public boolean isValid(String model) {
            try {
                if (model != null) {
                    BusModel.valueOf(model.toUpperCase().replaceAll(" ", "_"));
                    return true;
                } else {
                    return false;
                }
            }
            catch(IllegalArgumentException e) {
                return false;
            }

        }
    }

    //средний балл студента должен быть от 2 до 5
    public static class AverageGradeValidator implements Validate<Double> {
        @Override
        public boolean isValid(Double averageGrade) {
            return averageGrade != null && averageGrade >= 2 && averageGrade <= 5;
        }
    }


    //имя пользователя: минимум 3 символа, только буквы
    public static class UserNameValidator implements Validate<String> {
        @Override
        public boolean isValid(String name) {
            return name != null && name.length() >= 3 && name.matches("[a-zA-Zа-яА-Я]+");
        }
    }

    // пароль пользователя минимум 4 символа
    public static class UserPasswordValidator implements Validate<String> {
        @Override
        public boolean isValid(String password) {
            return password != null && password.length() >= 4;
        }
    }

    // почта пользователя соответствует стандарту e-mail
    public static class UserEmailValidator implements Validate<String> {
        @Override
        public boolean isValid(String email) {
            String emailRegex = "^[a-zA-Z\\d_+&*-]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$";
            return email != null && Pattern.matches(emailRegex, email);
        }
    }



}
