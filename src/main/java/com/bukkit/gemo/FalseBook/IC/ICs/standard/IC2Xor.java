package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class IC2Xor extends BaseIC {

    public IC2Xor() {
        this.ICName = "2-INPUT XOR";
        this.ICNumber = "ic.2xor";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, true, false, "Input A", "Input B", "");
        this.chipState.setOutputs("Output", "", "");
        this.ICDescription = "The ic.2xor outputs a high if and only if the first two inputs are not the same.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        if (currentInputs.isInputOneHigh() != currentInputs.isInputTwoHigh()) {
            switchLever(Lever.BACK, signBlock, true);
        } else {
            switchLever(Lever.BACK, signBlock, false);
        }
    }
}