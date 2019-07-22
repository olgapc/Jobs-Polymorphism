package com.itacademy.itproject.DAO;

import java.util.ArrayList;
import java.util.List;

public class GSheetDAO
{
    private List<String> header = new ArrayList<>(); // header names and order
    private List<List<String>> listRows = new ArrayList<>(); // list of a list of lines content all the rows   

    public GSheetDAO()
    {
        this.header = header;
        this.listRows = listRows;
    }

    public List<String> getHeader()
    {
        return header;
    }

    public void setHeader(List<String> header)
    {
        this.header = header;
    }

    public void addToHeader(String header)
    {
        this.header.add(header);
    }

    public List<List<String>> getListRows()
    {
        return listRows;
    }

    public void setListRows(List<List<String>> listRows)
    {
        this.listRows = listRows;
    }

    @Override
    public String toString()
    {
        return "ObjectRow{" + "header=" + header + ", listRows=" + listRows + '}';
    }
}
