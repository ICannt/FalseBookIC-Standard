package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICRsNorLatch extends BaseIC {

    public ICRsNorLatch() {
        this.ICName = "RS NOR LATCH";
        this.ICNumber = "ic.rsnorlatch";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, true, false, "Set", "Reset", "");
        this.chipState.setOutputs("Output", "", "");
        this.ICDescription = "The ic.rsnorlatch implements a RS NOR latch. Only the Q output is available. When the S (\"set\") input exclusively goes high, the output (Q) goes high and stays high even if S goes low again. When the R (\"reset\") input exclusively goes high, the output (Q) goes low and stays low even if R goes low again. If both go high at the same time, the output goes low. If both go low at the same time, the output does not change.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        if ((currentInputs.isInputOneHigh()) && (previousInputs.isInputOneLow()) && (currentInputs.isInputTwoHigh()) && (previousInputs.isInputTwoLow())) {
            switchLever(Lever.BACK, signBlock, false);
        } else if ((currentInputs.isInputOneHigh()) && (previousInputs.isInputOneLow()) && (currentInputs.isInputTwoLow())) {
            switchLever(Lever.BACK, signBlock, true);
        } else if ((currentInputs.isInputTwoHigh()) && (previousInputs.isInputTwoLow()) && (currentInputs.isInputOneLow())) {
            switchLever(Lever.BACK, signBlock, false);
        }
    }
}