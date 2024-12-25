import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class RandomEntityGenerator {

    private static final Random random = new Random();

    public static Object[] fillEntitiesRandomly(Class<?> entityClass, int numberOfEntities) {
        Object[] entities = new Object[numberOfEntities];

        for (int i = 0; i < numberOfEntities; i++) {
            try {
                Object entity = entityClass.getDeclaredConstructor().newInstance();
                fillEntityRandomly(entity);
                entities[i] = entity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return entities;
    }

    public static void fillEntityRandomly(Object entity) {
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = switch (field.getType().getSimpleName()) {
                    case "String" -> generateRandomString(field.getName());
                    case "int", "Integer" -> random.nextInt(101);
                    case "double", "Double" -> random.nextDouble() * 100;
                    case "boolean", "Boolean" -> random.nextBoolean();
                    default -> null;
                };
                field.set(entity, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    private static String generateRandomString(String fieldName) {

        Map<String, Supplier<String>> stringGenerators = Map.of(
                "name", RandomString::getRandomName,
                "model", RandomString::getRandomCarModel,
                "email", RandomString::getRandomEmail
        );

        return stringGenerators.entrySet()
                .stream()
                .filter(entry -> fieldName.toLowerCase().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(() -> generateRandomLetters(8))  // Если не найдено соответствие, генерируем случайные буквы
                .get();
    }

    private static String generateRandomLetters(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));  // Генерация случайного символа
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public class RandomString {

        public static String getRandomValueFromEnum(Class<? extends Enum<?>> enumClass) {
            Enum<?>[] enumValues = enumClass.getEnumConstants();
            return enumValues[random.nextInt(enumValues.length)].name();
        }


        public static String getRandomName() {
            return getRandomValueFromEnum(NameEnum.class);
        }

        public static String getRandomCarModel() {
            return getRandomValueFromEnum(CarModelEnum.class);
        }

        public static String getRandomEmail() {
            return generateRandomLetters(5) + "@" + getRandomValueFromEnum(EmailDomainEnum.class)+ ".com";
        }

        private static String generateRandomLetters(int length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                char randomChar = (char) ('a' + random.nextInt(26));
                sb.append(randomChar);
            }
            return sb.toString();
        }

        public enum NameEnum {
            ALEX, BOB, CHARLIE, DAVID, EMILY;
        }

        public enum CarModelEnum {
            TOYOTA, FORD, HONDA, BMW, AUDI;
        }

        public enum EmailDomainEnum {
            gmail, mail, yahoo, outlook;
        }
    }

}
