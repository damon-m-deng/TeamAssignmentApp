package team.service;


public class Data {
    public static final int EMPLOYEE = 10;
    public static final int PROGRAMMER = 11;
    public static final int DESIGNER = 12;
    public static final int ARCHITECT = 13;

    public static final int PC = 21;
    public static final int NOTEBOOK = 22;
    public static final int PRINTER = 23;

    //Employee  :  10, id, name, age, salary
    //Programmer:  11, id, name, age, salary
    //Designer  :  12, id, name, age, salary, bonus
    //Architect :  13, id, name, age, salary, bonus, stock
    public static final String[][] EMPLOYEES = {
        {"10", "1", "Alice", "22", "3000"},
        {"13", "2", "Bob", "32", "18000", "15000", "2000"},
        {"11", "3", "Bryan", "23", "7000"},
        {"11", "4", "Charlie", "24", "7300"},
        {"12", "5", "Doug", "28", "10000", "5000"},
        {"11", "6", "David", "22", "6800"},
        {"12", "7", "Evan", "29", "10800","5200"},
        {"13", "8", "Eddie", "30", "19800", "15000", "2500"},
        {"12", "9", "Fred", "26", "9800", "5500"},
        {"11", "10", "Gayle", "21", "6600"},
        {"11", "11", "Harley", "25", "7100"},
        {"12", "12", "Ian", "27", "9600", "4800"}
    };

    public static final String[][] EQUIPMENTS = {
        {},
        {"22", "MSI", "6000"},
        {"21", "Dell", "SAMSUNG17"},
        {"21", "Dell", "hp 17"},
        {"23", "Dell", "hp 2900"},
        {"21", "MSI", "SAMSUNG17 17"},
        {"21", "Dell", "SAMSUNG17 17"},
        {"23", "MSI", "hp20K"},
        {"22", "HP", "5800"},
        {"21", "HP", "NEC 17"},
        {"21", "MSI","SAMSUNG17 17"},
        {"22", "HP", "5800"}
    };
}
