package com.barysevich.project.model.result;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public class PagedResult
{
    @NotNull
    private final List<PersonIdBySkillsSearchResult> pagedResult = new ArrayList<>();

    @NotNull
    private List<Total> total = ImmutableList.of(new Total(0));


    @SuppressWarnings("unused")
    PagedResult() {}


    @JsonCreator
    private PagedResult(@JsonProperty(value = "pagedResult") final List<PersonIdBySkillsSearchResult> pagedResult,
                        @JsonProperty(value = "total") final List<Total> total)
    {
        this.pagedResult.addAll(pagedResult);
        this.total = total;
    }


    @JsonProperty(value = "pagedResult")
    public List<PersonIdBySkillsSearchResult> getPagedResult()
    {
        return pagedResult;
    }


    @JsonProperty(value = "total")
    public List<Total> getTotal()
    {
        return total;
    }


    public boolean isEmpty()
    {
        return this.total.isEmpty() || this.total.get(0).getTotal() == 0;
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
