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
        

		ICUpgrade.addUpgrader("[MC1000]", new ICUpgraderMC("ic.repeater"));
		ICUpgrade.addUpgrader("[MC1001]", new ICUpgraderMC("ic.not"));
		ICUpgrade.addUpgrader("[MC1017]", new ICUpgraderMC("ic.rising"));
		ICUpgrade.addUpgrader("[MC1018]", new ICUpgraderMC("ic.falling"));
		ICUpgrade.addUpgrader("[MC1020]", new ICUpgraderMC("ic.random"));
		ICUpgrade.addUpgrader("[MC1025]", new ICUpgraderMC("ic.reltime"));
		ICUpgrade.addUpgrader("[MC1030]", new ICUpgraderMC("ic.equals"));
		ICUpgrade.addUpgrader("[MC1050]", new ICUpgraderMC("ic.selector"));
		ICUpgrade.addUpgrader("[MC2020]", new ICUpgraderMC("ic.random3"));
		ICUpgrade.addUpgrader("[MC2100]", new ICUpgraderMC("ic.delayer"));
		ICUpgrade.addUpgrader("[MC2101]", new ICUpgraderMC("ic.notdelayer"));
		ICUpgrade.addUpgrader("[MC2110]", new ICUpgraderMC("ic.lowdelayer"));
		ICUpgrade.addUpgrader("[MC2111]", new ICUpgraderMC("ic.lownotdelay"));
		ICUpgrade.addUpgrader("[MC2500]", new ICUpgraderMC("ic.pulser"));
		ICUpgrade.addUpgrader("[MC2501]", new ICUpgraderMC("ic.notpulser"));
		ICUpgrade.addUpgrader("[MC2510]", new ICUpgraderMC("ic.lowpulser"));
		ICUpgrade.addUpgrader("[MC2511]", new ICUpgraderMC("ic.lownotpulser"));
		ICUpgrade.addUpgrader("[MC3000]", new ICUpgraderMC("ic.2and"));
		ICUpgrade.addUpgrader("[MC3001]", new ICUpgraderMC("ic.2nand"));
		ICUpgrade.addUpgrader("[MC3002]", new ICUpgraderMC("ic.3and"));
		ICUpgrade.addUpgrader("[MC3003]", new ICUpgraderMC("ic.3nand"));
		ICUpgrade.addUpgrader("[MC3020]", new ICUpgraderMC("ic.2xor"));
		ICUpgrade.addUpgrader("[MC3021]", new ICUpgraderMC("ic.2xnor"));
		ICUpgrade.addUpgrader("[MC3030]", new ICUpgraderMC("ic.rsnorlatch"));
		ICUpgrade.addUpgrader("[MC3031]", new ICUpgraderMC("ic.invrsnandlat"));
		ICUpgrade.addUpgrader("[MC3032]", new ICUpgraderMC("ic.negjk-ff"));
		ICUpgrade.addUpgrader("[MC3033]", new ICUpgraderMC("ic.rsnandlatch"));
		ICUpgrade.addUpgrader("[MC3034]", new ICUpgraderMC("ic.deflipflop"));
		ICUpgrade.addUpgrader("[MC3036]", new ICUpgraderMC("ic.dlflipflop"));
		ICUpgrade.addUpgrader("[MC3040]", new ICUpgraderMC("ic.multiplexer"));
		ICUpgrade.addUpgrader("[MC3101]", new ICUpgraderMC("ic.counter"));
		ICUpgrade.addUpgrader("[MC4000]", new ICUpgraderMC("ic.fulladd"));
		ICUpgrade.addUpgrader("[MC4010]", new ICUpgraderMC("ic.halfadd"));
		ICUpgrade.addUpgrader("[MC4100]", new ICUpgraderMC("ic.fullsub"));
		ICUpgrade.addUpgrader("[MC4110]", new ICUpgraderMC("ic.halfsub"));
		ICUpgrade.addUpgrader("[MC4200]", new ICUpgraderMC("ic.dispatch"));
    }
}