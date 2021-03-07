package team.service;

import team.domain.Architect;
import team.domain.Designer;
import team.domain.Employee;
import team.domain.Programmer;

/**
 * @Description 关于开发团队成员的管理，添加，删除等 Manages(add, delete..) members
 */
public class TeamService {
    private static int counter = 1; //给memberId赋值
    private final int MAX_NUMBER = 5; //限制开发团队的人数
    private Programmer[] team = new Programmer[MAX_NUMBER]; //保存开发团队成员
    private int total = 0; //记录开发团队中实际的人数

    public TeamService() {
        super();
    }

    /**
     *
     * @Description 获取开发团队中的所有成员 get members
     */
    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     *
     * @Description 将指定的员工添加到开发团队中 add member to the team
     * @param e
     * @throws TeamException
     */
    public void addMember(Employee e) throws TeamException {
        //		成员已满，无法添加 full
        if(total >= MAX_NUMBER)
            throw new TeamException("The team is full, cannot add");
        //		该成员不是开发人员，无法添加
        if(!(e instanceof Programmer))
            throw new TeamException("Cannot add non-developer members");
        //		该员工已在本开发团队中
        if(isExist(e))
            throw new TeamException("This member is already in the developer team");
        //		该员工已是某团队成员
        //		该员正在休假，无法添加
        Programmer p = (Programmer)e; //Employee类没有status，强转为Programmer类
        if("BUSY".equalsIgnoreCase(p.getStatus().getNAME()))//if(p.getStatus().getNAME().equals("BUSY"))避免空指针异常 反过来写
            throw new TeamException("This member is assigned to another team");
        if("VACATION".equalsIgnoreCase(p.getStatus().getNAME()))
            throw new TeamException("This member is on vacation");
        //		团队中至多只能有一名架构师 The team can only have 1 Architect
        //		团队中至多只能有两名设计师 The team can only have 2 Designers
        //		团队中至多只能有三名程序员 The team can only have 1 Programmers

        //获取team已有成员中架构师，设计师，程序员的人数 get existing number of the roles
        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect) numOfArch++;
            else if(team[i] instanceof Designer) numOfDes++;
            else if(team[i] instanceof Programmer) numOfPro++;
        }

        if(p instanceof Architect) {
            if (numOfArch >= 1)
                throw new TeamException("The team can only have 1 Architect");
        }
        if(p instanceof Designer){
            if(numOfDes >=2)
                throw new TeamException("The team can only have 2 Designers");
        }
        if(p instanceof Programmer){
            if(numOfPro>=3)
                throw new TeamException("The team can only have 1 Programmers");
        }

        //将p（或e）添加到现有的team中 add p, or e to the team
        team[total++] = p;
        //p的属性赋值
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
    }

    /**
     *
     * @Description 判断指定的员工是否已经存在于现有的开发团队中
     * @param e
     * @return
     */
    private boolean isExist(Employee e) {
        for (int i = 0; i < total; i++) {
            return team[i].getId() == e.getId();
        }
        return false;
    }

    /**
     *
     * @Description 从团队中删除成员
     * @param memberId
     * @throws TeamException
     */
    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        //找到找到指定memberId元素，改status
        for (; i < total; i++) {
            if(team[i].getMemberId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        //未找到指定memberId的情况
        if(i == total)
            throw new TeamException("Cannot find memberId, failed");

        //实现删除操作：覆盖之前的元素，置空最后一个
        //delete
        for (int j = i+1; j < total; j++) {
            team[j-1] = team[j];
        }
        team[total-1] = null;
        total--;  //team[--total] = null;


    }
}
