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

        } while (!action.equals("0"));

    }

    private void menu(){
        System.out.println("""
                1. Enter income
                2. Enter outcome
                3. Get income by category and date
                4. Get outcome by category and date
                5. Get balance sum
                6. Get list of entries
                7. Delete entry 
                8. Modify entry
                0. Exit
                """);
    }

    private void userAction (Scanner scanner, String action, Budget budget){
        switch (action){
            case "1" -> createIncomeEntry(scanner, budget);
            case "2" -> crateOutcomeEntry(scanner, budget);
            case "3" -> getIncomeEntry(scanner,budget);
            case "4" -> getOutcomeEntry(scanner,budget);
            case "5" -> System.out.println("Your balance: " + budget.balanceSum);
            case "6" -> budget.printEntryList();
            case "7" -> budget.deleteEntry();
            case "8" -> System.out.println("modify entry");
            case "0" -> System.out.println("Exit");
            default -> System.out.println("the is no such action !!!!!");
        }
    }
    private void createIncomeEntry (Scanner scanner, Budget budget){

        Random random = new Random();
        int id = random.nextInt(0, 200);

        BigDecimal incomeSum = budget.getCorrectNumber(scanner);

        Person person = budget.getPerson(scanner);

        System.out.println("Enter comment:");
        String comment = scanner.nextLine();

        IncomeEntry income = new IncomeEntry (
                id,
                incomeSum,
                LocalDate.now(),
                person,
                TransferStatus.IN_PROGRESS,
                comment,
                IncomeCategory.valueOf(budget.getIncomeCategory(scanner)),
                budget.getIncomeType(scanner));

        budget.addEntry(income);

        budget.getBalanceSum();
        //System.out.println("Balance update: " + budget.balanceSum);
    }
    private void crateOutcomeEntry (Scanner scanner, Budget budget){
        Random random = new Random();
        int id = random.nextInt(0,200);

        BigDecimal outcomeSum = budget.getCorrectNumber(scanner);

        BigDecimal negativeSum = outcomeSum.negate();

        Person person = budget.getPerson(scanner);

        System.out.println("Enter comment:");
        String comment = scanner.nextLine();

        OutcomeEntry outcome = new OutcomeEntry(
                id,
                negativeSum,
                LocalDate.now(),person,
                TransferStatus.IN_PROGRESS,
                comment,
                budget.getOutcomeCategory(scanner),
                budget.getOutcomeType(scanner));

        budget.addEntry(outcome);

        System.out.println("Budget update: " + budget.balanceSum);
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
        OutcomeCategory outcomeCategory = Data.OutcomeCategory.valueOf(category.toUpperCase());

        System.out.printf("Enter date yyyy.MM.dd : ");
        String date = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        List <OutcomeEntry> outcome = budget.getOutcomeEntry(outcomeCategory, localDate);
        for (OutcomeEntry entry : outcome) {
            System.out.println(entry);
        }
    }

    private void modifyEntry (Scanner scanner, Budget budget) {
        System.out.println("Enter id to modify entry");
        String id = scanner.nextLine();
       // budget.modifyEntry(id, main, scanner);
    }

}
