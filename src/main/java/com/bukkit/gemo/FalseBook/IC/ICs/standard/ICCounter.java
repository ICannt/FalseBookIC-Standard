package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import com.bukkit.gemo.utils.Parser;
import com.bukkit.gemo.utils.SignUtils;
import java.util.HashMap;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICCounter extends BaseIC {

    private HashMap<String, String> ModeList = new HashMap<String, String>();
    private HashMap<String, Integer> CountList = new HashMap<String, Integer>();
    private HashMap<String, Integer> StartCountList = new HashMap<String, Integer>();

    public ICCounter() {
        this.ICName = "COUNTER";
        this.ICNumber = "ic.counter";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, true, false, "Clock", "Reset", "");
        this.chipState.setOutputs("Output", "", "");
        this.chipState.setLines("the counter reset value", "ONCE (player resets the signal via resetinput) or INF (the IC resets itself, when reaching the counter reset value)");
        this.ICDescription = "The ic.counter implements a counter that counts down from a given input. The counter counts down each time clock input toggles from low to high, it starts from a predefined value to 0. Output is high when counter reaches 0. If in 'infinite' mode, it will automatically reset the next time clock is toggled. Otherwise, it only resets when the 'reset' input toggles from low to high.";
    }

    public void checkCreation(SignChangeEvent event) {
        int val = 0;
        String mode = "";
        String locStr = event.getBlock().getLocation().toString();

        if (!Parser.isInteger(event.getLine(1))) {
            SignUtils.cancelSignCreation(event, "Enter the resetvalue in line 3.");
            return;
        }
        event.setLine(1, String.valueOf(Math.abs(Integer.valueOf(event.getLine(1)).intValue())));

        if (event.getLine(2).length() > 0) {
            if ((!event.getLine(2).equalsIgnoreCase("ONCE")) && (!event.getLine(2).equalsIgnoreCase("INF"))) {
                SignUtils.cancelSignCreation(event, "Line 4 must be ONCE or INF. (default: INF)");
                return;
            }
            event.setLine(2, event.getLine(2).toUpperCase());
        } else {
            event.setLine(2, "INF");
        }
        val = Integer.valueOf(event.getLine(1)).intValue();
        mode = event.getLine(2);
        this.ModeList.put(locStr, mode);
        this.CountList.put(locStr, Integer.valueOf(val));
        this.StartCountList.put(locStr, Integer.valueOf(val));
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        if (!Parser.isInteger(signBlock.getLine(1))) {
            return;
        }
        int line2 = Math.abs(Parser.getInteger(signBlock.getLine(1), 1));

        String locStr = signBlock.getBlock().getLocation().toString();
        if ((!this.ModeList.containsKey(locStr)) || (!this.CountList.containsKey(locStr)) || (!this.StartCountList.containsKey(locStr))) {
            this.ModeList.put(locStr, signBlock.getLine(2));
            this.CountList.put(locStr, Integer.valueOf(line2));
            this.StartCountList.put(locStr, Integer.valueOf(line2));
        }

        if (this.ModeList.get(locStr).equalsIgnoreCase("INF")) {
            if ((currentInputs.isInputOneHigh()) && (previousInputs.isInputOneLow())) {
                this.CountList.put(locStr, Integer.valueOf(this.CountList.get(locStr).intValue() - 1));
                if (this.CountList.get(locStr).intValue() == 0) {
                    switchLever(Lever.BACK, signBlock, true);
                } else {
                    switchLever(Lever.BACK, signBlock, false);
                    if (this.CountList.get(locStr).intValue() == -1) {
                        this.CountList.put(locStr, this.StartCountList.get(locStr));
                    }
                }
            }
        } else if (this.ModeList.get(locStr).equalsIgnoreCase("ONCE")) {
            if ((currentInputs.isInputTwoHigh()) && (previousInputs.isInputTwoLow())) {
                this.CountList.put(locStr, this.StartCountList.get(locStr));
                switchLever(Lever.BACK, signBlock, false);
            } else if ((currentInputs.isInputOneHigh()) && (previousInputs.isInputOneLow())) {
                this.CountList.put(locStr, Integer.valueOf(this.CountList.get(locStr).intValue() - 1));
                if (this.CountList.get(locStr).intValue() < 1) {
                    switchLever(Lever.BACK, signBlock, true);
                }
            }
        }
    }
}