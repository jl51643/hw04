package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.*;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Implementation of <code>LSystemBuilder</code>
 */
public class LSystemBuilderImpl implements LSystemBuilder {

    /**
     * length of turtle's step
     */
    private double unitLength = 0.1;

    /**
     * Degree scaler of turtle's step
     */
    private double unitLengthDegreeScaler = 1;

    /**
     * Start point of drawing
     */
    private Vector2D origin = new Vector2D(0, 0);

    /**
     * Angle between positive x-axis and turtle's direction
     */
    private double angle = 0;

    /**
     * Starting axiom
     */
    private String axiom = "";

    /**
     * Dictionary of commands
     */
    private Dictionary<Character, ? super Command> commands = new Dictionary<>();

    /**
     * Dictionary of productions
     */
    private Dictionary<Character, String> productions = new Dictionary<>();

    /**
     * Sets unit length to given value
     *
     * @param v new value of unit length
     * @return returns <code>LSystemBuilder</code> with changed <code>unitLength</code>
     */
    @Override
    public LSystemBuilder setUnitLength(double v) {
        this.unitLength = v;
        return this;
    }

    /**
     * Sets new origin
     *
     * @param v x-coordinate of new origin
     * @param v1 y-coordinate of new origin
     * @return returns <code>LSystemBuilder</code> with changed <code>origin</code>
     */
    @Override
    public LSystemBuilder setOrigin(double v, double v1) {
        this.origin = new Vector2D(v, v1);
        return this;
    }

    /**
     * Sets new value of angle of turtle's direction
     *
     * @param v new value of angle of turtle's direction
     * @return returns <code>LSystemBuilder</code> with changed <code>angle</code>
     */
    @Override
    public LSystemBuilder setAngle(double v) {
        this.angle = v * (Math.PI / 180);
        return this;
    }

    /**
     * Sets new axiom
     *
     * @param s new axiom
     * @return returns <code>LSystemBuilder</code> with changed <code>axiom</code>
     */
    @Override
    public LSystemBuilder setAxiom(String s) {
        this.axiom = s;
        return this;
    }

    /**
     * Sets new value of degree scale
     *
     * @param v new value of degree scale
     * @return returns <code>LSystemBuilder</code> with changed <code>unitLengthDegreeScaler</code>
     */
    @Override
    public LSystemBuilder setUnitLengthDegreeScaler(double v) {
        this.unitLengthDegreeScaler = v;
        return this;
    }

    /**
     * Registers new production into dictionary of productions
     *
     * @param c key of production
     * @param s production for given character
     * @return returns <code>LSystemBuilder</code> with new production in dictionary of productions
     */
    @Override
    public LSystemBuilder registerProduction(char c, String s) {
        this.productions.put(c, s);
        return this;
    }

    /**
     * Registers new command into dictionary of productions
     *
     * @param c key of command
     * @param s command for given key
     * @return returns <code>LSystemBuilder</code> with added new command
     * @throws IllegalArgumentException if given command is not supported
     */
    @Override
    public LSystemBuilder registerCommand(char c, String s) {
        String[] command = s.split("\\s");
        switch (command[0]) {
            case "color" -> this.commands.put(c, new ColorCommand(new Color(Integer.parseInt(command[1], 16))));
            case "draw" -> this.commands.put(c, new DrawCommand(Double.parseDouble(command[1])));
            case "pop" -> this.commands.put(c, new PopCommand());
            case "push" -> this.commands.put(c, new PushCommand());
            case "rotate"-> this.commands.put(c, new RotateCommand(Double.parseDouble(command[1])));
            case "scale" -> this.commands.put(c, new ScaleCommand(Double.parseDouble(command[1])));
            case "skip" -> this.commands.put(c, new SkipCommand(Double.parseDouble(command[1])));
            default -> throw new IllegalArgumentException("Command " + command + " is not supported.");
        }

        return this;
    }

    /**
     * Configures <code>LSystemBuilder</code> from given array of strings
     *
     * @param strings configuration of <code>LSystemBuilder</code>
     * @return returns configured <code>LSystemBuilder</code>
     * @throws IllegalArgumentException if configuration to configure <code>LSystemBuilder</code> is invalid
     */
    @Override
    public LSystemBuilder configureFromText(String[] strings) {
        for (String s : strings) {
            String[] command = s.split("\\s+");
            switch (command[0]) {
                case "origin" -> this.setOrigin(Double.parseDouble(command[1]), Double.parseDouble(command[2]));
                case "angle" -> this.setAngle(Double.parseDouble(command[1]));
                case "unitLength" -> this.setUnitLength(Double.parseDouble(command[1]));
                case "unitLengthDegreeScaler" -> setUnitLengthDegreeScalerFromText(command);
                case "command" -> this.registerCommand(command[1].charAt(0), Arrays.stream(Arrays.copyOfRange(command, 2, command.length)).collect(Collectors.joining(" ")));
                case "axiom" -> this.setAxiom(command[1]);
                case "production" -> this.registerProduction(command[1].charAt(0), Arrays.stream(Arrays.copyOfRange(command, 2, command.length)).collect(Collectors.joining()));
                case "" -> {continue;}
                default -> throw new IllegalArgumentException("Symbol " + command[0] + " is not supported");
            }
        }
        return this;
    }

    /**
     * Parses argument given with <code>unitLengthDegreeScaler</code>
     * and determines it's value
     *
     * @param command array of arguments to set value of <code>unitLengthDegreeScaler</code>
     * @throws IllegalArgumentException if given configuration for <code>unitLengthDegreeScaler</code> is invalid
     */
    private void setUnitLengthDegreeScalerFromText(String[] command) {
        String commandArguments = Arrays.stream(Arrays.copyOfRange(command, 1, command.length)).collect(Collectors.joining());

        String v1 = "", v2 = "";
        int i = 0;
        v1 = getStringNumber(commandArguments, i);
        i += v1.length();

        i = skipWhiteSpaces(commandArguments, i);

        if (i < commandArguments.length() && commandArguments.charAt(i) == '/')
            i++;
        else
            throw new IllegalArgumentException("Expected \"/\" but got" + commandArguments.charAt(i));

        i = skipWhiteSpaces(commandArguments, i);
        v2 = getStringNumber(commandArguments, i);

        if (v1.equals(""))
            throw new IllegalArgumentException("There is no number argument");
        if (v2.equals("")) {
            this.setUnitLengthDegreeScaler(Double.parseDouble(v1));
        } else {
            double v2Number = Double.parseDouble(v2);
            if (Math.abs(v2Number - 0) < 1E-10)
                throw new IllegalArgumentException("Can not divide with 0");
            this.setUnitLengthDegreeScaler(Double.parseDouble(v1) / Double.parseDouble(v2));
        }
    }

    /**
     * Parses given string and finds number in it
     *
     * @param s String to parse
     * @param index start index in given string to search number
     * @return returns string which contains number
     */
    private String getStringNumber(String s, int index) {
        String v1 = "";
        while (index < s.length() && Character.isDigit(s.charAt(index)))
            v1 += s.charAt(index++);
        if (s.charAt(index) == '.' && Character.isDigit(s.charAt(index+1)))
            v1 += s.charAt(index++);
        while (index < s.length() && Character.isDigit(s.charAt(index)))
            v1 += s.charAt(index++);
        return v1;
    }

    /**
     * Skips whitespaces in given string and increments given index
     * for every skipped whitespace
     *
     * @param s string in which whitespaces are skipped
     * @param index start index in string to skip whitespaces
     * @return returns index incremented for every skipped whitespace
     */
    private int skipWhiteSpaces(String s, int index) {
        while (index < s.length() && Character.isWhitespace(s.charAt(index)))
            index++;
        return index;
    }

    /**
     * Returns configured <code>LSystem</code>
     *
     * @return returns configured <code>LSystem</code>
     */
    @Override
    public LSystem build() {

        class LSystemImpl implements LSystem {

            @Override
            public String generate(int i) {

                String axiom = LSystemBuilderImpl.this.axiom;

                if (i == 0)
                    return axiom;

                String nextAxiom = axiom;
                String degreeProduction = "";
                for (int degree = 0; degree < i; degree++) {
                    degreeProduction = "";
                    for (char c : nextAxiom.toCharArray()) {
                        if (LSystemBuilderImpl.this.productions.get(c) == null) {
                            degreeProduction += c;
                            continue;
                        }
                        degreeProduction += LSystemBuilderImpl.this.productions.get(c);
                    }
                    nextAxiom = degreeProduction;
                }
                return nextAxiom;
            }

            @Override
            public void draw(int i, Painter painter) {

                Context context = new Context();
                Vector2D directionVector = createDirectionVectorFromAngle();
                TurtleState state = new TurtleState(LSystemBuilderImpl.this.origin,
                                        directionVector,
                                        Color.BLACK,
                                        LSystemBuilderImpl.this.unitLength * Math.pow(LSystemBuilderImpl.this.unitLengthDegreeScaler, i));
                context.pushState(state);

                String generatedString = generate(i);

                for (int index = 0; index < generatedString.length(); index++){

                    char c = generatedString.charAt(index);
                    Command command = (Command) LSystemBuilderImpl.this.commands.get(c);
                    if (command == null)
                        continue;
                    command.execute(context, painter);
                }
            }

            /**
             * Creates new unit vector in direction of turtle's point of view
             *
             * @return returns unit vector in direction of turtle's point of view
             */
            private Vector2D createDirectionVectorFromAngle() {
                double directionVectorAngele = LSystemBuilderImpl.this.angle;
                double directionVectorX = Math.cos(directionVectorAngele);
                double directionVectorY = Math.sin(directionVectorAngele);
                return new Vector2D(directionVectorX, directionVectorY);
            }
        }
        return new LSystemImpl();
    }
}
