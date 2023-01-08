import Data.*;
import com.sun.source.tree.WhileLoopTree;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Budget {

    private List <Entry> entries = new ArrayList<>();
    private List <IncomeEntry> incomeEntries = new ArrayList<>();
    private List <OutcomeEntry> outcomeEntries = new ArrayList<>();
     BigDecimal balanceSum;


    void addEntry ( Entry entry) {
        entries.add(entry);
    }

    List <IncomeEntry> getIncomeEntry (IncomeCategory incomeCategory, LocalDate date){
        if (entries == null){
            return null;
        }
        for (Entry entry: entries){
            if (entry instanceof IncomeEntry incomeEntry){
                if (incomeEntry.getIncomeCategory().equals(incomeCategory) && incomeEntry.getDate().isEqual(date)){
                    incomeEntries.add(incomeEntry);
                }
            }
        } return  incomeEntries;
    }

    List <OutcomeEntry> getOutcomeEntry (OutcomeCategory outcomeCategory, LocalDate date ){
        if (entries == null){
            return null;
        }
        for (Entry entry: entries){
            if (entry instanceof OutcomeEntry outcomeEntry){
                if (outcomeEntry.getOutcomeCategory().equals(outcomeCategory) && outcomeEntry.getDate().equals(date)){
                    outcomeEntries.add(outcomeEntry);
                }
            }
        }return  outcomeEntries;
    }

     BigDecimal getBalanceSum (){
        BigDecimal sum = new BigDecimal(0);
        BigDecimal counter = new BigDecimal(0);
        for (Entry entry: entries){
            sum =  counter.add(entry.getSum());
        }
        return   sum;
    }

    void printEntryList (){
        for (Entry entry : entries)
            System.out.println(entry);
    }

    void deleteEntry () {
        System.out.println("Enter id number to delete entry");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        Iterator <Entry> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (Integer.toString(entry.getId()).equals(id)) {
                iterator.remove();
                System.out.println("Entry has been deleted ");
            }  else {
                System.out.println("There is no such entry");
            }
        }
    }

    void modifyEntry (String id, BudgetMain main, Scanner scanner){
        for (Entry entry : entries){
            if (Integer.toString(entry.getId()).equals(id)) {
                if (entry instanceof IncomeEntry)
                    entry.setSum(modifySum(scanner,entry));
                    entry.setPerson(modifyPerson(scanner,entry));
                    System.out.printf("Enter income category" + Arrays.toString(IncomeCategory.values()));
                    String categoryLine = scanner.nextLine();
                    IncomeCategory category = IncomeCategory.valueOf(categoryLine);
                    ((IncomeEntry) entry).setIncomeCategory(category);
                    entry.setComment(modifyComment(scanner, entry));
                    entry.setDate(modifyDate(scanner,entry));
                } else {
                    entry.setSum(modifySum(scanner,entry));
                    entry.setPerson(modifyPerson(scanner,entry));
                    System.out.printf("Enter outcome category" + Arrays.toString(OutcomeCategory.values()));
                    String categoryLine = scanner.nextLine();
                    OutcomeCategory category = OutcomeCategory.valueOf(categoryLine);
                    ((OutcomeEntry) entry).setOutcomeCategory(category);
                    entry.setComment(modifyComment(scanner,entry));
                    entry.setDate(modifyDate(scanner,entry));
                }
            }
        }


    BigDecimal getCorrectNumber (Scanner scanner) {
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

    LocalDate getDate (Scanner scanner){
        System.out.printf("Enter date yyyy.MM.dd : ");
        String date = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        return localDate;
    }

    String getComment (Scanner scanner) {
        System.out.println("Enter comment:");
        String comment = scanner.nextLine();
        return comment;
    }

     Person getPerson (Scanner scanner) {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter surname");
        String surname = scanner.nextLine();

        return new Person(name,surname);
    }

     IncomeType getIncomeType (Scanner scanner){
        IncomeType type;
        while (true) {
            System.out.println("Bank transfer? Y/N");
            String bankTransfer = scanner.nextLine();
            boolean isBankTransfer = "Y".equals(bankTransfer.toUpperCase());
            boolean notBankTransfer = "N".equals(bankTransfer.toUpperCase());
            if (isBankTransfer) {
                type = IncomeType.BANK_TRANSFER;
                break;
            } else if ( notBankTransfer) {
                 type = IncomeType.CASH;
            break;
            } else {
                System.out.println("Wrong entry");
            }
        }
        return type;
    }

    String getIncomeCategory (Scanner scanner){
        String category;
        while (true){
            System.out.println("Enter Income category: Salary ->[1]; Extra money -> [2]");
            String input = scanner.nextLine();
            boolean isSalary = input.equals("1");
            boolean isExtraMoney = input.equals("2");
            if (isSalary){
                category = IncomeCategory.SALARY.toString();
                break;
            } else if (isExtraMoney){
                category = IncomeCategory.EXTRA_MONEY.toString();
                break;
            } else {
                System.out.println("Wrong entry");
            }
        }
        return category;
    }

    OutcomeCategory getOutcomeCategory (Scanner scanner) {
        OutcomeCategory category = null;
        while (true){
            System.out.printf("Enter outcome category: Food -> [1]; Gas -> [2]; Taxes -> [3]; Entertainment -> [4]; Rent -> [5]; Extra -> [6]");
            String input = scanner.nextLine();
             switch (input) {
                 case "1" -> category = OutcomeCategory.FOOD;
                 case "2" -> category = OutcomeCategory.GAS;
                 case "3" -> category = OutcomeCategory.TAXES;
                 case "4" -> category = OutcomeCategory.ENTERTAINMENT;
                 case "5" -> category = OutcomeCategory.RENT;
                 case "6" -> category = OutcomeCategory.EXTRA;
                 default -> System.out.println("Wrong entry");
             }
             return category;
        }
    }

    OutcomeType getOutcomeType (Scanner scanner){
        OutcomeType type = null;
        System.out.println( "Enter outcome type: Cash -> [1]; Card -> [2]; Bank transfer -> [3]");
        String input = scanner.nextLine();
        while (true){
            switch (input){
                case "1" -> type = OutcomeType.CASH;
                case "2" -> type = OutcomeType.CARD;
                case "3" -> type = OutcomeType.BANK_TRANSFER;
                default -> System.out.println("Wrong entry");
            }
            return type;
        }
    }

    BigDecimal modifySum (Scanner scanner,Entry entry) {
        BigDecimal sum = null;
        while (true){
        System.out.println("Do you want to modify sum ?  YES -> [1]; NO -> [2] ");
        String input = scanner.nextLine();
        if (input.equals("1")){
            sum = getCorrectNumber(scanner);
        } else if (input.equals("2")) {
            sum = entry.getSum();
        }else {
            System.out.println("wrong  entry");
        } return sum;
        }
    }

    Person modifyPerson (Scanner scanner, Entry entry) {
        Person person = null;
        while (true){
            System.out.println("Do you want to Person  ?  YES -> [1]; NO -> [2] ");
            String input = scanner.nextLine();
            if (input.equals("1")){
                person = getPerson(scanner);
            } else if (input.equals("2")) {
                person = entry.getPerson();
            }else {
                System.out.println("wrong  entry");
            } return person;
        }
    }

    String modifyComment (Scanner scanner, Entry entry){
        String comment = null;
        while (true){
            System.out.println("Do you want to modify comment?  YES -> [1]; NO -> [2] ");
            String input = scanner.nextLine();
            if (input.equals("1")){
                comment = scanner.nextLine();
            } else if (input.equals("2")) {
                comment = entry.getComment();
            }else {
                System.out.println("wrong  entry");
            } return comment;
        }
    }

    LocalDate modifyDate (Scanner scanner, Entry entry) {
        LocalDate date = null;
        while (true) {
            System.out.println("Do you want to modify comment?  YES -> [1]; NO -> [2] ");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                date = getDate(scanner);
            } else if (input.equals("2")) {
                date = entry.getDate();
            } else {
                System.out.println("wrong  entry");
            }
            return date;
        }
    }



}
