import java.util.*;

public class PhoneBook {
    private static Map<String, Set<String>> phoneBook = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Телефонная книга:\n");
            System.out.println("1. Просмотр записей");
            System.out.println("2. Добавить");
            System.out.println("3. Удалить");
            System.out.println("4. Редактирование");
            System.out.println("5. Выход");
            System.out.print("\nЧтоб сделать выбор нажмите необходимую цифру от 1 до 5: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    firstNum();
                    break;
                case 2:
                    addNew();
                    break;
                case 3:
                    deleteNumber();
                    break;
                case 4:
                    editNumber();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("\nНекорректный выбор! Попробуйте снова.\n");
            }
        }
        System.out.println("\nРабота приложения завершена.\n");
    }

    private static void firstNum(){
        if (phoneBook.isEmpty()) {
            System.out.println("\nТелефонная книга пуста.\n");
        } else {
            List<Map.Entry<String, Set<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
            sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
            System.out.println("Записи в телефонной книге отсортированы по убыванию числа телефонов:\n");
            for (Map.Entry<String, Set<String>> entry : sortedEntries) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        waitEnter();
    }

    private static void addNew() {
        System.out.print("ДОБАВЛЕНИЕ ЗАПИСИ\n\n");
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();
        phoneBook.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
        System.out.println("\nЗапись добавлена: " + name + ": " + phoneNumber);
        waitEnter();
    }
    private static void deleteNumber() {
        System.out.print("УДАЛЕНИЕ ЗАПИСИ\n\n");
        System.out.print("Введите имя для удаления: ");
        String name = scanner.nextLine();
        if (phoneBook.containsKey(name)) {
            Set<String> phoneNumbers = phoneBook.remove(name);
            System.out.println("Запись удалена: " + name + ": " + phoneNumbers);
        } else {
            System.out.println("Записи с таким именем нет");
        }
        waitEnter();
    }

    private static void editNumber() {
        System.out.print("РЕДАКТИРОВАНИЕ ЗАПИСИ\n\n");
        System.out.print("Введите имя для редактирования: ");
        String name = scanner.nextLine();
        if (phoneBook.containsKey(name)) {
            System.out.print("Введите новый номер телефона: ");
            String newPhoneNumber = scanner.nextLine();
            phoneBook.get(name).add(newPhoneNumber);
            System.out.println("Запись отредактирована: " + name + ": " + phoneBook.get(name));
        } else {
            System.out.println("Записи с таким именем нет");
        }
        waitEnter();
    }

    private static void waitEnter() {
        System.out.print("\nНажмите ENTER для продолжения");
        scanner.nextLine();
    }

}