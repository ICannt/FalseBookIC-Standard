package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICNot extends BaseIC {

    public ICNot() {
        this.ICName = "NOT";
        this.ICNumber = "ic.not";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, false, false, "State", "", "");
        this.chipState.setOutputs("inverted State", "", "");
        this.ICDescription = "The ic.not inverts the inputstate.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        switchLever(Lever.BACK, signBlock, !currentInputs.isInputOneHigh());
    }
}