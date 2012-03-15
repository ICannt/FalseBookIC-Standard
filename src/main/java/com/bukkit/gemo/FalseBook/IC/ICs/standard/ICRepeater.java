package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICRepeater extends BaseIC {

    public ICRepeater() {
        this.ICName = "REPEATER";
        this.ICNumber = "ic.repeater";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, false, false, "State", "", "");
        this.chipState.setOutputs("Output = Input", "", "");
        this.ICDescription = "The ic.repeater repeats a state from input to output.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        switchLever(Lever.BACK, signBlock, currentInputs.isInputOneHigh());
    }
}