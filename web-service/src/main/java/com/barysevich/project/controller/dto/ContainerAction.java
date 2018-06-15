package com.barysevich.project.controller.dto;


/**
 * Created by BarysevichD on 2017-06-09.
 */
public enum ContainerAction
{

    INSERT(1),
    UPDATE(2),
    DELETE(3);

    private int value;


    private ContainerAction(int value)
    {
        this.value = value;
    }
}
