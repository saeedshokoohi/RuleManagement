package domain.Test;

import javax.inject.Named;
import java.util.UUID;

/**
 * Created by saeed on 21/01/2016.
 */
@Named
public class PersonnelOverTime {
    private UUID personnelRef;
    private int currentMonth;
    private int currentYear;

    private int realOverTimeAmount;
    private int computedOverTimeAmount;

    public UUID getPersonnelRef() {
        return personnelRef;
    }

    public void setPersonnelRef(UUID personnelRef) {
        this.personnelRef = personnelRef;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getRealOverTimeAmount() {
        return realOverTimeAmount;
    }

    public void setRealOverTimeAmount(int realOverTimeAmount) {
        this.realOverTimeAmount = realOverTimeAmount;
    }

    public int getComputedOverTimeAmount() {
        return computedOverTimeAmount;
    }

    public void setComputedOverTimeAmount(int computedOverTimeAmount) {
        this.computedOverTimeAmount = computedOverTimeAmount;
    }
}
