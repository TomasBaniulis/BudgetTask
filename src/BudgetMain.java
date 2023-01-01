import Data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BudgetMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BudgetMain main = new BudgetMain();
        Budget budget = new Budget();

        String action;

        do {
            main.menu();
            action=scanner.nextLine();

            main.userAction(scanner,action, budget);

        } while (!action.equals("5"));

    }

    private void menu(){
        System.out.println("""
                1. Enter income
                2. Enter outcome
                3. Get income by category and date
                4. Get outcome by category and date
                5. Exit
                """);
    }

    private Person getPerson (Scanner scanner) {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter surname");
        String surname = scanner.nextLine();

        return new Person(name,surname);
    }

    private BigDecimal getCorrectNumber (Scanner scanner) {
        while (true){
            try {
                System.out.println("Enter sum:");
                String line = scanner.nextLine();

                return new BigDecimal(line);
            }catch (NumberFormatException e) {
                System.out.println("Wrong entry");
            }
        }
    }
    private void userAction (Scanner scanner, String action, Budget budget){
        switch (action){
            case "1" -> createIncomeEntry(scanner, budget);
            case "2" -> crateOutcomeEntry(scanner, budget);
            case "3" -> getIncomeEntry(scanner,budget);
            case "4" -> getOutcomeEntry(scanner,budget);
            case "5" -> System.out.println("Exit");
            default -> System.out.println("the is no such action !!!!!");
        }
    }

    private void createIncomeEntry (Scanner scanner, Budget budget){

        Random random = new Random();
        int id = random.nextInt();

        BigDecimal incomeSum = getCorrectNumber(scanner);

        Person person = getPerson(scanner);

        System.out.printf("Enter Income category: " + Arrays.toString(IncomeCategory.values()));
        String category = scanner.nextLine();

        System.out.println("Bank transfer? Y/N");
        String bankTransfer = scanner.nextLine();
        boolean isBankTransfer = "Y".equals(bankTransfer.toUpperCase());
        if (isBankTransfer){
            IncomeType type = IncomeType.BANK_TRANSFER;
        } IncomeType type = IncomeType.CASH;

        System.out.println("Enter comment:");
        String comment = scanner.nextLine();

        IncomeEntry income = new IncomeEntry (
                id,
                incomeSum,
                LocalDate.now(),
                person,
                TransferStatus.IN_PROGRESS,
                comment,
                IncomeCategory.valueOf(category.toUpperCase()),
                type);

        budget.addEntry(income);
    }

    private void crateOutcomeEntry (Scanner scanner, Budget budget){
        Random random = new Random();
        int id = random.nextInt();

        BigDecimal outcomeSum = getCorrectNumber(scanner);

        Person person = getPerson(scanner);

        System.out.printf("Enter outcome category: " + Arrays.toString(OutcomeCategory.values()));
        String category = scanner.nextLine();

        System.out.printf("Enter outcome type: " + Arrays.toString(OutcomeType.values()));
        String type = scanner.nextLine();

        System.out.println("Enter comment:");
        String comment = scanner.nextLine();

        OutcomeEntry outcome = new OutcomeEntry(
                id,
                outcomeSum,
                LocalDate.now(),person,
                TransferStatus.IN_PROGRESS,
                comment,
                OutcomeCategory.valueOf(category.toUpperCase()),
                OutcomeType.valueOf(type.toUpperCase()));

        budget.addEntry(outcome);
    }

    private void getIncomeEntry (Scanner scanner, Budget budget){
        System.out.printf("Enter Income category: " + Arrays.toString(IncomeCategory.values()));
        String category = scanner.nextLine();
        IncomeCategory incomeCategory = Data.IncomeCategory.valueOf(category.toUpperCase());

        System.out.printf("Enter date yyyy.MM.dd : ");
        String date = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        List <IncomeEntry> income = budget.getIncomeEntry(incomeCategory, localDate);
        for (IncomeEntry entry: income){
            System.out.println(entry);
        }

    }

    private void getOutcomeEntry (Scanner scanner, Budget budget){
        System.out.printf("Enter outcome category: " + Arrays.toString(OutcomeCategory.values()));
        String category = scanner.nextLine();
        OutcomeCategory outcomeCategory = Data.OutcomeCategory.valueOf(category);

        System.out.printf("Enter date yyyy.MM.dd : ");
        String date = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        List <OutcomeEntry> outcome = budget.getOutcomeEntry(outcomeCategory, localDate);
        for (OutcomeEntry entry : outcome) {
            System.out.println(entry);
        }
    }

}
