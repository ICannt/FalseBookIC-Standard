package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.ExternalICPackage;
import com.bukkit.gemo.FalseBook.IC.ICs.ICUpgrade;
import com.bukkit.gemo.FalseBook.IC.ICs.ICUpgraderMC;

public class StandardPackage extends ExternalICPackage {

    public StandardPackage() {
        setAPI_VERSION("1.1");

        setShowImportMessages(false);
        addIC(ICRepeater.class);
        addIC(ICNot.class);
        addIC(ICRising.class);
        addIC(ICFalling.class);
        addIC(ICRandom.class);
        addIC(ICRelTimeMod2.class);
        addIC(ICEquals.class);
        addIC(ICSelector.class);
        addIC(IC3BitRandom.class);
        addIC(ICDelayer.class);
        addIC(ICNotDelayer.class);
        addIC(ICLowDelayer.class);
        addIC(ICLowNotDelay.class);
        addIC(ICPulser.class);
        addIC(ICNotPulser.class);
        addIC(ICLowPulser.class);
        addIC(ICLowNotPulser.class);
        addIC(IC2And.class);
        addIC(IC2Nand.class);
        addIC(IC3And.class);
        addIC(IC3Nand.class);
        addIC(IC2Xor.class);
        addIC(IC2Xnor.class);
        addIC(ICRsNorLatch.class);
        addIC(ICInvRsNandLat.class);
        addIC(ICNegJKFF.class);
        addIC(ICRsNandLatch.class);
        addIC(ICDEdgeflipFlop.class);
        addIC(ICDLevlFlipFlop.class);
        addIC(ICMultiplexer.class);
        addIC(ICCounter.class);
        addIC(ICFullAdder.class);
        addIC(ICHalfAdder.class);
        addIC(ICFullSubtractor.class);
        addIC(ICHalfSubtractor.class);
        addIC(ICDispatcher.class);
        

		addUpgrader("[MC1000]", new ICUpgraderMC("ic.repeater"));
		addUpgrader("[MC1001]", new ICUpgraderMC("ic.not"));
		addUpgrader("[MC1017]", new ICUpgraderMC("ic.rising"));
		addUpgrader("[MC1018]", new ICUpgraderMC("ic.falling"));
		addUpgrader("[MC1020]", new ICUpgraderMC("ic.random"));
		addUpgrader("[MC1025]", new ICUpgraderMC("ic.reltime"));
		addUpgrader("[MC1030]", new ICUpgraderMC("ic.equals"));
		addUpgrader("[MC1050]", new ICUpgraderMC("ic.selector"));
		addUpgrader("[MC2020]", new ICUpgraderMC("ic.random3"));
		addUpgrader("[MC2100]", new ICUpgraderMC("ic.delayer"));
		addUpgrader("[MC2101]", new ICUpgraderMC("ic.notdelayer"));
		addUpgrader("[MC2110]", new ICUpgraderMC("ic.lowdelayer"));
		addUpgrader("[MC2111]", new ICUpgraderMC("ic.lownotdelay"));
		addUpgrader("[MC2500]", new ICUpgraderMC("ic.pulser"));
		addUpgrader("[MC2501]", new ICUpgraderMC("ic.notpulser"));
		addUpgrader("[MC2510]", new ICUpgraderMC("ic.lowpulser"));
		addUpgrader("[MC2511]", new ICUpgraderMC("ic.lownotpulser"));
		addUpgrader("[MC3000]", new ICUpgraderMC("ic.2and"));
		addUpgrader("[MC3001]", new ICUpgraderMC("ic.2nand"));
		addUpgrader("[MC3002]", new ICUpgraderMC("ic.3and"));
		addUpgrader("[MC3003]", new ICUpgraderMC("ic.3nand"));
		addUpgrader("[MC3020]", new ICUpgraderMC("ic.2xor"));
		addUpgrader("[MC3021]", new ICUpgraderMC("ic.2xnor"));
		addUpgrader("[MC3030]", new ICUpgraderMC("ic.rsnorlatch"));
		addUpgrader("[MC3031]", new ICUpgraderMC("ic.invrsnandlat"));
		addUpgrader("[MC3032]", new ICUpgraderMC("ic.negjk-ff"));
		addUpgrader("[MC3033]", new ICUpgraderMC("ic.rsnandlatch"));
		addUpgrader("[MC3034]", new ICUpgraderMC("ic.deflipflop"));
		addUpgrader("[MC3036]", new ICUpgraderMC("ic.dlflipflop"));
		addUpgrader("[MC3040]", new ICUpgraderMC("ic.multiplexer"));
		addUpgrader("[MC3101]", new ICUpgraderMC("ic.counter"));
		addUpgrader("[MC4000]", new ICUpgraderMC("ic.fulladd"));
		addUpgrader("[MC4010]", new ICUpgraderMC("ic.halfadd"));
		addUpgrader("[MC4100]", new ICUpgraderMC("ic.fullsub"));
		addUpgrader("[MC4110]", new ICUpgraderMC("ic.halfsub"));
		addUpgrader("[MC4200]", new ICUpgraderMC("ic.dispatch"));
    }
}