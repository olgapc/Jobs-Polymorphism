package com.jobs.domain;

public class Volunteer extends AbsStaffMember
{

    protected String description;

    public Volunteer(String name, String address, String phone, String description) throws Exception
    {
        super(name, address, phone);
        this.description = description;

    }

    @Override
    public void pay()
    {
        totalPaid = 0;

    }

}
