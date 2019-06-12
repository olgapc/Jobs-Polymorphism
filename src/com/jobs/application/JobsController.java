package com.jobs.application;

import com.jobs.domain.AbsStaffMember;
import com.jobs.domain.Employee;
import com.jobs.domain.Volunteer;
import com.jobs.persistence.EmployeeRepository;
import java.util.List;

public class JobsController
{

    private EmployeeRepository repository = new EmployeeRepository();

    public JobsController()
    {

    }

    public void createBossEmployee(String name, String address, String phone, double salaryPerMonth) throws Exception
    {
        Employee boss = new Employee(name, address, phone, salaryPerMonth, PaymentFactory.createPaymentRateBoss());

        repository.addMember(boss);

    }

    public void createEmployee(String name, String address, String phone, double salaryPerMonth) throws Exception
    {
        Employee employee = new Employee(name, address, phone, salaryPerMonth, PaymentFactory.createPaymentRateEmployee());
        repository.addMember(employee);
    }

    public void createManagerEmployee(String name, String address, String phone, double salaryPerMonth) throws Exception
    {
        Employee manager = new Employee(name, address, phone, salaryPerMonth, PaymentFactory.createPaymentRateManager());
        repository.addMember(manager);

    }

    public void payAllEmployeers()
    {
        List<AbsStaffMember> allMembers = repository.getAllMembers();

        for (AbsStaffMember member : allMembers)
        {

            member.pay();
            System.out.println("PAGADO: " + member.getName() + " IMPORTE: " + member.getTotalPaid());
//            if (member instanceof Employee)
//            {
//                Employee paidEmployee = (Employee) member;
//                paidEmployee.pay();
//                System.out.println("PAGADO: " + paidEmployee.getId() + " " + paidEmployee.getName() + " SUELDO MENSUAL: " + paidEmployee.getSalaryPerMonth() + " SUELDO PAGADO: " + paidEmployee.getTotalPaid());
//            }
        }

    }

    public String getAllEmployees()
    {

        StringBuilder allEmployees = new StringBuilder();

        List<AbsStaffMember> allMembers1 = repository.getAllMembers();
        for (AbsStaffMember member : allMembers1)
        {
            allEmployees.append("\nID: ").append(member.getId()).append(" NOMBRE: ").append(member.getName());

        }
        //System.out.println(allEmployees);

        return allEmployees.toString();

    }

    public void createVolunteer(String name, String address, String phone) throws Exception
    {
        Volunteer volunteer = new Volunteer(name, address, phone, null);
        repository.addMember(volunteer);

    }

}
