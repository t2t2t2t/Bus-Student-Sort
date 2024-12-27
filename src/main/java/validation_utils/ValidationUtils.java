package validation_utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    //Число не отрицательное: номер автобуса, номер группы студ, номер зачетки
    public static class NumberValidator implements Validate<String> {
        @Override
        public boolean isValid (String number){
            try
            {return number != null && Integer.parseInt(number) > 0;
            }
            catch (NumberFormatException e){
                return false;
            }
        }

    }

    public static class MileageValidator implements Validate<Integer> {
        @Override
        public boolean isValid(Integer mileage){
            try{
                return mileage != null && mileage > 0;
            }
            catch(NumberFormatException e){
                return false;
            }
        }
    }
    public static class BooleanValidator implements Validate<Boolean> {
        @Override
        public boolean isValid(Boolean isSomething) {
            return isSomething != null;
        }
    }
    public static class StringValidator implements Validate<String> {
        @Override
        public boolean isValid(String str) {
            return !(str).isEmpty();
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
            try{
                return averageGrade != null && averageGrade >= 2 && averageGrade <= 5;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
    }


    //имя пользователя: минимум 3 символа, только буквы
    public static class UserNameValidator implements Validate<String> {
        @Override
        public boolean isValid(String name) {
            return name != null && name.trim().length() >= 3 && name.matches("[a-zA-Z]+");}

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
