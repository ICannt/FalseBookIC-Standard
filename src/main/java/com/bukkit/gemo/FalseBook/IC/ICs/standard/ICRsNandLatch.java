package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICRsNandLatch extends BaseIC {

    public ICRsNandLatch() {
        this.ICName = "RS NAND LATCH";
        this.ICNumber = "ic.rsnandlatch";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, true, false, "Set", "Reset", "");
        this.chipState.setOutputs("Output", "", "");
        this.ICDescription = "The ic.rsnandlatch implements a RS NAND latch. Only the Q output is available. When the S (inverse \"set\") input exclusively goes high, the output (Q) goes high and stays high even if S goes low again. When the R (inverse \"reset\") input exclusively goes high, the output (Q) goes low and stays low even if R goes high again. If both go low at the same time, the output goes high. If both go high at the same time, the output does not change.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        if ((currentInputs.isInputOneHigh()) && (previousInputs.isInputOneLow()) && (currentInputs.isInputTwoHigh()) && (previousInputs.isInputTwoLow())) {
            switchLever(Lever.BACK, signBlock, true);
        } else if ((currentInputs.isInputOneLow()) && (previousInputs.isInputOneHigh()) && (currentInputs.isInputTwoHigh())) {
            switchLever(Lever.BACK, signBlock, true);
        } else if ((currentInputs.isInputTwoLow()) && (previousInputs.isInputTwoHigh()) && (currentInputs.isInputOneHigh())) {
            switchLever(Lever.BACK, signBlock, false);
        }
    }
}