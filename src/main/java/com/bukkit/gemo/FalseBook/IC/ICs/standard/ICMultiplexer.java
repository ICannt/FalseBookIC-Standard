package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICMultiplexer extends BaseIC {

    public ICMultiplexer() {
        this.ICName = "MULTIPLEXER";
        this.ICNumber = "ic.multiplexer";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, true, true, "Input A", "Input B", "Select");
        this.chipState.setOutputs("Output = A if C is high, B otherwise", "", "");
        this.ICDescription = "The ic.multiplexer implements a multiplexer that sets its output to the state of either input A or B depending on the state of input C.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        boolean inputA = currentInputs.isInputOneHigh();
        boolean inputB = currentInputs.isInputTwoHigh();
        boolean inputS = currentInputs.isInputThreeHigh();
        if (inputS) {
            switchLever(Lever.BACK, signBlock, inputA);
        } else {
            switchLever(Lever.BACK, signBlock, inputB);
        }
    }
}