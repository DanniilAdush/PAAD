package Model;

import java.io.Serializable;
public class Tamagochi  implements Serializable {
    int LivedDay;
    String Date;
    int hunger,thirst,depres;
    String number;
    public static final String DATABASE_NAME = "tamagoshi.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Tamagochi";
    // Название столбцов
    public static final String COLUMN_Name = "Name";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_LivedDay = "LivedDay";
    public static final String COLUMN_ID = "Id";
    public static final int NUM_COLUMN_LivedDay = 1;
    public static final int NUM_COLUMN_DATE = 1;
    public static final int NUM_COLUMN_ID = 1;

    public Tamagochi(int livedDay, int hunger, int thirst) {
        LivedDay = livedDay;
        this.hunger = hunger;
        this.thirst = thirst;
        this.depres = depres;
    }

    public Tamagochi() {
    }

    public Tamagochi(long LivedDay, String Date, String number) {
            this.LivedDay = (int) LivedDay;
            this.Date = Date;
            this.number = number;

        }

    public int getLivedDay() {
        return LivedDay;
    }

    public void setLivedDay(int livedDay) {
        LivedDay = livedDay;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = String.valueOf(number);
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getDepres() {
        return depres;
    }

    public void setDepres(int depres) {
        this.depres = depres;
    }
}

