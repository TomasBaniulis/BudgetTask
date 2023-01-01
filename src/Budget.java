import Data.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Budget {

    private List <Entry> entries = new ArrayList<>();
    private List <IncomeEntry> incomeEntries = new ArrayList<>();
    private List <OutcomeEntry> outcomeEntries = new ArrayList<>();


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




}
