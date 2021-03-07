package team.view;

import team.domain.Employee;
import team.domain.Programmer;
import team.service.NameListService;
import team.service.TeamException;
import team.service.TeamService;

public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu(){
        boolean loopFlag = true;
        char menu = 0;
        while(loopFlag){
            if(menu != '1')
                listAllEmployees();
            System.out.print("1-TEAM LIST  2-ADD MEMBER  3-DELETE MEMBER 4-QUIT   SELECT(1-4)：");
            menu = TSUtility.readMenuSelection();
            switch (menu){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.print("Are you sure you want to quit? (Y/N)：");
                    char isExit = TSUtility.readConfirmSelection();
                    if(isExit == 'Y')
                        loopFlag = false;
                    break;
            }
        }
    }

    //show all employee information
    private void listAllEmployees(){
        System.out.println("SHOW ALL MEMBERS");
        System.out.println("-------------------------------TEAM ASSIGNMENT APP--------------------------------\n");

        Employee[] employees = listSvc.getAllEmployees();
        if(employees == null || employees.length == 0)
            System.out.println("NO EMPLOYEE INFORMATION FOUND！");
        else{
            System.out.println("ID\tNAME\tAGE\tSALARY\tROLE\tSTATUS\tBONUS\tSTOCK\tEQUIPMENT");
            for (int i = 0; i < employees.length; i++) {
                System.out.println(employees[i]);
            }
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    private void getTeam(){
        System.out.println("--------------------TEAM LIST---------------------\n");

        Programmer[] team = teamSvc.getTeam();
        if(team == null || team.length == 0)
            System.out.println("No members are assigned to this team yet！");
        else{
            System.out.println("TID/ID\tNAME\tAGE\tSALARY\tROLE\tBONUS\tSTOCK\n");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }

    }

    private void addMember(){
        System.out.println("---------------------ADD MEMBER---------------------");
        System.out.print("Please enter the member ID to add：");
        int id = TSUtility.readInt();

        try {
            Employee emp = listSvc.getEmployee(id);
            teamSvc.addMember(emp);
            System.out.println("ADDED");
        } catch (TeamException e) {
            System.out.println("FAILED, REASON: " + e.getMessage());
        }
        //press enter to continue...
        TSUtility.readReturn();
    }

    private void deleteMember(){
        System.out.println("---------------------DELETE MEMBER---------------------");
        System.out.print("Please enter the member ID to delete：");
        int memberId = TSUtility.readInt();

        System.out.print("ARE YOU SURE(Y/N)：");
        char isDelete = TSUtility.readConfirmSelection();
        if(isDelete == 'N')
            return;
        try {
            teamSvc.removeMember(memberId);
            System.out.println("DELETED");
        } catch (TeamException e) {
            System.out.println("FAILED, REASON: " + e.getMessage());
        }
        //press enter to continue...
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView view = new TeamView();
        view.enterMainMenu();
    }
}
