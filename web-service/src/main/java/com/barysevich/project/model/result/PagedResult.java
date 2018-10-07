package com.barysevich.project.model.result;


import java.util.List;


public class PagedResult
{
    private final List<PersonIdBySkillsSearchResult> pagedResult;

    private final List<Total> total;


    public PagedResult(final List<PersonIdBySkillsSearchResult> pagedResult, final List<Total> total)
    {
        this.pagedResult = pagedResult;
        this.total = total;
    }


    public List<PersonIdBySkillsSearchResult> getPagedResult()
    {
        return pagedResult;
    }


    public List<Total> getTotal()
    {
        return total;
    }


    @Override
    public String toString()
    {
        return "PagedResult{" +
                "pagedResult=" + pagedResult +
                ", total=" + total +
                '}';
    }


    public class Total
    {
        private final int total;


        public Total(final int total)
        {
            this.total = total;
        }


        public int getTotal()
        {
            return total;
        }
    }
}
