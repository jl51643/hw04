package hr.fer.zemris.lsystems.impl.demo;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DemoPlant1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("../hw04-0036516431/src/main/resources/examples/plant1.txt"),
                StandardCharsets.UTF_8
        );
        LSystemViewer.showLSystem(createKochCurve2(LSystemBuilderImpl::new, lines));
    }

    private static LSystem createKochCurve2(LSystemBuilderProvider provider, List<String> lines) {
        String[] data = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            data[i] = lines.get(i);
        }

        return provider.createLSystemBuilder().configureFromText(data).build();
    }

}
