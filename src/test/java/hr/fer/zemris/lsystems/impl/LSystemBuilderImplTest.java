package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LSystemBuilderImplTest {

    @Test
    public void testLSystemBuilderImpl() {
        LSystemViewer.showLSystem(createKochCurve(LSystemBuilderImpl::new));
    }

    private static LSystem createKochCurve(LSystemBuilderProvider provider) {
        return provider.createLSystemBuilder()
                .registerCommand('F', "draw 1")
                .registerCommand('+', "rotate 60")
                .registerCommand('-', "rotate -60")
                .setOrigin(0.05, 0.4)
                .setAngle(0)
                .setUnitLength(0.9)
                .setUnitLengthDegreeScaler(1.0/3.0)
                .registerProduction('F', "F+F--F+F")
                .setAxiom("F")
                .build();
    }

    @Test
    public void testGenerate() {
        LSystemBuilderImpl a = new LSystemBuilderImpl();
        a.registerCommand('F', "draw 1")
                .registerCommand('+', "rotate 60")
                .registerCommand('-', "rotate -60")
                .setOrigin(0.05, 0.4)
                .setAngle(0)
                .setUnitLength(0.9)
                .setUnitLengthDegreeScaler(1.0/3.0)
                .registerProduction('F', "F+F--F+F")
                .setAxiom("F")
                .build();

    }

    @Test
    public void testSetFunctions() {
        LSystemBuilderImpl lsb = new LSystemBuilderImpl();
        lsb.setUnitLength(33)
                .setOrigin(5, 5)
                .setAngle(30)
                .setAxiom("F+F--F+F")
                .setUnitLengthDegreeScaler(2.0/3.5)
                .registerProduction('F', "F+F--F+F")
                .registerCommand('F', "draw 1")
                .registerCommand('+', "rotate 60")
                .registerCommand('-', "rotate -60");
        //System.out.println(lsb);
        LSystemBuilderImpl l = new LSystemBuilderImpl();
        l.configureFromText(new String[] {
                "origin 0.05 0.4",
                "angle 0",
                "unitLength 0.9",
                "unitLengthDegreeScaler 1.0 / 3.0",
                "",
                "command F draw 1",
                "command + rotate 60",
                "command - rotate -60",
                "",
                "axiom F",
                "",
                "production F F+F--F+F"
        });
        System.out.println(l);
    }

}